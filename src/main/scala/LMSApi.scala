package sepia

import java.io.PrintWriter

import scala.lms.common._

trait Dsl extends PrimitiveOps with NumericOps
          with BooleanOps with LiftPrimitives
          with LiftNumeric with LiftBoolean
          with IfThenElse with Equal
          with RangeOps with FractionalOps
          with ArrayOps with SeqOps
          with ImageBufferOps with ShortOps {
}

trait DslExp extends Dsl with PrimitiveOpsExpOpt with NumericOpsExpOpt
             with BooleanOpsExpOpt with IfThenElseExp
             with RangeOpsExp with FractionalOpsExp
             with EqualExpBridgeOpt with ArrayOpsExpOpt
             with SeqOpsExp with ImageBufferOpsExp
             with ShortOpsExpOpt {}

trait DslGenC extends CGenNumericOps
  with CGenPrimitiveOps with CGenBooleanOps
  with CGenIfThenElse with CGenEqual
  with CGenRangeOps with CGenFractionalOps
  with CGenShortOps with CGenArrayOps  {
    val IR: DslExp
    import IR._

    override def isPrimitiveType(tpe: String) = tpe match {
      case "USHORT" => true
      case "UCHAR" => true
      case _ => super.isPrimitiveType(tpe)
    }

    override def remap[A](m: Typ[A]) = m.toString match {
      case "Array[Short]" => "UCHAR[]"
      case "Short" => "UCHAR"
      case _ => super.remap(m)

    }

    override def emitNode(sym: Sym[Any], rhs: Def[Any]) = {
      rhs match {
        case ArrayApply(x, m) => emitValDef(sym, src"$x[$m]")
        case ArrayUpdate(x, m, y) => stream.println(src"$x[$m] = $y;")
        case a@ArrayNew(m) => {
          val arrType = remap(a.m)
          stream.println(f"$arrType ${quote(sym)}[${quote(m)}];")
        }
        case _ => super.emitNode(sym, rhs)
      }
    }

    def emitSourceMut[T1: Typ, T2: Typ, R: Typ]
                    (f: (Exp[T1], Exp[T2]) => Exp[R],
                     className: String, stream: PrintWriter): List[(Sym[Any], Any)] = {
    // This marks the second argument as mutable
    val s1 = fresh[T1]
    val s2 = reflectMutableSym(fresh[T2])
    val body = reifyBlock(f(s1, s2))
    emitSource(List(s1, s2), body, className, stream)
  }
}
