import sepia._
import org.scalatest.FlatSpec
import scala.math._
import collection.mutable.ListBuffer

trait FiveByFive extends TestPipeline {
  	override def prog(in: Input, w: Rep[Int], h: Rep[Int]): Rep[Unit] = {
  		val g = func[Int] {
  			(x: Rep[Int], y: Rep[Int]) => (in(x+2, y) + in(x-2, y) + in(x, y) + in(x+1, y) + in(x-1, y)) / 5
  		}
  		val i = final_func {
  			(x: Rep[Int], y: Rep[Int]) => (g(x+2, y) + g(x-2, y) + g(x, y) + g(x, y+1) + g(x, y-1)) / 5
  		}

  		registerFunction("g", g)
  		registerFunction("i", i)
  	}
}

class FourByFour extends FlatSpec {
  "FourByFour" should "make a blurry boi" in {
    println("five by five blur")
    val blurProg =
      new FiveByFive with CompilerInstance with TestAstOps
    val blurProgAnalysis = new FiveByFive with TestPipelineAnalysis
    blurProg.compile(blurProgAnalysis.getBoundsGraph, "very_blurred")
  }
}
