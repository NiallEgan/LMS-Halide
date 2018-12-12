package sepia


trait ScheduleCompiler extends CompilerFuncOps {
	def computeLoopBounds(variable: Dim, stage: Func,
												boundsGraph: CallGraph): (Rep[Int], Rep[Int]) = {
		if (stage.inlined) (variable.min, variable.max)
		else {
			val v = stage.computeAt
							.getOrElse(throw new InvalidSchedule(f"Non-inlined function $stage has no computeAt variable"))
			val bound = BoundsAnalysis
					 .boundsForProdInCon(boundsGraph, stage.id, v.f.id, v.name)
					 .getOrElse(throw new InvalidSchedule(f"No bounds for ${v.name} found"))

			val lowerBound: Rep[Int] = {
				// This only works for x and y...
				if(v.name == variable.name) v.v + bound.lb
				else variable.min
			}

			val upperBound: Rep[Int] = {
					// If v.name == variable.name, then (at least when we're just dealing with x and y)
					// We are at the loop that must start from the producer variable
					if (v.name == variable.name) v.v + bound.ub + 1
					else variable.max
			}

			(lowerBound, upperBound)
		}
	}

	def evalSched(node: ScheduleNode[Func, Dim],
								boundsGraph: CallGraph): Rep[Unit] = node match {
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
      val v: RGBVal = stage.compute()
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
