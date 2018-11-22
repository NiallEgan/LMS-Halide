package sepia

import lms.common._

// This file allows for the creation of symbolic
// integers, arrays and function applications. This means that we can analyse
// input argument expressions, because we can distinguish
// between 'x' and '1' in x + 1, even though both have type Rep[Int]

trait SymbolicOps extends Base with ImageBufferOps {
  def newSymbolicInt(s: String): Rep[Int]
  def newSymbolicArray(): Rep[Array[UShort]]
}

trait SymbolicOpsExp extends SymbolicOps
                     with ImageBufferOpsExp {

  case class SymbolicInt(s: String) extends Def[Int]
  case class SymbolicArray() extends Def[Array[UShort]]

  override def newSymbolicInt(s: String): Rep[Int] = SymbolicInt(s)
  override def newSymbolicArray(): Rep[Array[UShort]] =
    SymbolicArray()
}

trait SymbolicFuncOpsExp extends SimpleFuncOps with ImageBufferOpsExp {
  // This trait produces nodes for function application.
  // It is used in the pre-interperation analysis of the
  // pipeline
  type Func = (Rep[Int], Rep[Int]) => RGBVal

  override def mkFunc(f: (Rep[Int], Rep[Int]) => RGBVal,
                      dom: ((Int, Int), (Int, Int)), id: Int) = f

  case class FuncApplication(f: Func, x: Exp[Int], y: Exp[Int]) extends Def[UShort]

  override def funcApply(f: Func, x: Exp[Int], y: Exp[Int]) = {
   RGBVal(FuncApplication(f, x, y), FuncApplication(f, x, y), FuncApplication(f, x, y))
  }
}
