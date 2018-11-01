package sepia

trait Pipeline extends SimpleFuncOps {
	// The trait the user mixes in to create their program
	def prog(in: Rep[Array[Array[Int]]]): Rep[Unit]

	class FuncOps(f: (Rep[Int], Rep[Int]) => Rep[Int]) {
		def withDomain(dom: (Int, Int)) = toFunc(f, dom)
	}
	implicit def toFuncOps(f: (Rep[Int], Rep[Int]) => Rep[Int]): FuncOps = {
		new FuncOps(f)
	}

	def toFunc(f: (Rep[Int], Rep[Int]) => Rep[Int], dom: (Int, Int)): Func
}

trait PipelineWithSchedManipulations extends Pipeline with ScheduleOps {
	// This trait is mixed in with a specific program and it adds
	// schedule manipulations, which can then be passed to a compiler
	private var schedule: Option[Schedule] = None


	override def toFunc(f: (Rep[Int], Rep[Int]) => Rep[Int], dom: (Int, Int)): Func = {
		val func: Func = mkFunc(f, dom)
		schedule = Some(newSimpleSched(func))
		func
	}

	def sched(): Schedule = schedule match {
		case Some(s) => s
		case None => throw new Exception("No schedule tree generated")
	}
}
