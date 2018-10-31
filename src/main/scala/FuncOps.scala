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

  def mkFunc(f: (Rep[Int], Rep[Int]) => Rep[Int],
             dom: (Int, Int)): Func
}

trait CompilerFuncOps extends SimpleFuncOps {
  // The api that is presented to the DSL compiler

  class Dim(val max: Rep[Int], val name: String) {
		private var value: Option[Rep[Int]] = None

		def v: Rep[Int] = value match {
			case Some(x) => x
			case None => throw new InvalidSchedule("Unbound variable")
		}

		// TODO: Why is this not working with _= syntax?
		def v_=(new_val: Rep[Int]) = {
			value = Some(new_val)
		}
	}

  class CompilerFunc(f: (Rep[Int], Rep[Int]) => Rep[Int],
         dom: (Int, Int)) {
    val x: Dim = new Dim(dom._1, "x")
    val y: Dim = new Dim(dom._2, "y")

    var inlined = true

    var buffer: Option[Rep[Array[Array[Int]]]] = None

    def apply(x: Rep[Int], y: Rep[Int]) = {
       if (inlined) f(x, y)
       else buffer match {
        case Some(b) => b(x, y)
        case None => throw new InvalidSchedule("No buffer allocated at application time")
       }
    }

    def compute() = f(x.v, y.v)

    def storeInBuffer(v: Rep[Int]) = buffer match {
      case Some(b) => b(x.v, y.v) = v
      case None => throw new InvalidSchedule("No buffer allocated at storage time")
    }

    def allocateNewBuffer() {
      buffer = Some(New2DArray[Int](x.max, y.max))
    }
  }

  type Func = CompilerFunc

  override def funcApply(f: Func, x: Rep[Int], y: Rep[Int]): Rep[Int] =
    if (f.inlined) f(x, y)
    else f.buffer match {
     case Some(b) => b(y, x)
     case None => throw new InvalidSchedule("No buffer allocated at application time")
  }

  def mkFunc(f: (Rep[Int], Rep[Int]) => Rep[Int], dom: (Int, Int)): Func = {
    new CompilerFunc(f, dom)
  }

}

trait FuncExp extends SimpleFuncOps with BaseExp {
  // This trait produces nodes for function application.
  // It is used in the pre interperation analysis of the
  // pipeline
  type Func = (Rep[Int], Rep[Int]) => Rep[Int]

  override def mkFunc(f: (Rep[Int], Rep[Int]) => Rep[Int], dom: (Int, Int)) = f

  case class FuncApplication(f: Func, x: Rep[Int], y: Rep[Int])
    extends Def[Int]

  override def funcApply(f: Func, x: Rep[Int], y: Rep[Int]) =
    FuncApplication(f, x, y)
}
