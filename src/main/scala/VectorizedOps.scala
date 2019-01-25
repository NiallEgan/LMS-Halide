package sepia

import scala.lms.common._
//import intrinsics
import ch.ethz.acl.passera.unsigned._
import ch.ethz.acl.intrinsics._

trait VectorOps extends IntrinsicsBaseOps {
  /*abstract class __m64
  abstract class __m128
  abstract class __m128d
  abstract class __m128i
  abstract class __m256
  abstract class __m256d
  abstract class __m256i
  abstract class __m512
  abstract class __m512d
  abstract class __m512i*/

  /* Setting values */
  def vector_set1_mm256(a: Rep[Float]): Rep[__m256]
  def vector_set1_mm256d(a: Rep[Double]): Rep[__m256d]
  def vector_set1_mm256i(a: Rep[Int]): Rep[__m256i]
  def vector_set_mm256(e7: Rep[Float], e6: Rep[Float], e5: Rep[Float], e4: Rep[Float],
                       e3: Rep[Float], e2: Rep[Float], e1: Rep[Float], e0: Rep[Float]): Rep[__m256]
  def vector_set_mm256d(e7: Rep[Double], e6: Rep[Double], e5: Rep[Double], e4: Rep[Double]): Rep[__m256d]
  def vector_set_mm256i(e7: Rep[Int], e6: Rep[Int], e5: Rep[Int], e4: Rep[Int],
                        e3: Rep[Int], e2: Rep[Int], e1: Rep[Int], e0: Rep[Int]): Rep[__m256i]

  /* Loading values */
  def vector_load_mm256(a: Rep[Array[Float]], offset: Rep[Int]): Rep[__m256]
  def vector_load_mm256i(a: Rep[Array[Int]], offset: Rep[Int]): Rep[__m256i]
  def vector_load_mm256d(a: Rep[Array[Double]], offset: Rep[Int]): Rep[__m256d]


  /* Arithmetic */
  def vector_plus_mm256(a: Rep[__m256], b: Rep[__m256]): Rep[__m256]
  def vector_plus_mm256i(a: Rep[__m256i], b: Rep[__m256i]): Rep[__m256i]
  def vector_plus_mm256d(a: Rep[__m256d], b: Rep[__m256d]): Rep[__m256d]

  def vector_minus_mm256(a: Rep[__m256], b: Rep[__m256]): Rep[__m256]
  def vector_minus_mm256i(a: Rep[__m256i], b: Rep[__m256i]): Rep[__m256i]
  def vector_minus_mm256d(a: Rep[__m256d], b: Rep[__m256d]): Rep[__m256d]

  def vector_times_mm256(a: Rep[__m256], b: Rep[__m256]): Rep[__m256]
  def vector_times_mm256i(a: Rep[__m256i], b: Rep[__m256i]): Rep[__m256i]
  def vector_times_mm256d(a: Rep[__m256d], b: Rep[__m256d]): Rep[__m256d]

  def vector_divide_mm256(a: Rep[__m256], b: Rep[__m256]): Rep[__m256]
  def vector_divide_mm256i(a: Rep[__m256i], b: Rep[__m256i]): Rep[__m256i]
  def vector_divide_mm256d(a: Rep[__m256d], b: Rep[__m256d]): Rep[__m256d]
}

trait VectorOpsExp extends AVX2 with AVX with IntrinsicsArrays with VectorOps {
  case class IntsTomm256i(a: Exp[Array[Int]]) extends Def[Array[__m256i]]

  override def vector_plus_mm256(a: Exp[__m256], b: Exp[__m256]): Exp[__m256] = {
    _mm256_add_ps(a, b)
  }
  override def vector_plus_mm256i(a: Exp[__m256i], b: Exp[__m256i]): Exp[__m256i] = {
    _mm256_add_epi32(a, b)

  }
  override def vector_plus_mm256d(a: Exp[__m256d], b: Exp[__m256d]): Exp[__m256d] = {
    _mm256_add_pd(a, b)
  }

  override def vector_minus_mm256(a: Exp[__m256], b: Exp[__m256]): Exp[__m256] = {
    _mm256_sub_ps(a, b)
  }
  override def vector_minus_mm256i(a: Exp[__m256i], b: Exp[__m256i]): Exp[__m256i] = {
    _mm256_sub_epi32(a, b)
  }
  override def vector_minus_mm256d(a: Exp[__m256d], b: Exp[__m256d]): Exp[__m256d] = {
    _mm256_sub_pd(a, b)
  }

  override def vector_times_mm256(a: Exp[__m256], b: Exp[__m256]): Exp[__m256] = {
    _mm256_mul_ps(a, b)
  }
  override def vector_times_mm256i(a: Exp[__m256i], b: Exp[__m256i]): Exp[__m256i]
  override def vector_times_mm256d(a: Exp[__m256d], b: Exp[__m256d]): Exp[__m256d] = {
    _mm256_mul_pd(a, b)
  }

  override def vector_divide_mm256(a: Exp[__m256], b: Exp[__m256]): Exp[__m256] = {
    _mm256_div_ps(a, b)
  }
  override def vector_divide_mm256d(a: Exp[__m256d], b: Exp[__m256d]): Exp[__m256d] = {
    _mm256_mul_pd(a, b)
  }
  override def vector_set1_mm256(a: Rep[Float]): Rep[__m256] = {
    _mm256_set1_ps(a)
  }
  override def vector_set1_mm256d(a: Exp[Double]): Exp[__m256d] = {
    _mm256_set1_pd(a)
  }
  override def vector_set1_mm256i(a: Exp[Int]): Exp[__m256i] = {
    _mm256_set1_epi32(a)
  }

  override def vector_set_mm256(e7: Exp[Float], e6: Exp[Float], e5: Exp[Float], e4: Exp[Float],
                                e3: Exp[Float], e2: Exp[Float], e1: Exp[Float], e0: Exp[Float]): Exp[__m256] = {
      _mm256_set_ps(e7, e6, e5, e4, e3, e2, e1, e0)
  }
  override def vector_set_mm256d(e7: Exp[Double], e6: Exp[Double], e5: Exp[Double], e4: Exp[Double]): Exp[__m256d] = {
    _mm256_set_pd(e7, e6, e5, e4)
  }
  override def vector_set_mm256i(e7: Exp[Int], e6: Exp[Int], e5: Exp[Int], e4: Exp[Int],
                                 e3: Exp[Int], e2: Exp[Int], e1: Exp[Int], e0: Exp[Int]): Exp[__m256i] = {
      _mm256_set_epi32(e7, e6, e5, e4, e3, e2, e1, e0)
  }

  override def vector_load_mm256(a: Exp[Array[Float]], offset: Exp[Int]): Exp[__m256] = {
    _mm256_load_ps(a, offset)

  }

  def castTo_mm256i(a: Exp[Array[Int]]): Exp[Array[__m256i]] = IntsTomm256i(a)

  override def vector_load_mm256i(a: Exp[Array[Int]], offset: Exp[Int]): Exp[__m256i] = {
    _mm256_loadu_si256(castTo_mm256i(a), offset)
  }
  override def vector_load_mm256d(a: Exp[Array[Double]], offset: Exp[Int]): Exp[__m256d] = {
    _mm256_load_pd(a, offset)
  }

}
