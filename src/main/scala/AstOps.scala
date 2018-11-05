package sepia

trait AstOps extends ScheduleOps {
	this: CompilerFuncOps =>

	override type Schedule = ScheduleNode[Func, Dim]

	private def simpleFuncTree(f: Func): ScheduleNode[Func, Dim] = {
		val cn: ComputeNode[Func, Dim] = ComputeNode[Func, Dim](f, List())
		val xLoop: LoopNode[Func, Dim] = LoopNode[Func, Dim](f.x, f,
											Sequential, List(cn))
		val yLoop: LoopNode[Func, Dim] = LoopNode[Func, Dim](f.y, f,
											Sequential, List(xLoop))
	  StorageNode[Func, Dim](f, List(yLoop))
	}

	def newSimpleSched(stage: Func): Schedule = {
		// A simple function for now that just returns
		// a new tree for stage. This assumes that everything is inlined
		// etc

    new RootNode[Func, Dim](List(simpleFuncTree(stage)))
	}

	private def insertNewLeftChild(sched: ScheduleNode[Func, Dim],
																 fTree: ScheduleNode[Func, Dim],
															 	 y: Dim) = match sched {
		// Inserts fTree at the loop for y in sched
		case RootNode(children) => {
			RootNode(children.map(insertNewLeftChild(sched, _, y)))
		}
		case ComputeNode(stage, children) => {
			ComputeNode(stage, children.map(insertNewLeftChild(sched, _, y)))
		}
		case StorageNode(stage, children) => {
			StorageNode(stage, children.map(insertNewLeftChild(sched, _, y)))
		}
		case LoopNode(variable, stage, loopType, children) => {
			if (variable == y) {
				LoopNode(variable, stage, loopType, fTree::children)
			} else {
				LoopNode(variable, stage, loopType,
								 children.map(insertNewLeftChild(sched, _, y)))
			}
		}
	}

	private def treeMatch(f: Func)(sched: ScheduleNode[Func, Dim]) = {
		sched match {
			case RootNode(_) => false
			case ComputeNode(stage, _) => stage == f
			case StorageNode(stage, _) => stage == f
			case LoopNode(_, stage, _, _) => stage == f
		}
	}

	private def deleteTreeFor(sched: Schedule, f: Func) = {
		sched match {
			case RootNode(children) => {
				RootNode(children.filter(!treeMatch(f)).map(deleteTreeFor(_, f)))
			}
			case LoopNode(variable, stage, loopType, children) => {
				LoopNode(variable, stage, loopType,
								 children.filter(!treeMatch(f)).map(deleteTreeFor(_, f)))
			}
			case StorageNode(stage, children) => {
				StorageNode(stage,
										children.filter(!treeMatch(f)).map(deleteTreeFor(_, f)))
			}
			case ComputeNode(stage, children) => {
				ComputeNode(stage,
										children.filter(!treeMatch(f)).map(deleteTreeFor(_, f)))
			}
		}
	}

	def oneOf[Option[T]](l: List[Option[T]]) = l match {
		case Nil => None
		case Some(x)::xs => match oneOf[T](xs) {
			case None => Some(x)
			case Some(_) => throw new InvalidSchedule("Too many computations")
		}
		case None::xs => oneOf[T](xs)
	}

	private def findTreeFor(sched: Schedule, f: Func): Option[Schedule] = {
		sched match {
			case RootNode(children) => oneOf[Schedule](children.map(_, f))
			case LoopNode(_, stage, _, children)@l => {
				if (stage == f) Some(l)
				else oneOf[Schedule](children.map(_, f))
			}
			case ComputeNode(stage, children)@c => {
				if (stage == f) Some(c)
				else oneOf[Schedule](children.map(_, f))
			}
			case StorageNode(stage, children)@s => {
				if (stage == f) Some(s)
				else oneOf[Schedule](children.map(_, f))
			}
		}
	}

	def computefAtX(sched: Schedule, f: Func, y: Dim): Schedule = {
		// If f is inlined, create a new sched tree for it.
		// Else, cut out the current f tree
		var newSched = sched
		val fTree: ScheduleNode =
			if (f.inlined) {
				f.inlined = false
				simpleFuncTree(f)
			} else {
				val fTree = findTreeFor(sched, f)
				newSched = deleteTreeFor(sched, f)
				fTree
			}

		// Move the part of the tree for f to be a child of yLoop
		insertNewLeftChild(newSched, fTree, y)
	}
}
