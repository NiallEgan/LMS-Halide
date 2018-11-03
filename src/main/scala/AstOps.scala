package sepia

trait AstOps extends ScheduleOps {
	this: CompilerFuncOps =>

	override type Schedule = RootNode[Func, Dim]

	def newSimpleSched(stage: Func): Schedule = {
		// A simple function for now that just returns
		// a new tree for stage. This assumes that everything is inlined
		// etc

		val cn: ComputeNode[Func, Dim] = new ComputeNode[Func, Dim](stage, List())
    val xLoop: LoopNode[Func, Dim] = new LoopNode[Func, Dim](stage.x, stage,
    									Sequential, List(cn))
    val yLoop: LoopNode[Func, Dim] = new LoopNode[Func, Dim](stage.y, stage,
    									Sequential, List(xLoop))
    val sn: StorageNode[Func, Dim] = new StorageNode[Func, Dim](stage, List(yLoop))
    new RootNode[Func, Dim](List(sn))
	}
}
