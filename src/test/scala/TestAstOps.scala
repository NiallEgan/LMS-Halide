import sepia._

abstract class TNode
case class TRootNode(children: List[TNode]) extends TNode
case class TComputeNode(func: String, children: List[TNode]) extends TNode
case class TStorageNode(func: String, children: List[TNode]) extends TNode
case class TLoopNode(variable: String, func: String, loopType: LoopType, children: List[TNode]) extends TNode

trait TestAstOps extends AstOps {
	this: CompilerFuncOps with TestPipeline with PipelineForCompiler =>
	private def toString(node: ScheduleNode): TNode = {
		node match {
			case RootNode(children) => TRootNode(children.map(toString))
			case ComputeNode(func, children) =>
					TComputeNode(asString(func), children.map(toString))
			case StorageNode(func, children) =>
					TStorageNode(asString(func), children.map(toString))
			case LoopNode(variable, func, loopType, children) =>
				 	TLoopNode(variable.name, asString(func), loopType,
										children.map(toString))
		}
	}

	def scheduleRep(): TNode = toString(sched)
}
