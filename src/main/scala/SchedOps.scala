package sepia

trait ScheduleOps {
	this: PipelineLike =>
	type Schedule 

	def newSimpleSched(stage: PipelineStage): Schedule
}