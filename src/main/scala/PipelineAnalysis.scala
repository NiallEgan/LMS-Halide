package sepia

import scala.lms.internal._
import scala.collection.mutable.{Map => MMap}
import scala.reflect.SourceContext


trait PipelineForAnalysis extends DslExp with SymbolicOpsExp
													with SymbolicFuncOpsExp with Pipeline
													 {
	// Possible refactoring: Change interperation to generate the graph directly!

	// Before we pass the program to the staged interpreter, we must
  // first do some analysis on

	override def array_update[T:Typ](a: Rep[Array[T]], n: Rep[Int], v: Rep[T])(implicit pos: SourceContext): Rep[Unit] = {
		()
	}

	override def array_obj_new[T:Typ](s: Rep[Int]): Rep[Array[T]] = {
		toAtom(ArrayNew(s))
	}

	val funcNames: MMap[Int, String] = MMap()

	def treeTraversal[T](f: List[T] => T, m: Exp[_] => T,
                       node: Def[_]): T = node match {
      case ObjDoubleParseDouble(x)      => f(List(x).map(m))
      case ObjDoublePositiveInfinity()  => f(List().map(m))
      case ObjDoubleNegativeInfinity()  => f(List().map(m))
      case ObjDoubleMinValue()          => f(List().map(m))
      case ObjDoubleMaxValue()          => f(List().map(m))
      case DoubleFloatValue(x)          => f(List(x).map(m))
      case DoubleToInt(x)               => f(List(x).map(m))
      case DoubleToFloat(x)             => f(List(x).map(m))
      case DoublePlus(x,y)              => f(List(x, y).map(m))
      case DoubleMinus(x,y)             => f(List(x, y).map(m))
      case DoubleTimes(x,y)             => f(List(x, y).map(m))
      case DoubleDivide(x,y)            => f(List(x, y).map(m))
      case ObjFloatParseFloat(x)        => f(List(x).map(m))
      case FloatToInt(x)                => f(List(x).map(m))
      case FloatToDouble(x)             => f(List(x).map(m))
      case FloatPlus(x,y)               => f(List(x, y).map(m))
      case FloatMinus(x,y)              => f(List(x, y).map(m))
      case FloatTimes(x,y)              => f(List(x, y).map(m))
      case FloatDivide(x,y)             => f(List(x, y).map(m))
      case ObjIntegerParseInt(x)        => f(List(x).map(m))
      case ObjIntMaxValue()             => f(List().map(m))
      case ObjIntMinValue()             => f(List().map(m))
      case IntDoubleValue(x)            => f(List(x).map(m))
      case IntFloatValue(x)             => f(List(x).map(m))
      case IntBitwiseNot(x)             => f(List(x).map(m))
      case IntPlus(x,y)                 => f(List(x, y).map(m))
      case IntMinus(x,y)                => f(List(x, y).map(m))
      case IntTimes(x,y)                => f(List(x, y).map(m))
      case IntDivide(x,y)               => f(List(x, y).map(m))
      case IntMod(x,y)                  => f(List(x, y).map(m))
      case IntBinaryOr(x,y)             => f(List(x, y).map(m))
      case IntBinaryAnd(x,y)            => f(List(x, y).map(m))
      case IntBinaryXor(x,y)            => f(List(x, y).map(m))
      case IntToLong(x)                 => f(List(x).map(m))
      case IntToFloat(x)                => f(List(x).map(m))
      case IntToDouble(x)               => f(List(x).map(m))
      case IntShiftLeft(x,y)            => f(List(x, y).map(m))
      case IntShiftRightLogical(x,y)    => f(List(x, y).map(m))
      case IntShiftRightArith(x,y)      => f(List(x, y).map(m))
      case ObjLongParseLong(x)          => f(List(x).map(m))
      case LongMod(x,y)                 => f(List(x, y).map(m))
      case LongShiftLeft(x,y)           => f(List(x, y).map(m))
      case LongBinaryOr(x,y)            => f(List(x, y).map(m))
      case LongBinaryAnd(x,y)           => f(List(x, y).map(m))
      case LongToInt(x)                 => f(List(x).map(m))
      case LongShiftRightUnsigned(x,y)  => f(List(x, y).map(m))
			case SymbolicInt(_) 							=> f(List().map(m))
			case ShortPlus(a, b)							=> f(List(a, b).map(m))
			case ShortDivide(a, b)						=> f(List(a, b).map(m))
			case ShortMinus(a, b)							=> f(List(a, b).map(m))
			case ShortTimes(a, b)							=> f(List(a, b).map(m))
			case ShortConvert(a)				  		=> f(List(a).map(m))
			case ShortToInt(a)				  	  	=> f(List(a).map(m))
			case NumericDivide(a, b)					=> f(List(a, b).map(m))
			case NumericPlus(a, b)					  => f(List(a, b).map(m))
			case NumericTimes(a, b)           => f(List(a, b).map(m))
			case NumericMinus(a, b)						=> f(List(a, b).map(m))
			case IntToDoubleConversion(a)			=> f(List(a).map(m))
			case ShortToDouble(a)             => f(List(a).map(m))
			case ShortToFloat(a)              => f(List(a).map(m))
			case DoubleToShortConversion(a)   => f(List(a).map(m))
			case CharToT(a)						        => f(List(a).map(m))
		  case TToChar(a)                   => f(List(a).map(m))
			//case IfThenElse(c, a, b)          => f(List().map(m))
			case MathSqrt(a)                  => f(List(a).map(m))
			case OrderingLT(a, b)             => f(List(a, b).map(m))
			case OrderingGT(a, b)             => f(List(a, b).map(m))
			case OrderingLTEQ(a, b)           => f(List(a, b).map(m))
			case OrderingGTEQ(a, b)           => f(List(a, b).map(m))
			case BooleanAnd(a, b)             => f(List(a, b).map(m))
			case BooleanOr(a, b)              => f(List(a, b).map(m))
			case Equal(a, b)                  => f(List(a, b).map(m))
			case NotEqual(a, b)               => f(List(a, b).map(m))
			case Tern(cond, l, u)             => f(List(cond, l, u).map(m))
			case ArrayApply(c, i)             => f(List(c, i).map(m))
			case ArrayFromSeq(seq)            => f(List().map(m))
  }

	var funcsToId: Map[Func[_], Int] = Map()
	private var id = 0

	def toFunc[T:Typ:Numeric:SepiaNum](f: (Rep[Int], Rep[Int]) => RGBVal[T], dom: Domain): Func[T] = {
		val madeFunc = mkFunc(f, dom, id)
		assert(!funcsToId.contains(madeFunc))
		funcsToId = funcsToId + (madeFunc -> id)
		id += 1
		//println(f"funcs to id)
		madeFunc
	}

	override def func[T:Typ:Numeric:SepiaNum](f: (Rep[Int], Rep[Int]) => RGBVal[T]): Func[T] = {
		toFunc(f, ((0, 0), (0, 0)))
	}

	override def final_func[T:Typ:Numeric:SepiaNum](f: (Rep[Int], Rep[Int]) => RGBVal[T]): Func[UChar] = {
		// todo: dry up
		val cast =
			if (typ[T] == typ[UChar]) realizeShort(f.asInstanceOf[(Rep[Int], Rep[Int]) => RGBVal[Short]])
			else if (typ[T] == typ[Int]) realizeInt(f.asInstanceOf[(Rep[Int], Rep[Int]) => RGBVal[Int]])
			else if (typ[T] == typ[Float]) realizeFloat(f.asInstanceOf[(Rep[Int], Rep[Int]) => RGBVal[Float]])
			else realizeDouble(f.asInstanceOf[(Rep[Int], Rep[Int]) => RGBVal[Double]])

		val castFunc = func(cast)
		finalFunc = Some(castFunc)
		castFunc

	}

	def mergeBoundsMaps(b1: Map[Int, Map[String, Bound]],
											b2: Map[Int, Map[String, Bound]]): Map[Int, Map[String, Bound]] = {
		// Given two maps for func -> bound, bound, will join the two maps together.
		// If the two maps both have a bound for key k, will merge the bounds by taking
		// the smallest of the min and the largest of the max.

		def mergeSingleBoundSet(a: Map[String, Bound], b: Map[String, Bound]) = {
			a ++ b.map { case (k, bBound) => k -> {
					val aBound = a.getOrElse(k, Bound.zero)
					aBound join bBound
				}}
		}

		b1 ++ b2.map{ case (k,v) => k -> {
				mergeSingleBoundSet(v, b1.getOrElse(k, Map())) }}
	}

	def extractBound(expr: Exp[_], dim: String) = expr match {
		case Def(v) => v match {
			case IntPlus(Def(SymbolicInt(dim)), Const(k)) => Bound(k, k, 1, 1, 1, 1)
			case IntPlus(Const(k), Def(SymbolicInt(dim))) => Bound(k, k, 1, 1, 1, 1)
			case IntMinus(Const(k), Def(SymbolicInt(dim))) => Bound(-k, -k, 1, 1, 1, 1)
			case IntMinus(Def(SymbolicInt(dim)), Const(k)) => Bound(-k, -k, 1, 1, 1, 1)
			case IntTimes(Const(k), Def(SymbolicInt(dim))) => Bound(0, 0, 1, 1, k, k)
			case IntTimes(Def(SymbolicInt(dim)), Const(k)) => Bound(0, 0, 1, 1, k, k)
			case IntDivide(Def(SymbolicInt(dim)), Const(k)) => Bound(0, 0, k, k, 1, 1)
			case IntDivide(Const(k), Def(SymbolicInt(dim))) => Bound(0, 0, k, k, 1, 1)
			case IntPlus(a, b) => (a, b) match {
				case (Def(IntTimes(Const(k1), Def(SymbolicInt(dim)))), Const(k2)) => {
					Bound(k2, k2, 1, 1, k1, k1)
				}
				case (Const(k2), Def(IntTimes(Const(k1), Def(SymbolicInt(dim))))) => {
					Bound(k2, k2, 1, 1, k1, k1)
				}
				case (Def(IntDivide(Def(SymbolicInt(dim)), Const(k1))), Const(k2)) => {
					Bound(k2, k2, k1, k1, 1, 1)
				}
				case (Const(k2), Def(IntDivide(Def(SymbolicInt(dim)), Const(k1)))) => {
					Bound(k2, k2, k1, k1, 1, 1)
				}
				case (Def(IntMinus(Def(IntDivide(Def(SymbolicInt(dim)), Const(k1))), Const(k2))), c) => {
					Bound(-k1, -k1, k2, k2, 1, 1)
				}

			}

			case IntMinus(a, b) => (a, b) match {
				case (Def(IntTimes(Const(k1), Def(SymbolicInt(dim)))), Const(k2)) => {
					Bound(-k2, -k2, 1, 1, k1, k1)
				}
				case (Def(IntDivide(Def(SymbolicInt(dim)), Const(k1))), Const(k2)) => {
					Bound(-k2, -k2, k1, k1, 1, 1)
				}
			}



			case SymbolicInt(dim) => Bound(0, 0, 1, 1, 1, 1)
			case _ => throw new InvalidAlgorithm(f"Error: Invalid input to function, $v")
		}
		case Const(_) => Bound(0, 0, 1, 1, 1, 1)
	}


	def analyseInputTransformations(xExpr: Exp[_],
																	yExpr: Exp[_]): Map[String, Bound] = {
	// For now, we just analyse additive constants on x / y coordinates.
	// Do we need anything else?

		val xTransformation: Bound = extractBound(xExpr, "x")
		val yTransformation: Bound = extractBound(yExpr, "y")

		Map("x" -> xTransformation, "y" -> yTransformation)
	}

	def getInputTransformations(v: RGBVal[_]): Map[Int, Map[String, Bound]] = {
		mergeBoundsMaps(getInputTransformations(v.red),
										mergeBoundsMaps(getInputTransformations(v.blue),
																		getInputTransformations(v.green)))
	}

  def getInputTransformations(e: Exp[_]): Map[Int, Map[String, Bound]] = e match {
    case Def(v) => v match {
    	case FuncApplication(func, xExpr, yExpr) =>  {
				Map(funcsToId(func) -> analyseInputTransformations(xExpr, yExpr))
			}

			case SymbolicArrayApplication(_, xExpr, yExpr) => {
				// -1 = in
				Map(-1 -> analyseInputTransformations(xExpr, yExpr))
			}

      case x => {
				treeTraversal[Map[Int, Map[String, Bound]]](
					_.foldLeft(Map[Int, Map[String, Bound]]())(mergeBoundsMaps),
					getInputTransformations _, x)
			}
    }
    case Const(_) => Map()
  }

	def getInputBounds(): Map[Int, Map[Int, Map[String, Bound]]] = {
		// f -> (g1 -> (a, b), g2 -> (c, d) ...) means that
		// f calls functions g1, g2 with (a, b) a bound on
		// g1's input and (c, d) a bound on g2's input.
		val symbolicInput = Buffer(0, 0, newSymbolicArray())
		prog(symbolicInput, newSymbolicInt("w"), newSymbolicInt("h"))
		val res = funcsToId.map( { case (f, id) => {
			(id -> getInputTransformations(
														 f(newSymbolicInt("x"), newSymbolicInt("y"))))
	  }})
	/* println(f"res: ${res(79)}")
	 val k = res(79).keys.toList(0)
	 assert(closed(res))
	 assert(!hasCycles(res))
	 println(f"k: $k")*/
	 //println(f"res2: ${res(k)}")
	 val reachableFromLast = reachableFrom(funcsToId(finalFunc.getOrElse(throw new Exception())), res)
	 for (i <- 0 to id-1: Range) {
		 if (!reachableFromLast.contains(i)) {
			 val name = funcNames.getOrElse(i, "")
			 println(f"Warning: function $i, ${name}, is not reachable from the final func")
		 }
	 }
	 val nReachable = reachableFromLast.size
	 println(f"Number of expected apps: $nReachable")
	 res

	}

	def reachableFrom(src: Int, m: Map[Int, Map[Int, Map[String, Bound]]]): List[Int] = {
		if (src == -1) List()
		else m(src).keys.toList.flatMap(reachableFrom(_, m)) ++ m(src).keys.toList
	}

	private def closed(m: Map[Int, Map[Int, Map[String, Bound]]]) = {
		def closedFrom(i: Int): Boolean = {
			val neighbours = m(i).keys.filter(_ != -1)
			neighbours.forall(n => m.contains(n) && closedFrom(n))
		}
		var c = true
		for (i <- m.keys) {
			val cf = closedFrom(i)
			if (!cf) {
				println(f"Not closed for $i")
			}
			c = c & cf
		}

		c
	}

	def getBoundsGraph(): CallGraph = {
	    val m = getInputBounds
			CallGraph.graphFromMap(m,
				funcsToId(finalFunc.getOrElse(throw new InvalidAlgorithm("Error: No final func"))))
	}

	def hasCycles(m: Map[Int, Map[Int, Map[String, Bound]]]): Boolean = {
    def hasCyclesFrom(n: Int) = {
			val reach = reachableFrom(n, m)
			println(f"Reacable from $n: $reach")
			reach.contains(n)
    }

		m.keys.toList.exists(hasCyclesFrom(_))
  }



	class UselessFuncOps[T:Typ:Numeric:SepiaNum](f: Func[T]) extends FuncOps(f) {
		// Just convert sched ops to no-ops in the analysis phase
		override def computeAt[U:Typ:Numeric:SepiaNum](consumer: Func[U], s: String): Unit = return
		override def storeAt[U:Typ:Numeric:SepiaNum](consumer: Func[U], s: String): Unit = return
		override def storeRoot(): Unit = return
		override def computeRoot(): Unit = return
		override def split(v: String, outer: String, inner: String, splitFactor: Int): Unit = return
		override def reorder(v1: String, v2: String): Unit = return
		override def fuse(v: String, outer: String, inner: String): Unit = return
		override def vectorize(v: String, vectorWidth: Int): Unit = return
		override def addName(s: String) = funcNames(funcsToId(f)) = s
	}

	override implicit def toFuncOps[T:Typ:Numeric:SepiaNum](f: Func[T]) = new UselessFuncOps(f)
}
