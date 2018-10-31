package sepia

trait ScheduleCompiler extends CompilerFuncOps {
	def evalSched(node: ScheduleNode[Func, Dim]): Rep[Unit] = node match {
      case LoopNode(variable, stage, loopType, children) =>
        loopType match {
          case Sequential =>
            for (i <- (0 until variable.max): Rep[Range]) {
              variable.v_=(i)
              for (child <- children) evalSched(child)
            }
          case Unrolled =>
            for (i <- 0 until variable.max) {
              variable.v_=(i)
              for (child <- children) evalSched(child)
            }
        }

      case ComputeNode(stage, children) => {
        /* At a compute node, we compute f.stage and store it.
          TODO: if we computed f in a previous iteration, we need to skip over
          it */
        val v: Rep[Int] = stage.compute()
        stage.storeInBuffer(v)
        for (child <- children) evalSched(child)
      }

 	  case StorageNode(stage, children) => {
			stage.allocateNewBuffer()
   	 	for (child <- children) evalSched(child)
 	  }

      case RootNode(children) => {
        for (child <- children) evalSched(child)
      }

    }
}
