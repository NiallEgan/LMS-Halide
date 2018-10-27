package sepia

trait Ast {
  this: PipelineLike =>
  sealed trait LoopType
  case object Sequential extends LoopType
  case object Unrolled extends LoopType
  // TODO: Vectorized, parallelized

  sealed trait ScheduleNode
  case class LoopNode(variable: Dim, stage: PipelineStage, 
  					  children: List[ScheduleNode],
                      loopType: LoopType) extends ScheduleNode
  case class ComputeNode(stage: PipelineStage, children: List[ScheduleNode])
    extends ScheduleNode
  case class RootNode(children: List[ScheduleNode]) extends ScheduleNode
  case class StorageNode(stage: PipelineStage,
  						 childen: List[ScheduleNode]) extends ScheduleNode
}