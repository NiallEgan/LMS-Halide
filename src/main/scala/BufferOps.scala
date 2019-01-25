package sepia

import lms.common._

trait ImageBufferOps extends PrimitiveOps with ArrayOps
                     with ShortOps with NumericOps {
  type UShort = Short

  trait ScalarConvertable[T] {
    implicit def int2T(v: Rep[Int]): Rep[T]
    implicit def T2Double(v: Rep[T]): Rep[Double]
    implicit def T2short(v: Rep[T]): Rep[Short]
  }

  trait CNumeric[T] {
    def plus(a: Rep[T], b: Rep[T]): Rep[T]
    def times(a: Rep[T], b: Rep[T]): Rep[T]
    def divide(a: Rep[T], b: Rep[T]): Rep[T]
    def minus(a: Rep[T], b: Rep[T]): Rep[T]
  }

  def makeCNumeric[T:Numeric:Typ]: CNumeric[T] = {
    new CNumeric[T] {
      override def plus(a: Rep[T], b: Rep[T]) =  numeric_plus(a, b)
      override def times(a: Rep[T], b: Rep[T]) = numeric_times(a, b)
      override def divide(a: Rep[T], b: Rep[T]) = numeric_divide(a, b)
      override def minus(a: Rep[T], b: Rep[T]) = numeric_minus(a, b)
    }
  }

  //val __mm128Numeric = new CNumeric[__mm128] {

  //}

  def int2double(v: Rep[Int]): Rep[Double]
  def double2int(v: Rep[Double]): Rep[Int]
  def double2short(v: Rep[Double]): Rep[Short]
  def int2short(v: Rep[Int]): Rep[Short]


  class ScalarConvertableDouble extends ScalarConvertable[Double] {
    override def int2T(v: Rep[Int]): Rep[Double] = int2double(v)
    override def T2Double(v: Rep[Double]): Rep[Double] = v
    override def T2short(v: Rep[Double]): Rep[Short] = double2short(v)
  }
  class ScalarConvertableInt extends ScalarConvertable[Int] {
    override def int2T(v: Rep[Int]): Rep[Int] = v
    override def T2Double(v: Rep[Int]): Rep[Double] = int2double(v)
    override def T2short(v: Rep[Int]): Rep[Short] = int2short(v)

  }
  implicit val scalarConvertableDouble = new ScalarConvertableDouble()
  implicit val scalarConvertableInt = new ScalarConvertableInt()


  case class Buffer(val width: Rep[Int], val height: Rep[Int], val a: Rep[Array[UShort]]) {
    def apply(x: Rep[Int], y: Rep[Int]) =
      bufferApply(this, x, y)
    def free(): Rep[Unit] = bufferFree(this)
  }

  class RGBValOps[T:Numeric:Typ:ScalarConvertable](v: RGBVal[T]) {
    val scalarConvertable = implicitly[ScalarConvertable[T]]
    import scalarConvertable._
    def +(other: RGBVal[T]): RGBVal[T] = RGBVal(v.red + other.red,
                                          v.green + other.green,
                                          v.blue + other.blue)
    def +(other: Rep[T]): RGBVal[T] = RGBVal(numeric_plus(v.red, other),
                                              numeric_plus(v.green, other),
                                              numeric_plus(v.blue, other))
    def +(other: Rep[Double])(implicit d1: DummyImplicit): RGBVal[Double] = RGBVal[Double](other + v.red,
                                            v.green + other,
                                            v.blue + other)

    def -(other: RGBVal[T]): RGBVal[T] = RGBVal(v.red - other.red,
                                          v.green - other.green,
                                          v.blue - other.blue)
    def -(other: Rep[T]): RGBVal[T] = RGBVal(v.red - other,
                                               v.green - other,
                                               v.blue - other)
    def -(other: Rep[Double])(implicit d1: DummyImplicit): RGBVal[Double] =
                                RGBVal(v.red - other,
                                       v.green - other,
                                       v.blue - other)

    def *(other: RGBVal[T]): RGBVal[T] = RGBVal(v.red * other.red,
                                                v.green * other.green,
                                                v.blue * other.blue)
    def *(other: Rep[T]): RGBVal[T] = RGBVal(v.red * other,
                                             v.green * other,
                                             v.blue * other)
    def *(other: Rep[Double])(implicit d1: DummyImplicit): RGBVal[Double] =
                              RGBVal(v.red * other,
                                     v.green * other,
                                     v.blue * other)

    def /(other: RGBVal[T]): RGBVal[T] = RGBVal(v.red / other.red,
                                                v.green / other.green,
                                                v.blue / other.blue)
    def /(other: Rep[T]): RGBVal[T] = RGBVal(v.red / other,
                                             v.green / other,
                                             v.blue / other)
    def /(other: Rep[Double])(implicit d1: DummyImplicit): RGBVal[Double] =
                                RGBVal(v.red / other,
                                       v.green / other,
                                       v.blue / other)
  }

  implicit def RGBValToOps[T:Typ:Numeric:ScalarConvertable](v: RGBVal[T]): RGBValOps[T] = new RGBValOps(v)

  implicit class RGBEnrichedDoubles(v: Rep[Double]) {
    def makeRGBValOp[T:Typ:Numeric:ScalarConvertable](f: (Rep[Double], Rep[Double]) => Rep[Double])(rgb: RGBVal[T]): RGBVal[Double] = {
      val sNum = implicitly[ScalarConvertable[T]]
      RGBVal(f(v,sNum.T2Double(rgb.red)),
             f(v, sNum.T2Double(rgb.green)),
             f(v, sNum.T2Double(rgb.blue)))
    }
    def *[T:Typ:Numeric:ScalarConvertable](rgb: RGBVal[T]): RGBVal[Double] = {
      makeRGBValOp[T](numeric_times)(rgb)
    }

    def /[T:Typ:Numeric:ScalarConvertable](rgb: RGBVal[T]): RGBVal[Double] = {
      makeRGBValOp[T](numeric_divide)(rgb)
    }

    def -[T:Typ:Numeric:ScalarConvertable](rgb: RGBVal[T]): RGBVal[Double] = {
      makeRGBValOp[T](numeric_minus)(rgb)
    }

    def +[T:Typ:Numeric:ScalarConvertable](rgb: RGBVal[T]): RGBVal[Double] = {
      makeRGBValOp[T](numeric_plus)(rgb)
    }
  }

  implicit class RGBEnrichedInts(v: Rep[Int]) {
    def makeRGBValOp[T:Typ:Numeric:ScalarConvertable](f: (Rep[T], Rep[T]) => Rep[T])(rgb: RGBVal[T]): RGBVal[T] = {
      val sNum = implicitly[ScalarConvertable[T]]
      RGBVal(f(sNum.int2T(v),rgb.red),
             f(sNum.int2T(v), rgb.green),
             f(sNum.int2T(v), rgb.blue))
    }
    def *[T:Typ:Numeric:ScalarConvertable](rgb: RGBVal[T]): RGBVal[T] = {
      makeRGBValOp[T](numeric_times)(rgb)
    }

    def /[T:Typ:Numeric:ScalarConvertable](rgb: RGBVal[T]): RGBVal[T] = {
      makeRGBValOp[T](numeric_divide)(rgb)
    }

    def -[T:Typ:Numeric:ScalarConvertable](rgb: RGBVal[T]): RGBVal[T] = {
      makeRGBValOp[T](numeric_minus)(rgb)
    }

    def +[T:Typ:Numeric:ScalarConvertable](rgb: RGBVal[T]): RGBVal[T] = {
      makeRGBValOp[T](numeric_plus)(rgb)
    }
  }

  case class RGBVal[T:Typ:Numeric:ScalarConvertable](red: Rep[T], green: Rep[T], blue: Rep[T])

  def bufferApply(b: Buffer, x: Rep[Int], y: Rep[Int]): RGBVal[Int]
  def bufferFree(b: Buffer): Rep[Unit]
}

