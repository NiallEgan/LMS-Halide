package sepia

import lms.common._

trait ImageBufferOps extends PrimitiveOps with ArrayOps with ShortOps {
  case class US(v: Int)

  type UShort = Short

  case class Buffer(val width: Rep[Int], val height: Rep[Int], val a: Rep[Array[UShort]])
  object NewBuffer {
    def apply(width: Rep[Int], height: Rep[Int]) = newBuffer(width, height)
  }


  class RGBValOps(v: RGBVal) {
    def +(other: RGBVal): RGBVal = RGBVal(v.red + other.red,
                                          v.green + other.green,
                                          v.blue + other.blue)
    def +(other: Rep[Int]): RGBVal = RGBVal(v.red + other,
                                               v.green + other,
                                               v.blue + other)

    def -(other: RGBVal): RGBVal = RGBVal(v.red - other.red,
                                          v.green - other.green,
                                          v.blue - other.blue)
    def -(other: Rep[Int]): RGBVal = RGBVal(v.red - other,
                                               v.green - other,
                                               v.blue - other)

    def *(other: RGBVal): RGBVal = RGBVal(v.red * other.red,
                                          v.green * other.green,
                                          v.blue * other.blue)
    def *(other: Rep[Int]): RGBVal = RGBVal(v.red * other,
                                               v.green * other,
                                               v.blue * other)

    def /(other: RGBVal): RGBVal = RGBVal(v.red / other.red,
                                          v.green / other.green,
                                          v.blue / other.blue)
    def /(other: Rep[Int]): RGBVal = RGBVal(v.red / other,
                                               v.green / other,
                                               v.blue / other)
  }

  implicit def RGBValToOps(v: RGBVal): RGBValOps = new RGBValOps(v)

  case class RGBVal(red: Rep[Int], green: Rep[Int], blue: Rep[Int])

  implicit def bufferToBufferOps(b: Buffer): BufferOps = new BufferOps(b)

  class BufferOps(b: Buffer) {
    def apply(x: Rep[Int], y: Rep[Int]) =
      bufferApply(b, x, y)
    def update(x: Rep[Int], y: Rep[Int], v: RGBVal) =
      bufferUpdate(b, x, y, v)
  }

  class InArrayOps(b: Rep[Array[UShort]]) {
    def apply(x: Rep[Int], y: Rep[Int])(implicit width: Int): Rep[Int] = {
      s2i(array_apply[UShort](b, x + y * width))
    }
  }

  implicit def inArrayToInArrayOps(b: Rep[Array[UShort]]): InArrayOps = {
    new InArrayOps(b)
  }

  def newBuffer(width: Rep[Int], height: Rep[Int]): Buffer

  def bufferApply(b: Buffer, x: Rep[Int], y: Rep[Int]): RGBVal
  def bufferUpdate(b: Buffer, x: Rep[Int], y: Rep[Int], v: RGBVal): Rep[Unit]
}

trait ImageBufferOpsExp extends ImageBufferOps
                        with ArrayOpsExpOpt with PrimitiveOpsExpOpt {

  override def newBuffer(m: Exp[Int], n: Exp[Int]) = {
    Buffer(m, n, array_obj_new[UShort](m * n * 3))
  }

  override def bufferApply(b: Buffer, x: Exp[Int], y: Exp[Int]) = {
    RGBVal(s2i(array_apply(b.a, 3 * (x + b.width * y) + 2)),
           s2i(array_apply(b.a, 3 * (x + b.width * y) + 1)),
           s2i(array_apply(b.a, 3 * (x + b.width * y))))
  }

  override def bufferUpdate(b: Buffer, x: Exp[Int], y: Exp[Int], v: RGBVal) = {
    array_update(b.a, 3 * (x + b.width * y) + 2, i2s(v.red))
    array_update(b.a, 3 * (x + b.width * y) + 1, i2s(v.green))
    array_update(b.a, 3 * (x + b.width * y), i2s(v.blue))
  }
}
