package sepia

import lms.common._

// This file allows for the creation of symbolic
// integers, arrays and function applications. This means that we can analyse
// input argument expressions, because we can distinguish
// between 'x' and '1' in x + 1, even though both have type Rep[Int]

trait SymbolicOps extends Base with ImageBufferOps {
  def newSymbolicInt(s: String): Rep[Int]
  def newSymbolicArray(): Rep[Array[UChar]]
}

trait SymbolicOpsExp extends SymbolicOps with PrimitiveOpsExpOpt {
  case class SymbolicInt(s: String) extends Def[Int]
  case class SymbolicArray() extends Def[Array[UChar]]
  case class SymbolicArrayApplication[T:Typ:Numeric:SepiaNum](s: String, x: Exp[Int], y: Exp[Int]) extends Def[T]

  override def newSymbolicInt(s: String): Rep[Int] = SymbolicInt(s)
  override def newSymbolicArray(): Rep[Array[UChar]] =
    SymbolicArray()
}

trait SymbolicImageBufferOpsExp extends ImageBufferOps with SymbolicOpsExp {
  def symbolicRGBVal[T:Typ:Numeric:SepiaNum](x: Exp[Int], y: Exp[Int]) = {
      RGBVal[T](SymbolicArrayApplication[T]("r", x, y),
                SymbolicArrayApplication[T]("g", x, y),
                SymbolicArrayApplication[T]("b", x, y))
  }

  override def bufferApply[T:Typ:Numeric:SepiaNum](b: Buffer[T], x: Exp[Int], y: Exp[Int]) = {
    symbolicRGBVal(x, y)
  }
}

trait SymbolicFuncOpsExp extends SimpleFuncOps with SymbolicImageBufferOpsExp {
  // This trait produces nodes for function application.
  // It is used in the pre-interperation analysis of the
  // pipeline
  class TaggedFunc2[T](f: (Rep[Int], Rep[Int]) => RGBVal[T], id: Int)
     {
      def apply(x: Rep[Int], y: Rep[Int]) = f(x, y)
      override def toString() = id.toString
  }

  type Func[T] = TaggedFunc2[T]

  override def mkFunc[T:Typ:Numeric:SepiaNum](f: (Rep[Int], Rep[Int]) => RGBVal[T],
                      dom: Domain, id: Int) = new TaggedFunc2(f, id)

  case class FuncApplication[T:Typ:Numeric:SepiaNum](f: Func[T], x: Exp[Int], y: Exp[Int]) extends Def[T]

  override def funcApply[T:Typ:Numeric:SepiaNum](f: Func[T], x: Exp[Int], y: Exp[Int]) = {
   RGBVal(FuncApplication(f, x, y), FuncApplication(f, x, y), FuncApplication(f, x, y))
  }
}
