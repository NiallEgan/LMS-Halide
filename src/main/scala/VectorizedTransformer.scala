package sepia

import scala.reflect.SourceContext
import lms.common._
import lms.internal._

trait VectorizedOps extends PrimitiveOps with RangeOps with ArrayOps {
  def vectorized_loop(r: Range,
                      block: Rep[Int] => Rep[Unit]): Rep[Unit]
}

trait VectorizedOpsExp extends VectorizedOps with BaseExp
                       with RangeOpsExp with ArrayOpsExpOpt {
 case class VectorForEach(start: Int, end: Int,
                           i: Sym[Int], body: Block[Unit]) extends Def[Unit]
 case class VectorForEachUnvectorized(start: Int, end: Int,
                                      i: Sym[Int], body: Block[Unit]) extends Def[Unit]



 def vectorized_loop(r: Range,
                     block: Exp[Int] => Exp[Unit]): Exp[Unit] = {
    val i = fresh[Int]
    val a = reifyEffects{block(i)}
    reflectEffect(VectorForEachUnvectorized(r.start, r.end, i, a), summarizeEffects(a))
  }

  def vectorized_loop(start: Int, end: Int,
                      i: Sym[Int], body: Block[Unit]): Exp[Unit] = {
    reflectEffect(VectorForEach(start, end, i, body), summarizeEffects(body))
  }

  override def mirror[A:Typ](e: Def[A], f: Transformer)(implicit pos: SourceContext): Exp[A] = (e match {
    case Reflect(VectorForEach(s, e, i, b), u, es) => reflectMirrored(Reflect(VectorForEach(s, e, f(i).asInstanceOf[Sym[Int]],f(b)), mapOver(f,u), f(es)))(mtyp1[A], pos)
    case VectorForEach(s, e, i, b) => {
      throw new Exception("hi")
    }
    case Reflect(VectorForEachUnvectorized(s, e, i, b), u, es) => reflectMirrored(Reflect(VectorForEachUnvectorized(s, e, f(i).asInstanceOf[Sym[Int]],f(b)), mapOver(f,u), f(es)))(mtyp1[A], pos)
    case _ => super.mirror(e, f)
  }).asInstanceOf[Exp[A]]


  override def syms(e: Any): List[Sym[Any]] = e match {
    case VectorForEach(start, end, a, body) => syms(a):::syms(body)
    case VectorForEachUnvectorized(start, end, a, body) => syms(a):::syms(body)
    case _ => super.syms(e)
  }

  override def boundSyms(e: Any): List[Sym[Any]] = e match {
    case VectorForEach(start, end, x, body) => x :: effectSyms(body)
    case VectorForEachUnvectorized(start, end, x, body) => x :: effectSyms(body)
    case _ => super.boundSyms(e)
  }

  override def symsFreq(e: Any): List[(Sym[Any], Double)] = e match {
    case VectorForEach(start, end, a, body) => freqNormal(a):::freqHot(body)
    case VectorForEachUnvectorized(start, end, a, body) => freqNormal(a):::freqHot(body)
    case _ => super.symsFreq(e)
  }
}

trait Vectorizer extends ForwardTransformer {
  val IR: DslExp
  import IR._

  def actOnSym[A](exp: Exp[A], f: Sym[_] => Unit) {
      exp match {
        case s@Sym(_) => f(s)
        case _ => ()
      }
  }

  def make_constant_i16(size: Int, v: Int) = {
    val xs = for (i <- 15 to -1 by -1: Range) yield (if (i < size) v else 0)
    _mm256_set_epi16(xs(0), xs(1), xs(2), xs(3), xs(4), xs(5), xs(6), xs(7), xs(8), xs(9), xs(10), xs(11), xs(12), xs(13), xs(14), xs(15))
  }

  def make_index_i16(start: Int, end: Int) = {
    val size = end - start
    val xs = for (i <- 0 until 16: Range) yield(if (i < size) i + start else 0)
    _mm256_set_epi16(xs(15), xs(14), xs(13), xs(12), xs(11), xs(10), xs(9), xs(8), xs(7), xs(6), xs(5), xs(4), xs(3), xs(2), xs(1), xs(0))
  }

