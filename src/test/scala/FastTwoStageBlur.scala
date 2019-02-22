import org.scalatest.FlatSpec
import scala.collection.mutable.ListBuffer
import java.io.File

import sepia._

trait TwoStageBoxBlurFast extends TestPipeline {
	override def prog(in: Input, w: Rep[Int], h: Rep[Int]): Rep[Unit] = {
		val f = func[Short] {
			(x: Rep[Int], y: Rep[Int]) => (in(x, y) + in(x+1, y) + in(x-1, y)) / 3.toShort
		}
		val g = final_func[Short] {
			(x: Rep[Int], y: Rep[Int]) => (f(x, y) + f(x, y+1) + f(x, y-1)) / 3.toShort
		}

		f.computeRoot()
		//f.computeAt(g, "y")
		//f.storeRoot()
    //f.tile("x", "y", "x_outer", "y_outer", "x_inner", "y_inner", 2, 2)
		f.vectorize("x", 16)
		//g.vectorize("x", 16)
    registerFunction("f", f)
		registerFunction("g", g)
	}
}


class FastTwoStageBlur extends FlatSpec {
  "Fast box blur" should "go zoom" in {
    println("Fast two stage blur")
      val blurProg = new TwoStageBoxBlurFast with CompilerInstance
                   with TestAstOps
    val blurProgAnalysis = new TwoStageBoxBlurFast
                           with TestPipelineAnalysis
    val correctAst: TNode = TRootNode(List(
      TStorageNode("f", List(
        TStorageNode("g", List(
          TLoopNode("y", "g", Sequential(), List(
            TLoopNode("y_outer", "f", Sequential(), List(
              TLoopNode("x_outer", "f", Sequential(), List(
                TLoopNode("y_inner", "f", Sequential(), List(
                  TLoopNode("x_inner", "f", Sequential(), List(
                      TComputeNode("f", List())
                  ))
                ))
              ))
            )),
            TLoopNode("x", "g", Sequential(), List(
              TComputeNode("g", List())
            ))
          ))
        ))
      ))
    ))

    blurProg.compile(blurProgAnalysis.getBoundsGraph, "fast_blur")
    assertResult(correctAst)(blurProg.scheduleRep)
  }
}
