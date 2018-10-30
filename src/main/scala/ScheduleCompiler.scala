package sepia

trait ScheduleCompiler extends CompilerFuncOps {
	def evalSched(node: ScheduleNode[PipelineStage, Dim]): Rep[Unit] = node match {
      case LoopNode(variable, stage, loopType, children) =>
        loopType match {
          /* Here we generate a for loop for 'variable', finding its upper bound from stage.
             The value of the variable (a rep of an int or an actual int)
             gets added to the env */
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
        val v: Rep[Int] = compute(stage)
        storeInBuffer(v)
        for (child <- children) evalSched(child)
      }

 	  case StorageNode(stage, children) => {
   	 	stage.buffer = Some(New2DArray[Int](stage.y.max, stage.x.max))
   	 	for (child <- children) evalSched(child)
 	  }

      case RootNode(children) => {
        for (child <- children) evalSched(child)
      }

    }
}
