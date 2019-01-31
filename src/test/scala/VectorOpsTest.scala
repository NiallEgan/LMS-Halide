import org.scalatest.FlatSpec
import java.io.{PrintWriter,StringWriter,FileOutputStream}
import scala.collection.mutable.ListBuffer

import sepia._

trait VectorProgram extends Dsl {
  def prog() = {
    val a: Rep[Array[Int]] = array_obj_new[Int](4)
    vectorized_loop(0 until 4,
                   (i: Rep[Int]) => a(i) = i)
  }
}

trait Runner {
  val p: VectorProgram with DslExp
  def run() = {
    import p.unitTyp
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
