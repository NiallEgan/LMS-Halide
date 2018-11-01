package sepia

import scala.reflect.SourceContext

import lms.common._

trait Array2DOps extends Base {
  object New2DArray {
    def apply[T:Typ](m: Rep[Int], n: Rep[Int]) = array2DObjNew[T](m, n)
  }


  implicit def repArrayToArrayOps[T:Typ](a: Rep[Array[Array[T]]]) = new Array2DOpsCls(a)
  class Array2DOpsCls[T:Typ](a: Rep[Array[Array[T]]]) {
    def apply(n: Rep[Int], m: Rep[Int])(implicit pos: SourceContext) =
      array2DApply(a, n, m)
    def update(n: Rep[Int], m: Rep[Int], y: Rep[T])(implicit pos: SourceContext) =
      array2DUpdate(a, n, m, y)
  }

  def array2DObjNew[T:Typ](n: Rep[Int], m: Rep[Int]): Rep[Array[Array[T]]]
  def array2DApply[T:Typ](x: Rep[Array[Array[T]]], n: Rep[Int], m: Rep[Int])
                           (implicit pos: SourceContext): Rep[T]
  def array2DUpdate[T:Typ](x: Rep[Array[Array[T]]], n: Rep[Int], m: Rep[Int], y: Rep[T])
                            (implicit pos: SourceContext): Rep[Unit]

}

trait Array2DOpsExp extends Array2DOps with EffectExp {
  implicit def array2DTyp[T:Typ]: Typ[Array[Array[T]]] = {
    implicit val ManifestTyp(m) = typ[T]
    manifestTyp
  }

  case class Array2DNew[T:Typ](n: Exp[Int], m: Exp[Int]) extends Def[Array[Array[T]]] {
    def man = typ[T]
  }

  case class Array2DApply[T:Typ](x: Exp[Array[Array[T]]], n: Exp[Int], m: Exp[Int])
    extends Def[T] {
    def man = typ[T]
  }

  case class Array2DUpdate[T:Typ](x: Exp[Array[Array[T]]], n: Exp[Int], m: Exp[Int], y: Exp[T])
    extends Def[Unit] {
    def man = typ[T]
  }


  override def array2DObjNew[T:Typ](n: Exp[Int], m: Exp[Int]) = reflectMutable(Array2DNew(n, m))
  override def array2DApply[T:Typ](x: Exp[Array[Array[T]]], n: Exp[Int], m: Exp[Int])
                                    (implicit pos: SourceContext) = Array2DApply(x, n, m)
  override def array2DUpdate[T:Typ](x: Exp[Array[Array[T]]], n: Exp[Int], m: Exp[Int], y: Exp[T])
                                     (implicit pos: SourceContext) =
    reflectWrite(x)(Array2DUpdate(x, n, m, y))
}

trait CGenArray2DOps extends CGenBase {
  val IR: Array2DOpsExp
  import IR._

  override def emitNode(sym: Sym[Any], rhs: Def[Any]) = {
    rhs match {
      case Array2DApply(x, n, m) => emitValDef(sym, src"$x[$n][$m]")
      case Array2DUpdate(x, n, m, y) => stream.println(src"$x[$n][$m] = $y;")
      case a@Array2DNew(n, m) => {
        val arrType = remap(a.man)
        stream.println(f"$arrType[${quote(n)}][${quote(m)}] ${quote(sym)};")
      }
      case _ => super.emitNode(sym, rhs)
    }
  }

  override def remap[A](m: Typ[A]) = m.toString match {
    case "Array[Array[Int]]" => "int*" // TODO: Change codegen so we can have int ** here
    case _ => super.remap(m)
  }
}
