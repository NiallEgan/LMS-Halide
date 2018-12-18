package sepia

trait Pipeline extends SimpleFuncOps {
	// The trait the user mixes in to create their program
	var finalFunc: Option[Func] = None

	def prog(in: Buffer, w: Rep[Int], h: Rep[Int]): Rep[Unit]

	def compiler_prog(in: Rep[Array[UShort]], out: Rep[Array[UShort]],
										w: Rep[Int], h: Rep[Int]) = {
		prog(Buffer(w, h, in), w, h)
	}

	class FOps(f: (Rep[Int], Rep[Int]) => RGBVal) {
		def withDomain(w: Rep[Int], h: Rep[Int]): Func = withNZDomain((0, w), (0, h))
		def withNZDomain(bl: (Rep[Int], Rep[Int]), tr: (Rep[Int], Rep[Int])): Func = toFunc(f, (bl, tr))
	}

	implicit def toFOps(f: (Rep[Int], Rep[Int]) => RGBVal): FOps = {
		new FOps(f)
	}

	implicit def intFToFOps(f: (Rep[Int], Rep[Int]) => Rep[Int]): FOps = {
		// This is for converting something like f(x, y) => x + y
		new FOps((x: Rep[Int], y: Rep[Int]) => RGBVal(f(x, y),
																									f(x, y), f(x, y)))
	}

	abstract class FuncOps(f: Func) {
		def computeAt(consumer: Func, v: String): Unit
		def storeAt(consumer: Func, v: String): Unit
		def realize(): Unit
	}

	implicit def toFuncOps(f: Func): FuncOps

	def toFunc(f: (Rep[Int], Rep[Int]) => RGBVal, dom: Domain): Func
}

trait PipelineForCompiler extends Pipeline
													with ScheduleOps with CompilerFuncOps
													with CompilerImageOps {
	// This trait is mixed in with a specific program and it adds
	// schedule manipulations, which can then be passed to a compiler
	private var schedule: Option[Schedule] = None
	private var id = 0
	var idToFunc: Map[Int, Func] = Map()

	override def toFunc(f: (Rep[Int], Rep[Int]) => RGBVal, dom: Domain): Func = {
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

		override def storeAt(consumer: Func, s: String) = {
			schedule = Some(storefAtX(sched, f, consumer, s))
		}

		override def realize(): Unit = {
			finalFunc = Some(f)
			f.inlined = false
			f.computeRoot = true
			f.storeRoot = true
		}
	}

	def assignOutArray(out: Rep[Array[UShort]]): Rep[Unit] = {
		val finalBuffer: Buffer = finalFunc.getOrElse(throw new InvalidAlgorithm("No final function has been realized"))
															.buffer.getOrElse(throw new InvalidSchedule("Final func has no storage allocated"))
		Buffer(finalBuffer.width, finalBuffer.height, out).memcpy(finalBuffer)
	}

	override implicit def toFuncOps(f: Func): FuncOps = new FuncOpsImp(f)

	def sched(): Schedule = schedule.getOrElse(throw new Exception("No schedule tree generated"))
}
