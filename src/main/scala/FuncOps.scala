package sepia

import scala.collection.mutable.{Map => MMap}
import lms.common._

trait SimpleFuncOps extends Dsl {
  // The api that is presented to the DSL user
  type Func[T]
  type Domain = ((Rep[Int], Rep[Int]), (Rep[Int], Rep[Int]))

  class FuncOps[T:Typ:Numeric:SepiaNum](f: Func[T]) {
    def apply(x: Rep[Int], y: Rep[Int]) =
      funcApply(f, x, y)
  }

  implicit def funcToFuncOps[T:Typ:Numeric:SepiaNum](f: Func[T]) = new FuncOps(f)

  def funcApply[T:Typ:Numeric:SepiaNum](f: Func[T], x: Rep[Int], y: Rep[Int]): RGBVal[T]

  def mkFunc[T:Typ:Numeric:SepiaNum](f: (Rep[Int], Rep[Int]) => RGBVal[T],
             dom: Domain, id: Int): Func[T]
}

trait CompilerFuncOps extends SimpleFuncOps with CompilerImageOps {
  // The api that is presented to the DSL compiler
  var nApps = 0

  class Dim(val min: Rep[Int], val max: Rep[Int],
            val name: String, val f: Func[_]) {
		protected var value: Option[Rep[Int]] = None
    // Shading name is the name of the variable, except for
    // outer variables where it is the name of either x or y,
    // depending on where it was originally split from

    val shadowingName = name

    println(f"$f $name has min values $min")

    private var offset: Option[Rep[Int]] = None
    private var loopLowerBound: Option[Rep[Int]] = None
    private var loopUpperBound: Option[Rep[Int]] = None
    private var shadowingLowerBound: Option[Rep[Int]] = None
    private var shadowingUpperBound: Option[Rep[Int]] = None

		def v: Rep[Int] = value.getOrElse(throw new InvalidSchedule(f"Unbound variable at $name for $f"))

    def vSave: Rep[Int] = value.getOrElse(0)

    def shadowingLb(): Rep[Int] = looplb

    def shadowingUb(): Rep[Int] = loopub

    def looplb: Rep[Int] = {
      loopLowerBound.getOrElse(throw new InvalidSchedule(f"Unbound looplb for $name"))
    }

    def looplb_=(newVal: Rep[Int]) = loopLowerBound = Some(newVal)

    def loopub: Rep[Int] = {
      loopUpperBound.getOrElse(throw new InvalidSchedule(f"Unbound loopub for $name"))
    }

    def loopub_=(newVal: Rep[Int]) = loopUpperBound = Some(newVal)

    def dimOffset: Rep[Int] = {
      offset.getOrElse(throw new InvalidSchedule(f"Unbound loop offset for $name"))
    }

    def dimOffset_=(new_val: Rep[Int]) = offset = Some(new_val)

    def dimDefined: Boolean = value.isDefined

    override def toString() = name

		// TODO: Why is this not working with _= syntax?
		def v_=(new_val: Rep[Int]) = {
			value = Some(new_val)
		}

    val scaleRatio = 1

    val splitFactor = 1

    val pseudoLoops: Map[(Func[_], String), Dim] = Map((f, shadowingName) -> this)
	}

  class SplitDim(min: Rep[Int], max: Rep[Int], name: String, f: Func[_],
                 var outer: Dim, var inner: Dim, override val splitFactor: Int, val old: Dim) extends Dim(min, max, name, f) {

    override def v: Rep[Int] = outer.v + inner.vSave

    override def vSave: Rep[Int] = outer.v + inner.vSave

    override def v_=(new_val: Rep[Int]) = {
      throw new Exception("Error: should not be directly assigning to a split variable")
    }

    override def shadowingLb(): Rep[Int] = {
      old.shadowingLb
    }

    override def shadowingUb(): Rep[Int] = {
      old.shadowingUb
    }

    override def dimDefined: Boolean = inner.dimDefined
  }

  class OuterDim(min: Rep[Int], max: Rep[Int], name: String, f: Func[_],
                 sName: String, sRatio: Int, val old: Dim, override val splitFactor: Int) extends Dim(min, max, name, f) {
      // scale ratio is the ratio of this dimension to the original x or y
      override val scaleRatio = sRatio
      override val shadowingName = sName

      override def v: Rep[Int] = value.getOrElse(throw new InvalidSchedule(f"Unbound variable at $name for $f")) * splitFactor

      override def shadowingLb(): Rep[Int] = {
        old.shadowingLb
      }

      override def shadowingUb(): Rep[Int] = {
        old.shadowingUb
      }

      override def dimDefined: Boolean = false

      override val pseudoLoops: Map[(Func[_], String), Dim] = Map((f, shadowingName) -> this)
  }

