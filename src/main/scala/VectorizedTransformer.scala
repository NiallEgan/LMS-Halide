package sepia

import scala.reflect.SourceContext
import lms.common._
import lms.internal._

trait VectorizedOps extends PrimitiveOps with RangeOps {
  def vectorized_loop(r: Range,
                      block: Rep[Int] => Rep[Unit]): Rep[Unit]
}

trait VectorizedOpsExp extends VectorizedOps with BaseExp
                       with RangeOpsExp {
 case class VectorForEach(start: Int, end: Int,
                           i: Sym[Int], body: Block[Unit]) extends Def[Unit]
 case class VectorForEachUnvectorized(start: Int, end: Int,
                                      i: Sym[Int], body: Block[Unit]) extends Def[Unit]

 def vectorized_loop(r: Range,
                     block: Exp[Int] => Exp[Unit]): Exp[Unit] = {
    val i = fresh[Int]
    val a = reifyEffects(block(i))
    reflectEffect(VectorForEachUnvectorized(r.start, r.end, i, a), summarizeEffects(a).star)
  }

  def vectorized_loop(start: Int, end: Int,
                      i: Sym[Int], body: Block[Unit]): Exp[Unit] = {
    // Needed because toAtom is protected
    reflectEffect(VectorForEach(start, end, i, body), summarizeEffects(body).star)
  }

  override def mirror[A:Typ](e: Def[A], f: Transformer)(implicit pos: SourceContext): Exp[A] = (e match {
    case Reflect(VectorForEach(s, e, i, b), u, es) => reflectMirrored(Reflect(VectorForEach(s, e, f(i).asInstanceOf[Sym[Int]],f(b)), mapOver(f,u), f(es)))(mtyp1[A], pos)
    case VectorForEach(s, e, i, b) => {
      throw new Exception("hi")
    }
    case Reflect(VectorForEachUnvectorized(s, e, i, b), u, es) => reflectMirrored(Reflect(VectorForEachUnvectorized(s, e, f(i).asInstanceOf[Sym[Int]],f(b)), mapOver(f,u), f(es)))(mtyp1[A], pos)

    case _ => super.mirror(e, f)
  }).asInstanceOf[Exp[A]]
}

trait Vectorizer extends RecursiveTransformer {
  val IR: DslExp
  import IR._

  def intVectorizer(exp: Exp[Int], start: Int,
                    end: Int, indexSymbol: Sym[Int]): Exp[__m256i] = {
    val n = 8

    def make_constant_i32(size: Int, v: Int) = {
      val xs = for (i <- 0 until 8: Range) yield (if (i < size) 0 else v)
      _mm256_set_epi32(xs(0), xs(1), xs(2), xs(3), xs(4), xs(5), xs(6), xs(7))
    }

    def make_index_i32(start: Int, end: Int) = {
      val size = end - start
      val xs = for (i <- 0 until 8: Range) yield(if (i < size) i + start else 0)
      _mm256_set_epi32(xs(7), xs(6), xs(5), xs(4), xs(3), xs(2), xs(1), xs(0))
    }

    exp match {
      case Def(v) => v match {
        case IntPlus(a, b) => (a, b) match {
          case (a@Def(av), b@Def(bv)) => _mm256_add_epi32(intVectorizer(a, start, end, indexSymbol), intVectorizer(b, start, end, indexSymbol))
          case (Const(av), b@Def(bv)) => _mm256_add_epi32(make_constant_i32(n, av), intVectorizer(b, start, end, indexSymbol))
          case (a@Def(av), Const(bv)) => _mm256_add_epi32(intVectorizer(a, start, end, indexSymbol), make_constant_i32(n, bv))
          case (Const(av), Const(bv)) => _mm256_add_epi32(make_constant_i32(n, av), make_constant_i32(n, bv))
        }
      }
      case Const(v) => make_constant_i32(n, v)
      case s@Sym(_) => {
        if (s == indexSymbol) {
          println(s)
          make_index_i32(start, end)
        }
        else throw new Exception("Should never get here?")
      }
    }
  }

  def stripIndex(n: Exp[Int], i: Sym[Int]): Exp[Int] = {
    n match {
      case s@Sym(_) if s == i => Const(0)  // Is this correct - multiplcation?
      case _ => n
    }
  }

  def vectorizeBlock(body: Block[Unit], start: Int, end: Int, indexSymbol: Sym[Int]): Block[Unit] = {
    val e = reflectBlock(body)
    val vecExp = e match {
      case Def(v) => {
         v match {
          case Reflect(arr@ArrayUpdate(a, n, y), _, _) => {
            if (arr.m == typ[Int]) {
              val vectorizedExp: Exp[__m256i] = intVectorizer(y.asInstanceOf[Exp[Int]], start, end, indexSymbol)
              _mm256_storeu_si256(a.asInstanceOf[Exp[Array[__m256i]]], vectorizedExp, stripIndex(n, indexSymbol))
            } else ???
          }
        }
      }
    }
    reifyEffects(vecExp)
  }

  var isInVectorizedLoop = false
  var s: Int = _
  var e: Int = _
  var i: Sym[Int] = _


  /*override def transformStm(stm: Stm): Exp[Any] = stm match {
    case TP(sym, node@Reflect(arr@ArrayUpdate(a, n, y), _, _)) if isInVectorizedLoop => {
      println("Vectorizing")
      throw new Exception()
      vectorizeBlock(arr, s, e, i)
    }
    case TP(sym, Reflect(VectorForEachUnvectorized(start, end, indexSym, body), _, _)) => {
      println("Transforming")
      println(f"body: $body")
      val saveFlag = isInVectorizedLoop
      isInVectorizedLoop = true
      s = start; e = end; i = indexSym
      val r = reifyBlock(reflectBlock(body))
      isInVectorizedLoop = saveFlag
      vectorized_loop(start, end, indexSym, r)
      //super.transformStm(stm)
    }
    case _ => {
      println(f"stm $stm, $isInVectorizedLoop")
      if (isInVectorizedLoop) throw new Exception()
      else super.transformStm(stm)
    }
  }*/

  override def transformDef[T](lhs: Sym[T], d: Def[T]): Option[() => Def[T]] = (d match {
    case Reflect(VectorForEachUnvectorized(start, end, indexSym, body), _, _) => {
      Some(() => VectorForEach(start, end, indexSym, vectorizeBlock(body, start, end, indexSym)))
    }
    case _ => super.transformDef(lhs, d)
  }).asInstanceOf[Option[() => Def[T]]]

}
