package sepia

trait ScheduleCompiler extends CompilerFuncOps {

	def makeFlatBounds(idToFunc: Map[Int, Func],
										 boundsGraph: Map[Int, Map[Int, (Bound, Bound)]]): Map[(Func, Dim), Bound] = {
		// (f, x) -> Bound(a, b) means that x has bounds (a, b) when used in f
		// N.B. x is bound to some function g
		var bs: Map[(Func, Dim), Bound] = Map()
		for (conId <- boundsGraph.keys) {
			val consumer = idToFunc(conId)
			for (prodId <- boundsGraph(conId).keys) {
				val producer = idToFunc(prodId)
				bs += (consumer, producer.x) -> boundsGraph(conId)(prodId)._1
				bs += (consumer, producer.y) -> boundsGraph(conId)(prodId)._2
			}
		}

		bs
	}
	def computeLoopBounds(variable: Dim, stage: Func, flatBounds: Map[(Func, Dim), Bound]): (Rep[Int], Rep[Int]) = {
		val lowerBound: Rep[Int] =
			if (stage.inlined) variable.min // TODO: Compte at root
			else {
				stage.computeAt match {
					case None => throw new InvalidSchedule(f"Non-inlined function $stage has no computeAt variable")
					// This only works for x and y...
					case Some(v) => {
						if(v.name == variable.name) v.v + flatBounds(v.f, variable).lb
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
						if (v.name == variable.name) v.v + flatBounds(v.f, variable).ub + 1
						else unit(variable.max)
					}

				}
			}

		(lowerBound, upperBound)
	}
	def evalSched(node: ScheduleNode[Func, Dim],
								flatBounds: Map[(Func, Dim), Bound]): Rep[Unit] = node match {
    case LoopNode(variable, stage, loopType, children) =>
			val (lb, ub) = computeLoopBounds(variable, stage, flatBounds)
      loopType match {
        case Sequential =>
          for (i <- (lb until ub): Rep[Range]) {
            variable.v_=(i)
            for (child <- children) evalSched(child, flatBounds)
          }
        case Unrolled =>
          for (i <- lb until ub) {
            variable.v_=(i)
            for (child <- children) evalSched(child, flatBounds)
          }
      }

    case ComputeNode(stage, children) => {
      /* At a compute node, we compute f.stage and store it.
        TODO: if we computed f in a previous iteration, we need to skip over
        it */
      val v: Rep[Int] = stage.compute()
      stage.storeInBuffer(v)
      for (child <- children) evalSched(child, flatBounds)
    }

 	  case StorageNode(stage, children) => {
			stage.storeAt match {
				case None => stage.allocateNewBuffer()
				case Some(storeAtDim) => {
					// TODO: For now, only support storing at one level up
					val consumerFunction = storeAtDim.f
					val xBound = flatBounds((consumerFunction, stage.x))
					val yBound = flatBounds((consumerFunction, stage.y))

					if (storeAtDim.name == "x") {
						stage.allocateNewBuffer(stage.x.max, yBound.width)
					} else if (storeAtDim.name == "y") {
						stage.allocateNewBuffer(xBound.width, stage.y.max)
					} else {
						throw new RuntimeException("Error: Unknown variable name")
					}
				}
			}
   	 	for (child <- children) evalSched(child, flatBounds)
 	  }

    case RootNode(children) => {
      for (child <- children) evalSched(child, flatBounds)
    }
  }
}
