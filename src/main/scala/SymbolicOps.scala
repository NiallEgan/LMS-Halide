package sepia

import lms.common._

// This file allows for the creation of symbolic
// integers and arrays. This means that we can analyse
// input argument expressions, because we can distinguish
// between 'x' and '1' in x + 1, even though both have type Rep[Int]

trait SymbolicOps extends Base with PrimitiveOps {
  // TODO: Do we need more than one symbolic int?
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
