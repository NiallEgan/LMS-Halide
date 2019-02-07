package sepia

import lms.common._
import scala.reflect.SourceContext

trait ShortOps extends PrimitiveOps {
  // Should we be allowed to add other types
  def infix_-(a: Rep[Short], b: Rep[Short]) = short_minus(a, b)
  def infix_+(a: Rep[Short], b: Rep[Short]) = short_plus(a, b)
  def infix_*(a: Rep[Short], b: Rep[Short]) = short_times(a, b)
  def infix_/(a: Rep[Short], b: Rep[Short]) = short_divide(a, b)

  def short_minus(a: Rep[Short], b: Rep[Short]): Rep[Short]
  def short_plus(a: Rep[Short], b: Rep[Short]): Rep[Short]
  def short_times(a: Rep[Short], b: Rep[Short]): Rep[Short]
  def short_divide(a: Rep[Short], b: Rep[Short]): Rep[Short]

  def i2s(a: Rep[Int]): Rep[Short]
  def s2i(a: Rep[Short]): Rep[Int]
  def d2s(a: Rep[Double]): Rep[Short]

  def repShortToRepInt(a: Rep[Short]): Rep[Int] = s2i(a)
  def repShortToRepDouble(a: Rep[Short]): Rep[Double]
  def repShortToRepFloat(a: Rep[Short]): Rep[Float]

  implicit def i2reps(i: Int) = unit(i.toShort)

}

trait ShortOpsExp extends ShortOps with EffectExp {
  case class ShortMinus(a: Exp[Short], b: Exp[Short]) extends Def[Short]
  case class ShortPlus(a: Exp[Short], b: Exp[Short]) extends Def[Short]
  case class ShortTimes(a: Exp[Short], b: Exp[Short]) extends Def[Short]
  case class ShortDivide(a: Exp[Short], b: Exp[Short]) extends Def[Short]
  case class ShortToDouble(a: Exp[Short]) extends Def[Double]
  case class ShortToFloat(a: Exp[Short]) extends Def[Float]
  case class ShortConvert(a: Exp[Int]) extends Def[Short]
  case class IntConvert(a: Exp[Short]) extends Def[Int]
  case class DoubleToShortConversion(a: Exp[Double]) extends Def[Short]

  override def short_minus(a: Exp[Short], b: Exp[Short]) = ShortMinus(a, b)
  override def short_plus(a: Exp[Short], b: Exp[Short]) = ShortPlus(a, b)
  override def short_times(a: Exp[Short], b: Exp[Short]) = ShortTimes(a, b)
  override def short_divide(a: Exp[Short], b: Exp[Short]) = ShortDivide(a, b)

  override def i2s(a: Exp[Int]) = ShortConvert(a)
  override def s2i(a: Exp[Short]) = IntConvert(a)
  override def d2s(a: Exp[Double]) = DoubleToShortConversion(a)
  override def repShortToRepDouble(a: Rep[Short]): Rep[Double] = ShortToDouble(a)
  override def repShortToRepFloat(a: Rep[Short]): Rep[Float] = ShortToFloat(a)


  override def mirror[A:Typ](e: Def[A], f: Transformer)(implicit pos: SourceContext): Exp[A] = {
    super.mirror(e, f)(typ[A], pos)
  }

}

