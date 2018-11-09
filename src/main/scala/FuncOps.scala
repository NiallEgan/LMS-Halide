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
             dom: ((Int, Int), (Int, Int)), id: Int): Func
}

trait CompilerFuncOps extends SimpleFuncOps {
  // The api that is presented to the DSL compiler

  class Dim(val min: Int, val max: Int,
            val name: String, val f: Func) {
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
                     dom: ((Int, Int), (Int, Int)), val id: Int) {
    val x: Dim = new Dim(dom._1._1, dom._1._2, "x", this)
    val y: Dim = new Dim(dom._2._1, dom._2._2, "y", this)

    var inlined = true
    var storeAt: Option[Dim] = None
    var computeAt: Option[Dim] = None
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

    def allocateNewBuffer(m: Int, n: Int) {
      buffer = Some(New2DArray[Int](m, n))
    }
  }

  type Func = CompilerFunc

  override def funcApply(f: Func, x: Rep[Int], y: Rep[Int]): Rep[Int] =
    if (f.inlined) f(x, y)
    else f.buffer match {
     case Some(b) => b(y, x)
     case None => throw new InvalidSchedule("No buffer allocated at application time")
  }

  def mkFunc(f: (Rep[Int], Rep[Int]) => Rep[Int], dom: ((Int, Int), (Int, Int)), id: Int): Func = {
    new CompilerFunc(f, dom, id)
  }

}