  def intVectorizer(exp: Exp[Short], start: Int,
                    end: Int, indexSymbol: Sym[Int]): Exp[__m256i] = {
    val n = end - start
    exp match {
      case Def(v) => v match {
        case NumericPlus(a, b) => _mm256_add_epi16(intVectorizer(a.asInstanceOf[Exp[Short]], start, end, indexSymbol),
                                                   intVectorizer(b.asInstanceOf[Exp[Short]], start, end, indexSymbol))
        case NumericMinus(a, b) => _mm256_sub_epi16(intVectorizer(a.asInstanceOf[Exp[Short]], start, end, indexSymbol),
                                                 intVectorizer(b.asInstanceOf[Exp[Short]], start, end, indexSymbol))
        case NumericTimes(a, b) => throw new VectorisationException("Error: Vector int multiplication not implemented yet")
        case NumericDivide(a, b) => {
          b match {
            case Const(bv) => {
              if ((bv & (bv - 1)) == 0) {
                val shiftAmount = FastIntegerDivision.ilog(bv)
                _mm256_srli_epi16(intVectorizer(a.asInstanceOf[Exp[Short]], start, end, indexSymbol), shiftAmount)
              } else {
                val (multiplier, sh1, sh2) = FastIntegerDivision.getDivisor(bv)
                val multiplierVector = make_constant_i16(n, multiplier)
                val numerator = intVectorizer(a.asInstanceOf[Exp[Short]], start, end, indexSymbol)
                val t1 = _mm256_mulhi_epi16(numerator, multiplierVector)
                val firstShifted = _mm256_srli_epi16(_mm256_sub_epi16(numerator, t1), sh1)
                _mm256_srli_epi16(_mm256_add_epi16(t1, firstShifted), sh2)
              }
            }
            case _ => throw new VectorisationException("Error: Only division by constants ")
          }
        }
        case app@ArrayApply(a, n) => {
          // todo: Short!
          if (app.m != typ[Short]) throw new VectorisationException(f"Error: Can only load from short arrays if trying to vectorize, not arrays of type ${app.m}")
          else _mm256_loadu_si256(apply(a).asInstanceOf[Exp[Array[__m256i]]], stripIndex(n, indexSymbol))

        }
        case Reflect(app@ArrayApply(a, n), _, _) => {
          if (app.m != typ[Short]) throw new VectorisationException(f"Error: Can only load from short arrays if trying to vectorize, not arrays of type ${app.m}")
          else {
            generate_comment("loadu")
            _mm256_loadu_si256(apply(a).asInstanceOf[Exp[Array[__m256i]]], stripIndex(n, indexSymbol))
          }
        }
        //case ShortToInt(a) => intVectorizer(a.asInstanceOf[Exp[Int]], start, end, indexSymbol)


      }
      case Const(v) => make_constant_i16(n, v)
      case s@Sym(_) => {
        if (s == indexSymbol) {
          make_index_i16(start, end)
        }
        else throw new VectorisationException("Should never get here?")
      }
    }
  }

