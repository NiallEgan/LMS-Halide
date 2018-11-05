package sepia


sealed trait LoopType
case object Sequential extends LoopType
case object Unrolled extends LoopType
// TODO: Vectorized, parallelized

sealed trait ScheduleNode[Stage, Dimension]
case class LoopNode[Stage, Dimension](variable: Dimension, stage: Stage,
									  loopType: LoopType,
					          children: List[ScheduleNode[Stage, Dimension]])
  extends ScheduleNode[Stage, Dimension]
case class ComputeNode[Stage, Dimension](stage: Stage, children: List[ScheduleNode[Stage, Dimension]])
  extends ScheduleNode[Stage, Dimension]
case class RootNode[Stage, Dimension](children: List[ScheduleNode[Stage, Dimension]])
  extends ScheduleNode[Stage, Dimension]
case class StorageNode[Stage, Dimension](stage: Stage,
						                  children: List[ScheduleNode[Stage, Dimension]])
  extends ScheduleNode[Stage, Dimension]
