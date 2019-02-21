import org.scalatest.FlatSpec
import scala.collection.mutable.ListBuffer
import java.io.File

import sepia._

class IntDivisorSpec extends FlatSpec {
  import FastIntegerDivision._

  "7" should "have a multiplier of 9363" in {
    assertResult(9363)(getDivisor(7)._1)
  }

  "183" should "have a multiplier of 26135" in {
    assertResult(26135)(getDivisor(183)._1)
  }

  "813" should "have a multiplier of 16881" in {
    assertResult(16881)(getDivisor(813)._1)
  }

  "255" should "have a multiplier of 258" in {
    assertResult(258)(getDivisor(255)._1)
  }

  "3124" should "have a multiplier of 19441" in {
    assertResult(19441)(getDivisor(3124)._1)

  }
}
