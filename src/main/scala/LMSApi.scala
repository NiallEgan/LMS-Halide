package sepia

import java.io.PrintWriter

import scala.lms.common._

trait Dsl extends PrimitiveOps with NumericOps
          with BooleanOps with LiftPrimitives
          with LiftNumeric with LiftBoolean
          with IfThenElse with Equal
          with RangeOps with FractionalOps
          with ArrayOps with SeqOps
          with ImageBufferOps with ShortOps
          with OrderingOps {
}

trait DslExp extends Dsl with PrimitiveOpsExpOpt with NumericOpsExpOpt
             with BooleanOpsExpOpt with IfThenElseExpOpt
             with RangeOpsExp with FractionalOpsExp
             with EqualExpBridgeOpt with ArrayOpsExpOpt
             with SeqOpsExp with ImageBufferOpsExp
             with ShortOpsExpOpt with OrderingOpsExpOpt {}

trait DslGenC extends CGenNumericOps
  with CGenPrimitiveOps with CGenBooleanOps
  with CGenIfThenElse with CGenEqual
  with CGenRangeOps with CGenFractionalOps
  with CGenShortOps with CGenArrayOps
  with CGenOrderingOps  {
    val IR: DslExp
    import IR._

    override def isPrimitiveType(tpe: String) = tpe match {
      case "USHORT" => true
      case "UCHAR" => true
      case _ => super.isPrimitiveType(tpe)
    }

    override def remap[A](m: Typ[A]) = m.toString match {
      case "Array[Short]" => "UCHAR[]"
      case "Short" => "UCHAR"
      case _ => super.remap(m)

    }

    override def emitNode(sym: Sym[Any], rhs: Def[Any]) = {
      rhs match {
        case ArrayApply(x, m) => emitValDef(sym, src"$x[$m]")
        case ArrayUpdate(x, m, y) => stream.println(src"$x[$m] = $y;")
        case a@ArrayNew(m) => {
          val arrType = remap(a.m)
          //stream.println(f"$arrType ${quote(sym)}[${quote(m)}];")
          stream.println(f"$arrType *${quote(sym)} = malloc(sizeof($arrType) * ${quote(m)});")
          //emitValDef(sym, f"")
        }
        case ArrayFree(a) => stream.println(src"free($a);")
        case MemCpy(src, dest, size) => stream.println(
          src"memcpy($dest, $src, $size);")
        case IntToDoubleConversion(a) => emitValDef(sym, src"(double) $a")
        case DoubleToIntConversion(a) => emitValDef(sym, src"(int32_t) $a")
        case IntsTomm256i(a) => emitValDef(sym , src"(__m256i *) $a")
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
    val body = reifyBlock(f(s1, s2, s3, s4))
    emitSource(List(s1, s2, s3, s4), body, className, stream)
  }
}
