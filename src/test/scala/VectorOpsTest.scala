import org.scalatest.FlatSpec
import java.io.{PrintWriter,StringWriter,FileOutputStream}
import scala.collection.mutable.ListBuffer

import sepia._

trait VectorProgram extends Dsl {
  def prog() = {
    val a: Rep[Array[Double]] = array_obj_new[Double](4)

    vectorized_loop(0 until 8,
                   (i: Rep[Int]) => a(i) = i - 2)
    a(1)
  }
}

trait Runner {
  val p: VectorProgram with DslExp
  def run() = {
    import p.{unitTyp, intTyp, doubleTyp}
    val y = p.reifyEffects(p.prog())
    val codegen = new DslGenC {val IR: p.type = p}
    val trans = new Vectorizer {
      val IR: p.type = p
    }

    val graph = p.globalDefs
    //graph foreach println
    val z = trans.transformBlock(y)

    codegen.withStream(new PrintWriter(System.out)) {
      codegen.emitBlock(z)
    }
  }
}

class VectorOpsSpec extends FlatSpec {
  val r = new Runner {val p = new VectorProgram with DslExp }
  "vectorized prog" should "compile sucessfuly" in {
    r.run()
  }
}
