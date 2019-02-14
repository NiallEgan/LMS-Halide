package sepia

import lms.common._
import scala.lms.util.OverloadHack


trait RGBValOps extends PrimitiveOps with ArrayOps
                with ShortOps with NumericOps {
  type UChar = Short

  trait SepiaNum[T] {
    implicit def int2T(v: Rep[Int]): Rep[T]
    implicit def T2Double(v: Rep[T]): Rep[Double]
    implicit def T2short(v: Rep[T]): Rep[Short]
  }

  def double2short(v: Rep[Double]): Rep[Short]
  def int2short(v: Rep[Int]): Rep[Short]
  def short2double(v: Rep[Short]): Rep[Double]

  class SepiaNumDouble extends SepiaNum[Double] {
    override def int2T(v: Rep[Int]): Rep[Double] = v.toDouble
    override def T2Double(v: Rep[Double]): Rep[Double] = v
    override def T2short(v: Rep[Double]): Rep[Short] = double2short(v)
  }
  class SepiaNumInt extends SepiaNum[Int] {
    override def int2T(v: Rep[Int]): Rep[Int] = v
    override def T2Double(v: Rep[Int]): Rep[Double] = v.toDouble
    override def T2short(v: Rep[Int]): Rep[Short] = int2short(v)
  }
  class SepiaNumShort extends SepiaNum[Short] {
    override def int2T(v: Rep[Int]): Rep[Short] = int2short(v)
    override def T2Double(v: Rep[Short]): Rep[Double] = short2double(v)
    override def T2short(v: Rep[Short]): Rep[Short] = v
  }
  class SepiaNumFloat extends SepiaNum[Float] {
    override def int2T(v: Rep[Int]): Rep[Float] = repIntToRepFloat(v)
    override def T2Double(v: Rep[Float]): Rep[Double] = repFloatToRepDouble(v)
    override def T2short(v: Rep[Float]): Rep[Short] = ???
  }
  implicit val sepiaNumDouble = new SepiaNumDouble()
  implicit val sepiaNumInt = new SepiaNumInt()
  implicit val sepiaNumShort = new SepiaNumShort()
  implicit val sepiaNumFloat = new SepiaNumFloat()

  implicit def constToRgb[T:Numeric:Typ:SepiaNum](v: T): RGBVal[T] = repConstToRgb(unit(v))
  implicit def repConstToRgb[T:Numeric:Typ:SepiaNum](v: Rep[T]) = {
    new RGBVal(v, v, v)
  }

  def sameTypeRGBOp[T:Typ:Numeric:SepiaNum](a: RGBVal[T], b: RGBVal[T], f: (Rep[T], Rep[T]) => Rep[T]) = {
    RGBVal(f(a.red, b.red), f(a.green, b.green), f(a.blue, b.blue))
  }

  trait Widener[T, U] {
    def widenScalar(v: Rep[T]): Rep[U]
  }

  def rgbValWidener[T:Typ:Numeric:SepiaNum, U:Typ:Numeric:SepiaNum](v: RGBVal[T])(implicit w: Widener[T, U]) = {
    RGBVal(w.widenScalar(v.red), w.widenScalar(v.green), w.widenScalar(v.blue))
  }
  // Widenings
  trait WiderThanShort[T] extends Widener[Short, T]

  /*implicit val s2sWidener: WiderThanShort[Short] = new WiderThanShort[Short] {
    override def widenScalar(v: Rep[Short]) = v
  }*/
  implicit val s2iWidener: WiderThanShort[Int] = new WiderThanShort[Int] {
    override def widenScalar(v: Rep[Short]) = repShortToRepInt(v)
  }
  implicit val s2fWidener: WiderThanShort[Float] = new WiderThanShort[Float] {
    override def widenScalar(v: Rep[Short]) = repShortToRepFloat(v)
  }
  implicit val s2dWidener: WiderThanShort[Double] = new WiderThanShort[Double] {
    override def widenScalar(v: Rep[Short]) = repShortToRepDouble(v)
  }


  trait WiderThanInt[T] extends Widener[Int, T]

  /*implicit val i2iWidener: WiderThanInt[Int] = new WiderThanInt[Int] {
    override def widenScalar(v: Rep[Int]) = v
  }*/
  implicit val i2fWidener: WiderThanInt[Float] = new WiderThanInt[Float] {
    override def widenScalar(v: Rep[Int]) = repIntToRepFloat(v)
  }
  implicit val i2dWidener: WiderThanInt[Double] = new WiderThanInt[Double] {
    override def widenScalar(v: Rep[Int]) = repIntToRepDouble(v)
  }

  trait WiderThanFloat[T] extends Widener[Float, T]

  /*implicit val f2fWidener: WiderThanFloat[Float] = new WiderThanFloat[Float] {
    override def widenScalar(v: Rep[Float]) = v
  }*/
  implicit val f2dWidener: WiderThanFloat[Double] = new WiderThanFloat[Double] {
    override def widenScalar(v: Rep[Float]) = repFloatToRepDouble(v)
  }

  trait WiderThanDouble[T] extends Widener[Double, T]

  implicit def rgbValShortToInt(v: RGBVal[Short])(implicit o: Overloaded1): RGBVal[Int] = rgbValWidener(v)
  implicit def rgbValShortToInt(v: RGBVal[Short])(implicit o: Overloaded2): RGBVal[Float] = rgbValWidener(v)
  implicit def rgbValShortToInt(v: RGBVal[Short])(implicit o: Overloaded3): RGBVal[Double] = rgbValWidener(v)
  implicit def rgbValShortToInt(v: RGBVal[Int])(implicit o: Overloaded4): RGBVal[Float] = rgbValWidener(v)
  implicit def rgbValShortToInt(v: RGBVal[Int])(implicit o: Overloaded5): RGBVal[Double] = rgbValWidener(v)
  implicit def rgbValShortToInt(v: RGBVal[Float])(implicit o: Overloaded6): RGBVal[Double] = rgbValWidener(v)

  // Arithmetic ops
  def infix_+(a: RGBVal[Short], b: RGBVal[Short]) = {
    // todo
    sameTypeRGBOp[Int](rgbValWidener(a), rgbValWidener(b), numeric_plus)
  }
  def infix_+(a: RGBVal[Short], b: RGBVal[Int])(implicit o: Overloaded1) = {
    sameTypeRGBOp[Int](rgbValWidener(a), b, numeric_plus)
  }
  def infix_+(a: RGBVal[Short], b: RGBVal[Float])(implicit o: Overloaded2) = {
    sameTypeRGBOp[Float](rgbValWidener(a), b, numeric_plus)
  }
  def infix_+(a: RGBVal[Short], b: RGBVal[Double])(implicit o: Overloaded3) = {
    sameTypeRGBOp[Double](rgbValWidener(a), b, numeric_plus)
  }
  def infix_+(a: RGBVal[Int], b: RGBVal[Short])(implicit o: Overloaded4) = {
    sameTypeRGBOp[Int](a, rgbValWidener(b), numeric_plus)
  }
  def infix_+(a: RGBVal[Int], b: RGBVal[Int])(implicit o: Overloaded5) = {
    sameTypeRGBOp[Int](a, b, numeric_plus)
  }
  def infix_+(a: RGBVal[Int], b: RGBVal[Float])(implicit o: Overloaded6) = {
    sameTypeRGBOp[Float](rgbValWidener(a), b, numeric_plus)
  }
  def infix_+(a: RGBVal[Int], b: RGBVal[Double])(implicit o: Overloaded7) = {
    sameTypeRGBOp[Double](rgbValWidener(a), b, numeric_plus)
  }
  def infix_+(a: RGBVal[Float], b: RGBVal[Short])(implicit o: Overloaded8) = {
    sameTypeRGBOp[Float](a, rgbValWidener(b), numeric_plus)
  }
  def infix_+(a: RGBVal[Float], b: RGBVal[Int])(implicit o: Overloaded9) = {
    sameTypeRGBOp[Float](a, rgbValWidener(b), numeric_plus)
  }
  def infix_+(a: RGBVal[Float], b: RGBVal[Float])(implicit o: Overloaded10) = {
    sameTypeRGBOp[Float](a, b, numeric_plus)
  }
  def infix_+(a: RGBVal[Float], b: RGBVal[Double])(implicit o: Overloaded11) = {
    sameTypeRGBOp[Double](rgbValWidener(a), b, numeric_plus)
  }
  def infix_+(a: RGBVal[Double], b: RGBVal[Short])(implicit o: Overloaded12) = {
    sameTypeRGBOp[Double](a, rgbValWidener(b), numeric_plus)
  }
  def infix_+(a: RGBVal[Double], b: RGBVal[Int])(implicit o: Overloaded13) = {
    sameTypeRGBOp[Double](a, rgbValWidener(b), numeric_plus)
  }
  def infix_+(a: RGBVal[Double], b: RGBVal[Float])(implicit o: Overloaded14) = {
    sameTypeRGBOp[Double](a, rgbValWidener(b), numeric_plus)
  }
  def infix_+(a: RGBVal[Double], b: RGBVal[Double])(implicit o: Overloaded15) = {
    sameTypeRGBOp[Double](a, b, numeric_plus)
  }


  def infix_/(a: RGBVal[Short], b: RGBVal[Short]) = {
    sameTypeRGBOp[Short](a, b, numeric_divide)
  }
  def infix_/(a: RGBVal[Short], b: RGBVal[Int])(implicit o: Overloaded1) = {
    sameTypeRGBOp[Int](rgbValWidener(a), b, numeric_divide)
  }
  def infix_/(a: RGBVal[Short], b: RGBVal[Float])(implicit o: Overloaded2) = {
    sameTypeRGBOp[Float](rgbValWidener(a), b, numeric_divide)
  }
  def infix_/(a: RGBVal[Short], b: RGBVal[Double])(implicit o: Overloaded3) = {
    sameTypeRGBOp[Double](rgbValWidener(a), b, numeric_divide)
  }
  def infix_/(a: RGBVal[Int], b: RGBVal[Short])(implicit o: Overloaded4) = {
    sameTypeRGBOp[Int](a, rgbValWidener(b), numeric_divide)
  }
  def infix_/(a: RGBVal[Int], b: RGBVal[Int])(implicit o: Overloaded5) = {
    sameTypeRGBOp[Int](a, b, numeric_divide)
  }
  def infix_/(a: RGBVal[Int], b: RGBVal[Float])(implicit o: Overloaded6) = {
    sameTypeRGBOp[Float](rgbValWidener(a), b, numeric_divide)
  }
  def infix_/(a: RGBVal[Int], b: RGBVal[Double])(implicit o: Overloaded7) = {
    sameTypeRGBOp[Double](rgbValWidener(a), b, numeric_divide)
  }
  def infix_/(a: RGBVal[Float], b: RGBVal[Short])(implicit o: Overloaded8) = {
    sameTypeRGBOp[Float](a, rgbValWidener(b), numeric_divide)
  }
  def infix_/(a: RGBVal[Float], b: RGBVal[Int])(implicit o: Overloaded9) = {
    sameTypeRGBOp[Float](a, rgbValWidener(b), numeric_divide)
  }
  def infix_/(a: RGBVal[Float], b: RGBVal[Float])(implicit o: Overloaded10) = {
    sameTypeRGBOp[Float](a, b, numeric_divide)
  }
  def infix_/(a: RGBVal[Float], b: RGBVal[Double])(implicit o: Overloaded11) = {
    sameTypeRGBOp[Double](rgbValWidener(a), b, numeric_divide)
  }
  def infix_/(a: RGBVal[Double], b: RGBVal[Short])(implicit o: Overloaded12) = {
    sameTypeRGBOp[Double](a, rgbValWidener(b), numeric_divide)
  }
  def infix_/(a: RGBVal[Double], b: RGBVal[Int])(implicit o: Overloaded13) = {
    sameTypeRGBOp[Double](a, rgbValWidener(b), numeric_divide)
  }
  def infix_/(a: RGBVal[Double], b: RGBVal[Float])(implicit o: Overloaded14) = {
    sameTypeRGBOp[Double](a, rgbValWidener(b), numeric_divide)
  }
  def infix_/(a: RGBVal[Double], b: RGBVal[Double])(implicit o: Overloaded15) = {
    sameTypeRGBOp[Double](a, b, numeric_divide)
  }

  def infix_*(a: RGBVal[Short], b: RGBVal[Short]) = {
    sameTypeRGBOp[Short](a, b, numeric_times)
  }
  def infix_*(a: RGBVal[Short], b: RGBVal[Int])(implicit o: Overloaded1) = {
    sameTypeRGBOp[Int](rgbValWidener(a), b, numeric_times)
  }
  def infix_*(a: RGBVal[Short], b: RGBVal[Float])(implicit o: Overloaded2) = {
    sameTypeRGBOp[Float](rgbValWidener(a), b, numeric_times)
  }
  def infix_*(a: RGBVal[Short], b: RGBVal[Double])(implicit o: Overloaded3) = {
    sameTypeRGBOp[Double](rgbValWidener(a), b, numeric_times)
  }
  def infix_*(a: RGBVal[Int], b: RGBVal[Short])(implicit o: Overloaded4) = {
    sameTypeRGBOp[Int](a, rgbValWidener(b), numeric_times)
  }
  def infix_*(a: RGBVal[Int], b: RGBVal[Int])(implicit o: Overloaded5) = {
    sameTypeRGBOp[Int](a, b, numeric_times)
  }
  def infix_*(a: RGBVal[Int], b: RGBVal[Float])(implicit o: Overloaded6) = {
    sameTypeRGBOp[Float](rgbValWidener(a), b, numeric_times)
  }
  def infix_*(a: RGBVal[Int], b: RGBVal[Double])(implicit o: Overloaded7) = {
    sameTypeRGBOp[Double](rgbValWidener(a), b, numeric_times)
  }
  def infix_*(a: RGBVal[Float], b: RGBVal[Short])(implicit o: Overloaded8) = {
    sameTypeRGBOp[Float](a, rgbValWidener(b), numeric_times)
  }
  def infix_*(a: RGBVal[Float], b: RGBVal[Int])(implicit o: Overloaded9) = {
    sameTypeRGBOp[Float](a, rgbValWidener(b), numeric_times)
  }
  def infix_*(a: RGBVal[Float], b: RGBVal[Float])(implicit o: Overloaded10) = {
    sameTypeRGBOp[Float](a, b, numeric_times)
  }
  def infix_*(a: RGBVal[Float], b: RGBVal[Double])(implicit o: Overloaded11) = {
    sameTypeRGBOp[Double](rgbValWidener(a), b, numeric_times)
  }
  def infix_*(a: RGBVal[Double], b: RGBVal[Short])(implicit o: Overloaded12) = {
    sameTypeRGBOp[Double](a, rgbValWidener(b), numeric_times)
  }
  def infix_*(a: RGBVal[Double], b: RGBVal[Int])(implicit o: Overloaded13) = {
    sameTypeRGBOp[Double](a, rgbValWidener(b), numeric_times)
  }
  def infix_*(a: RGBVal[Double], b: RGBVal[Float])(implicit o: Overloaded14) = {
    sameTypeRGBOp[Double](a, rgbValWidener(b), numeric_times)
  }
  def infix_*(a: RGBVal[Double], b: RGBVal[Double])(implicit o: Overloaded15) = {
    sameTypeRGBOp[Double](a, b, numeric_times)
  }

  def infix_-(a: RGBVal[Short], b: RGBVal[Short]) = {
    sameTypeRGBOp[Short](a, b, numeric_minus)
  }
  def infix_-(a: RGBVal[Short], b: RGBVal[Int])(implicit o: Overloaded1) = {
    sameTypeRGBOp[Int](rgbValWidener(a), b, numeric_minus)
  }
  def infix_-(a: RGBVal[Short], b: RGBVal[Float])(implicit o: Overloaded2) = {
    sameTypeRGBOp[Float](rgbValWidener(a), b, numeric_minus)
  }
  def infix_-(a: RGBVal[Short], b: RGBVal[Double])(implicit o: Overloaded3) = {
    sameTypeRGBOp[Double](rgbValWidener(a), b, numeric_minus)
  }
  def infix_-(a: RGBVal[Int], b: RGBVal[Short])(implicit o: Overloaded4) = {
    sameTypeRGBOp[Int](a, rgbValWidener(b), numeric_minus)
  }
  def infix_-(a: RGBVal[Int], b: RGBVal[Int])(implicit o: Overloaded5) = {
    sameTypeRGBOp[Int](a, b, numeric_minus)
  }
  def infix_-(a: RGBVal[Int], b: RGBVal[Float])(implicit o: Overloaded6) = {
    sameTypeRGBOp[Float](rgbValWidener(a), b, numeric_minus)
  }
  def infix_-(a: RGBVal[Int], b: RGBVal[Double])(implicit o: Overloaded7) = {
    sameTypeRGBOp[Double](rgbValWidener(a), b, numeric_minus)
  }
  def infix_-(a: RGBVal[Float], b: RGBVal[Short])(implicit o: Overloaded8) = {
    sameTypeRGBOp[Float](a, rgbValWidener(b), numeric_minus)
  }
  def infix_-(a: RGBVal[Float], b: RGBVal[Int])(implicit o: Overloaded9) = {
    sameTypeRGBOp[Float](a, rgbValWidener(b), numeric_minus)
  }
  def infix_-(a: RGBVal[Float], b: RGBVal[Float])(implicit o: Overloaded10) = {
    sameTypeRGBOp[Float](a, b, numeric_minus)
  }
  def infix_-(a: RGBVal[Float], b: RGBVal[Double])(implicit o: Overloaded11) = {
    sameTypeRGBOp[Double](rgbValWidener(a), b, numeric_minus)
  }
  def infix_-(a: RGBVal[Double], b: RGBVal[Short])(implicit o: Overloaded12) = {
    sameTypeRGBOp[Double](a, rgbValWidener(b), numeric_minus)
  }
  def infix_-(a: RGBVal[Double], b: RGBVal[Int])(implicit o: Overloaded13) = {
    sameTypeRGBOp[Double](a, rgbValWidener(b), numeric_minus)
  }
  def infix_-(a: RGBVal[Double], b: RGBVal[Float])(implicit o: Overloaded14) = {
    sameTypeRGBOp[Double](a, rgbValWidener(b), numeric_minus)
  }
  def infix_-(a: RGBVal[Double], b: RGBVal[Double])(implicit o: Overloaded15) = {
    sameTypeRGBOp[Double](a, b, numeric_minus)
  }

  implicit class RGBEnrichedInts(v: Rep[Int]) {
   def *(rgb: RGBVal[Int]): RGBVal[Int] = {
     repConstToRgb(v) * rgb
   }
 }

  case class RGBVal[T:Typ:Numeric:SepiaNum](red: Rep[T], green: Rep[T], blue: Rep[T]) {
    def map[U:Typ:Numeric:SepiaNum](f: Rep[T] => Rep[U]): RGBVal[U] = {
      RGBVal(f(red), f(green), f(blue))
    }
  }

}
