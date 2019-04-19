package sepia

import scala.reflect.SourceContext

import lms.common._

trait ImageBufferOps extends RGBValOps {

  case class Buffer[T:Typ:Numeric:SepiaNum](val width: Rep[Int],
                                            val height: Rep[Int],
                                            val a: Rep[Array[T]]) {
    def apply(x: Rep[Int], y: Rep[Int]) = bufferApply(this, x, y)
    def free(): Rep[Unit] = bufferFree(this)

    val typEv = typ[T]
    val numEv = implicitly[Numeric[T]]
    val sepEv = implicitly[SepiaNum[T]]
  }

  def bufferApply[T:Typ:Numeric:SepiaNum](b: Buffer[T], x: Rep[Int],
                                          y: Rep[Int]): RGBVal[T]
  def bufferFree[T:Typ:Numeric:SepiaNum](b: Buffer[T]): Rep[Unit]
}

trait CompilerImageOps extends ImageBufferOps {
  object NewBuffer {
    def apply[T:Typ:Numeric:SepiaNum](width: Rep[Int], height: Rep[Int]) = newBuffer[T](width, height)
  }

  implicit def bufferToBufferOps[T:Typ:Numeric:SepiaNum](b: Buffer[T]): BufferOps[T] = new BufferOps(b)

  class BufferOps[T:Typ:Numeric:SepiaNum](b: Buffer[T]) {
    def update(x: Rep[Int], y: Rep[Int], v: RGBVal[T]) = bufferUpdate(b, x, y, v)
  }

  def newBuffer[T:Typ:Numeric:SepiaNum](width: Rep[Int], height: Rep[Int]): Buffer[T]
  def bufferUpdate[T:Typ:Numeric:SepiaNum](b: Buffer[T], x: Rep[Int], y: Rep[Int], v: RGBVal[T]): Rep[Unit]
  def bufferMemCpy[T:Typ:Numeric:SepiaNum](src: Buffer[T], dest: Buffer[UChar]): Rep[Unit]

}

trait ImageBufferOpsExp extends ImageBufferOps with CompilerImageOps
                        with ArrayOpsExpOpt with PrimitiveOpsExpOpt
                        with OrderingOpsExpOpt {

  case class MemCpy[T:Typ:Numeric:SepiaNum](src: Exp[Array[T]], dest: Exp[Array[UChar]],
                                            size: Exp[Int]) extends Def[Unit] {
    val typEv = typ[T]
    val numEv = implicitly[Numeric[T]]
    val sepEv = implicitly[SepiaNum[T]]
  }
  case class IntToDoubleConversion(x: Exp[Int]) extends Def[Double]
  case class DoubleToIntConversion(x: Exp[Double]) extends Def[Int]
  case class ShortToDoubleConversion(x: Exp[Short]) extends Def[Double]
  case class ArrayFree[T:Typ:Numeric:SepiaNum](b: Exp[Array[T]]) extends Def[Unit] {
    val typEv = typ[T]
    val numEv = implicitly[Numeric[T]]
    val sepEv = implicitly[SepiaNum[T]]
  }


  override def mirror[A:Typ](e: Def[A], f: Transformer)(implicit pos: SourceContext): Exp[A] =
    (e match {
      case af@ArrayFree(x) => arrayFree(x)(af.typEv, af.numEv, af.sepEv)
      case Reflect(af@ArrayFree(x), u, es) => {
        reflectMirrored(Reflect(ArrayFree(f(x))(af.typEv, af.numEv, af.sepEv), mapOver(f, u), f(es)))(mtyp1[A], pos)
      }
      case IntToDoubleConversion(x) => int2double(f(x))
      case DoubleToIntConversion(x) => double2int(f(x))
      case Reflect(mc@MemCpy(src, dest, len), u, es) => {
         reflectMirrored(Reflect(MemCpy(f(src),f(dest),f(len))(mc.typEv, mc.numEv, mc.sepEv),
                                 mapOver(f,u), f(es)))(mtyp1[A], pos)
      }
      case _ => super.mirror(e, f)
    }).asInstanceOf[Exp[A]]


  override def newBuffer[T:Typ:Numeric:SepiaNum](m: Exp[Int], n: Exp[Int]) = {
    Buffer(m, n, array_obj_new[T](m * n * 3))
  }

  override def bufferApply[T:Typ:Numeric:SepiaNum](b: Buffer[T], x: Exp[Int], y: Exp[Int]) = {
    val base_x = ordering_max(unit(0), ordering_min(x, b.width - 1))
    val base_y = ordering_max(unit(0), ordering_min(y, b.height - 1))
    RGBVal(array_apply(b.a, 3 * (base_x + b.width * base_y) + 2),
           array_apply(b.a, 3 * (base_x + b.width * base_y) + 1),
           array_apply(b.a, 3 * (base_x + b.width * base_y)))
  }

  override def bufferFree[T:Typ:Numeric:SepiaNum](b: Buffer[T]) = {
    arrayFree(b.a)  // todo: Should we be marking some side effects here?
  }
  def arrayFree[T:Typ:Numeric:SepiaNum](a: Rep[Array[T]]) = {
    ArrayFree(a)
  }

  override def bufferUpdate[T:Typ:Numeric:SepiaNum](b: Buffer[T], x: Exp[Int], y: Exp[Int], v: RGBVal[T]) = {
    val sepiaNum = implicitly[SepiaNum[T]]
    val base_x = x
    val base_y = y
    //val base_x = ordering_max(unit(0), ordering_min(x, b.width) - 1)
    //val base_y =  ordering_max(unit(0), ordering_min(y, b.height) - 1)
    array_update[T](b.a, 3 * (base_x + b.width * base_y) + 2, v.red)
    array_update[T](b.a, 3 * (base_x + b.width * base_y) + 1, v.green)
    array_update[T](b.a, 3 * (base_x + b.width * base_y), v.blue)
  }

  // We only use this at the very end, so keep it as just Buffer[UChar]
  override def bufferMemCpy[T:Typ:Numeric:SepiaNum](src: Buffer[T], dest: Buffer[UChar]): Exp[Unit] = {
    bufferMemCpy(src.a, dest.a, src.width * src.height * 3)
  }

  def bufferMemCpy[T:Typ:Numeric:SepiaNum](src: Exp[Array[T]], dest: Exp[Array[UChar]],
                   len: Rep[Int]) = {
    /*reflectWrite(dest)(*/MemCpy(src, dest, len) // unsafe... like ArrayCopy, the issue is some weird sharing...
  }

  def int2double(v: Exp[Int]): Exp[Double] = IntToDoubleConversion(v)
  def double2int(v: Exp[Double]): Exp[Int] = DoubleToIntConversion(v)
  def short2double(v: Exp[Short]): Exp[Double] = ShortToDoubleConversion(v)
  def double2short(v: Exp[Double]): Exp[Short] = d2s(v)
  def int2short(v: Exp[Int]): Exp[Short] = i2s(v)
}
