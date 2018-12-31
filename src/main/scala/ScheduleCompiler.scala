package sepia


trait ScheduleCompiler extends CompilerFuncOps {
	def computeLoopBounds(variable: Dim, stage: Func,
												boundsGraph: CallGraph,
												enclosingLoops: Map[(Func, String), Dim]): (Rep[Int], Rep[Int]) = {
		if (stage.inlined) throw new InvalidSchedule(f"Inlined function $stage should have no loops")
		else if (stage.computeRoot) (variable.min, variable.max)
		else {
			val v = stage.computeAt
							.getOrElse(throw new InvalidSchedule(f"Non-inlined function $stage has no computeAt variable"))
			val shouldAdjust = enclosingLoops.keySet contains (v.f, variable.name)

			if (!shouldAdjust) (variable.min, variable.max)
			else {
				val baseVar = enclosingLoops((v.f, variable.name))
				val bound = BoundsAnalysis
						 .boundsForProdInCon(boundsGraph, stage.id, v.f.id, variable.name)
						 .getOrElse(throw new InvalidSchedule(f"No bounds for ${v.name} found"))
				(baseVar.v + bound.lb, baseVar.v + bound.ub + 1)
			}
		}
	}

	def computeStorageBounds(stage: Func,
													 boundsGraph: CallGraph,
												 	 enclosingLoops: Map[(Func, String), Dim]): (Rep[Int], Rep[Int]) = {
		if (stage.inlined) throw new InvalidSchedule(f"Inlined function $stage should have no storage")
		else if (stage.storeRoot) (stage.domWidth, stage.domHeight)
		else {
			val v = stage.storeAt
							.getOrElse(throw new InvalidSchedule(f"Non-inlined function $stage has no storeAt"))

			val shouldAdjustX = enclosingLoops.keySet contains (v.f, "x")
			val xDim: Rep[Int] = if (!shouldAdjustX) stage.domWidth else {
				val bound = BoundsAnalysis
						 .boundsForProdInCon(boundsGraph, stage.id, v.f.id, "x")
						 .getOrElse(throw new InvalidSchedule(f"No bounds for ${v.name} found"))
				bound.width
			}

			val shouldAdjustY = enclosingLoops.keySet contains (v.f, "y")
			val yDim: Rep[Int] = if (!shouldAdjustY) stage.domWidth else {
				val bound = BoundsAnalysis
						 .boundsForProdInCon(boundsGraph, stage.id, v.f.id, "y")
						 .getOrElse(throw new InvalidSchedule(f"No bounds for ${v.name} found"))
				bound.width
			}

			(xDim, yDim)
		}
	}

	def red(children: List[ScheduleNode[Func, Dim]],
					f: ScheduleNode[Func, Dim] => Map[(Func, String), Dim]) = {
		children.map(f(_)).foldLeft(Map[(Func, String), Dim]())(_ ++ _)
	}

	def getLoopsAfterSN(stage: Func, completeTree: ScheduleNode[Func, Dim]): Map[(Func, String), Dim] = {
		// Collect all of the loop nodes on the path from the storage node for f to its compute node

		def cnIsChild(node: ScheduleNode[Func, Dim]): Boolean = {
			node.exists((n: ScheduleNode[Func, Dim]) => n match {
				case ComputeNode(_, _) => n.belongsTo(stage)
				case _ => false
			})
		}

		def collectLoops(node: ScheduleNode[Func, Dim]): Map[(Func, String), Dim] = node match {
			case LoopNode(v, stage, _, children) => {
				if (cnIsChild(node)) red(children, collectLoops) + ((stage, v.name) -> v)
				else Map()
			}
			case _ => red(node.getChildren, collectLoops)
		}

		completeTree match {
			case RootNode(children) => red(children, getLoopsAfterSN(stage, _))
			case ComputeNode(_, children) => red(children, getLoopsAfterSN(stage, _))
			case s@StorageNode(otherStage, children) => {
				if (otherStage == stage) {
					val loops = collectLoops(s)
					println(f"loops: $loops")
					loops
				}
				else red(children, getLoopsAfterSN(stage, _))
			}
			case LoopNode(_, _, _, children) => red(children, getLoopsAfterSN(stage, _))

		}
	}
	def notPreviouslyComputed(stage: Func,
												    completeTree: ScheduleNode[Func, Dim],
											 	    boundsGraph: CallGraph): Rep[Boolean] = {
			if (stage.computeRoot) true
			else {
				val computeAtFunc: Func = stage.computeAt.getOrElse(throw new InvalidSchedule("non-inlined function has no compute at")).f
				val relevantEnclosingLoops = getLoopsAfterSN(stage, completeTree).filterKeys(_._1 == computeAtFunc)

				val relevantBounds: Map[String, Bound] =
					for (((f, name), d) <- relevantEnclosingLoops)
							yield name -> {
								BoundsAnalysis.boundsForProdInCon(boundsGraph, stage.id, f.id, name).getOrElse(throw new Exception())
							}

				if (relevantBounds.isEmpty) unit(true)
				else {
					println(f"Bounds: ${relevantBounds.isEmpty}")
					val consumerVariables: Map[String, Dim] = computeAtFunc.vars

					 // Generate list for each variable
					 // v < v.min + overlapSize || v == upperVariable + overlapSize - 1
					 def p(name: String) = {
						 val v = stage.vars(name)
						 val w = relevantBounds(name).width - 1
						 v.v < v.min + w || v.v == consumerVariables(name).v + w - 1
					 }
					 relevantBounds.keys.toList.foldLeft(unit(false))({
						 case (acc, n) => acc || p(n)
					 })
				 }
			 }
	}

	def evalSched(node: ScheduleNode[Func, Dim],
								boundsGraph: CallGraph,
								enclosingLoops: Map[(Func, String), Dim],
							  completeTree: ScheduleNode[Func, Dim]): Rep[Unit] = node match {
    case LoopNode(variable, stage, loopType, children) =>
			val (lb, ub) = computeLoopBounds(variable, stage, boundsGraph, enclosingLoops)
			variable.loopStartOffset_=(lb)
      loopType match {
        case Sequential =>
          for (i <- (lb until ub): Rep[Range]) {
            variable.v_=(i)
						for (child <- children) evalSched(child, boundsGraph,
																							enclosingLoops + ((stage, variable.name) -> variable),
																							completeTree)
          }
        case Unrolled =>
          for (i <- lb until ub) {
            variable.v_=(i)
            for (child <- children) evalSched(child, boundsGraph,
																							enclosingLoops + ((stage, variable.name) -> variable),
																							completeTree)
          }
      }

    case ComputeNode(stage, children) => {
      /* At a compute node, we compute f.stage and store it.
        TODO: if we computed f in a previous iteration, we need to skip over
        it */
			if (notPreviouslyComputed(stage, completeTree, boundsGraph)) {
	      val v: RGBVal = stage.compute()
	      stage.storeInBuffer(v)
			}
      for (child <- children) evalSched(child, boundsGraph, enclosingLoops, completeTree)
    }

 	  case StorageNode(stage, children) => {
			val dims = computeStorageBounds(stage, boundsGraph, enclosingLoops)
			stage.allocateNewBuffer(dims._1, dims._2)
   	 	for (child <- children) evalSched(child, boundsGraph, enclosingLoops, completeTree)
 	  }

    case RootNode(children) => {
      for (child <- children) evalSched(child, boundsGraph, enclosingLoops, completeTree)
    }
  }
}
