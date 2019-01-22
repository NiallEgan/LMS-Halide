package sepia

trait AstOps extends Ast {
	this: CompilerFuncOps =>
	type Stage[T] = Func[T]
	type Dimension = Dim

	type Schedule = ScheduleNode
	type N = ScheduleNode

	private def simpleFuncTree[T:Typ:Numeric:SepiaNum](f: Func[T]): ScheduleNode = {
		val cn: ComputeNode[T] = ComputeNode[T](f, List())
		val xLoop: LoopNode[T] = LoopNode[T](f.x, f,
											Sequential, List(cn))
		val yLoop: LoopNode[T] = LoopNode[T](f.y, f, Sequential, List(xLoop))
	  StorageNode[T](f, List(yLoop))
	}

	def newSimpleSched[T:Typ:Numeric:SepiaNum](stage: Func[T]): Schedule = {
		// A simple function for now that just returns
		// a new tree for stage. This assumes that everything is inlined
		// etc

    new RootNode(List(simpleFuncTree(stage)))
	}

	private def addChildren(t: ScheduleNode,
													children: List[ScheduleNode]) = t match {
			case RootNode(otherChildren) => RootNode(otherChildren ++ children)
			case ComputeNode(s, otherChildren) => ComputeNode(s, otherChildren ++ children)
			case StorageNode(s, otherChildren) => StorageNode(s, otherChildren ++ children)
			case LoopNode(v, s, loopType,  otherChildren) => LoopNode(v, s, loopType, otherChildren ++ children)

	}

