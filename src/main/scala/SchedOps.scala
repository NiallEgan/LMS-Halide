package sepia

trait ScheduleOps {
	this: SimpleFuncOps =>
	type Schedule

	def newSimpleSched(stage: Func): Schedule
	def computefAtX(f: Func, x: Dim): Schedule
}
