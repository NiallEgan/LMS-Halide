package sepia

trait Pipeline extends SimpleFuncOps with ScheduleOps {
	private var schedule: Option[Schedule] = None

	def prog(): Rep[Unit]

	implicit def toFunc(f: (Rep[Int], Rep[Int]) => Rep[Int]): Func = {
		val func: Func = mkFunc(f)
		schedule = Some(newSimpleSched(func))
		func
	}

	def sched(): Schedule = schedule match {
		case Some(s) => s
		case None => throw new Exception("No schedule tree generated")
	}
}
