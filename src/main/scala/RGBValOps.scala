package sepia

import lms.common._

trait RGBValOps extends PrimitiveOps with ArrayOps
          with ShortOps with NumericOps with VectorOps {
   type UChar = Short

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
     def d2T(a: Rep[Double]): Rep[T]
     def i2T(a: Rep[Int]): Rep[T]
   }

   def makeCNumeric[T:Numeric:Typ]: CNumeric[T] = {
     new CNumeric[T] {
       override def plus(a: Rep[T], b: Rep[T]) = numeric_plus(a, b)
       override def times(a: Rep[T], b: Rep[T]) = numeric_times(a, b)
       override def divide(a: Rep[T], b: Rep[T]) = numeric_divide(a, b)
       override def minus(a: Rep[T], b: Rep[T]) = numeric_minus(a, b)
       //override def d2T
     }
   }

   val __mm128Numeric = new CNumeric[__m256] {
     override def plus(a: Rep[__m256], b: Rep[__m256]) = vector_plus_mm256(a, b)
     override def minus(a: Rep[__m256], b: Rep[__m256]) = vector_minus_mm256(a, b)
     override def divide(a: Rep[__m256], b: Rep[__m256]) = vector_divide_mm256(a, b)
     override def times(a: Rep[__m256], b: Rep[__m256]) = vector_times_mm256(a, b)
     // TODO: Think about this behaviour. Is this really what we want?
     override def d2T(a: Rep[Double]) = throw new SepiaTypeError("TypeError: Can't downcast double to float")
     override def i2T(a: Rep[Double]) = ??? // TODO: Implement float conversion
   }

   val __mm128dNumeric = new CNumeric[__m256d] {
     override def plus(a: Rep[__m256d], b: Rep[__m256d]) = vector_plus_mm256d(a, b)
     override def minus(a: Rep[__m256d], b: Rep[__m256d]) = vector_minus_mm256d(a, b)
     override def divide(a: Rep[__m256d], b: Rep[__m256d]) = vector_divide_mm256d(a, b)
     override def times(a: Rep[__m256d], b: Rep[__m256d]) = vector_times_mm256d(a, b)
     override def d2T(a: Rep[Double]) = vector_set1_mm256d(a)
     override def i2T(a: Rep[Int]) = vector_set1_mm256d(int2double(a))

   }

   val __mm128iNumeric = new CNumeric[__m256i] {
     override def plus(a: Rep[__m256i], b: Rep[__m256i]) = vector_plus_mm256i(a, b)
     override def minus(a: Rep[__m256i], b: Rep[__m256i]) = vector_minus_mm256i(a, b)
     override def divide(a: Rep[__m256i], b: Rep[__m256i]) = ???
     override def times(a: Rep[__m256i], b: Rep[__m256i]) = vector_times_mm256i(a, b)
     override def i2T(a: Rep[Int]) = vector_set1_mm256i(a)
     // This is inconsitent behaviour. Sit down and actually think about type rules.
     // for neel
     override def d2T(a: Rep[Double]) = vector_set1_mm256d(double2int(a))
   }

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

}