trait CompilerImageOps extends ImageBufferOps {
  object NewBuffer {
    def apply(width: Rep[Int], height: Rep[Int]) = newBuffer(width, height)
  }

  implicit def bufferToBufferOps(b: Buffer): BufferOps = new BufferOps(b)

  class BufferOps(b: Buffer) {
    def update[T:Typ:Numeric:ScalarConvertable](x: Rep[Int], y: Rep[Int], v: RGBVal[T]) = bufferUpdate(b, x, y, v)
    def memcpy(src: Buffer) = bufferMemCpy(src, b)
  }

  def newBuffer(width: Rep[Int], height: Rep[Int]): Buffer
  def bufferUpdate[T:Typ:Numeric:ScalarConvertable](b: Buffer, x: Rep[Int], y: Rep[Int], v: RGBVal[T]): Rep[Unit]
  def bufferMemCpy(src: Buffer, dest: Buffer): Rep[Unit]

}

trait ImageBufferOpsExp extends ImageBufferOps with CompilerImageOps
                        with ArrayOpsExpOpt with PrimitiveOpsExpOpt {

  case class MemCpy(src: Rep[Array[UShort]], dest: Rep[Array[UShort]], size: Rep[Int])
    extends Def[Unit]
  case class IntToDoubleConversion(x: Rep[Int]) extends Def[Double]
  case class DoubleToIntConversion(x: Rep[Double]) extends Def[Int]
  case class ArrayFree(b: Rep[Array[UShort]]) extends Def[Unit]


  override def newBuffer(m: Exp[Int], n: Exp[Int]) = {
    Buffer(m, n, array_obj_new[UShort](m * n * 3))
  }

  override def bufferApply(b: Buffer, x: Exp[Int], y: Exp[Int]) = {
    RGBVal(s2i(array_apply(b.a, 3 * (x + b.width * y) + 2)),
           s2i(array_apply(b.a, 3 * (x + b.width * y) + 1)),
           s2i(array_apply(b.a, 3 * (x + b.width * y))))
  }

  override def bufferFree(b: Buffer) = {
    ArrayFree(b.a)
  }

  override def bufferUpdate[T:Typ:Numeric:ScalarConvertable](b: Buffer, x: Exp[Int], y: Exp[Int], v: RGBVal[T]) = {
    val sepiaNum = implicitly[SepiaNum[T]]
    array_update(b.a, 3 * (x + b.width * y) + 2, sepiaNum.T2short(v.red))
    array_update(b.a, 3 * (x + b.width * y) + 1, sepiaNum.T2short(v.green))
    array_update(b.a, 3 * (x + b.width * y), sepiaNum.T2short(v.blue))
  }

  override def bufferMemCpy(src: Buffer, dest: Buffer) = {
    MemCpy(src.a, dest.a, src.width * src.height * 3)
  }

  def int2double(v: Rep[Int]): Rep[Double] = IntToDoubleConversion(v)
  def double2int(v: Rep[Double]): Rep[Int] = DoubleToIntConversion(v)
  def double2short(v: Rep[Double]): Rep[Short] = d2s(v)
  def int2short(v: Rep[Int]): Rep[Short] = i2s(v)
}
