package sepia

trait ScheduleOps {
	this: SimpleFuncOps =>
	type Schedule

	def newSimpleSched(stage: Func): Schedule
	def computefAtX(sched: Schedule, producer: Func,
									consumer: Func, x: String): Schedule
}
