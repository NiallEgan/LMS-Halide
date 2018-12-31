package sepia


sealed trait LoopType
case object Sequential extends LoopType
case object Unrolled extends LoopType
// TODO: Vectorized, parallelized

sealed trait ScheduleNode[Stage, Dimension] {
	def findAndTransform(p: ScheduleNode[Stage, Dimension] => Boolean,
											 f: ScheduleNode[Stage, Dimension] => ScheduleNode[Stage, Dimension]): ScheduleNode[Stage, Dimension]
	def findAndTransform(n: ScheduleNode[Stage, Dimension],
											 f: ScheduleNode[Stage, Dimension] => ScheduleNode[Stage, Dimension]): ScheduleNode[Stage, Dimension] = {
			findAndTransform(n == _, f)
		}

	def mapChildren(f: ScheduleNode[Stage, Dimension] =>
										 ScheduleNode[Stage, Dimension]): ScheduleNode[Stage, Dimension]

	def withChildren(l: List[ScheduleNode[Stage, Dimension]]): ScheduleNode[Stage, Dimension]
	def withChildren(l: ScheduleNode[Stage, Dimension]): ScheduleNode[Stage, Dimension] = withChildren(List(l))

	def getChildren(): List[ScheduleNode[Stage, Dimension]]

	def exists(f: ScheduleNode[Stage, Dimension] => Boolean): Boolean

	def belongsTo(s: Stage): Boolean

}

case class LoopNode[Stage, Dimension](variable: Dimension, stage: Stage,
									  loopType: LoopType,
					          children: List[ScheduleNode[Stage, Dimension]])
  extends ScheduleNode[Stage, Dimension] {
		override def findAndTransform(p: ScheduleNode[Stage, Dimension] => Boolean,
																	f: ScheduleNode[Stage, Dimension] => ScheduleNode[Stage, Dimension]): ScheduleNode[Stage, Dimension] = {
			if (p(this)) f(this)
			else this.mapChildren(_.findAndTransform(p, f))
		}

		override def mapChildren(f: ScheduleNode[Stage, Dimension] => ScheduleNode[Stage, Dimension]) = {
			LoopNode(variable, stage, loopType, children.map(f))
		}

		override def withChildren(l: List[ScheduleNode[Stage, Dimension]]) =
			LoopNode(variable, stage, loopType, l)

		override def getChildren() = children

		override def exists(f: ScheduleNode[Stage, Dimension] => Boolean) = {
			f(this) || children.exists(_.exists(f))
		}

		override def belongsTo(s: Stage): Boolean = s == stage

	}

case class ComputeNode[Stage, Dimension](stage: Stage, children: List[ScheduleNode[Stage, Dimension]])
  extends ScheduleNode[Stage, Dimension] {
		override def findAndTransform(p: ScheduleNode[Stage, Dimension] => Boolean,
																	f: ScheduleNode[Stage, Dimension] => ScheduleNode[Stage, Dimension]): ScheduleNode[Stage, Dimension] = {
			if (p(this)) f(this)
			else this.mapChildren(_.findAndTransform(p, f))
		}

		override def mapChildren(f: ScheduleNode[Stage, Dimension] => ScheduleNode[Stage, Dimension]) = {
			ComputeNode(stage, children.map(f))
		}

		override def withChildren(l: List[ScheduleNode[Stage, Dimension]]) =
			ComputeNode(stage, l)

		override def getChildren() = children

		override def exists(f: ScheduleNode[Stage, Dimension] => Boolean) = {
			f(this) || children.exists(_.exists(f))
		}

		override def belongsTo(s: Stage): Boolean = s == stage
}

case class RootNode[Stage, Dimension](children: List[ScheduleNode[Stage, Dimension]])
  extends ScheduleNode[Stage, Dimension] {
		override def findAndTransform(p: ScheduleNode[Stage, Dimension] => Boolean,
																	f: ScheduleNode[Stage, Dimension] => ScheduleNode[Stage, Dimension]): ScheduleNode[Stage, Dimension] = {
			if (p(this)) f(this)
			else this.mapChildren(_.findAndTransform(p, f))
		}

		override def mapChildren(f: ScheduleNode[Stage, Dimension] => ScheduleNode[Stage, Dimension]) = {
			RootNode(children.map(f))
		}

		override def withChildren(l: List[ScheduleNode[Stage, Dimension]]) =
			RootNode(l)

		override def getChildren() = children

		override def exists(f: ScheduleNode[Stage, Dimension] => Boolean) = {
			f(this) || children.exists(_.exists(f))
		}

		override def belongsTo(s: Stage): Boolean = false
}

case class StorageNode[Stage, Dimension](stage: Stage,
						                  					 children: List[ScheduleNode[Stage, Dimension]])
  extends ScheduleNode[Stage, Dimension] {
		override def findAndTransform(p: ScheduleNode[Stage, Dimension] => Boolean,
																	f: ScheduleNode[Stage, Dimension] => ScheduleNode[Stage, Dimension]): ScheduleNode[Stage, Dimension] = {
			if (p(this)) f(this)
			else this.mapChildren(_.findAndTransform(p, f))
		}

		override def mapChildren(f: ScheduleNode[Stage, Dimension] => ScheduleNode[Stage, Dimension]) = {
			StorageNode(stage, children.map(f))
		}

		override def withChildren(l: List[ScheduleNode[Stage, Dimension]]) =
			StorageNode(stage, l)

		override def getChildren() = children

		override def exists(f: ScheduleNode[Stage, Dimension] => Boolean) = {
			f(this) || children.exists(_.exists(f))
		}

		override def belongsTo(s: Stage): Boolean = s == stage
	}
