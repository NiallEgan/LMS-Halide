package sepia


trait ScheduleCompiler extends CompilerFuncOps {
	def computeLoopBounds(variable: Dim, stage: Func,
												boundsGraph: Map[Int, Map[Int, Map[String, Bound]]]): (Rep[Int], Rep[Int]) = {
		val lowerBound: Rep[Int] =
			if (stage.inlined) variable.min // TODO: Compute at root
			else {
				stage.computeAt match {
					case None => throw new InvalidSchedule(f"Non-inlined function $stage has no computeAt variable")
					// This only works for x and y...
					case Some(v) => {
						if(v.name == variable.name) {
							 BoundsAnalysis.boundsForProdInCon(boundsGraph, stage.id, v.f.id, v.name) match {
								case Some(bound) => v.v + bound.lb
								case None => throw new InvalidSchedule(f"No bounds for ${v.name} found")
							}
						}
						else unit(variable.min)
					}
				}
			}

		val upperBound: Rep[Int] =
			if (stage.inlined) variable.max
			else {
				stage.computeAt match {
					case None => throw new InvalidSchedule(f"Non-inlined function $stage has no computeAt variable")
					case Some(v) => {
						// If v.name == variable.name, then (at least when we're just dealing with x and y)
						// We are at the loop that must start from the producer variable
						if (v.name == variable.name) {
						 BoundsAnalysis.boundsForProdInCon(boundsGraph, stage.id, v.f.id, v.name) match {
								case Some(bound) => v.v + bound.ub + 1
								case None => throw new InvalidSchedule(f"No bounds for ${v.name} found")
							}
						}
						else unit(variable.max)
					}
				}
			}

		(lowerBound, upperBound)
	}

	def evalSched(node: ScheduleNode[Func, Dim],
								boundsGraph: Map[Int, Map[Int, Map[String, Bound]]]): Rep[Unit] = node match {
    case LoopNode(variable, stage, loopType, children) =>
			val (lb, ub) = computeLoopBounds(variable, stage, boundsGraph)
			variable.loopStartOffset_=(lb)
      loopType match {
        case Sequential =>
          for (i <- (lb until ub): Rep[Range]) {
            variable.v_=(i)
            for (child <- children) evalSched(child, boundsGraph)
          }
        case Unrolled =>
          for (i <- lb until ub) {
            variable.v_=(i)
            for (child <- children) evalSched(child, boundsGraph)
          }
      }

    case ComputeNode(stage, children) => {
      /* At a compute node, we compute f.stage and store it.
        TODO: if we computed f in a previous iteration, we need to skip over
        it */
      val v: Rep[UShort] = stage.compute()
      stage.storeInBuffer(v)
      for (child <- children) evalSched(child, boundsGraph)
    }

 	  case StorageNode(stage, children) => {
			stage.storeAt match {
				case None => stage.allocateNewBuffer()
				case Some(storeAtDim) => {
					// TODO: For now, only support storing at one level up
					val consumerFunction = storeAtDim.f
					val xBound =
						BoundsAnalysis.boundsForProdInCon(boundsGraph, stage.id,
							consumerFunction.id, stage.x.name)
							.getOrElse(throw new InvalidSchedule("Couldn't find bound for "))
					val yBound =
						BoundsAnalysis.boundsForProdInCon(boundsGraph, stage.id,
							consumerFunction.id, stage.y.name)
							.getOrElse(throw new InvalidSchedule("Couldn't find bound for "))

					if (storeAtDim.name == "y") {
						stage.allocateNewBuffer(stage.x.max, yBound.width)
					} else if (storeAtDim.name == "x") {
						stage.allocateNewBuffer(xBound.width, stage.y.max)
					} else {
						throw new RuntimeException("Error: Unknown variable name")
					}
				}
			}
   	 	for (child <- children) evalSched(child, boundsGraph)
 	  }

    case RootNode(children) => {
      for (child <- children) evalSched(child, boundsGraph)
    }
  }
}
