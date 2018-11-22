package sepia

import lms.common._

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

  implicit def i2reps(i: Int) = unit(i.toShort)

}

trait ShortOpsExp extends ShortOps with BaseExp {
  case class ShortMinus(a: Exp[Short], b: Exp[Short]) extends Def[Short]
  case class ShortPlus(a: Exp[Short], b: Exp[Short]) extends Def[Short]
  case class ShortTimes(a: Exp[Short], b: Exp[Short]) extends Def[Short]
  case class ShortDivide(a: Exp[Short], b: Exp[Short]) extends Def[Short]

  case class ShortConvert(a: Exp[Int]) extends Def[Short]

  override def short_minus(a: Exp[Short], b: Exp[Short]) = ShortMinus(a, b)
  override def short_plus(a: Exp[Short], b: Exp[Short]) = ShortPlus(a, b)
  override def short_times(a: Exp[Short], b: Exp[Short]) = ShortTimes(a, b)
  override def short_divide(a: Exp[Short], b: Exp[Short]) = ShortDivide(a, b)

  override def i2s(a: Exp[Int]) = ShortConvert(a)
}

trait ShortOpsExpOpt extends ShortOpsExp {
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
      case _ => super.emitNode(sym, rhs)
    }
  }
}
