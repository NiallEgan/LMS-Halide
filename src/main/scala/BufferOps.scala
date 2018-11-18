package sepia

import lms.common._

trait ImageBufferOps extends Base with ArrayOps with PrimitiveOps {
  case class Buffer(val width: Rep[Int], val a: Rep[Array[UShort]])
  type UShort = Int

  object NewBuffer {
    def apply(width: Rep[Int], height: Rep[Int]) = newBuffer(width, height)
  }

  implicit def bufferToBufferOps(b: Buffer) = new BufferOps(b)

  class BufferOps(b: Buffer) {
    def apply(x: Rep[Int], y: Rep[Int]) =
      bufferApply(b, x, y)
    def update(x: Rep[Int], y: Rep[Int], v: Rep[UShort]) =
      bufferUpdate(b, x, y, v)
  }

  class InArrayOps(b: Rep[Array[UShort]]) {
    def apply(x: Rep[Int], y: Rep[Int])(implicit width: Int): Rep[UShort] = {
      array_apply[UShort](b, x + y * width)
    }
  }

  implicit def inArrayToInArrayOps(b: Rep[Array[UShort]]): InArrayOps = {
    new InArrayOps(b)
  }

  def newBuffer(width: Rep[Int], height: Rep[Int]): Buffer

  def bufferApply(b: Buffer, x: Rep[Int], y: Rep[Int]): Rep[UShort]
  def bufferUpdate(b: Buffer, x: Rep[Int], y: Rep[Int], v: Rep[UShort]): Rep[Unit]
}

trait ImageBufferOpsExp extends ImageBufferOps
                        with ArrayOpsExpOpt with PrimitiveOpsExpOpt {

  override def newBuffer(m: Exp[Int], n: Exp[Int]) = {
    Buffer(m, array_obj_new[UShort](m * n))
  }

  override def bufferApply(b: Buffer, x: Exp[Int], y: Exp[Int]) = {
    array_apply(b.a, x + b.width * y)
  }

  override def bufferUpdate(b: Buffer, x: Exp[Int], y: Exp[Int], v: Rep[UShort]) = {
    array_update[UShort](b.a, x + b.width * y, v)
  }
}
