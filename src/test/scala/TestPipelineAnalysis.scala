import org.scalatest.FlatSpec
import scala.collection.mutable.ListBuffer

import sepia._

trait TestPipelineAnalysis extends PipelineForAnalysis with TestPipeline {
  def boundsAsStrings(): Map[String, Map[String, Map[String, Bound]]] = {
    val bounds = getInputBounds()

    bounds.map{case (k, v) => asString(k) ->
         v.map{case (k1, v2) => asString(k1) -> v2}}
  }
}

class AnalysisSpec extends FlatSpec {
  "f in the blurred grad program" should
    "have bounds of (-1, 0), (-1, 0)" in {
      val blurredGradProg = new BlurredGradProg with TestPipelineAnalysis

      val bounds = blurredGradProg.boundsAsStrings
      assertResult(Bound(-1, 0))(bounds("g")("f")("x"))
      assertResult(Bound(-1, 0))(bounds("g")("f")("y"))
      assertResult(0)(bounds("f").size)
      assertResult(1)(bounds("g").size)
      assertResult(2)(bounds.size)
    }

  "the simple grad program" should "have no bounds" in {
    val gradProg = new GradProg with TestPipelineAnalysis

    val bounds = gradProg.boundsAsStrings

    assertResult(1)(bounds.size)
    assertResult(0)(bounds("f").size)
  }

  "f in the funky bounds program" should
    "have bounds of (-11, 10) (-80, 12)" in {
      val funkyBoundsProg = new FunkyBoundsProg with TestPipelineAnalysis

      val bounds = funkyBoundsProg.boundsAsStrings
      assertResult(Bound(-11, 10))(bounds("g")("f")("x"))
      assertResult(Bound(-80, 12))(bounds("g")("f")("y"))
      assertResult(0)(bounds("f").size)
      assertResult(1)(bounds("g").size)
      assertResult(2)(bounds.size)
    }

  "ThreeStageBoxBlur" should "have bounds of " in {
    val threeStageBoxBlur = new ThreeStageBoxBlur with TestPipelineAnalysis
    val bounds = threeStageBoxBlur.boundsAsStrings
    assertResult(Bound(0, 0))(bounds("h")("g")("x"))
    assertResult(Bound(-1, 1))(bounds("h")("g")("y"))
    assertResult(Bound(-1, 1))(bounds("g")("f")("x"))
    assertResult(Bound(0, 0))(bounds("g")("f")("y"))


  }
}
