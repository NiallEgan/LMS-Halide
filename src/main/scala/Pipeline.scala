package sepia

trait Pipeline extends SimpleFuncOps {
	// The trait the user mixes in to create their program
	var finalFunc: Option[Func[_]] = None

	def prog(in: Buffer, w: Rep[Int], h: Rep[Int]): Rep[Unit]

	def compilerProg(in: Rep[Array[UShort]], out: Rep[Array[UShort]],
										w: Rep[Int], h: Rep[Int]) = {
		prog(Buffer(w, h, in), w, h)
	}

	class FOps[T:Typ:Numeric:ScalarConvertable](f: (Rep[Int], Rep[Int]) => RGBVal[T]) {
		def withDomain(w: Rep[Int], h: Rep[Int]): Func[T] = withNZDomain((0, w), (0, h))
		def withNZDomain(bl: (Rep[Int], Rep[Int]), tr: (Rep[Int], Rep[Int])): Func[T] = toFunc(f, (bl, tr))
	}

	implicit def toFOps[T:Typ:Numeric:ScalarConvertable](f: (Rep[Int], Rep[Int]) => RGBVal[T]): FOps[T] = {
		new FOps(f)
	}

	implicit def intFToFOps(f: (Rep[Int], Rep[Int]) => Rep[Int]): FOps[Int] = {
		// This is for converting something like f(x, y) => x + y
		new FOps((x: Rep[Int], y: Rep[Int]) => RGBVal(f(x, y),
																									f(x, y), f(x, y)))
	}

	abstract class FuncOps[T:Typ:Numeric:ScalarConvertable](f: Func[T]) {
		def computeAt[U:Typ:Numeric:ScalarConvertable](consumer: Func[U], v: String): Unit
		def storeAt[U:Typ:Numeric:ScalarConvertable](consumer: Func[U], v: String): Unit
		def split(v: String, outer: String, inner: String, splitFactor: Int): Unit
		def fuse(v: String, outer: String, inner: String): Unit
		def reorder(v1: String, v2: String): Unit
		def realize(): Unit
		def storeRoot(): Unit
		def computeRoot(): Unit

		def tile(x: String, y: String,
						 xOuter: String, yOuter: String, xInner: String,
					 	 yInner: String, xSplit: Int, ySplit: Int) = {
			split(x, xOuter, xInner, xSplit)
			split(y, yOuter, yInner, xSplit)
			reorder(yInner, xOuter)
		}
	}

	implicit def toFuncOps[T:Typ:Numeric:ScalarConvertable](f: Func[T]): FuncOps[T]

	implicit def toFuncSansDomain[T:Typ:Numeric:ScalarConvertable](f: (Rep[Int], Rep[Int]) => RGBVal[T]): Func[T]

	implicit def itoFuncSansDomain(f: (Rep[Int], Rep[Int]) => Rep[Int]): Func[Int] = {
		toFuncSansDomain((x: Rep[Int], y: Rep[Int]) => RGBVal(f(x, y), f(x, y), f(x, y)))
	}

	def toFunc[T:Typ:Numeric:ScalarConvertable](f: (Rep[Int], Rep[Int]) => RGBVal[T], dom: Domain): Func[T]
}

