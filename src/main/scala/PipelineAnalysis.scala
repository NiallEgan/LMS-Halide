package sepia

import scala.collection.mutable.ListBuffer

case class Bound(val lb: Int, val ub: Int) {
	def join(other: Bound): Bound = {
		val newLb = if (lb < other.lb) lb else other.lb
		val newUb = if (ub > other.ub) ub else other.ub
		new Bound(newLb, newUb)
	}

	//override def toString(): String = (lb, ub).toString
}

object Bound {
	// TODO: Why doesn't Int.min, Int.max work here?
	val intMin = -1000000000
	val intMax = 1000000000
	val zero = Bound(intMax, intMin)
}

trait PipelineAnalysis extends FuncExp with DslExp
										with SymbolicOpsExp with Pipeline {
	// Before we pass the program to the staged interpreter, we must
  // first do some analysis on

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
			case SymbolicInt(_) 								=> f(List().map(m))
  }

	val funcs: ListBuffer[(Rep[Int], Rep[Int]) => Rep[Int]] = new ListBuffer()

	def toFunc(f: (Rep[Int], Rep[Int]) => Rep[Int], dom: (Int, Int)): Func = {
		funcs += f
		mkFunc(f, dom)
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

	def extractBound(expr: Exp[_], dim: String) = expr match {
		case Def(v) => v match {
			case IntPlus(Def(SymbolicInt(dim)), Const(k)) => Bound(k, k)
			case IntPlus(Const(k), Def(SymbolicInt(dim))) => Bound(k, k)
			case IntMinus(Const(k), Def(SymbolicInt(dim))) => Bound(-k, -k)
			case IntMinus(Def(SymbolicInt(dim)), Const(k)) => Bound(-k, -k)
			case SymbolicInt(dim) => Bound(0, 0)
			case _ => throw new InvalidAlgorithm(f"Error: Invalid x input to function, $v")
		}
		case Const(_) => Bound(0, 0)
	}


	def analyseInputTransformations(xExpr: Exp[_],
																	yExpr: Exp[_]): (Bound, Bound) = {
	// For now, we just analyse additive constants on x / y coordinates.
	// Do we need anything else?

	val xTransformation: Bound = extractBound(xExpr, "x")
	val yTransformation: Bound = extractBound(yExpr, "y")

	(xTransformation, yTransformation)
	}

  def getInputTransformations(e: Exp[_]): Map[Func, (Bound, Bound)] = e match {
    case Def(v) => v match {
    	case FuncApplication(func, xExpr, yExpr) =>  {
				Map(func -> analyseInputTransformations(xExpr, yExpr))
			}
      case x => {
				treeTraversal[Map[Func, (Bound, Bound)]](
					_.foldLeft(Map[Func, (Bound, Bound)]())(mergeBoundsMaps),
					getInputTransformations _, x)
			}
    }
    case Const(_) => Map()
  }

	def getInputBounds(): Map[Func, Map[Func, (Bound, Bound)]] = {
		// f -> (g1 -> (a, b), g2 -> (c, d) ...) means that
		// f calls functions g1, g2 with (a, b) a bound on
		// g1's input and (c, d) a bound on g2's input.
		// TODO: Abstract into a better class?
		val x = newSymbolic2DArray[Int]()
		prog(x)
		funcs.toList.foldLeft(Map[Func, Map[Func, (Bound, Bound)]]())
								{(m, f) =>
									m + (f -> getInputTransformations(f(newSymbolicInt("x"),
																										 newSymbolicInt("y"))))}
	}
}
