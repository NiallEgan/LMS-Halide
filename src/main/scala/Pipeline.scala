package sepia

trait Pipeline extends SimpleFuncOps {
	// The trait the user mixes in to create their program
	def prog(in: Rep[Array[Array[Int]]]): Rep[Unit]

	class FOps(f: (Rep[Int], Rep[Int]) => Rep[Int]) {
		def withDomain(dom: (Int, Int)) = toFunc(f, dom)
	}
	implicit def toFOps(f: (Rep[Int], Rep[Int]) => Rep[Int]): FOps = {
		new FOps(f)
	}

	abstract class FuncOps(f: Func) {
		def computeAt(consumer: Func, v: String): Unit
	}

	implicit def toFuncOps(f: Func): FuncOps

	def toFunc(f: (Rep[Int], Rep[Int]) => Rep[Int], dom: (Int, Int)): Func
}

trait PipelineForCompiler extends Pipeline with ScheduleOps {
	// This trait is mixed in with a specific program and it adds
	// schedule manipulations, which can then be passed to a compiler
	private var schedule: Option[Schedule] = None
	private var id = 0
	var idToFunc: Map[Int, Func] = Map()

	override def toFunc(f: (Rep[Int], Rep[Int]) => Rep[Int], dom: (Int, Int)): Func = {
		val func: Func = mkFunc(f, dom, id)
		idToFunc += id -> func
		id += 1
		schedule = Some(newSimpleSched(func))
		func
	}

	class FuncOpsImp(f: Func) extends FuncOps(f) {
		override def computeAt(consumer: Func, s: String) = {
			schedule = Some(computefAtX(sched, f, consumer, s))
		}
	}

	override implicit def toFuncOps(f: Func): FuncOps = new FuncOpsImp(f)


	def sched(): Schedule = schedule match {
		case Some(s) => s
		case None => throw new Exception("No schedule tree generated")
	}
}
