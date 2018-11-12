import sepia._

trait TestAstOps extends AstOps {
	this: CompilerFuncOps with TestPipeline with PipelineForCompiler =>

	private def toString(node: ScheduleNode[Func, Dim]): ScheduleNode[String, String] = {
		node match {
			case RootNode(children) =>
				new RootNode[String, String](children.map(toString))
			case ComputeNode(func, children) =>
				new ComputeNode[String, String](asString(func), children.map(toString))
			case StorageNode(func, children) =>
				new StorageNode[String, String](asString(func), children.map(toString))
			case LoopNode(variable, func, loopType, children) =>
				new LoopNode[String, String](variable.name, asString(func), loopType,
											 children.map(toString))
		}
	}

	def scheduleRep(): ScheduleNode[String, String] = toString(sched)
}
