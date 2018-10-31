import sepia._

trait TestAstOps extends AstOps {
	this: CompilerFuncOps with TestPipeline =>

	private def toStringRep(node: ScheduleNode[Func, Dim]): ScheduleNode[String, String] = {
		node match {
			case RootNode(children) =>
				new RootNode[String, String](children.map(toStringRep))
			case ComputeNode(func, children) =>
				new ComputeNode[String, String](getStage(func), children.map(toStringRep))
			case StorageNode(func, children) =>
				new StorageNode[String, String](getStage(func), children.map(toStringRep))
			case LoopNode(variable, func, loopType, children) =>
				new LoopNode[String, String](variable.name, getStage(func), loopType,
											 children.map(toStringRep))
		}
	}

	def scheduleRep(): ScheduleNode[String, String] = toStringRep(sched)
}