  def doubleVectorizer(exp: Exp[Double], start: Int,
                       end: Int, indexSymbol: Sym[Int]): Exp[__m256d] = {
     val n = end - start

     def make_constant_d32(size: Int, v: Exp[Double]) = {
       val xs: IndexedSeq[Exp[Double]] = for (i <- 3 to -1 by -1: Range) yield (if (i < size) v else Const(0.0))
       _mm256_set_pd(xs(0), xs(1), xs(2), xs(3))
     }

     def make_index_d32(start: Int, end: Int) = {
       println("Making index:")
       val size = end - start
       val xs = for (i <- 0 until 4: Range) yield(if (i < size) i + start else 0)
       _mm256_set_pd(xs(3), xs(2), xs(1), xs(0))
     }

     exp match {
       case Def(v) => v match {
         // todo: are all of these needed?
         case NumericPlus(a, b) => _mm256_add_pd(doubleVectorizer(a.asInstanceOf[Exp[Double]], start, end, indexSymbol),
                                                 doubleVectorizer(b.asInstanceOf[Exp[Double]], start, end, indexSymbol))
         case NumericMinus(a, b) => _mm256_sub_pd(doubleVectorizer(a.asInstanceOf[Exp[Double]], start, end, indexSymbol),
                                                  doubleVectorizer(b.asInstanceOf[Exp[Double]], start, end, indexSymbol))
         case NumericTimes(a, b) => _mm256_mul_pd(doubleVectorizer(a.asInstanceOf[Exp[Double]], start, end, indexSymbol),
                                                  doubleVectorizer(b.asInstanceOf[Exp[Double]], start, end, indexSymbol))
         case NumericDivide(a, b) => {
           println("Double divide")
           _mm256_div_pd(doubleVectorizer(a.asInstanceOf[Exp[Double]], start, end, indexSymbol),
                                                   doubleVectorizer(b.asInstanceOf[Exp[Double]], start, end, indexSymbol))
         }
         case DoublePlus(a, b) => _mm256_add_pd(doubleVectorizer(a, start, end, indexSymbol),
                                                 doubleVectorizer(b, start, end, indexSymbol))
         case DoubleMinus(a, b) => _mm256_sub_pd(doubleVectorizer(a, start, end, indexSymbol),
                                                  doubleVectorizer(b, start, end, indexSymbol))
         case DoubleTimes(a, b) => _mm256_mul_pd(doubleVectorizer(a, start, end, indexSymbol),
                                                  doubleVectorizer(b, start, end, indexSymbol))
         case DoubleDivide(a, b) => {
           println("Double divide")
           _mm256_div_pd(doubleVectorizer(a, start, end, indexSymbol),
                                                   doubleVectorizer(b, start, end, indexSymbol))
         }
         /*case IntToDouble(a) => {
          println("Int to double")
          val vecExp = intVectorizer(a, start, end, indexSymbol)
          _mm256_castsi256_pd(vecExp)
          a match {
             case s@Sym(_) if s == indexSymbol => make_index_d32(start, end)
             case _ => make_constant_d32(n, a)
           }
         }*/
         case ShortToDouble(a) => {
           make_constant_d32(n, repShortToRepDouble(a))
           /*a match {
             case Def(v) {}
           }*/
         }
         case app@ArrayApply(a, n) => {
           if (app.m != typ[Double]) throw new VectorisationException("Error: Can only load from double arrays if trying to vectorize")
           else _mm256_loadu_pd(apply(a), stripIndex(n, indexSymbol))
         }
         case Reflect(app@ArrayApply(a, n), _, _) => {
           if (app.m != typ[Double]) throw new VectorisationException("Error: Can only load from double arrays if trying to vectorize")
           else _mm256_loadu_pd(apply(a), stripIndex(n, indexSymbol))
         }
      }
       case Const(v) => make_constant_d32(n, v)
       case s@Sym(_) => {
         if (s == indexSymbol) {
           make_index_d32(start, end)
         }
         else throw new Exception("Should never get here?")
       }
     }
   }

 def make_constant_32(size: Int, v: Exp[Float]) = {
   val xs: IndexedSeq[Exp[Float]] = for (i <- 7 to -1 by -1: Range) yield (if (i < size) v else Const(0.0f))
   _mm256_set_ps(xs(0), xs(1), xs(2), xs(3), xs(4), xs(5), xs(6), xs(7))
 }

 def make_index_32(start: Int, end: Int) = {
   val size = end - start
   val xs = for (i <- 0 until 8: Range) yield(if (i < size) i + start else 0)
   _mm256_set_ps(xs(7), xs(6), xs(5), xs(4), xs(3), xs(2), xs(1), xs(0))
 }

 def floatVectorizer(exp: Exp[Float], start: Int,
                      end: Int, indexSymbol: Sym[Int]): Exp[__m256] = {
    val n = end - start

    exp match {
      case Def(v) => v match {
        // todo: are all of these needed?
        case NumericPlus(a, b) => _mm256_add_ps(floatVectorizer(a.asInstanceOf[Exp[Float]], start, end, indexSymbol),
                                                floatVectorizer(b.asInstanceOf[Exp[Float]], start, end, indexSymbol))
        case NumericMinus(a, b) => _mm256_sub_ps(floatVectorizer(a.asInstanceOf[Exp[Float]], start, end, indexSymbol),
                                                 floatVectorizer(b.asInstanceOf[Exp[Float]], start, end, indexSymbol))
        case NumericTimes(a, b) => _mm256_mul_ps(floatVectorizer(a.asInstanceOf[Exp[Float]], start, end, indexSymbol),
                                                 floatVectorizer(b.asInstanceOf[Exp[Float]], start, end, indexSymbol))
        case NumericDivide(a, b) => _mm256_div_ps(floatVectorizer(a.asInstanceOf[Exp[Float]], start, end, indexSymbol),
                                                  floatVectorizer(b.asInstanceOf[Exp[Float]], start, end, indexSymbol))
        case FloatPlus(a, b) => _mm256_add_ps(floatVectorizer(a, start, end, indexSymbol),
                                              floatVectorizer(b, start, end, indexSymbol))
        case FloatMinus(a, b) => _mm256_sub_ps(floatVectorizer(a, start, end, indexSymbol),
                                               floatVectorizer(b, start, end, indexSymbol))
        case FloatTimes(a, b) => _mm256_mul_ps(floatVectorizer(a, start, end, indexSymbol),
                                               floatVectorizer(b, start, end, indexSymbol))
        case FloatDivide(a, b) => _mm256_div_ps(floatVectorizer(a, start, end, indexSymbol),
                                                floatVectorizer(b, start, end, indexSymbol))
        case IntToFloat(a) => {
         a match {
            case s@Sym(_) if s == indexSymbol => make_index_32(start, end)
            case _ => make_constant_32(n, a)
          }
        }
        case app@ArrayApply(a, n) => {
          // todo: Short!
          if (app.m != typ[Float]) throw new VectorisationException("Error: Can only load from float arrays if trying to vectorize")
          else _mm256_loadu_ps(apply(a), stripIndex(n, indexSymbol))
        }
        case Reflect(app@ArrayApply(a, n), _, _) => {
          if (app.m != typ[Float]) throw new VectorisationException("Error: Can only load from float arrays if trying to vectorize")
          else _mm256_loadu_ps(apply(a), stripIndex(n, indexSymbol))
        }

      }
      case Const(v) => make_constant_32(n, v)
      case s@Sym(_) => {
        if (s == indexSymbol) {
          make_index_32(start, end)
        }
        else throw new Exception("Should never get here?")
      }
    }
  }

