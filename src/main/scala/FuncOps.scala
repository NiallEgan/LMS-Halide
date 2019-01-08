package sepia

import scala.collection.mutable.{Map => MMap}
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

trait CompilerFuncOps extends SimpleFuncOps with CompilerImageOps {
  // The api that is presented to the DSL compiler

  class Dim(val min: Rep[Int], val max: Rep[Int],
            val name: String, val f: Func) {
		private var value: Option[Rep[Int]] = None
    // Shading name is the name of the variable, except for
    // outer variables where it is the name of either x or y,
    // depending on where it was originally split from

    val shadowingName = name

    private var offset: Option[Rep[Int]] = None
    private var loopLowerBound: Option[Rep[Int]] = None
    private var shadowingUpperBound: Option[Rep[Int]] = None

		def v: Rep[Int] = value.getOrElse(throw new InvalidSchedule(f"Unbound variable at $name for $f"))

    def shadowingUb_=(newVal: Rep[Int]) = {
      shadowingUpperBound = Some(newVal)
    }

    def shadowingUb(): Rep[Int] = {
      shadowingUpperBound.getOrElse(throw new InvalidSchedule(f"Unbound loopub for $name"))
    }

    def looplb: Rep[Int] = {
      loopLowerBound.getOrElse(throw new InvalidSchedule(f"Unbound looplb for $name"))
    }

    def looplb_=(new_val: Rep[Int]) = {
      loopLowerBound = Some(new_val)
    }

    def dimOffset: Rep[Int] = {
      offset.getOrElse(throw new InvalidSchedule(f"Unbound loop offset for $name"))
    }

    def dimOffset_=(new_val: Rep[Int]) = {
      offset = Some(new_val)
    }

    override def toString() = name

		// TODO: Why is this not working with _= syntax?
		def v_=(new_val: Rep[Int]) = {
			value = Some(new_val)
		}

    val scaleRatio = 1

    def adjustLower(x: Rep[Int]) = x
    def adjustUpper(x: Rep[Int]) = x
	}

  class SplitDim(min: Rep[Int], max: Rep[Int], name: String, f: Func,
                 val outer: Dim, val inner: Dim, val splitFactor: Int) extends Dim(min, max, name, f) {
      override def v: Rep[Int] = {
        val clampedOuter: Rep[Int] =
          if (outer.v * splitFactor > outer.shadowingUb - splitFactor) outer.shadowingUb - splitFactor
          else outer.v * splitFactor
        clampedOuter + inner.v

      }
      override def v_=(new_val: Rep[Int]) = {
        throw new Exception("Error: should not be directly assigning to a split variable")
      }

      override def dimOffset: Rep[Int] = {
        super.dimOffset
      }
  }

  class OuterDim(min: Rep[Int], max: Rep[Int], name: String, f: Func,
                 sName: String, sRatio: Int) extends Dim(min, max, name, f) {
      // scale ratio is the ratio of this dimension to the original x or y
      override val scaleRatio = sRatio
      override val shadowingName = sName

      override def adjustLower(x: Rep[Int]) = {
        x / scaleRatio
      }

      override def adjustUpper(x: Rep[Int]) = {
        if (x % scaleRatio == 0) x / scaleRatio else x / scaleRatio + 1
      }
  }

  class CompilerFunc(val f: (Rep[Int], Rep[Int]) => RGBVal,
                     dom: Domain, val id: Int) {
    def x = vars("x")
    def y = vars("y")

    val vars = MMap("x" -> new Dim(dom._1._1, dom._1._2, "x", this),
                    "y" -> new Dim(dom._2._1, dom._2._2, "y", this))
    var inlined = true
    var computeRoot = false
    var storeRoot = false
    var storeAt: Option[Dim] = None
    var computeAt: Option[Dim] = None
    var buffer: Option[Buffer] = None

    def compute() = f(x.v, y.v)

    def storeInBuffer(vs: RGBVal) = buffer match {
      case Some(b) => b(x.v - x.dimOffset, y.v - y.dimOffset) = vs
      case None => throw new InvalidSchedule(f"No buffer allocated at storage time for ")
    }

    def setOffsets(offsets: List[(String, Rep[Int])]) = {
      offsets.foreach({case (v, off) => vars(v).dimOffset_=(off)})
    }

    def allocateNewBuffer() {
      buffer = Some(NewBuffer(x.max - x.min, y.max - y.min))
    }

    def allocateNewBuffer(m: Rep[Int], n: Rep[Int]) {
      buffer = Some(NewBuffer(m, n))
    }

    def domWidth = x.max - x.min
    def domHeight = y.max - y.min

    def split(v: String, outer: String, inner: String, splitFactor: Int) = {
      val innerDim = new Dim(0, splitFactor, inner, this)
      // We floor the bottom and ceil at the top to make sure
      // that we hit every value: todo - a clamp
      val oldDim = vars(v)
      val x = oldDim.max - splitFactor
      val outerDim = new OuterDim(oldDim.min / splitFactor,
          if (x % splitFactor == 0) x / splitFactor + 1 else x / splitFactor + 2,
          outer, this, oldDim.shadowingName, oldDim.scaleRatio * splitFactor)
      vars(v) = new SplitDim(oldDim.min, oldDim.max,
                             oldDim.name, oldDim.f,
                             outerDim, innerDim, splitFactor)
      vars(outer) = outerDim
      vars(inner) = innerDim
    }
  }

  type Func = CompilerFunc

  override def funcApply(f: Func, x: Rep[Int], y: Rep[Int]): RGBVal = {
    if (f.inlined) f.f(x, y)
    else f.buffer
         .getOrElse(throw new InvalidSchedule(f"No buffer allocated at application time for"))(x - f.x.dimOffset, y - f.y.dimOffset)
  }

  def mkFunc(f: (Rep[Int], Rep[Int]) => RGBVal, dom: Domain, id: Int): Func = {
    new CompilerFunc(f, dom, id)
  }
}
