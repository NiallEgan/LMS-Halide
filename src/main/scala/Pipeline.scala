package sepia

import scala.lms.util.OverloadHack

trait Pipeline extends SimpleFuncOps {
	// The trait the user mixes in to create their program
	var finalFunc: Option[Func[UChar]] = None
	type Input = Buffer[UChar]

	def prog(in: Buffer[UChar], w: Rep[Int], h: Rep[Int]): Rep[Unit]

	def compilerProg(in: Rep[Array[UChar]], out: Rep[Array[UChar]],
										w: Rep[Int], h: Rep[Int]) = {
		prog(Buffer(w, h, in), w, h)
	}

	class FOps[T:Typ:Numeric:SepiaNum](f: (Rep[Int], Rep[Int]) => RGBVal[T]) {
		def withDomain(w: Rep[Int], h: Rep[Int]): Func[T] = withNZDomain((0, w), (0, h))
		def withNZDomain(bl: (Rep[Int], Rep[Int]), tr: (Rep[Int], Rep[Int])): Func[T] = toFunc(f, (bl, tr))
	}

	implicit def toFOps[T:Typ:Numeric:SepiaNum](f: (Rep[Int], Rep[Int]) => RGBVal[T]): FOps[T] = {
		new FOps(f)
	}

	implicit def intFToFOps(f: (Rep[Int], Rep[Int]) => Rep[Int]): FOps[Int] = {
		// This is for converting something like f(x, y) => x + y
		new FOps((x: Rep[Int], y: Rep[Int]) => RGBVal(f(x, y),
																									f(x, y), f(x, y)))
	}

	abstract class FuncOps[T:Typ:Numeric:SepiaNum](f: Func[T]) {
		def computeAt[U:Typ:Numeric:SepiaNum](consumer: Func[U], v: String): Unit
		def storeAt[U:Typ:Numeric:SepiaNum](consumer: Func[U], v: String): Unit
		def split(v: String, outer: String, inner: String, splitFactor: Int): Unit
		def fuse(v: String, outer: String, inner: String): Unit
		def reorder(v1: String, v2: String): Unit
		def storeRoot(): Unit
		def computeRoot(): Unit
		def vectorize(v: String, vectorWidth: Int): Unit

		def tile(x: String, y: String,
						 xOuter: String, yOuter: String, xInner: String,
					 	 yInner: String, xSplit: Int, ySplit: Int) = {
			split(x, xOuter, xInner, xSplit)
			split(y, yOuter, yInner, xSplit)
			reorder(yInner, xOuter)
		}
	}

	implicit def toFuncOps[T:Typ:Numeric:SepiaNum](f: Func[T]): FuncOps[T]

	/*implicit def toFuncSansDomain[T:Typ:Numeric:SepiaNum](f: (Rep[Int], Rep[Int]) => RGBVal[T]): Func[T]
	implicit def toFuncSansDomainS2I(f: (Rep[Int], Rep[Int]) => RGBVal[Short]): Func[Int] = {
		toFuncSansDomain[Int]((x: Rep[Int], y: Rep[Int]) => f(x, y))
	}

	implicit def itoFuncSansDomain(f: (Rep[Int], Rep[Int]) => Rep[Int]): Func[Int] = {
		toFuncSansDomain((x: Rep[Int], y: Rep[Int]) => RGBVal(f(x, y), f(x, y), f(x, y)))
	}*/

	def toFunc[T:Typ:Numeric:SepiaNum](f: (Rep[Int], Rep[Int]) => RGBVal[T], dom: Domain): Func[T]

	def realizeShort(f: (Rep[Int], Rep[Int]) => RGBVal[Short]): (Rep[Int], Rep[Int]) => RGBVal[Short] = f
	def realizeInt(f: (Rep[Int], Rep[Int]) => RGBVal[Int]): (Rep[Int], Rep[Int]) => RGBVal[Short] = {
		//(x: Rep[Int], y: Rep[Int]) => f(x, y).map(i2s)
		f(_, _).map(i2s)
	}
	def realizeDouble(f: (Rep[Int], Rep[Int]) => RGBVal[Double]): (Rep[Int], Rep[Int]) => RGBVal[Short] = {
		f(_, _).map(d2s)
	}
	def realizeFloat(f: (Rep[Int], Rep[Int]) => RGBVal[Float]): (Rep[Int], Rep[Int]) => RGBVal[Short] = {
		???
	}

