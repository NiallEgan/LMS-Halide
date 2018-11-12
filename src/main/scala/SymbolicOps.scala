package sepia

import lms.common._

// This file allows for the creation of symbolic
// integers, arrays and function applications. This means that we can analyse
// input argument expressions, because we can distinguish
// between 'x' and '1' in x + 1, even though both have type Rep[Int]

trait SymbolicOps extends Base with PrimitiveOps {
  def newSymbolicInt(s: String): Rep[Int]
  def newSymbolic2DArray[T:Typ](): Rep[Array[Array[T]]]
}

trait SymbolicOpsExp extends SymbolicOps
                     with Array2DOpsExp {

  case class SymbolicInt(s: String) extends Def[Int]
  case class Symbolic2DArray[T:Typ]() extends Def[Array[Array[T]]]

  override def newSymbolicInt(s: String): Rep[Int] = SymbolicInt(s)
  override def newSymbolic2DArray[T:Typ](): Rep[Array[Array[T]]] =
    Symbolic2DArray[T]()
}

trait SymbolicFuncOpsExp extends SimpleFuncOps with BaseExp {
  // This trait produces nodes for function application.
  // It is used in the pre-interperation analysis of the
  // pipeline
  type Func = (Rep[Int], Rep[Int]) => Rep[Int]

  override def mkFunc(f: (Rep[Int], Rep[Int]) => Rep[Int],
                      dom: ((Int, Int), (Int, Int)), id: Int) = f

  case class FuncApplication(f: Func, x: Rep[Int], y: Rep[Int])
    extends Def[Int]

  override def funcApply(f: Func, x: Rep[Int], y: Rep[Int]) =
    FuncApplication(f, x, y)
}
