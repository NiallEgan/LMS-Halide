package sepia

import scala.lms.common._

trait Dsl extends PrimitiveOps with NumericOps
          with BooleanOps with LiftPrimitives
          with LiftNumeric with LiftBoolean
          with IfThenElse with Equal
          with RangeOps with FractionalOps
          with ArrayOps with SeqOps
          with Array2DOps {}

trait DslExp extends Dsl with PrimitiveOpsExpOpt with NumericOpsExpOpt
             with BooleanOpsExpOpt with IfThenElseExp
             with RangeOpsExp with FractionalOpsExp
             with EqualExpBridgeOpt with ArrayOpsExpOpt
             with SeqOpsExp with Array2DOpsExp {}

trait DslGenC extends CGenNumericOps
  with CGenPrimitiveOps with CGenBooleanOps
  with CGenIfThenElse with CGenEqual
  with CGenRangeOps with CGenFractionalOps
  with CGenArrayOps with CGenArray2DOps {
    val IR: DslExp
    import IR._
}