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

trait SymbolicOpsExp extends SymbolicOps with PrimitiveOpsExpOpt {
  case class SymbolicInt(s: String) extends Def[Int]
  case class SymbolicArray() extends Def[Array[UShort]]
  case class SymbolicArrayApplication(s: String, x: Exp[Int], y: Exp[Int]) extends Def[Int]

  override def newSymbolicInt(s: String): Rep[Int] = SymbolicInt(s)
  override def newSymbolicArray(): Rep[Array[UShort]] =
    SymbolicArray()
}

trait SymbolicImageBufferOpsExp extends ImageBufferOps with SymbolicOpsExp {
  def symbolicRGBVal(x: Exp[Int], y: Exp[Int]) = {
      RGBVal(new SymbolicArrayApplication("r", x, y),
             new SymbolicArrayApplication("g", x, y),
             new SymbolicArrayApplication("b", x, y))
  }

  override def bufferApply(b: Buffer, x: Exp[Int], y: Exp[Int]) = {
    symbolicRGBVal(x, y)
  }
}

trait SymbolicFuncOpsExp extends SimpleFuncOps with SymbolicImageBufferOpsExp {
  // This trait produces nodes for function application.
  // It is used in the pre-interperation analysis of the
  // pipeline
  type Func[T] = (Rep[Int], Rep[Int]) => RGBVal[T]

  override def mkFunc[T:Typ:Numeric:SepiaNum](f: (Rep[Int], Rep[Int]) => RGBVal[T],
                      dom: Domain, id: Int) = f

  case class FuncApplication[T:Typ:Numeric:SepiaNum](f: Func[T], x: Exp[Int], y: Exp[Int]) extends Def[T]

  override def funcApply[T:Typ:Numeric:SepiaNum](f: Func[T], x: Exp[Int], y: Exp[Int]) = {
   RGBVal(FuncApplication(f, x, y), FuncApplication(f, x, y), FuncApplication(f, x, y))
  }
}
