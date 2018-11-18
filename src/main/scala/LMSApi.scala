package sepia

import scala.lms.common._

trait Dsl extends PrimitiveOps with NumericOps
          with BooleanOps with LiftPrimitives
          with LiftNumeric with LiftBoolean
          with IfThenElse with Equal
          with RangeOps with FractionalOps
          with ArrayOps with SeqOps
          with ImageBufferOps {
 //type UShort = Int
}

trait DslExp extends Dsl with PrimitiveOpsExpOpt with NumericOpsExpOpt
             with BooleanOpsExpOpt with IfThenElseExp
             with RangeOpsExp with FractionalOpsExp
             with EqualExpBridgeOpt with ArrayOpsExpOpt
             with SeqOpsExp with ImageBufferOpsExp {}

trait DslGenC extends CGenNumericOps
  with CGenPrimitiveOps with CGenBooleanOps
  with CGenIfThenElse with CGenEqual
  with CGenRangeOps with CGenFractionalOps
  with CGenArrayOps {
    val IR: DslExp
    import IR._

    override def remap[A](m: Typ[A]) = m.toString match {
      case "Array[Int]" => "int32_t[]"
      case _ => super.remap(m)

    }

    override def emitNode(sym: Sym[Any], rhs: Def[Any]) = {
      rhs match {
        case ArrayApply(x, m) => emitValDef(sym, src"$x[$m]")
        case ArrayUpdate(x, m, y) => stream.println(src"$x[$m] = $y;")
        case a@ArrayNew(m) => {
          val arrType = remap(a.m)
          stream.println(f"$arrType[${quote(m)}] ${quote(sym)};")
        }
        case _ => super.emitNode(sym, rhs)
      }
    }
}
