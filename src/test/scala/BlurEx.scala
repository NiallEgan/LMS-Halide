import sepia._
import org.scalatest.FlatSpec

trait Blur extends TestPipeline {
  override def prog(in: Input, w: Rep[Int], h: Rep[Int]): Rep[Unit] = {

    val g = func[Short] {
      (x: Rep[Int], y: Rep[Int]) => in(x,y) / 1.toShort
    }
    val h = func[Short] {
      (x: Rep[Int], y: Rep[Int]) => (g(x+2, y) + g(x, y) + g(x-2, y)) / 3.toShort
    }
    val i = final_func[Short] {
      (x: Rep[Int], y: Rep[Int]) => (h(x, y+2) + h(x, y) + h(x, y-2)) / 3.toShort
    }

    i.tile("x", "y", "x_outer", "y_outer", "x_inner", "y_inner", 32, 32)
    //i.split("y_inner", "y_outer2", "y_inner2", 16)
    h.computeAt(i, "y_outer")
    h.storeAt(i, "y_outer")
    h.tile("x", "y", "x_outer", "y_outer", "x_inner", "y_inner", 8, 8)
//    i.split("y_inner", "y_outer2", "y_inner2", 4)
//    g.storeAt(h, "y")
    g.computeAt(h, "y_outer")
//    g.split("y", "y_outer", "y_inner", 2)

    registerFunction("g", g)
    registerFunction("h", h)
    registerFunction("i", i)
  }
}

class BlurEx extends FlatSpec {
  "Blur" should "should make a blurry boi" in {
    println("BlurEx")

    val blur = new Blur with CompilerInstance with TestAstOps
    val blurAnalysis = new Blur with TestPipelineAnalysis
    blur.compile(blurAnalysis.getBoundsGraph, "blurry")
  }
}
