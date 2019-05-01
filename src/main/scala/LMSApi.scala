package sepia

import java.io.PrintWriter
import scala.reflect.SourceContext

import scala.lms.common._
import ch.ethz.acl.intrinsics.{AVX, AVX2, IntrinsicsArrays, CGenAVX, CGenAVX2,
        SSE3, SSE2, CGenSSE3, CGenSSE2}
import ch.ethz.acl.passera.unsigned.{UByte, UInt, ULong, UShort}

trait ImageOps extends PrimitiveOps {
  def tern[T:Typ](cond: Rep[Boolean],
                  lower: Rep[T], upper: Rep[T]): Rep[T]
  def constArray[T:Typ](a: Array[T]): Rep[Array[T]]
}

trait ImageOpsExp extends ImageOps with BaseExp with ArrayOpsExp {
  case class Tern[T:Typ](cond: Exp[Boolean],
                         lower: Exp[T], upper: Exp[T]) extends Def[T]
  case class ConstArray[T:Typ](a: Array[T]) extends Def[Array[T]] {
    val ev: Typ[T] = implicitly[Typ[T]]
  }

  override def tern[T:Typ](cond: Rep[Boolean],
                           lower: Rep[T], upper: Rep[T]): Rep[T] = {
     Tern(cond, lower, upper)
  }

  override def constArray[T:Typ](a: Array[T]) = {
    val v: Exp[Array[T]] = ConstArray(a)
    //println("gooadfb")
    v
  }

  override def mirror[A:Typ](e: Def[A], f: Transformer)(implicit pos: SourceContext): Exp[A] =
    (e match {
      case Tern(cond, lower, upper) => tern(f(cond), f(lower), f(upper))
      case ca@ConstArray(a) => constArray(a)(ca.ev)
      case _ => super.mirror(e, f)(typ[A], pos)
    }).asInstanceOf[Exp[A]]
}


trait Dsl extends PrimitiveOps with NumericOps
          with BooleanOps with LiftPrimitives
          with LiftNumeric with LiftBoolean
          with IfThenElse with Equal
          with RangeOps with FractionalOps
          with ArrayOps with SeqOps
          with ImageBufferOps with ShortOps
          with OrderingOps with VectorizedOps
          with MathOps with ImageOps {
  def generate_comment(l: String): Rep[Unit]
  def comment[A:Typ](l: String, verbose: Boolean = true)(b: => Rep[A]): Rep[A]

}

trait DslExp extends Dsl with ShortOpsExpOpt with PrimitiveOpsExpOpt with NumericOpsExpOpt
             with BooleanOpsExpOpt with IfThenElseExpOpt
             with ImageBufferOpsExp
             with RangeOpsExp with FractionalOpsExp
             with EqualExpBridgeOpt with ArrayOpsExpOpt
             with SeqOpsExp
             with OrderingOpsExpOpt
             with AVX with AVX2 with SSE2 with SSE3 with VectorizedOpsExp
             with IntrinsicsArrays with MathOpsExp
             with ImageOpsExp {
 implicit def anyTyp    : Typ[Any]    = manifestTyp
 implicit def uByteTyp  : Typ[UByte]  = manifestTyp
 implicit def uIntTyp   : Typ[UInt]   = manifestTyp
 implicit def uLongTyp  : Typ[ULong]  = manifestTyp
 implicit def uShortTyp : Typ[UShort] = manifestTyp

 case class GenerateComment(l: String) extends Def[Unit]
 def generate_comment(l: String) = reflectEffect(GenerateComment(l))
 case class Comment[A:Typ](l: String, verbose: Boolean, b: Block[A]) extends Def[A]
 def comment[A:Typ](l: String, verbose: Boolean)(b: => Rep[A]): Rep[A] = {
   val br = reifyEffects(b)
   val be = summarizeEffects(br)
   reflectEffect[A](Comment(l, verbose, br), be)
 }

 override def boundSyms(e: Any): List[Sym[Any]] = e match {
   case Comment(_, _, b) => effectSyms(b)
   case _ => super.boundSyms(e)
 }

 override def array_apply[T:Typ](x: Exp[Array[T]], n: Exp[Int])(implicit pos: SourceContext): Exp[T] = ArrayApply(x, n)

}

