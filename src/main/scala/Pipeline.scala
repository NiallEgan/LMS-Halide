package sepia

trait Pipeline extends SimpleFuncOps {
	// The trait the user mixes in to create their program
	val width: Rep[Int]
	val height: Rep[Int]

	var finalFunc: Option[Func] = None

	def prog(in: Buffer): Rep[Unit]

	def compiler_prog(in: Rep[Array[UShort]], out: Rep[Array[Int]]) = {
		prog(Buffer(width, height, in))
	}

	class FOps(f: (Rep[Int], Rep[Int]) => RGBVal) {
		def withDomain(dom: (Int, Int)): Func = withNZDomain((0, dom._1), (0, dom._2))
		def withNZDomain(dom: ((Int, Int), (Int, Int))): Func = toFunc(f, dom)
	}

	implicit def toFOps(f: (Rep[Int], Rep[Int]) => RGBVal): FOps = {
		new FOps(f)
	}

	implicit def intFToFOps(f: (Rep[Int], Rep[Int]) => Rep[Int]): FOps = {
		new FOps((x: Rep[Int], y: Rep[Int]) => RGBVal(f(x, y), f(x, y), f(x, y)))
	}

	abstract class FuncOps(f: Func) {
		def computeAt(consumer: Func, v: String): Unit
		def realize(): Unit = finalFunc = Some(f)
	}

	implicit def toFuncOps(f: Func): FuncOps

	def toFunc(f: (Rep[Int], Rep[Int]) => RGBVal, dom: ((Int, Int), (Int, Int))): Func
}

trait PipelineForCompiler extends Pipeline with ScheduleOps with CompilerFuncOps {
	// This trait is mixed in with a specific program and it adds
	// schedule manipulations, which can then be passed to a compiler
	private var schedule: Option[Schedule] = None
	private var id = 0
	var idToFunc: Map[Int, Func] = Map()

	override def toFunc(f: (Rep[Int], Rep[Int]) => RGBVal, dom: ((Int, Int), (Int, Int))): Func = {
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

	def assignOutArray(out: Rep[Array[Int]]): Rep[Unit] = {
		val finalBuffer: Buffer = finalFunc.getOrElse(throw new InvalidAlgorithm("No final function has been realized"))
															.buffer.getOrElse(throw new InvalidSchedule("Final func has no storage allocated"))
		// TODO: Look into more efficient assignment here
		for (y <- 0 until finalBuffer.height) {
			for (x <- 0 until finalBuffer.width) {
				array_update(out, x + finalBuffer.width * y + 2, finalBuffer(x, y).red)
				array_update(out, x + finalBuffer.width * y + 1, finalBuffer(x, y).green)
				array_update(out, x + finalBuffer.width * y, finalBuffer(x, y).blue)
			}
		}
	}

	override implicit def toFuncOps(f: Func): FuncOps = new FuncOpsImp(f)

	def sched(): Schedule = schedule match {
		case Some(s) => s
		case None => throw new Exception("No schedule tree generated")
	}
}
