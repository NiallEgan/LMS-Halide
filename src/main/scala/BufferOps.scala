package sepia

import lms.common._

trait ImageBufferOps extends RGBValOps {
  case class Buffer(val width: Rep[Int], val height: Rep[Int], val a: Rep[Array[UChar]]) {
    def apply(x: Rep[Int], y: Rep[Int]) =
      bufferApply(this, x, y)
    def free(): Rep[Unit] = bufferFree(this)
  }

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

  case class MemCpy(src: Rep[Array[UChar]], dest: Rep[Array[UChar]], size: Rep[Int])
    extends Def[Unit]
  case class IntToDoubleConversion(x: Rep[Int]) extends Def[Double]
  case class DoubleToIntConversion(x: Rep[Double]) extends Def[Int]
  case class ArrayFree(b: Rep[Array[UChar]]) extends Def[Unit]


  override def newBuffer(m: Exp[Int], n: Exp[Int]) = {
    Buffer(m, n, array_obj_new[UChar](m * n * 3))
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
    val sepiaNum = implicitly[ScalarConvertable[T]]
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
