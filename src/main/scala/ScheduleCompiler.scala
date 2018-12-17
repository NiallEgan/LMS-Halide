package sepia


trait ScheduleCompiler extends CompilerFuncOps {
	def computeLoopBounds(variable: Dim, stage: Func,
												boundsGraph: CallGraph,
												loopsEncountered: Map[(Func, String), Dim]): (Rep[Int], Rep[Int]) = {
		if (stage.inlined) throw new InvalidSchedule(f"Inlined function $stage should have no loops")
		if (stage.computeRoot) (variable.min, variable.max)
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
      val v: RGBVal = stage.compute()
      stage.storeInBuffer(v)
      for (child <- children) evalSched(child, boundsGraph, loopsEncountered)
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
   	 	for (child <- children) evalSched(child, boundsGraph, loopsEncountered)
 	  }

    case RootNode(children) => {
      for (child <- children) evalSched(child, boundsGraph, loopsEncountered)
    }
  }
}
