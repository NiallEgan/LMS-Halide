import org.scalatest.FlatSpec
import java.io.{PrintWriter,StringWriter,FileOutputStream}
import scala.collection.mutable.ListBuffer

import sepia._

trait VectorProgram extends Dsl {
  def prog() = {
    val a: Rep[Array[Short]] = array_obj_new[Short](16)
    for (i <- 0 until 16: Range) {
      a(i) = unit((1 << i).toShort)
    }

    vectorized_loop(0 until 16,
                   (i: Rep[Int]) => a(i) = numeric_divide[Short](a(i), 3.toShort))
    a(1)
  }
}

trait Runner {
  val p: VectorProgram with DslExp
  def run() = {
    import p.{unitTyp, intTyp, doubleTyp, floatTyp, shortTyp}
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

  "One stage box blur vectorized" should "vectorize x" in {
		println("One stage blur, vectorized: ")
		val blurProg = new BrightenedGradVectorized with CompilerInstance
									 with TestAstOps
		val blurProgAnalysis = new BrightenedGradVectorized
													 with TestPipelineAnalysis
		val correctAst: TNode = TRootNode(List(
			TStorageNode("f",List(
				TLoopNode("y","f",Sequential(),List(
					TLoopNode("x","f",Sequential(),List(
						TComputeNode("f",List())
          ))
        )),
				TStorageNode("i",List(
					TLoopNode("y","i",Sequential(),List(
						TStorageNode("g",List(
							TLoopNode("y","g",Sequential(),List(
								TLoopNode("x_outer", "g", Sequential(),List(
									TLoopNode("x", "g", Vectorized(16),List(
										TComputeNode("g",List())
                  ))
                ))
              )),
							TLoopNode("x","i",Sequential(),List(
                TComputeNode("i",List())
              )))
					))
				))
			))
		))
			)

		blurProg.compile(blurProgAnalysis.getBoundsGraph, "one_stage_blur_vectorized")
		assertResult(correctAst)(blurProg.scheduleRep)
	}
}