  def stripIndex[T:Typ](n: Exp[T], i: Sym[Int]): Exp[T] = {
    n match {
      case s@Sym(_) if s == i => Const(0).asInstanceOf[Exp[T]]  // Is this correct - multiplcation?
      case Def(v) => v match {
        case op@NumericPlus(a, b) => numeric_plus(stripIndex(a, i), stripIndex(b, i))(op.aev.asInstanceOf[Numeric[T]],
                                     typ[T], implicitly[SourceContext])
        case op@NumericDivide(a, b) => numeric_divide(stripIndex(a, i), stripIndex(b, i))(op.aev.asInstanceOf[Numeric[T]],
                                       typ[T], implicitly[SourceContext])
        case op@NumericMinus(a, b) => numeric_minus(stripIndex(a, i), stripIndex(b, i))(op.aev.asInstanceOf[Numeric[T]],
                                      typ[T], implicitly[SourceContext])
        case op@NumericTimes(a, b) => numeric_times(stripIndex(a, i), stripIndex(b, i))(op.aev.asInstanceOf[Numeric[T]],
                                      typ[T], implicitly[SourceContext])
        case IntPlus(Def(IntTimes(Const(3), a)), Const(k)) => {
          // Bad hack :( how is this going to generalize to different vector widths????
          int_plus(int_times(3, stripIndex(a, i)), Const(k * 16))
        }
        case IntPlus(a, b) => {
          int_plus(stripIndex(a, i), stripIndex(b, i))
        }
        case IntDivide(a, b) => int_divide(stripIndex(a, i), stripIndex(b, i))
        case IntMinus(a, b) => int_minus(stripIndex(a, i), stripIndex(b, i))
        case IntTimes(a, b) => int_times(stripIndex(a, i), stripIndex(b, i))
        case IfThenElse(a, b, c) => __ifThenElse(stripIndex(a, i), { stripIndex(reflectBlock(b), i) }, { stripIndex(reflectBlock(c), i) })
        case op@OrderingGT(a, b) => ordering_gt(stripIndex(a, i), stripIndex(b, i))(op.aev, op.mev, implicitly[SourceContext])
        case ShortToInt(a) => repShortToRepInt(stripIndex(a, i))
        case app@ArrayApply(a, n) => array_apply(a, stripIndex(n, i))(mtyp1[T], implicitly[SourceContext])
        case cast@TToChar(a) => toCharCast(a)(cast.fromTyp)
        case cast@CharToT(a) => charCast(a)(cast.toTyp)
        //case Reflectapp@ArrayApply(a, n) => array_apply(a, stripIndex(n, i))(mtyp1[T], implicitly[SourceContext])

        case _ => {
          println(f"Don't know how to strip $v")
          n
        }

      }
      case _ => n
    }
  }

  var inVectorizedLoop = false
  var start = 0
  var end = 0
  var indexSymbol: Sym[Int] = null // ):
  var vectorizingInt: Boolean = false

