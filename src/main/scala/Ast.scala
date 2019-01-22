package sepia


sealed trait LoopType
case object Sequential extends LoopType
case object Unrolled extends LoopType
// TODO: Vectorized, parallelized

trait Ast {
	type Stage[_]
	type Dimension

	sealed trait ScheduleNode {
		def findAndTransform(p: ScheduleNode => Boolean,
												  f: ScheduleNode => ScheduleNode): ScheduleNode
		def findAndTransform(n: ScheduleNode,
												    f: ScheduleNode => ScheduleNode): ScheduleNode = {
				findAndTransform(n == _, f)
			}

		def mapChildren(f: ScheduleNode => ScheduleNode): ScheduleNode

		def withChildren(l: List[ScheduleNode]): ScheduleNode
		def withChildren(l: ScheduleNode): ScheduleNode = withChildren(List(l))

		def getChildren(): List[ScheduleNode]

		def exists(f: ScheduleNode => Boolean): Boolean

		def belongsTo[T](s: Stage[T]): Boolean

	}

	case class LoopNode[T](variable: Dimension, stage: Stage[T],
										     loopType: LoopType,
							           children: List[ScheduleNode])
	  extends ScheduleNode {
			override def findAndTransform(p: ScheduleNode => Boolean,
																		f: ScheduleNode => ScheduleNode): ScheduleNode = {
				if (p(this)) f(this)
				else this.mapChildren(_.findAndTransform(p, f))
			}

			override def mapChildren(f: ScheduleNode => ScheduleNode) = {
				LoopNode(variable, stage, loopType, children.map(f))
			}

			override def withChildren(l: List[ScheduleNode]) =
				LoopNode(variable, stage, loopType, l)

			override def getChildren() = children

			override def exists(f: ScheduleNode => Boolean) = {
				f(this) || children.exists(_.exists(f))
			}

			override def belongsTo[T](s: Stage[T]): Boolean = s == stage

		}

	case class ComputeNode[T](stage: Stage[T], children: List[ScheduleNode])
	  extends ScheduleNode {
			override def findAndTransform(p: ScheduleNode => Boolean,
																		f: ScheduleNode => ScheduleNode): ScheduleNode = {
				if (p(this)) f(this)
				else this.mapChildren(_.findAndTransform(p, f))
			}

			override def mapChildren(f: ScheduleNode => ScheduleNode) = {
				ComputeNode(stage, children.map(f))
			}

			override def withChildren(l: List[ScheduleNode]) =
				ComputeNode(stage, l)

			override def getChildren() = children

			override def exists(f: ScheduleNode => Boolean) = {
				f(this) || children.exists(_.exists(f))
			}

			override def belongsTo[T](s: Stage[T]): Boolean = s == stage
	}

	case class RootNode(children: List[ScheduleNode])
	  extends ScheduleNode {
			override def findAndTransform(p: ScheduleNode => Boolean,
																		f: ScheduleNode => ScheduleNode): ScheduleNode = {
				if (p(this)) f(this)
				else this.mapChildren(_.findAndTransform(p, f))
			}

			override def mapChildren(f: ScheduleNode => ScheduleNode) = {
				RootNode(children.map(f))
			}

			override def withChildren(l: List[ScheduleNode]) =
				RootNode(l)

			override def getChildren() = children

			override def exists(f: ScheduleNode => Boolean) = {
				f(this) || children.exists(_.exists(f))
			}

			override def belongsTo[T](s: Stage[T]): Boolean = false
	}

	case class StorageNode[T](stage: Stage[T], children: List[ScheduleNode])
	  extends ScheduleNode {
			override def findAndTransform(p: ScheduleNode => Boolean,
																		f: ScheduleNode => ScheduleNode): ScheduleNode = {
				if (p(this)) f(this)
				else this.mapChildren(_.findAndTransform(p, f))
			}

			override def mapChildren(f: ScheduleNode => ScheduleNode) = {
				StorageNode(stage, children.map(f))
			}

			override def withChildren(l: List[ScheduleNode]) =
				StorageNode(stage, l)

			override def getChildren() = children

			override def exists(f: ScheduleNode => Boolean) = {
				f(this) || children.exists(_.exists(f))
			}

			override def belongsTo[T](s: Stage[T]): Boolean = s == stage
		}
}