  class FusedDim(min: Rep[Int], max: Rep[Int], name: String, f: Func[_],
                 val inner: Dim, val outer: Dim) extends Dim(min, max, name, f) {
    private def innerWidth = inner.loopub - inner.looplb

    override def v_=(newVal: Rep[Int]) = {
      inner.v_=((newVal - inner.looplb) % innerWidth + inner.looplb)
      outer.v_=((newVal - inner.looplb) / innerWidth)
      value = Some(newVal)
    }

    override val pseudoLoops = inner.pseudoLoops ++ outer.pseudoLoops
  }

  class CompilerFunc[T:Typ:Numeric:SepiaNum](val f: (Rep[Int], Rep[Int]) => RGBVal[T],
                                             val dom: Domain, val id: Int) {
    def x = vars("x")
    def y = vars("y")
    var finalFunc: Boolean = false

    val domain = Map("x" -> dom._1, "y" -> dom._2)

    val vars = MMap("x" -> new Dim(dom._1._1, dom._1._2, "x", this),
                    "y" -> new Dim(dom._2._1, dom._2._2, "y", this))
    var inlined = true
    var computeRoot = false
    var storeRoot = false
    var storeAt: Option[Dim] = None
    var computeAt: Option[Dim] = None
    var buffer: Option[Buffer[T]] = None

    override def toString(): String = id.toString

    def compute() = {
      f(x.v, y.v)
    }

    def storeInBuffer(vs: RGBVal[T]) = buffer match {
      case Some(b) => b(x.v - x.dimOffset, y.v - y.dimOffset) = vs
      case None => throw new InvalidSchedule(f"No buffer allocated at storage time for ")
    }

    def setOffsets(offsets: List[(String, Rep[Int])]) = {
      offsets.foreach({case (v, off) => {
        println(f"offset: $off")
        vars(v).dimOffset_=(off)
      }})
    }

    def allocateNewBuffer() {
      buffer = allocateNewBuffer(x.max - x.min, y.max - y.min)
    }

    def deallocBuffer() {
      if (!finalFunc) buffer.getOrElse(throw new Exception("Error: Trying to free unalloced buffer")).free()
      else ()
    }

    def allocateNewBuffer(m: Rep[Int], n: Rep[Int]) {
      println(f"Buffer allocation: $id has type ${typ[T]}")
      buffer = Some(NewBuffer[T](m, n))
    }

    def domWidth = x.max - x.min
    def domHeight = y.max - y.min

    def split(v: String, outer: String, inner: String, splitFactor: Int) = {

      def updateSplitDims(oldDim: Dim, newDim: Dim) = {
        vars.foreach({ case (f, d) => {
          if (d.isInstanceOf[SplitDim]) {
            val splitDim = d.asInstanceOf[SplitDim]
            if (splitDim.outer == oldDim) splitDim.outer = newDim
            if (splitDim.inner == oldDim) splitDim.inner = newDim
          }
        }})
      }

      val oldDim = vars(v)
      val innerDim = new Dim(oldDim.min, oldDim.min + splitFactor, oldDim.shadowingName, this)
      val outerDim = new OuterDim(0, (oldDim.max - oldDim.min - splitFactor) / splitFactor,
          outer, this, oldDim.shadowingName, oldDim.scaleRatio * splitFactor, oldDim, splitFactor)

      vars(outer) = outerDim
      vars(inner) = innerDim
      vars(v) = new SplitDim(oldDim.min, oldDim.max,
        oldDim.name, oldDim.f,
        outerDim, innerDim, splitFactor, oldDim)

      updateSplitDims(oldDim, vars(v))
    }

    def fuse(v: String, outer: String, inner: String) = {
      val oldOuter = vars(outer)
      val fusedMin = inner.min + (inner.max - inner.min) * outer.min
      val fuseMax = inner.max + (inner.max - inner.max) * outer.max
      val fusedVariable = new FusedDim(fusedMin, fuseMax, oldOuter.shadowingName, this, vars(inner), vars(outer))
      vars(v) = fusedVariable
    }
  }

  type Func[T] = CompilerFunc[T]

  override def funcApply[T:Typ:Numeric:SepiaNum](f: Func[T], x: Rep[Int], y: Rep[Int]): RGBVal[T] = {
    //println(f"computing ${f.id}")
    nApps += 1
    if (f.inlined) f.f(x, y)
    else {
        val r = f.buffer
                 .getOrElse(throw new InvalidSchedule(f"No buffer allocated at application time for ${f.id}"))(x - f.x.dimOffset, y - f.y.dimOffset)
        new RGBVal(r.red, r.green, r.blue)
    }
  }

  def mkFunc[T:Typ:Numeric:SepiaNum](f: (Rep[Int], Rep[Int]) => RGBVal[T], dom: Domain, id: Int): Func[T] = {
    new CompilerFunc(f, dom, id)
  }
}
