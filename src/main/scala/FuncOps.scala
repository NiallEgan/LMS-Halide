package sepia

import lms.common._

trait SimpleFuncOps extends Dsl {
  // The api that is presented to the DSL user
  type Func
  type Domain = ((Rep[Int], Rep[Int]), (Rep[Int], Rep[Int]))

  class FuncOps(f: Func) {
    def apply(x: Rep[Int], y: Rep[Int]) =
      funcApply(f, x, y)
  }

  implicit def funcToFuncOps(f: Func) = new FuncOps(f)

  def funcApply(f: Func, x: Rep[Int], y: Rep[Int]): RGBVal

  def mkFunc(f: (Rep[Int], Rep[Int]) => RGBVal,
             dom: Domain, id: Int): Func
}

trait CompilerFuncOps extends SimpleFuncOps {
  // The api that is presented to the DSL compiler

  class Dim(val min: Rep[Int], val max: Rep[Int],
            val name: String, val f: Func) {
		private var value: Option[Rep[Int]] = None

    private var loopStart: Option[Rep[Int]] = None

		def v: Rep[Int] = value.getOrElse(throw new InvalidSchedule("Unbound variable"))

    def loopStartOffset: Rep[Int] = {
      loopStart.getOrElse(throw new InvalidSchedule("Unbound loop v"))
    }

    def loopStartOffset_=(new_val: Rep[Int]) = {
      loopStart = Some(new_val)
    }

		// TODO: Why is this not working with _= syntax?
		def v_=(new_val: Rep[Int]) = {
			value = Some(new_val)
		}
	}

  class CompilerFunc(val f: (Rep[Int], Rep[Int]) => RGBVal,
                     dom: Domain, val id: Int) {
    val x: Dim = new Dim(dom._1._1, dom._1._2, "x", this)
    val y: Dim = new Dim(dom._2._1, dom._2._2, "y", this)

    var inlined = true
    var storeAt: Option[Dim] = None
    var computeAt: Option[Dim] = None
    var buffer: Option[Buffer] = None

    def compute() = f(x.v, y.v)

    def storeInBuffer(vs: RGBVal) = buffer match {
      case Some(b) => b(x.v - x.loopStartOffset, y.v - y.loopStartOffset) = vs
      case None => throw new InvalidSchedule(f"No buffer allocated at storage time for ")
    }

    def allocateNewBuffer() {
      buffer = Some(NewBuffer(x.max - x.min, y.max - y.min))
    }

    def allocateNewBuffer(m: Rep[Int], n: Rep[Int]) {
      buffer = Some(NewBuffer(m, n))
    }
  }

  type Func = CompilerFunc

  override def funcApply(f: Func, x: Rep[Int], y: Rep[Int]): RGBVal = {
    if (f.inlined) f.f(x, y)
    else f.buffer
         .getOrElse(throw new InvalidSchedule(f"No buffer allocated at application time for"))(x - f.x.loopStartOffset, y - f.y.loopStartOffset)
  }

  def mkFunc(f: (Rep[Int], Rep[Int]) => RGBVal, dom: Domain, id: Int): Func = {
    new CompilerFunc(f, dom, id)
  }
}
