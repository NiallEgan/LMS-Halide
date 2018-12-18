package sepia


trait ScheduleCompiler extends CompilerFuncOps {
	def computeLoopBounds(variable: Dim, stage: Func,
												boundsGraph: CallGraph,
												loopsEncountered: Map[(Func, String), Dim]): (Rep[Int], Rep[Int]) = {
		if (stage.inlined) throw new InvalidSchedule(f"Inlined function $stage should have no loops")
		else if (stage.computeRoot) (variable.min, variable.max)
		else {
			val v = stage.computeAt
							.getOrElse(throw new InvalidSchedule(f"Non-inlined function $stage has no computeAt variable"))
			val shouldAdjust = loopsEncountered.keySet contains (v.f, variable.name)

			if (!shouldAdjust) (variable.min, variable.max)
			else {
				val baseVar = loopsEncountered((v.f, variable.name))
				val bound = BoundsAnalysis
						 .boundsForProdInCon(boundsGraph, stage.id, v.f.id, variable.name)
						 .getOrElse(throw new InvalidSchedule(f"No bounds for ${v.name} found"))
				(baseVar.v + bound.lb, baseVar.v + bound.ub + 1)
			}
		}
	}

	def computeStorageBounds(stage: Func,
													 boundsGraph: CallGraph,
												 	 loopsEncountered: Map[(Func, String), Dim]): (Rep[Int], Rep[Int]) = {
		if (stage.inlined) throw new InvalidSchedule(f"Inlined function $stage should have no storage")
		else if (stage.storeRoot) (stage.domWidth, stage.domHeight)
		else {
			val v = stage.storeAt
							.getOrElse(throw new InvalidSchedule(f"Non-inlined function $stage has no storeAt"))

			val shouldAdjustX = loopsEncountered.keySet contains (v.f, "x")
			val xDim: Rep[Int] = if (!shouldAdjustX) stage.domWidth else {
				val bound = BoundsAnalysis
						 .boundsForProdInCon(boundsGraph, stage.id, v.f.id, "x")
						 .getOrElse(throw new InvalidSchedule(f"No bounds for ${v.name} found"))
				bound.width
			}

			val shouldAdjustY = loopsEncountered.keySet contains (v.f, "y")
			val yDim: Rep[Int] = if (!shouldAdjustY) stage.domWidth else {
				val bound = BoundsAnalysis
						 .boundsForProdInCon(boundsGraph, stage.id, v.f.id, "y")
						 .getOrElse(throw new InvalidSchedule(f"No bounds for ${v.name} found"))
				bound.width
			}

			(xDim, yDim)
		}
	}

	def previouslyComputed(stage: Func): Rep[Boolean] = {
		
	}

	def evalSched(node: ScheduleNode[Func, Dim],
								boundsGraph: CallGraph,
								loopsEncountered: Map[(Func, String), Dim]): Rep[Unit] = node match {
    case LoopNode(variable, stage, loopType, children) =>
			val (lb, ub) = computeLoopBounds(variable, stage, boundsGraph, loopsEncountered)
			variable.loopStartOffset_=(lb)
      loopType match {
        case Sequential =>
          for (i <- (lb until ub): Rep[Range]) {
            variable.v_=(i)
            for (child <- children) evalSched(child, boundsGraph, loopsEncountered + ((stage, variable.name) -> variable))
          }
        case Unrolled =>
          for (i <- lb until ub) {
            variable.v_=(i)
            for (child <- children) evalSched(child, boundsGraph, loopsEncountered + ((stage, variable.name) -> variable))
          }
      }

    case ComputeNode(stage, children) => {
      /* At a compute node, we compute f.stage and store it.
        TODO: if we computed f in a previous iteration, we need to skip over
        it */
			if (!previouslyComputed(stage)) {
	      val v: RGBVal = stage.compute()
	      stage.storeInBuffer(v)
			}
      for (child <- children) evalSched(child, boundsGraph, loopsEncountered)
    }

 	  case StorageNode(stage, children) => {
			val dims = computeStorageBounds(stage, boundsGraph, loopsEncountered)
			stage.allocateNewBuffer(dims._1, dims._2)
   	 	for (child <- children) evalSched(child, boundsGraph, loopsEncountered)
 	  }

    case RootNode(children) => {
      for (child <- children) evalSched(child, boundsGraph, loopsEncountered)
    }
  }
}
