package sepia

trait AstOps extends ScheduleOps {
	this: PipelineLike =>

	override type Schedule = RootNode[PipelineStage, Dim]

	def newSimpleSched(stage: PipelineStage): Schedule = {
		// A simple function for now that just returns
		// a new tree for stage. This assumes that everything is inlined
		// etc

		val cn: ComputeNode[PipelineStage, Dim] = new ComputeNode[PipelineStage, Dim](stage, List())
	    val xLoop: LoopNode[PipelineStage, Dim] = new LoopNode[PipelineStage, Dim](stage.x, stage, 
	    									Sequential, List(cn))
	    val yLoop: LoopNode[PipelineStage, Dim] = new LoopNode[PipelineStage, Dim](stage.y, stage, 
	    									Sequential, List(xLoop))
	    val sn: StorageNode[PipelineStage, Dim] = new StorageNode[PipelineStage, Dim](stage, List(yLoop))
	    new RootNode[PipelineStage, Dim](List(sn))
	}
}