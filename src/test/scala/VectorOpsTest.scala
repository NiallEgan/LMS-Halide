import org.scalatest.FlatSpec
import scala.collection.mutable.ListBuffer

import sepia._

trait VectorProgram extends Dsl {
  def prog() = {
    val a: Rep[Array[Int]] = array_obj_new[Int](4)
    vectorized_loop(0 until 4,
                   (i: Rep[Int]) => a(i) = i)
  }
}

class VectorOpsSpec extends FlatSpec {

}