trait PipelineForCompiler extends Pipeline
													with AstOps with CompilerFuncOps
													with CompilerImageOps {
	// This trait is mixed in with a specific program and it adds
	// schedule manipulations, which can then be passed to a compiler
	private var schedule: Option[Schedule] = None
	private var id = 0
	var idToFunc: Map[Int, Func[_]] = Map()
	var w: Rep[Int]
	var h: Rep[Int]
	var callGraph: CallGraph

	private def getSingleVariableDomain(cg: CallGraph, fId: Int, v: String, wOrH: Rep[Int]): (Rep[Int], Rep[Int]) = {
		// Check if f eventually gets to in
		BoundsAnalysis.boundsForProdInCon(cg, -1, fId, v) match {
			case Some(b) => (0 - b.lb, wOrH - b.ub)  // This special case isn't really necessary...
			case None => {
				if (cg.producersOf(fId).length == 0) {
					(0, wOrH)
				} else {
					// Want to merge adjusted producer domains
					cg.producersOf(fId).foldLeft((unit(0), wOrH)){case (acc, prod) =>
						val prodDomain = idToFunc(prod).domain(v)
						val b = BoundsAnalysis.boundsForProdInCon(cg, prod, fId, v).getOrElse(throw new Exception("Not possible"))
						val adjustedDomain: (Rep[Int], Rep[Int]) = (prodDomain._1 - b.lb, prodDomain._2 - b.ub)
						(adjustedDomain._1 max acc._1, adjustedDomain._2 min acc._2)
					}
				}
			}
		}
	}
	def getDomain(fId: Int): Domain = {
		(getSingleVariableDomain(callGraph, fId, "x", w),
		 getSingleVariableDomain(callGraph, fId, "y", h))
	}

	override def toFunc[T:Typ:Numeric:ScalarConvertable](f: (Rep[Int], Rep[Int]) => RGBVal[T], dom: Domain): Func[T] = {
		val func: Func[T] = mkFunc(f, dom, id)
		idToFunc += id -> func
		id += 1
		schedule = Some(newSimpleSched(func))
		func
	}

	implicit def toFuncSansDomain[T:Typ:Numeric:ScalarConvertable](f: (Rep[Int], Rep[Int]) => RGBVal[T]): Func[T] = {
		toFunc(f, getDomain(id))
	}


	class FuncOpsImp[T:Typ:Numeric:ScalarConvertable](f: Func[T]) extends FuncOps(f) {
		override def computeAt[U:Typ:Numeric:ScalarConvertable](consumer: Func[U], s: String) = {
			schedule = Some(computefAtX(sched, f, consumer, s))
		}

		override def storeAt[U:Typ:Numeric:ScalarConvertable](consumer: Func[U], s: String) = {
			schedule = Some(storefAtX(sched, f, consumer, s))
		}

		override def realize(): Unit = {
			finalFunc = Some(f)
			f.inlined = false
			f.finalFunc = true
			f.computeRoot = true
			f.storeRoot = true
		}

		override def storeRoot(): Unit = {
			schedule = Some(storeAtRoot(sched, f))
		}

		override def computeRoot(): Unit = {
			schedule = Some(computeAtRoot(sched, f))
		}

		override def split(v: String, outer: String, inner: String, splitFactor: Int) = {
			f.split(v, outer, inner, splitFactor)
			schedule = Some(splitLoopNode(sched, f.vars(v),
											f.vars(outer), f.vars(inner)))
		}

		override def fuse(v: String, outer: String, inner: String) = {
			f.fuse(v, outer, inner)
			schedule = Some(fuseLoopNodes(sched, f.vars(v),
											f.vars(outer), f.vars(inner)))
		}

		override def reorder(v1: String, v2: String): Unit = {
			schedule = Some(swapLoopNodes(sched, f.vars(v1), f.vars(v2)))
		}

	}

	def assignOutArray(out: Rep[Array[UShort]]): Rep[Unit] = {
		val finalBuffer: Buffer = finalFunc.getOrElse(throw new InvalidAlgorithm("No final function has been realized"))
															.buffer.getOrElse(throw new InvalidSchedule("Final func has no storage allocated"))
		Buffer(finalBuffer.width, finalBuffer.height, out).memcpy(finalBuffer)
		finalBuffer.free()
	}

	override implicit def toFuncOps[T:Typ:Numeric:ScalarConvertable](f: Func[T]): FuncOps[T] = new FuncOpsImp(f)

	def sched(): Schedule = schedule.getOrElse(throw new Exception("No schedule tree generated"))
}
