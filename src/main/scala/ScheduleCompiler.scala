package sepia

trait ScheduleCompiler extends CompilerFuncOps {
	def evalSched(node: ScheduleNode[Func, Dim],
								bounds: Map[Int, Map[Int, (Bound, Bound)]]): Rep[Unit] = node match {
    case LoopNode(variable, stage, loopType, children) =>
      loopType match {
        case Sequential =>
          for (i <- (0 until variable.max): Rep[Range]) {
            variable.v_=(i)
            for (child <- children) evalSched(child, bounds)
          }
        case Unrolled =>
          for (i <- 0 until variable.max) {
            variable.v_=(i)
            for (child <- children) evalSched(child, bounds)
          }
      }

    case ComputeNode(stage, children) => {
      /* At a compute node, we compute f.stage and store it.
        TODO: if we computed f in a previous iteration, we need to skip over
        it */
      val v: Rep[Int] = stage.compute()
      stage.storeInBuffer(v)
      for (child <- children) evalSched(child, bounds)
    }

 	  case StorageNode(stage, children) => {
			stage.storeAt match {
				case None => stage.allocateNewBuffer()
				case Some(dim) => {
					// TODO: For now, only support storing at one level up
					val topConsumerFunctionId: Int = dim.f.id
					val (xBound, yBound): (Bound, Bound) = bounds(topConsumerFunctionId)(stage.id)
					if (dim.name == "x") {
						stage.allocateNewBuffer(stage.x.max, yBound.width)
					} else if (dim.name == "y") {
						stage.allocateNewBuffer(xBound.width, stage.y.max)
					} else {
						throw new RuntimeException("Error: Unknown variable name")
					}
				}
			}
   	 	for (child <- children) evalSched(child, bounds)
 	  }

    case RootNode(children) => {
      for (child <- children) evalSched(child, bounds)
    }
  }
}
