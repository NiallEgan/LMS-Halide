package sepia

trait ScheduleOps {
	this: SimpleFuncOps =>
	type Schedule

	def newSimpleSched(stage: Func): Schedule
	def computefAtX(sched: Schedule, producer: Func,
									consumer: Func, x: String): Schedule
	def storefAtX(sched: Schedule, producer: Func,
									consumer: Func, x: String): Schedule
	def storeAtRoot(sched: Schedule, producer: Func): Schedule
	def computeAtRoot(sched: Schedule, producer: Func): Schedule

}