  override def transformStm(stm: Stm): Exp[Any] = stm match {
    case TP(sym, Reflect(VectorForEachUnvectorized(vstart, vend, indexSym, body), a, b)) => {
      inVectorizedLoop = true
      start = vstart
      end = vend
      indexSymbol = indexSym
      //val a = vectorizeBlock(transformBlock(body), start, end, indexSym)
      println(f"Transforming block: $body")
      val transformedBlock = transformBlock(body)
      inVectorizedLoop = false
      println("Made false 1")
      vectorized_loop(start, end, indexSym, transformedBlock)
    }
    case _ => {
      if (inVectorizedLoop) {
        stm match {
          case TP(sym, v) => v match {
           case Reflect(arr@ArrayUpdate(a, n, y), _, _) => {
             if (arr.m == typ[Short]) {
                val vectorizedExp: Exp[__m256i] = apply(intVectorizer(y.asInstanceOf[Exp[Short]], start, end, indexSymbol))
                vectorizingInt = true
                println("Found an int store")
                //val vectorizedExp: Exp[__m256i] = apply(y).asInstanceOf[Exp[__m256i]]
                vectorizingInt = false
                println("Ended int store")
               _mm256_storeu_si256(apply(a).asInstanceOf[Exp[Array[__m256i]]], vectorizedExp, stripIndex(n, indexSymbol))
             } else if (arr.m == typ[Double]) {
               val vectorizedExp: Exp[__m256d] = doubleVectorizer(y.asInstanceOf[Exp[Double]], start, end, indexSymbol)
               _mm256_storeu_pd(apply(a).asInstanceOf[Exp[Array[Double]]], vectorizedExp, stripIndex(n, indexSymbol))
             } else if (arr.m == typ[Float]) {
               val vectorizedExp: Exp[__m256] = floatVectorizer(y.asInstanceOf[Exp[Float]], start, end, indexSymbol)
               _mm256_storeu_ps(apply(a).asInstanceOf[Exp[Array[Float]]], vectorizedExp, stripIndex(n, indexSymbol))
             } else {
               throw new VectorisationException(f"Error: Can only vectorize Int, Double or Float, not ${arr.m}, $arr")
             }
           }
           case IntToFloat(a) => {
             repIntToRepFloat(stripIndex(apply(a), indexSymbol))
           }
           case ShortToInt(a) => {
             stripIndex(s2i(apply(a)), indexSymbol) // dodgy...
           }
           case _ => stripIndex(super.transformStm(stm), indexSymbol)
          /* case IntPlus(a, b) if vectorizingInt => _mm256_add_epi16(apply(a).asInstanceOf[Exp[__m256i]], apply(b).asInstanceOf[Exp[__m256i]])
           case IntMinus(a, b) if vectorizingInt => _mm256_sub_epi16(apply(a).asInstanceOf[Exp[__m256i]], apply(b).asInstanceOf[Exp[__m256i]])
           case NumericPlus(a, b) if vectorizingInt => _mm256_add_epi16(apply(a).asInstanceOf[Exp[__m256i]], apply(b).asInstanceOf[Exp[__m256i]])
           case NumericMinus(a, b) if vectorizingInt => _mm256_sub_epi16(apply(a).asInstanceOf[Exp[__m256i]], apply(b).asInstanceOf[Exp[__m256i]])
           case NumericTimes(a, b) if vectorizingInt => throw new VectorisationException("Error: Vector int multiplication not implemented yet")
           case NumericDivide(a, b) if vectorizingInt => throw new VectorisationException("Error: Vector int division not possible")
           case app@ArrayApply(a, n) if vectorizingInt => {
             // todo: Short!
             if (app.m != typ[Int]) throw new VectorisationException("Error: Can only load from int arrays if trying to vectorize")
             else _mm256_loadu_si256(apply(a).asInstanceOf[Exp[Array[__m256i]]], stripIndex(n, indexSymbol))

           }
           case Reflect(app@ArrayApply(a, n), _, _) if vectorizingInt => {
             if (app.m != typ[Int]) throw new VectorisationException("Error: Can only load from int arrays if trying to vectorize")
             else {
               generate_comment("loadu")
               _mm256_loadu_si256(apply(a).asInstanceOf[Exp[Array[__m256i]]], stripIndex(n, indexSymbol))
             }
           }
           case ShortToInt(a) if vectorizingInt => apply(a)
           case _ => {
             if (sym == indexSymbol && vectorizingInt) make_index_i16(start, end)
             else {
               println(f"No rule for: $v")
               stripIndex(super.transformStm(stm), indexSymbol)
             }
           }*/
         }
        }
      } else {
        super.transformStm(stm)
      }
    }
  }
}
