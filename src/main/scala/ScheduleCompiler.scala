package sepia


trait ScheduleCompiler extends CompilerFuncOps with AstOps {
	def computeLoopBounds(variable: Dim, stage: Func[_],
												boundsGraph: CallGraph,
												enclosingLoops: Map[(Func[_], String), Dim]): (Rep[Int], Rep[Int]) = {
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
		} else if (variable.isInstanceOf[OuterDim]) {
			val outerVariable = variable.asInstanceOf[OuterDim]
			val normalBounds = computeSimpleLoopBounds(outerVariable.old, stage, boundsGraph, enclosingLoops)
			val a = normalBounds._1
			val b = normalBounds._2

			outerVariable.setOldLoopOffset(a)
			val extent = outerVariable.splitFactor
			// (x + y - 1) / y ceils
			// maybe q = (x % y) ? x / y + 1 : x / y is better?
			val lowerBound = 0
			val upperBound = (b - a + extent - 1) / extent
			variable.looplb_=(lowerBound)
			variable.loopub_=(upperBound)
			variable.shadowingUb_=(b)
			(lowerBound, upperBound)
		} else computeSimpleLoopBounds(variable, stage, boundsGraph, enclosingLoops)
	}

	def computeSimpleLoopBounds(variable: Dim, stage: Func[_],
															boundsGraph: CallGraph,
															enclosingLoops: Map[(Func[_], String), Dim]): (Rep[Int], Rep[Int]) = {

		def clamp(enclosingVar: Dim): Rep[Int] = {
			val extent = enclosingVar.splitFactor
			val lb: Rep[Int] = if (enclosingVar.dimDefined) 0
												 else enclosingVar.looplb

			if (enclosingVar.v + lb > enclosingVar.shadowingUb - extent)
				enclosingVar.shadowingUb - (enclosingVar.v + lb)
			else
				unit(extent)
		}

		if (stage.inlined) throw new InvalidSchedule(f"Inlined function $stage should have no loops")
		else if (stage.computeRoot) {
			val lb = variable.min
			val ub =
				if (enclosingLoops.keySet contains (variable.f, variable.shadowingName)) {
					lb + clamp(enclosingLoops(variable.f, variable.shadowingName))
				} else
				variable.max

			variable.looplb_=(lb)
			variable.loopub_=(ub)
			variable.shadowingUb_=(ub)
			(lb, ub)
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
				val baseVar = v.f.vars(variable.shadowingName)
				var adjLb: Rep[Int] = 0
				var adjUb: Rep[Int] = 0
				if (enclosingLoops.keySet contains (variable.f, variable.shadowingName)) {
					val enclosingVar = enclosingLoops(variable.f, variable.shadowingName)
					val clamped = clamp(enclosingVar)

					adjLb = enclosingVar.looplb
					adjUb = adjLb + clamped

				} else {
					val enclosingVar = enclosingLoops(v.f, variable.shadowingName)
					val bound = BoundsAnalysis
						.boundsForProdInCon(boundsGraph, stage.id, v.f.id, variable.shadowingName)
						.getOrElse(throw new InvalidSchedule(f"No bounds for ${v.name} found"))

					var clamped = clamp(enclosingVar)

					adjLb = if (baseVar.dimDefined) bound.mulLower * baseVar.v / bound.divLower + bound.lb
					else bound.mulLower * (baseVar.v + baseVar.looplb) / bound.divLower + bound.lb
					adjUb = if (baseVar.dimDefined) bound.mulHigher * (baseVar.v + clamped) / bound.divHigher + bound.ub
					else bound.mulHigher * (baseVar.v + clamped + baseVar.looplb) / bound.divHigher + bound.ub
				}

				variable.looplb_=(adjLb)
				variable.shadowingUb_=(adjUb)
				variable.loopub_=(adjUb)
				(adjLb, adjUb)
			}
		}
	}

	def computeSimpleLoopBounds1(variable: Dim, stage: Func[_],
															boundsGraph: CallGraph,
															enclosingLoops: Map[(Func[_], String), Dim]): (Rep[Int], Rep[Int]) = {

		def clamp(extent: Rep[Int], baseVar: Dim): Rep[Int] = {

			val lb: Rep[Int] = if (baseVar.f.vars(variable.shadowingName).dimDefined) 0
							 else baseVar.looplb

			if (baseVar.v + lb > baseVar.shadowingUb - extent)
				baseVar.shadowingUb - (baseVar.v + lb)
			else
				extent
		}

		if (stage.inlined) throw new InvalidSchedule(f"Inlined function $stage should have no loops")
		else if (stage.computeRoot) {
			val lb = variable.min
			val ub = if (enclosingLoops.keySet contains (variable.f, variable.shadowingName)) {
					val extent = enclosingLoops(variable.f, variable.shadowingName).scaleRatio
					lb + clamp(extent, enclosingLoops(variable.f, variable.shadowingName))
				}
				else
					variable.max

			variable.looplb_=(lb)
			variable.loopub_=(ub)
			variable.shadowingUb_=(ub)
			(lb, ub)
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
				val baseVar = v.f.vars(variable.shadowingName)
				val enclosingVar =
					if (enclosingLoops.keySet contains (variable.f, variable.shadowingName))
						enclosingLoops(variable.f, variable.shadowingName)
					else
						enclosingLoops(v.f, variable.shadowingName)

				val bound = BoundsAnalysis
						 .boundsForProdInCon(boundsGraph, stage.id, v.f.id, variable.shadowingName)
						 .getOrElse(throw new InvalidSchedule(f"No bounds for ${v.name} found"))

				val extent = enclosingVar.scaleRatio

				var clamped = clamp(extent, enclosingVar)

				if (enclosingLoops.keySet contains (variable.f, variable.shadowingName))
					clamped = clamped - bound.ub - 1

				//TODO: prettify (baseVar looplb (maybe shadowing) should return 0 for fully defined baseVars)
				var unadjLb = if (baseVar.dimDefined) bound.mulLower * baseVar.v / bound.divLower + bound.lb
											else bound.mulLower * (baseVar.v + baseVar.looplb) / bound.divLower + bound.lb
				var unadjUb = if (baseVar.dimDefined) bound.mulHigher * baseVar.v / bound.divHigher + bound.ub + clamped - 1
											else bound.mulHigher * (baseVar.v + baseVar.looplb) / bound.divHigher + bound.ub + clamped - 1

			  variable.looplb_=(unadjLb)
				variable.shadowingUb_=(unadjUb + 1)
				variable.loopub_=(unadjUb + 1)
				(unadjLb, unadjUb + 1)
			}
		}
	}

	def computeStorageBounds(stage: Func[_],
													 boundsGraph: CallGraph,
												 	 enclosingLoops: Map[(Func[_], String), Dim]): (Rep[Int], Rep[Int]) = {
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
				     .width - 1 + enclosingLoops((v.f, "x")).scaleRatio
			}

			val shouldAdjustY = enclosingLoops.keySet contains (v.f, "y")
			val yDim: Rep[Int] = if (!shouldAdjustY) stage.domWidth else {
				      BoundsAnalysis
						 .boundsForProdInCon(boundsGraph, stage.id, v.f.id, "y")
						 .getOrElse(throw new InvalidSchedule(f"No bounds for ${v.name} found"))
				     .width  - 1 + enclosingLoops((v.f, "y")).scaleRatio
			}

			(xDim, yDim)
		}
	}

	def red(children: List[ScheduleNode],
					f: ScheduleNode => Map[(Func[_], String), Dim]) = {
		children.map(f(_)).foldLeft(Map[(Func[_], String), Dim]())(_ ++ _)
	}

	def getLoopsAfterSN(stage: Func[_], completeTree: ScheduleNode): Map[(Func[_], String), Dim] = {
		// Collect all of the loop nodes on the path from the storage node for f to its compute node

		def cnIsChild(node: ScheduleNode): Boolean = {
			node.exists((n: ScheduleNode) => n match {
				case ComputeNode(_, _) => n.belongsTo(stage)
				case _ => false
			})
		}

		def collectLoops(node: ScheduleNode): Map[(Func[_], String), Dim] = node match {
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

	def notPreviouslyComputed(stage: Func[_],
												    completeTree: ScheduleNode,
											 	    boundsGraph: CallGraph,
														enclosingLoops: Map[(Func[_], String), Dim]): Rep[Boolean] = {
			if (stage.computeRoot) true
			else {
				val computeAtFunc: Func[_] = stage.computeAt.getOrElse(throw new InvalidSchedule("non-inlined function has no compute at")).f
				val relevantEnclosingLoops = getLoopsAfterSN(stage, completeTree).filterKeys(_._1 == computeAtFunc)

				val relevantBounds: Map[String, Bound] =
					for (((f, name), d) <- relevantEnclosingLoops)
							yield d.shadowingName -> {
								BoundsAnalysis.boundsForProdInCon(boundsGraph, stage.id, f.id, d.shadowingName).getOrElse(throw new Exception())
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
						 case (acc, n) => true
					 })
				 }
			 }
	}

	def getOffsets(enclosingLoops: Map[(Func[_], String), Dim],
								 sn: ScheduleNode,
							 	 boundsGraph: CallGraph): List[(String, Rep[Int])] = {
			sn match {
				case StorageNode(f, _) => {
					if (f.computeRoot) {
						// variable.min
						println("getting offsets from compute root:")

						f.vars.map({case (k, v) => {
							val bound = BoundsAnalysis
									 .boundsForProdInCon(boundsGraph, -1, f.id, v.shadowingName)
									 .getOrElse(Bound(0, 0, 1, 1, 1, 1))
							println(f"bound for offset: $bound")
							(k, v.min)}}).toList
					} else {
						// if a loop node is above sn, offset = lb
						// otherwise, offset = variable.min
						def getAdjustment(consumer: Func[_], name: String) = {
							val baseVar = consumer.vars(name)
							val bound = BoundsAnalysis
									 .boundsForProdInCon(boundsGraph, f.id, consumer.id, name)
									 .getOrElse(throw new InvalidSchedule(f"No bounds for ${name} found"))

							if (baseVar.dimDefined)
								(baseVar.v + bound.lb) * bound.mulLower / bound.divLower
							else
								(baseVar.v + baseVar.looplb + bound.lb) * bound.mulLower / bound.divLower
						}

						val storeAtFunc = f.storeAt.getOrElse(throw new InvalidSchedule("No compute at for non inlined function")).f
						f.vars.map({case (name, v) =>
							(name, if (enclosingLoops.keySet.contains((storeAtFunc, v.shadowingName))) getAdjustment(storeAtFunc, v.shadowingName) else v.min)
						}).toList
					}
				}
			}
	}

	def evalSched(node: ScheduleNode,
								boundsGraph: CallGraph,
								enclosingLoops: Map[(Func[_], String), Dim],
							  completeTree: ScheduleNode): Rep[Unit] = {
		node match {
    case LoopNode(variable, stage, loopType, children) =>
			val (lb, ub) = computeLoopBounds(variable, stage, boundsGraph, enclosingLoops)
      loopType match {
        case Sequential() =>
          for (i <- (lb until ub): Rep[Range]) {
            variable.v_=(i)
						for (child <- children) evalSched(child, boundsGraph,
																							enclosingLoops ++ variable.pseudoLoops,
																							completeTree)
          }
        case Unrolled() =>
          for (i <- lb until ub) {
            variable.v_=(i)
            for (child <- children) evalSched(child, boundsGraph,
																							enclosingLoops ++ variable.pseudoLoops,
																							completeTree)
          }

				case Vectorized(n) => {
					// What I really want...
					/*for (i <- lb until ub: Vectorized[Range]) {
						variable.v_=(i)
						for (child <- children) evalSched(child, boundsGraph,
																							enclosingLoops ++ variable.pseudoLoops,
																							completeTree)
					}*/

					// TODO: Generalize - a lot!
					vectorized_loop(0 until n, i => {
						variable.v_=(i)
						//print(children)
						assert(children.length == 1)
						val child = children(0)
						child match {
							case ComputeNode(s, cs) if cs == List() => {
								if (notPreviouslyComputed(stage, completeTree, boundsGraph, enclosingLoops ++ variable.pseudoLoops)) {
									stage.storeInBuffer(stage.compute())
								}
							}
							case _ => throw new InvalidSchedule("Error: Can only vectorize loops with a direct, single compute node child.")
						}
					})
				}
      }

    case ComputeNode(stage, children) => {
			if (notPreviouslyComputed(stage, completeTree, boundsGraph, enclosingLoops)) {
				println("Computing")
				val v = stage.compute()
	      stage.storeInBuffer(v)
			}
      for (child <- children) evalSched(child, boundsGraph, enclosingLoops, completeTree)
    }

 	  case StorageNode(stage, children) => {

			val dims = computeStorageBounds(stage, boundsGraph, enclosingLoops)
			stage.allocateNewBuffer(dims._1, dims._2)
			val offsets = getOffsets(enclosingLoops, node, boundsGraph)
			println(f"offsets: $offsets")
			stage.setOffsets(offsets)
   	 	for (child <- children) evalSched(child, boundsGraph, enclosingLoops, completeTree)
			stage.deallocBuffer()
 	  }

    case RootNode(children) => {
      for (child <- children) evalSched(child, boundsGraph, enclosingLoops, completeTree)
    }
  }}
}
