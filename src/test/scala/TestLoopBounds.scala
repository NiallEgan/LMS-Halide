import org.scalatest.FlatSpec
import scala.collection.mutable.ListBuffer

import sepia._

class LoopBoundsSpec extends FlatSpec {
  "f in the ThreeStageBoundsAnalysisExample" should "have bounds of (-2, 2)" in {
    val progAnalysis = new ThreeStageBoundsAnalysisExample with TestPipelineAnalysis
    val bounds = progAnalysis.getBoundsGraph
    assertResult(Some(Bound(-2, 2)))(BoundsAnalysis.boundsForProdInCon(bounds, 0, 2, "x"))
  }
}
