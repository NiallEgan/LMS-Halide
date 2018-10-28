package sepia

trait Pipeline extends PipelineLike with ScheduleOps {
	private var schedule: Option[Schedule] = None

	class Func(f: (Rep[Int], Rep[Int]) => Rep[Int],
			   dom: (Int, Int)) extends PipelineStage(f, dom) {
		val x: Dim = new Dim(dom._1, "x")
		val y: Dim = new Dim(dom._2, "y")

		private var inlined = true

		schedule = Some(newSimpleSched(this))

		var buffer: Option[Rep[Array[Array[Int]]]] = None

		def apply(x: Rep[Int], y: Rep[Int]) = {
			 if (inlined) f(x, y)
      		 else buffer match {
      		 	case Some(b) => b(y, x)
      		 	case None => throw new InvalidSchedule("No buffer allocated at application time")
      		 }
      	}

      	def compute() = f(x.v, y.v)

      	def storeInBuffer(v: Rep[Int]) = buffer match {
      		case Some(b) => b(y.v, x.v) = v
      		case None => throw new InvalidSchedule("No buffer allocated at storage time")
      	}
	}

	class FOps(f: (Rep[Int], Rep[Int]) => Rep[Int]) {
		def withDomain(dom: (Int, Int)): Func = {
			new Func(f, dom)
		}
	}

	implicit def fToFOps(f: (Rep[Int], Rep[Int]) => Rep[Int]): FOps = {
		new FOps(f)
	}

	def prog(): Rep[Unit]

	def sched(): Schedule = schedule match {
		case Some(s) => s 
		case None => throw new Exception("No schedule tree generated")
	}
}