	private def findLoopNodeFor(sched: N, computeAtVar: Dim): Option[N] = sched match {
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

	private def addLeastSibling(child: ScheduleNode,
															parent: ScheduleNode,
														  sched: ScheduleNode): ScheduleNode = {
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

	private def nodeFor(f: Func[_], sched: ScheduleNode) = {
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

	def listToOption(l: List[Option[ScheduleNode]]): Option[ScheduleNode] = l match {
		case Nil => None
		case x::Nil => x
		case _ => throw new InvalidSchedule("Too many matches")
	}

	private def isComputeNode(node: ScheduleNode, consumer: Func[_]) = node match {
		case ComputeNode(f, _) if f == consumer => true
		case _ => false
	}

	private def isStorageNodeFor(node: ScheduleNode, f: Func[_]) =
		node match {
			case StorageNode(fun, _) if f == fun => true
			case _ => false
		}

	private def deInline[T:Typ:Numeric:SepiaNum, U:Typ:Numeric:SepiaNum]
											(producer: Func[T], consumer: Func[U], sched: Schedule) = {
		def findParentOfCnFor(consumer: Func[U], sched: Schedule): Option[ScheduleNode] =
			if (sched.getChildren.exists(isComputeNode(_, consumer))) Some(sched)
			else listToOption(sched.getChildren.map(findParentOfCnFor(consumer, _)))

		// Find the consumer cn in the Schedule (and its parent)
		val parent = findParentOfCnFor(consumer, sched).getOrElse(throw new InvalidSchedule("Unable to locate consumer. Is it inlined?"))
		assert(parent.belongsTo(consumer))
		val cn: ComputeNode[T] = ComputeNode(producer, List())
		val xLoop: LoopNode[T] = LoopNode(producer.x, producer,
											Sequential, List(cn))
		val yLoop: LoopNode[T] = LoopNode(producer.y, producer,
											Sequential, List(xLoop))
		sched.findAndTransform(parent, (n: Schedule) =>
			n.withChildren(StorageNode(producer, yLoop::n.getChildren))
		)
	}

	def isolateProducer[T:Typ:Numeric:SepiaNum](producer: Func[T], sched: Schedule): Option[Schedule] = {
		// We find the first node s.t. there is a path of only producer nodes to the producer cn
		def goesToCn(node: Schedule): Boolean = {
			if (isComputeNode(node, producer)) true
			else node.getChildren.filter(nodeFor(producer, _)).exists(goesToCn(_))
		}

		if(nodeFor(producer, sched) && goesToCn(sched)) {
			Some(sched)
		}
		else listToOption(sched.getChildren.map(isolateProducer(producer, _)).filter(_.isDefined))

	}

	def removeProducerSchedule[T:Typ:Numeric:SepiaNum](deInlinedSched: N, producer: Func[T]): (Schedule, Schedule) = {
		val producerSchedule: N = isolateProducer(producer, deInlinedSched).getOrElse(throw new InvalidSchedule(f"Couldn't find producer in tree $deInlinedSched"))

		val notMovingChildren = producerSchedule.getChildren.filter(!_.belongsTo(producer))
		val newProducerSchedule = producerSchedule.withChildren(
			producerSchedule.getChildren.filter(_.belongsTo(producer)))
		val schedLessProducer = deInlinedSched.findAndTransform(
			(n: N) => n.getChildren.exists(_ == producerSchedule),
			(n: N) => n.withChildren(notMovingChildren)
		)

		(schedLessProducer, newProducerSchedule)
	}

	def insertComputeAtNode[T:Typ:Numeric:SepiaNum](schedLessProducer: Schedule,
													producer: Func[T],
													newProducerSchedule: ScheduleNode,
													newParent: ScheduleNode): Schedule = {
		if (isStorageNodeFor(newProducerSchedule, producer)) {
			schedLessProducer.findAndTransform(newParent,
				(n: Schedule) => newParent.withChildren(
					newProducerSchedule.withChildren(
						newProducerSchedule.getChildren ++ newParent.getChildren
					)
				)
			)
		}
		else addLeastSibling(newProducerSchedule, newParent, schedLessProducer)
	}

	def computeAtRoot[T:Typ:Numeric:SepiaNum](sched: Schedule, producer: Func[T]): Schedule = {
		producer.computeRoot = true
		producer.storeRoot = true

		val deInlinedSched = if (producer.inlined) {
			producer.inlined = false
			val cn: ComputeNode[T] = ComputeNode(producer, List())
			val xLoop: LoopNode[T] = LoopNode(producer.x, producer, Sequential, List(cn))
			val yLoop: LoopNode[T] = LoopNode(producer.y, producer, Sequential, List(xLoop))
			sched.withChildren(StorageNode(producer, yLoop::sched.getChildren))
		} else sched

		val (schedLessProducer, newProducerSchedule) = removeProducerSchedule(deInlinedSched, producer)
		val newParent = schedLessProducer
		insertComputeAtNode(schedLessProducer, producer, newProducerSchedule, newParent)
	}

	def computefAtX[T:Typ:Numeric:SepiaNum, U:Typ:Numeric:SepiaNum](sched: Schedule, producer: Func[T], consumer: Func[U], s: String): Schedule = {
		// If f is inlined, create a new sched tree for it.
		// Else, cut out the current f tree
		// TODO: Producer consumer checks
		val computeAtDim: Dim = if (s == "x") consumer.x
														else if (s == "y") consumer.y
														else throw new InvalidSchedule(f"Invalid computeAt var $s")
		producer.computeAt = Some(computeAtDim)
		producer.storeAt = Some(computeAtDim)

		val deInlinedSched = if (producer.inlined) {
			producer.inlined = false
			deInline(producer, consumer, sched)
		} else sched

		val (schedLessProducer, newProducerSchedule) = removeProducerSchedule(deInlinedSched, producer)

		// Move the part of the tree for f to be a child of yLoop
		val newParent = findLoopNodeFor(schedLessProducer, computeAtDim).getOrElse(throw new
			InvalidSchedule("Couldn't find consumer"))
		insertComputeAtNode(schedLessProducer, producer, newProducerSchedule, newParent)
	}

	def cutOutNode(sched: N, node: N): N = {
		sched match {
			case RootNode(children) => {
				if(children.exists(_ == node)) {
					// Make the children of node the children of its parent
					RootNode(children.flatMap(x => if (x == node) node.getChildren else List(x)))
				} else RootNode(children.map(cutOutNode(_, node)))
			}
			case ComputeNode(f, children) => {
				if(children.exists(_ == node)) {
					// Make the children of node the children of its parent
					ComputeNode(f, children.flatMap(x => if (x == node) node.getChildren else List(x)))
				} else ComputeNode(f, children.map(cutOutNode(_, node)))
			}
			case StorageNode(f, children) => {
				if(children.exists(_ == node)) {
					// Make the children of node the children of its parent
					StorageNode(f, children.flatMap(x => if (x == node) node.getChildren else List(x)))
				} else StorageNode(f, children.map(cutOutNode(_, node)))
			}
			case LoopNode(variable, stage, loopType, children) => {
				if(children.exists(_ == node)) {
					// Make the children of node the children of its parent
					LoopNode(variable, stage, loopType, children.flatMap(x => if (x == node) node.getChildren else List(x)))
				} else LoopNode(variable, stage, loopType, children.map(cutOutNode(_, node)))
			}
		}
	}

	def spliceInNewNode(nodeToInsert: ScheduleNode,
											newParent: ScheduleNode,
											oldParent: ScheduleNode,
											sched: ScheduleNode) = {
		sched.findAndTransform(newParent,
			(n: ScheduleNode) => n.mapChildren(c => if (c == oldParent) nodeToInsert.withChildren(oldParent) else c)
		)
	}

	def storefAtX[T:Typ:Numeric:SepiaNum, U:Typ:Numeric:SepiaNum](sched: N,
							  producer: Func[T], consumer: Func[U], s: String): N = {
		val storeAtDim: Dim = if (s == "x") consumer.x
														else if (s == "y") consumer.y
														else throw new InvalidSchedule(f"Invalid computeAt var $s")

		// If no computeAt, storeAt is useless ORDER!
		val newParent = findLoopNodeFor(sched, storeAtDim).getOrElse(throw new InvalidSchedule("Couldn't find consumer"))
		producer.storeAt = Some(storeAtDim)
		storeAtNode(sched, producer, newParent)
	}

	def storeAtRoot[T:Typ:Numeric:SepiaNum](sched: N, producer: Func[T]): N = {
		val newParent = sched
		producer.storeRoot = true
		storeAtNode(sched, producer, newParent)
	}

	def storeAtNode[T:Typ:Numeric:SepiaNum](sched: ScheduleNode, producer: Func[T], newParent: ScheduleNode) = {
		def findStoreNode(sched: ScheduleNode, producer: Func[T]): Option[ScheduleNode] = {
			def r(children: List[ScheduleNode]) = listToOption(children.map(findStoreNode(_, producer)).filter(_.isDefined))
			sched match {
				case RootNode(children) => r(children)
				case ComputeNode(_, children) => r(children)
				case LoopNode(_, _, _, children) => r(children)
				case n@StorageNode(f, children) => {
					if (f != producer) r(children)
					else Some(n)
				}
			}
		}

		val storageNode: ScheduleNode = findStoreNode(sched, producer).getOrElse(StorageNode(producer, List()))
		val oldChildren = newParent.getChildren
		val oldParent = oldChildren.filter(_.exists(n => n == storageNode))(0)
		spliceInNewNode(StorageNode[T](producer, List()),
										cutOutNode(newParent, storageNode),
										cutOutNode(oldParent, storageNode),
										cutOutNode(sched, storageNode))
	}

	def splitLoopNode(sched: Schedule, currentDim: Dim, outer: Dim, inner: Dim) = {
		val s = sched.findAndTransform(
			_ match {
				case LoopNode(d, _, _, _) if d.name == currentDim.name && d.f == currentDim.f => {
					true
				}
				case _ => false
			},
			_ match {
				case LoopNode(_, f, _, children) => LoopNode(outer, f, Sequential, List(LoopNode(inner, f, Sequential, children)))
			}
		)
		s
	}

	def swapLoopNodes(sched: Schedule, v1: Dim, v2: Dim): Schedule = {
		sched match {
			case LoopNode(variable, stage, loopType, children)
				if variable == v1 => {
					LoopNode(v2, stage, loopType, children.map(swapLoopNodes(_, v1, v2)))
				}
			case LoopNode(variable, stage, loopType, children)
				if variable == v2 => {
					LoopNode(v1, stage, loopType, children.map(swapLoopNodes(_, v1, v2)))
				}
			case _ => sched.mapChildren(swapLoopNodes(_, v1, v2))
		}
	}

	def fuseLoopNodes(sched: Schedule, newDim: Dim, outer: Dim, inner: Dim) = {
		def isLoopNodeFor(dim: Dim)(n: Schedule) = n match {
			case LoopNode(d, _, _, _) if d.name == dim.name && d.f == dim.f => {
				true
			}
			case _ => false
		}
		sched.findAndTransform(isLoopNodeFor(outer)(_),
			_ match {
				case LoopNode(o, stage, loopType, children) => {
					println(f"chilun: $children")
					if (!children.exists(isLoopNodeFor(inner)(_))) throw new InvalidSchedule("Can only fuse loops in a parent-child relationship")
					else {
						LoopNode(newDim, stage, loopType, children.flatMap(x =>
							if (isLoopNodeFor(inner)(x)) x.getChildren
							else List(x)
						))
					}
				}
			}
		)
	}
}