trait DslGenC extends CGenNumericOps
  with CGenPrimitiveOps with CGenBooleanOps
  with CGenIfThenElse with CGenEqual
  with CGenRangeOps with CGenFractionalOps
  with CGenShortOps with CGenArrayOps
  with CGenOrderingOps with CGenAVX
  with CGenAVX2 with CGenSSE2 with CGenSSE3
  with CGenMathOps {
    self =>
    val IR: DslExp
    import IR._

    override def isPrimitiveType(tpe: String) = tpe match {
      case "USHORT" => true
      case "UCHAR" => true
      case "Char" => true
      case "__m64" => true
      case "__m128" => true
      case "__m128d" => true
      case "__m128i" => true
      case "__m256" => true
      case "__m256i" => true
      case "__m256d" => true
      case "__m512" => true
      case "__m512d" => true
      case "__m512i" => true
      case _ => super.isPrimitiveType(tpe)
    }

    override def remap[A](m: Typ[A]) = m.toString match {
      case "Array[Char]" => "UCHAR *"
      case "Char" => "UCHAR"
      case "Short" => "uint16_t"
      case "Int" => "int32_t"
      case _ => super.remap(m)

    }

    override def emitNode(sym: Sym[Any], rhs: Def[Any]) = {
      rhs match {
        case ArrayApply(x, m) => emitValDef(sym, src"$x[$m]")
        case ArrayUpdate(x, m, y) => stream.println(src"$x[$m] = $y;")
        case a@ArrayNew(m) => {
          val arrType = remap(a.m)
          stream.println(f"$arrType *${quote(sym)} = malloc(sizeof($arrType) * ${quote(m)});")
        }
        case ArrayFree(a) => stream.println(src"free($a);")
        case MemCpy(src, dest, size) => stream.println(
          src"memcpy($dest, $src, $size);")
        case IntToDoubleConversion(a) => emitValDef(sym, src"(double) $a")
        case DoubleToIntConversion(a) => emitValDef(sym, src"(int32_t) $a")
        case VectorForEach(_, _, _, body) => {
          emitBlock(body) // TODO: Don't let VectorForEach get this far
        }
        case GenerateComment(s) => stream.println(f"// $s")
        case n@CharToT(a) => emitValDef(sym, f"(${remap(n.toTyp)}) ${quote(a)}")
        case n@TToChar(a) => emitValDef(sym, f"(UCHAR) ${quote(a)}")
        case Tern(cond, l, u) => emitValDef(sym, src"($cond) ? $l : $u")
        case ConstArray(a) => {
          val values = a.mkString(", ")
          emitValDef(sym, "{" + values + "}")
        }
        case a@ArrayFromSeq(s) => {
          val arrType = remap(a.m)
          val values = s.map(x => x match {case Const(v) => v}).mkString(", ")
          stream.println(f"$arrType ${quote(sym)}[${s.length}] = {" + values + "};")
        }

        //emitValDef(sym, src"if ($v < $threshold) $l else $u")
        case _ => super.emitNode(sym, rhs)
      }
    }

    private def argsRemap[A](m: Typ[A]): String = m.toString match {
      case "Array[Short]" => "UCHAR *"
      case _ => remap(m)
    }

    override def emitSource[A:Typ](args: List[Sym[_]], body: Block[A],
                                   functionName: String, out: PrintWriter) = {
      val sA = remap(typ[A])
      println("Emitting source")
      withStream(out) {
        stream.println("#include <string.h>")
        stream.println("#include \"pipeline.h\"")

        stream.println(f"$sA $functionName(${args.map(a => argsRemap(a.tp)+ " " + quote(a)).mkString(", ")}) {")
        emitBlock(body)
        val y = getBlockResult(body)
        if (remap(y.tp) != "void") stream.println("return " + quote(y) + ";")
        stream.println("}")
      }
      Nil
    }

    def emitStaticData(name: String, v: Int, out: PrintWriter) = {
      withStream(out) {
        stream.println(f"int32_t $name = $v;")
      }
    }

    def emitSourceMut[T1: Typ, T2: Typ, R: Typ, T3: Typ, T4: Typ]
                    (f: (Exp[T1], Exp[T2], Exp[T3], Exp[T4]) => Exp[R],
                     className: String, stream: PrintWriter): List[(Sym[Any], Any)] = {
    // This marks the second argument as mutable


    val s1 = fresh[T1]
    val s2 = reflectMutableSym(fresh[T2])
    val s3 = fresh[T3]
    val s4 = fresh[T4]
    val trans = new Vectorizer {
      val IR: self.IR.type = self.IR
    }

    val body = reifyBlock(f(s1, s2, s3, s4))
    emitSource(List(s1, s2, s3, s4), trans.transformBlock(body), className, stream)
  }
}
