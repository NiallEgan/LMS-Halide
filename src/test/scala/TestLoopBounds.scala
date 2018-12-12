import org.scalatest.FlatSpec
import scala.collection.mutable.ListBuffer

import sepia._

class LoopBoundsSpec extends FlatSpec {
  "f in the ThreeStageBoundsAnalysisExample" should "have bounds of (-2, 2)" in {
    val progAnalysis = new ThreeStageBoundsAnalysisExample with TestPipelineAnalysis
    val bounds = progAnalysis.getBoundsGraph
    assertResult(Some(Bound(-2, 2)))(BoundsAnalysis.boundsForProdInCon(bounds, 0, 2, "x"))
  }

  "in in the one stage box blur" should "have bounds of (-1, -1)" in {
    val progAnalysis = new OneStageBoxBlur with TestPipelineAnalysis

    val bounds = progAnalysis.getBoundsGraph
    println("One ")
    println(progAnalysis.boundsAsStrings)

    assertResult(Some(Bound(-1, 1)))(BoundsAnalysis.boundsForProdInCon(bounds, -1, 0, "x"))
    assertResult(Some(Bound(-1, 1)))(BoundsAnalysis.boundsForProdInCon(bounds, -1, 0, "y"))

  }

  "in in the two stage box blur" should "have bounds of (-1, -1)" in {
    val progAnalysis = new TwoStageBoxBlur with TestPipelineAnalysis

    val bounds = progAnalysis.getBoundsGraph
    println("Two ")
    println(progAnalysis.boundsAsStrings)

    assertResult(Some(Bound(-1, 1)))(BoundsAnalysis.boundsForProdInCon(bounds, -1, 1, "x"))
    assertResult(Some(Bound(-1, 1)))(BoundsAnalysis.boundsForProdInCon(bounds, -1, 1, "y"))
  }

  "in in the three stage box blur" should "have bounds of (-1, -1)" in {
    val progAnalysis = new ThreeStageBoxBlur with TestPipelineAnalysis

    val bounds = progAnalysis.getBoundsGraph
    println("One ")
    println(progAnalysis.boundsAsStrings)

    assertResult(Some(Bound(-1, 1)))(BoundsAnalysis.boundsForProdInCon(bounds, -1, 2, "x"))
    assertResult(Some(Bound(-1, 1)))(BoundsAnalysis.boundsForProdInCon(bounds, -1, 2, "y"))

  }

}