	def final_func[T:Typ:Numeric:SepiaNum](f: (Rep[Int], Rep[Int]) => RGBVal[T]): Func[Short]
	def final_func[T:Typ:Numeric:SepiaNum](f: (Rep[Int], Rep[Int]) => Rep[T])(implicit d: DummyImplicit): Func[Short] = {
		final_func {
			(x: Rep[Int], y: Rep[Int]) => RGBVal(f(x, y), f(x, y), f(x, y))
		}
	}
	def func[T:Typ:Numeric:SepiaNum](f: (Rep[Int], Rep[Int]) => Rep[T])(implicit d: DummyImplicit): Func[T] = {
		func {
			(x: Rep[Int], y: Rep[Int]) => RGBVal(f(x, y), f(x, y), f(x, y))
		}
	}
	def func[T:Typ:Numeric:SepiaNum](f: (Rep[Int], Rep[Int]) => RGBVal[T]): Func[T]
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

	override def toFunc[T:Typ:Numeric:SepiaNum](f: (Rep[Int], Rep[Int]) => RGBVal[T], dom: Domain): Func[T] = {
		val func: Func[T] = mkFunc(f, dom, id)
		idToFunc += id -> func
		id += 1
		schedule = Some(newSimpleSched(func))
		func
	}

	override def func[T:Typ:Numeric:SepiaNum](f: (Rep[Int], Rep[Int]) => RGBVal[T]): Func[T] = {
		toFunc(f, getDomain(id))
	}

	override def final_func[T:Typ:Numeric:SepiaNum](f: (Rep[Int], Rep[Int]) => RGBVal[T]): Func[Short] = {
		val cast =
			if (typ[T] == typ[UChar]) realizeShort(f.asInstanceOf[(Rep[Int], Rep[Int]) => RGBVal[UChar]])
			else if (typ[T] == typ[Int]) realizeInt(f.asInstanceOf[(Rep[Int], Rep[Int]) => RGBVal[Int]])
			else if (typ[T] == typ[Float]) realizeFloat(f.asInstanceOf[(Rep[Int], Rep[Int]) => RGBVal[Float]])
			else realizeDouble(f.asInstanceOf[(Rep[Int], Rep[Int]) => RGBVal[Double]])

		val castFunc = func(cast)

		finalFunc = Some(castFunc)
		castFunc.inlined = false
		castFunc.finalFunc = true
		castFunc.computeRoot = true
		castFunc.storeRoot = true

		castFunc
	}

	class FuncOpsImp[T:Typ:Numeric:SepiaNum](f: Func[T]) extends FuncOps(f) {
		override def computeAt[U:Typ:Numeric:SepiaNum](consumer: Func[U], s: String) = {
			schedule = Some(computefAtX(sched, f, consumer, s))
		}

		override def storeAt[U:Typ:Numeric:SepiaNum](consumer: Func[U], s: String) = {
			schedule = Some(storefAtX(sched, f, consumer, s))
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

		override def vectorize(v: String, vectorWidth: Int): Unit = {
			split(v, v + "_outer", v + "_inner", vectorWidth)
			schedule = Some(vectorizeLoop(sched, f.vars(v + "_inner")))
		}
	}

	def assignOutArray(out: Rep[Array[UChar]]): Rep[Unit] = {
		val finalBuffer = finalFunc.getOrElse(throw new InvalidAlgorithm("No final function has been realized"))
											.buffer.getOrElse(throw new InvalidSchedule("Final func has no storage allocated"))
		bufferMemCpy(finalBuffer, Buffer[UChar](finalBuffer.width, finalBuffer.height, out))(
								 finalBuffer.typEv, finalBuffer.numEv, finalBuffer.sepEv)
		finalBuffer.free()
	}

	override implicit def toFuncOps[T:Typ:Numeric:SepiaNum](f: Func[T]): FuncOps[T] = new FuncOpsImp(f)

	def sched(): Schedule = schedule.getOrElse(throw new Exception("No schedule tree generated"))
}
