package sepia

trait AstOps extends Ast with ScheduleOps {
	this: PipelineLike =>

	override type Schedule = RootNode

	def newSimpleSched(stage: PipelineStage): Schedule = {
		// A simple function for now that just returns
		// a new tree for stage. This assumes that everything is inlined
		// etc

		val cn: ComputeNode = new ComputeNode(stage, List())
	    val x_loop: LoopNode = new LoopNode(stage.x, stage, 
	    									List(cn), Sequential)
	    val y_loop: LoopNode = new LoopNode(stage.y, stage, 
	    									List(x_loop), Sequential)
	    val sn: StorageNode = new StorageNode(stage, List(y_loop))
	    new RootNode(List(sn))
	}
}