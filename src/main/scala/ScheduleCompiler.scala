package sepia


trait ScheduleCompiler extends CompilerFuncOps {
	def computeLoopBounds(variable: Dim, stage: Func,
												boundsGraph: CallGraph,
												enclosingLoops: Map[(Func, String), Dim]): (Rep[Int], Rep[Int]) = {
		if (variable.isInstanceOf[FusedDim]) {
			val fusedVariable = variable.asInstanceOf[FusedDim]
			val innerBounds = computeSimpleLoopBounds(fusedVariable.inner, stage,
																								boundsGraph, enclosingLoops)
			val outerBounds = computeSimpleLoopBounds(fusedVariable.outer, stage,
																								boundsGraph, enclosingLoops)
			val a = outerBounds._1
			val b = outerBounds._2
			val c = innerBounds._1
			val d = innerBounds._2
			val lowerBound = c + (d - c) * a
			val upperBound = d + (b - 1) * (d - c)
			variable.looplb_=(lowerBound)
			variable.loopub_=(upperBound)
			variable.shadowingUb_=(upperBound)
			(lowerBound, upperBound)
		} else computeSimpleLoopBounds(variable, stage, boundsGraph, enclosingLoops)
	}

	def computeSimpleLoopBounds(variable: Dim, stage: Func,
															boundsGraph: CallGraph,
															enclosingLoops: Map[(Func, String), Dim]): (Rep[Int], Rep[Int]) = {
		if (stage.inlined) throw new InvalidSchedule(f"Inlined function $stage should have no loops")
		else if (stage.computeRoot) {
			variable.looplb_=(variable.min)
			variable.loopub_=(variable.max)
			variable.shadowingUb_=(stage.vars(variable.shadowingName).max)
			(variable.min, variable.max)
		} else {
			val v = stage.computeAt
							.getOrElse(throw new InvalidSchedule(f"Non-inlined function $stage has no computeAt variable"))
			val shouldAdjust = enclosingLoops.keySet contains (v.f, variable.shadowingName)

			if (!shouldAdjust) {
				variable.looplb_=(variable.min)
				variable.loopub_=(variable.max)
				variable.shadowingUb_=(stage.vars(variable.shadowingName).max)
				(variable.min, variable.max)
			}
			else {
				val baseVar = enclosingLoops((v.f, variable.shadowingName))
				val bound = BoundsAnalysis
						 .boundsForProdInCon(boundsGraph, stage.id, v.f.id, variable.shadowingName)
						 .getOrElse(throw new InvalidSchedule(f"No bounds for ${v.name} found"))
			  val unadjLb = baseVar.v + bound.lb
				val unadjUb = baseVar.v + bound.ub
			  variable.looplb_=(variable.adjustLower(unadjLb))
				variable.shadowingUb_=(unadjUb + 1)
				variable.loopub_=(unadjUb + 1)
				(variable.adjustLower(unadjLb),
				 variable.adjustUpper(unadjUb) + 1)
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
				      BoundsAnalysis
						 .boundsForProdInCon(boundsGraph, stage.id, v.f.id, "x")
						 .getOrElse(throw new InvalidSchedule(f"No bounds for ${v.name} found"))
				     .width
			}

			val shouldAdjustY = enclosingLoops.keySet contains (v.f, "y")
			val yDim: Rep[Int] = if (!shouldAdjustY) stage.domWidth else {
				      BoundsAnalysis
						 .boundsForProdInCon(boundsGraph, stage.id, v.f.id, "y")
						 .getOrElse(throw new InvalidSchedule(f"No bounds for ${v.name} found"))
				     .width
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
					loops
				}
				else red(children, getLoopsAfterSN(stage, _))
			}
			case LoopNode(_, _, _, children) => red(children, getLoopsAfterSN(stage, _))

		}
	}

	def notPreviouslyComputed(stage: Func,
												    completeTree: ScheduleNode[Func, Dim],
											 	    boundsGraph: CallGraph,
														enclosingLoops: Map[(Func, String), Dim]): Rep[Boolean] = {
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
					val consumerVariables: Map[String, Dim] = computeAtFunc.vars.toMap

					 // Generate list for each variable
					 // v < v.min + overlapSize || v == upperLoopBound
					 def p(name: String) = {
						 val v = stage.vars(name)
						 val w = relevantBounds(name).width - 1
						 val (_, ub): (Rep[Int], Rep[Int]) = computeLoopBounds(v, v.f, boundsGraph, enclosingLoops)
						 v.v < v.min + w || v.v == ub - 1
					 }
					 relevantBounds.keys.toList.foldLeft(unit(false))({
						 case (acc, n) => acc || p(n)
					 })
				 }
			 }
	}

	def getOffsets(enclosingLoops: Map[(Func, String), Dim],
								 sn: ScheduleNode[Func, Dim],
							 	 boundsGraph: CallGraph): List[(String, Rep[Int])] = {
			sn match {
				case StorageNode(f, _) => {
					if (f.computeRoot) {
						// variable.min
						f.vars.map({case (k, v) => (k, v.min)}).toList
					} else {
						// if a loop node is above sn, offset = lb
						// otherwise, offset = variable.min
						def getAdjustment(consumer: Func, name: String) = {
							val baseVar = enclosingLoops((consumer, name))
							val bound = BoundsAnalysis
									 .boundsForProdInCon(boundsGraph, f.id, consumer.id, name)
									 .getOrElse(throw new InvalidSchedule(f"No bounds for ${name} found"))
							baseVar.v + bound.lb
						}

						val computeAtFunc = f.computeAt.getOrElse(throw new InvalidSchedule("No compute at for non inlined function")).f
						f.vars.map({case (name, v) =>
							(name, if (enclosingLoops.keySet.contains((computeAtFunc, name))) getAdjustment(computeAtFunc, name) else v.min)
						}).toList
					}
				}
			}
	}

	def evalSched(node: ScheduleNode[Func, Dim],
								boundsGraph: CallGraph,
								enclosingLoops: Map[(Func, String), Dim],
							  completeTree: ScheduleNode[Func, Dim]): Rep[Unit] = node match {
    case LoopNode(variable, stage, loopType, children) =>
			val (lb, ub) = computeLoopBounds(variable, stage, boundsGraph, enclosingLoops)
      loopType match {
        case Sequential =>
          for (i <- (lb until ub): Rep[Range]) {
            variable.v_=(i)
						for (child <- children) evalSched(child, boundsGraph,
																							enclosingLoops ++ variable.pseudoLoops,
																							completeTree)
          }
        case Unrolled =>
          for (i <- lb until ub) {
            variable.v_=(i)
            for (child <- children) evalSched(child, boundsGraph,
																							enclosingLoops ++ variable.pseudoLoops,
																							completeTree)
          }
      }

    case ComputeNode(stage, children) => {
			if (notPreviouslyComputed(stage, completeTree, boundsGraph, enclosingLoops)) {
	      val v: RGBVal = stage.compute()
	      stage.storeInBuffer(v)
			}
      for (child <- children) evalSched(child, boundsGraph, enclosingLoops, completeTree)
    }

 	  case StorageNode(stage, children) => {
			val dims = computeStorageBounds(stage, boundsGraph, enclosingLoops)
			stage.allocateNewBuffer(dims._1, dims._2)
			val offsets = getOffsets(enclosingLoops, node, boundsGraph)
			stage.setOffsets(offsets)
   	 	for (child <- children) evalSched(child, boundsGraph, enclosingLoops, completeTree)
 	  }

    case RootNode(children) => {
      for (child <- children) evalSched(child, boundsGraph, enclosingLoops, completeTree)
    }
  }
}
