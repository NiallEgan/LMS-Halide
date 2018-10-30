package sepia

import lms.common._

trait SimpleFuncOps extends Dsl {
  // The api that is presented to the DSL user

  type Func

  class FuncOps(f: Func) {
    def apply(x: Rep[Int], y: Rep[Int]) =
      funcApply(f, x, y)
  }
  implicit def funcToFuncOps(f: Func) = new FuncOps(f)

  def funcApply(f: Func, x: Rep[Int], y: Rep[Int]): Rep[Int]

  implicit def toFunc(f: (Rep[Int], Rep[Int]) => Rep[Int]): Func
}

trait CompilerFuncOps extends SimpleFuncOps {
  // The api that is presented to the DSL compiler

  def compute(f: Func, x: Rep[Int], y: Rep[Int]): Rep[Int]
  def storeInBuffer(x: Rep[Int], y: Rep[Int],
                    v: Rep[Int], b: Rep[Array[Array[Int]]]): Rep[Unit]
  def allocateBuffer(f: Func, x_dim: Rep[Int], y_dim: Rep[Int]): Rep[Unit]

}

trait CompilerFuncOpsImplementation extends CompilerFuncOps {
  class CompilerFunc(val f: (Rep[Int], Rep[Int]) => Rep[Int]) {
    val inlined = true
    var buffer: Option[Rep[Array[Array[Int]]]] = None
  }

  type Func = CompilerFunc

  override implicit def toFunc(f: (Rep[Int], Rep[Int]) => Rep[Int]) =
    new CompilerFunc(f)

  override def compute(f: Func, x: Rep[Int], y: Rep[Int]): Rep[Int] =
    f.f(x, y)

  override def storeInBuffer(x: Rep[Int], y: Rep[Int],
                             v: Rep[Int],
                             b: Rep[Array[Array[Int]]]): Rep[Unit] =
    b(x, y) = v

  override def funcApply(f: Func, x: Rep[Int], y: Rep[Int]): Rep[Int] =
    if (f.inlined) f(x, y)
    else f.buffer match {
     case Some(b) => b(y, x)
     case None => throw new InvalidSchedule("No buffer allocated at application time")
  }

  override def allocateBuffer(f: Func, x_dim: Rep[Int], y_dim: Rep[Int]) =
    f.buffer = Some(New2DArray[Int](x_dim, y_dim))

}

trait FuncExpr extends SimpleFuncOps with Exp {
  // This trait produces nodes for function application.
  // It is used in the pre interperation analysis of the
  // pipeline
  type Func = (Rep[Int], Rep[Int]) => Rep[Int]

  override implicit def toFunc(f: (Rep[Int], Rep[Int]) => Rep[Int]) =
    f

  case class FuncApplication(f: Func, x: Rep[Int], y: Rep[Int])
    extends Def[Rep[Int]]

  override def funcApply(f: Func, x: Rep[Int], y: Rep[Int]) =
    FuncApplication(f, x, y)
}
