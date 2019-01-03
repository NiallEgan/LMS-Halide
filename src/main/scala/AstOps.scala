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

	private def isStorageNodeFor(node: ScheduleNode[Func, Dim], f: Func) =
		node match {
			case StorageNode(fun, _) if f == fun => true
			case _ => false
		}

	private def deInline(producer: Func, consumer: Func, sched: Schedule) = {
		def findParentOfCnFor(consumer: Func, sched: Schedule): Option[ScheduleNode[Func, Dim]] =
			if (sched.getChildren.exists(isComputeNode(_, consumer))) Some(sched)
			else listToOption(sched.getChildren.map(findParentOfCnFor(consumer, _)))

		// Find the consumer cn in the Schedule (and its parent)
		val parent = findParentOfCnFor(consumer, sched).getOrElse(throw new InvalidSchedule("Unable to locate consumer. Is it inlined?"))
		assert(parent.belongsTo(consumer))
		val cn: ComputeNode[Func, Dim] = ComputeNode[Func, Dim](producer, List())
		val xLoop: LoopNode[Func, Dim] = LoopNode[Func, Dim](producer.x, producer,
											Sequential, List(cn))
		val yLoop: LoopNode[Func, Dim] = LoopNode[Func, Dim](producer.y, producer,
											Sequential, List(xLoop))
		sched.findAndTransform(parent, (n: Schedule) =>
			n.withChildren(StorageNode(producer, yLoop::n.getChildren))
		)
	}

	def isolateProducer(producer: Func, sched: Schedule): Option[Schedule] = {
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

	def removeProducerSchedule(deInlinedSched: Schedule, producer: Func): (Schedule, Schedule) = {
		val producerSchedule: Schedule = isolateProducer(producer, deInlinedSched).getOrElse(throw new InvalidSchedule(f"Couldn't find producer in tree $deInlinedSched"))

		val notMovingChildren = producerSchedule.getChildren.filter(!_.belongsTo(producer))
		val newProducerSchedule = producerSchedule.withChildren(
			producerSchedule.getChildren.filter(_.belongsTo(producer)))
		val schedLessProducer = deInlinedSched.findAndTransform(
			(n: Schedule) => n.getChildren.exists(_ == producerSchedule),
			(n: Schedule) => n.withChildren(notMovingChildren)
		)

		(schedLessProducer, newProducerSchedule)
	}

	def insertComputeAtNode(schedLessProducer: Schedule,
													producer: Func,
													newProducerSchedule: ScheduleNode[Func, Dim],
													newParent: ScheduleNode[Func, Dim]): Schedule = {
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

	def computeAtRoot(sched: Schedule, producer: Func): Schedule = {
		producer.computeRoot = true
		producer.storeRoot = true

		val deInlinedSched = if (producer.inlined) {
			producer.inlined = false
			val cn: ComputeNode[Func, Dim] = ComputeNode[Func, Dim](producer, List())
			val xLoop: LoopNode[Func, Dim] = LoopNode[Func, Dim](producer.x, producer,
												Sequential, List(cn))
			val yLoop: LoopNode[Func, Dim] = LoopNode[Func, Dim](producer.y, producer,
												Sequential, List(xLoop))
			sched.withChildren(StorageNode(producer, yLoop::sched.getChildren))
		} else sched

		val (schedLessProducer, newProducerSchedule) = removeProducerSchedule(deInlinedSched, producer)
		val newParent = schedLessProducer
		insertComputeAtNode(schedLessProducer, producer, newProducerSchedule, newParent)
	}

	def computefAtX(sched: Schedule, producer: Func, consumer: Func, s: String): Schedule = {
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

	def cutOutNode(sched: Schedule, node: ScheduleNode[Func, Dim]): Schedule = {
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

	def spliceInNewNode(nodeToInsert: ScheduleNode[Func, Dim],
											newParent: ScheduleNode[Func, Dim],
											oldParent: ScheduleNode[Func, Dim],
											sched: Schedule) = {
		sched.findAndTransform(newParent,
			(n: ScheduleNode[Func, Dim]) => n.mapChildren(c => if (c == oldParent) nodeToInsert.withChildren(oldParent) else c)
		)
	}

	def storefAtX(sched: Schedule, producer: Func, consumer: Func, s: String): Schedule = {
		val storeAtDim: Dim = if (s == "x") consumer.x
														else if (s == "y") consumer.y
														else throw new InvalidSchedule(f"Invalid computeAt var $s")

		// If no computeAt, storeAt is useless ORDER!
		val newParent = findLoopNodeFor(sched, storeAtDim).getOrElse(throw new InvalidSchedule("Couldn't find consumer"))
		producer.storeAt = Some(storeAtDim)
		storeAtNode(sched, producer, newParent)
	}

	def storeAtRoot(sched: Schedule, producer: Func): Schedule = {
		val newParent = sched
		producer.storeRoot = true
		storeAtNode(sched, producer, newParent)
	}

	def storeAtNode(sched: Schedule, producer: Func, newParent: Schedule) = {
		def findStoreNode(sched: Schedule, producer: Func): Option[Schedule] = {
			def r(children: List[Schedule]) = listToOption(children.map(findStoreNode(_, producer)).filter(_.isDefined))
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

		val storageNode = findStoreNode(sched, producer).getOrElse(StorageNode(producer, List()))
		val oldChildren = newParent.getChildren
		val oldParent = oldChildren.filter(_.exists(n => n == storageNode))(0)
		spliceInNewNode(StorageNode(producer, List()),
										cutOutNode(newParent, storageNode),
										cutOutNode(oldParent, storageNode),
										cutOutNode(sched, storageNode))
	}
}
