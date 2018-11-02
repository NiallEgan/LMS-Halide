package sepia

import scala.collection.mutable.ListBuffer

trait PipelineAnalysis extends FuncExp with DslExp
										with SymbolicArray2DOpsExp with Pipeline {
	// Before we pass the program to the staged interpreter, we must
  // first do some analysis on

	////
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
  }





	val funcs: ListBuffer[(Rep[Int], Rep[Int]) => Rep[Int]] = new ListBuffer()

	def toFunc(f: (Rep[Int], Rep[Int]) => Rep[Int], dom: (Int, Int)): Func = {
		funcs += f
		mkFunc(f, dom)
	}

	class Bound(val lb: Int, val ub: Int) {
		def join(other: Bound): Bound = {
			val newLb = if (lb < other.lb) lb else other.lb
			val newUb = if (ub > other.ub) ub else other.ub
			new Bound(newLb, newUb)
		}
	}

	object Bound {
		// TODO: Why doesn't Int.min, Int.max work here?
		val intMin = -1000000000
		val intMax = 1000000000
		val zero = new Bound(intMax, intMin)
	}

	def mergeBoundsMaps(b1: Map[Func, (Bound, Bound)],
											b2: Map[Func, (Bound, Bound)]): Map[Func, (Bound, Bound)] = {
		// TODO: Rewrite with scalaz?
		b1 ++ b2.map{ case (k,v) => k -> {
				val xb1 = v._1
				val yb1 = v._2
				val xb2 = b1.getOrElse(k, (Bound.zero, Bound.zero))._1
				val yb2 = b1.getOrElse(k, (Bound.zero, Bound.zero))._2
				(xb1 join xb2, yb1 join yb2)
			}
		}
	}

	def analyseInputTransformations(xExpr: Exp[_],
																	yExpr: Exp[_]): (Bound, Bound) = {
	// For now, we just analyse additive constants on x / y coordinates.
	// Do we need anything else?
	val xTransformation: Bound = xExpr match {
		case Def(v) => v match {
			case IntPlus(_, Const(k)) => new Bound(k, k)
			case IntPlus(Const(k), _) => new Bound(k, k)
			case _ => throw new InvalidAlgorithm(f"Error: Invalid x input to function")
			}
		case Const(_) => new Bound(0, 0)
		}

	// TODO: DRY
	val yTransformation = yExpr match {
		case Def(v) => v match {
			case IntPlus(_, Const(k)) => new Bound(k, k)
			case IntPlus(Const(k), _) => new Bound(k, k)
			case _ => throw new InvalidAlgorithm(f"Error: Invalid y input to function")
			}
		case Const(_) => new Bound(0, 0)
		}

	(xTransformation, yTransformation)
	}

  def getFunctionInputTransformations(e: Exp[_]): Map[Func, (Bound, Bound)] = e match {
    case Def(v) => v match {
      case FuncApplication(func, xExpr, yExpr) =>
				Map(func -> analyseInputTransformations(xExpr, yExpr))
      case x => {
				treeTraversal[Map[Func, (Bound, Bound)]](
					_.foldLeft(Map[Func, (Bound, Bound)]())(mergeBoundsMaps),
					getFunctionInputTransformations _, x)
			}
    }
    case Const(_) => Map()
  }

	def analyse() = {
		val x = newSymbolicArray(0, 0) // TODO: We want this to be recognizable from other arrays
		prog(x)
		for (f <- funcs.toList) {
      println(f)
			println(f(0, 4)) // TODO: We want symbolic ints here.
			f(0, 4) match {
				case Def(v) => println(v)
        // TODO: Traverse the graph. How? What are we looking for?
        // We want to know about the operations performed on the inputs to the functions

				case Const(v) => println(v)
			}
		}
		println(globalDefsCache)
	}
}