trait ShortOpsExpOpt extends ShortOpsExp {
  override def mirror[A:Typ](e: Def[A], f: Transformer)(implicit pos: SourceContext): Exp[A] = {

    //println(f"Mirroring $e")
    (e match {
      case ShortMinus(a, b) => short_minus(f(a), f(b))
      case ShortPlus(a, b) => short_plus(f(a), f(b))
      case ShortTimes(a, b) => short_times(f(a), f(b))
      case ShortDivide(a, b) => short_divide(f(a), f(b))
      case ShortToFloat(a) => repShortToRepFloat(a)
      case ShortToDouble(a) => repShortToRepDouble(a)
      case ShortConvert(a) => i2s(f(a))
      case IntConvert(a) => s2i(f(a))
      case DoubleToShortConversion(a) => d2s(f(a))

      case Reflect(ShortMinus(a, b), u, es) =>
        reflectMirrored(Reflect(ShortMinus(f(a), f(b)), mapOver(f, u), f(es)))
      case Reflect(ShortPlus(a, b), u, es) =>
        reflectMirrored(Reflect(ShortPlus(f(a), f(b)), mapOver(f, u), f(es)))
      case Reflect(ShortTimes(a, b), u, es) =>
        reflectMirrored(Reflect(ShortTimes(f(a), f(b)), mapOver(f, u), f(es)))
      case Reflect(ShortDivide(a, b), u, es) =>
        reflectMirrored(Reflect(ShortDivide(f(a), f(b)), mapOver(f, u), f(es)))
      case Reflect(ShortConvert(a), u, es) =>
        reflectMirrored(Reflect(ShortConvert(f(a)), mapOver(f, u), f(es)))
      case Reflect(IntConvert(a), u, es) =>
        reflectMirrored(Reflect(IntConvert(f(a)), mapOver(f, u), f(es)))
      case Reflect(DoubleToShortConversion(a), u, es) =>
        reflectMirrored(Reflect(DoubleToShortConversion(f(a)), mapOver(f, u), f(es)))
      case Reflect(ShortToFloat(a), u, es) =>
        reflectMirrored(Reflect(ShortToFloat(f(a)), mapOver(f, u), f(es)))
      case Reflect(ShortToDouble(a), u, es) =>
        reflectMirrored(Reflect(ShortToDouble(f(a)), mapOver(f, u), f(es)))

      case _ => super.mirror(e, f)(typ[A], pos)
    }).asInstanceOf[Exp[A]]
  }

  override def short_minus(a: Exp[Short], b: Exp[Short]) = (a, b) match {
    // TODO: These .toShort converions are dubious. Sign?
    case (Const(a), Const(b)) => unit((a - b).toShort)
    case (Const(0), _) => b
    case (_, Const(0)) => a
    case _ => super.short_minus(a, b)
  }

  override def short_plus(a: Exp[Short], b: Exp[Short]) = (a, b) match {
    case (Const(a), Const(b)) => unit((a + b).toShort)
    case (Const(0), _) => b
    case (_, Const(0)) => a
    case _ => super.short_plus(a, b)
  }

  override def short_divide(a: Exp[Short], b: Exp[Short]) = (a, b) match {
    case (Const(a), Const(b)) => unit((a / b).toShort)
    case (Const(1), _) => b
    case (_, Const(1)) => a
    case _ => super.short_divide(a, b)
  }
  override def short_times(a: Exp[Short], b: Exp[Short]) = (a, b) match {
    case (Const(a), Const(b)) => unit((a * b).toShort)
    case (Const(1), _) => b
    case (_, Const(1)) => a
    case _ => super.short_times(a, b)
  }
}

trait CGenShortOps extends CGenBase {
  val IR: ShortOpsExp
  import IR._

  override def emitNode(sym: Sym[Any], rhs: Def[Any]) = {
    rhs match {
      case ShortPlus(a, b) => emitValDef(sym, src"$a + $b")
      case ShortTimes(a, b) => emitValDef(sym, src"$a * $b")
      case ShortMinus(a, b) => emitValDef(sym, src"$a - $b")
      case ShortDivide(a, b) => emitValDef(sym, src"$a / $b")
      case ShortConvert(a) => emitValDef(sym, src"$a")
      case IntConvert(a) => emitValDef(sym, src"(int) $a") // unsafe...
      case DoubleToShortConversion(a) => emitValDef(sym, src"(UCHAR) $a")
      case ShortToFloat(a) => emitValDef(sym, src"(float) $a")
      case ShortToDouble(a) => emitValDef(sym, src"(double) $a")
      case _ => super.emitNode(sym, rhs)
    }
  }
}
