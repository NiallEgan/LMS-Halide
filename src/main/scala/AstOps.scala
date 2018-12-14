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

	private def addChildren(t: ScheduleNode[Func, Dim],
													children: List[ScheduleNode[Func, Dim]]) = t match {
			case RootNode(otherChildren) => RootNode(otherChildren ++ children)
			case ComputeNode(s, otherChildren) => ComputeNode(s, otherChildren ++ children)
			case StorageNode(s, otherChildren) => StorageNode(s, otherChildren ++ children)
			case LoopNode(v, s, loopType,  otherChildren) => LoopNode(v, s, loopType, otherChildren ++ children)

	}

	private def findLoopNodeFor(sched: ScheduleNode[Func, Dim],
															computeAtVar: Dim): Option[ScheduleNode[Func, Dim]] = sched match {
		// Inserts fTree at the loop for y in sched
		case RootNode(children) => {
			listToOption(children.map(findLoopNodeFor(_, computeAtVar)))
		}
		case ComputeNode(stage, children) => {
			listToOption(children.map(findLoopNodeFor(_, computeAtVar)))
		}
		case StorageNode(stage, children) => {
			listToOption(children.map(findLoopNodeFor(_, computeAtVar)))
		}
		case l@LoopNode(variable, stage, loopType, children) => {
			if (variable == computeAtVar) Some(l)
			else {
				listToOption(children.map(findLoopNodeFor(_, computeAtVar)))
			}
		}
	}

	private def addLeastSibling(child: ScheduleNode[Func, Dim],
															parent: ScheduleNode[Func, Dim],
														  sched: ScheduleNode[Func, Dim]): ScheduleNode[Func, Dim] = {
		def insert() = parent match {
			case RootNode(children) => RootNode(child::children)
			case ComputeNode(stage, children) => ComputeNode(stage, child::children)
			case StorageNode(stage, children) => StorageNode(stage, child::children)
			case LoopNode(variable, stage, loopType, children) => LoopNode(variable, stage, loopType, child::children)

		}
		if (sched == parent) insert()
		else {
			sched match {
				case RootNode(children) => RootNode(children.map(addLeastSibling(child, parent, _)))
				case ComputeNode(stage, children) => ComputeNode(stage, children.map(addLeastSibling(child, parent, _)))
				case StorageNode(stage, children) => StorageNode(stage, children.map(addLeastSibling(child, parent, _)))
				case LoopNode(variable, stage, loopType, children) => {
					LoopNode(variable, stage, loopType, children.map(addLeastSibling(child, parent, _)))
				}

			}
		}

	}

	private def nodeFor(f: Func, sched: ScheduleNode[Func, Dim]) = {
		sched match {
			case RootNode(_) => false
			case ComputeNode(stage, _) => stage == f
			case StorageNode(stage, _) => stage == f
			case LoopNode(_, stage, _, _) => stage == f
		}
	}

	private def removeTree(sched: Schedule, toRemove: Schedule): Schedule = {
		sched match {
			case RootNode(children) => {
				RootNode(children.filter(_ != toRemove).map(removeTree(_, toRemove)))
			}
			case LoopNode(variable, stage, loopType, children) => {
				LoopNode(variable, stage, loopType,
								 children.filter(_ != toRemove).map(removeTree(_, toRemove)))
			}
			case StorageNode(stage, children) => {
				StorageNode(stage,
										children.filter(_ != toRemove).map(removeTree(_, toRemove)))
			}
			case ComputeNode(stage, children) => {
				ComputeNode(stage,
										children.filter(_ != toRemove).map(removeTree(_, toRemove)))
			}
		}
	}

	def listToOption[T](l: List[Option[T]]): Option[T] = l match {
		case Nil => None
		case x::Nil => x
		case _ => throw new InvalidSchedule("Too many matches")
	}

	private def isComputeNode(node: ScheduleNode[Func, Dim], consumer: Func) = node match {
		case ComputeNode(f, _) if f == consumer => true
		case _ => false
	}

	private def deInline(producer: Func, consumer: Func, sched: Schedule) = {
		def findParentOfCnFor(consumer: Func, sched: Schedule): Option[ScheduleNode[Func, Dim]] = sched match {
			case n@RootNode(children) => {
				if (children.exists(isComputeNode(_, consumer))) Some(n)
				else listToOption(children.map(findParentOfCnFor(consumer, _)))
			}
			case n@LoopNode(_, _, _, children) => {
				if (children.exists(isComputeNode(_, consumer))) Some(n)
				else listToOption(children.map(findParentOfCnFor(consumer, _)))
			}
			case n@ComputeNode(_, children) => {
				if (children.exists(isComputeNode(_, consumer))) Some(n)
				else listToOption(children.map(findParentOfCnFor(consumer, _)))
			}
			case n@StorageNode(_, children) => {
				if (children.exists(isComputeNode(_, consumer))) Some(n)
				else listToOption(children.map(findParentOfCnFor(consumer, _)))
			}
		}
		// Find the consumer cn in the Schedule (and its parent)
		val parent = findParentOfCnFor(consumer, sched).getOrElse(throw new InvalidSchedule("Unable to locate consumer. Is it inlined?"))
		// make the de-inlined producer a sibling of the cn
		addLeastSibling(simpleFuncTree(producer), parent, sched)
	}

	private def findTreeFor(sched: Schedule, f: Func): Option[Schedule] = {
		sched match {
			case RootNode(children) => listToOption(children.map(findTreeFor(_, f)).filter(_.isDefined))
			case l@LoopNode(_, stage, _, children) => {
				if (stage == f) Some(l)
				else listToOption(children.map(findTreeFor(_, f)).filter(_.isDefined))
			}
			case c@ComputeNode(stage, children) => {
				if (stage == f) Some(c)
				else listToOption(children.map(findTreeFor(_, f)).filter(_.isDefined))
			}
			case s@StorageNode(stage, children) => {
				if (stage == f) Some(s)
				else listToOption(children.map(findTreeFor(_, f)).filter(_.isDefined))
			}
		}
	}

	def childrenOf(node: Schedule) = node match {
		case RootNode(children) => children
		case LoopNode(_, _, _, children) => children
		case ComputeNode(_, children) => children
		case StorageNode(_, children) => children
	}

	def isolateProducer(producer: Func, sched: Schedule): Option[Schedule] = {
		// We find the first node s.t. there is a path of only producer nodes to the producer cn
		def goesToCn(node: Schedule): Boolean = {
			if (isComputeNode(node, producer)) true
			else childrenOf(node).filter(nodeFor(producer, _)).exists(goesToCn(_))
		}

		if(nodeFor(producer, sched) && goesToCn(sched)) {
			println(f"Parent: $sched")
			Some(sched)
		}
		else listToOption(childrenOf(sched).map(isolateProducer(producer, _)).filter(_.isDefined))

	}

	def computefAtX(sched: Schedule, producer: Func, consumer: Func, s: String): Schedule = {
		// If f is inlined, create a new sched tree for it.
		// Else, cut out the current f tree
		// TODO: Producer consumer checks
		val computeAtDim: Dim = if (s == "x") consumer.x
														else if (s == "y") consumer.y
														else throw new InvalidSchedule(f"Invalid computeAt var $s")
		var newSched = sched
		producer.computeAt = Some(computeAtDim)
		producer.storeAt = Some(computeAtDim)

		if (producer.inlined) {
			producer.inlined = false
			newSched = deInline(producer, consumer, sched)
		}

		println(f"Sched: $newSched")
		val producerSchedule: Schedule = isolateProducer(producer, newSched).getOrElse(throw new InvalidSchedule("Couldn't find producer"))
		newSched  = removeTree(newSched, producerSchedule)

		// Move the part of the tree for f to be a child of yLoop
		val newParent = findLoopNodeFor(newSched, computeAtDim).getOrElse(throw new InvalidSchedule("Couldn't find consumer"))

		addLeastSibling(producerSchedule, newParent, newSched)
	}
}
