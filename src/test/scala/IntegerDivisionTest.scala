import org.scalatest.FlatSpec
import scala.collection.mutable.ListBuffer
import java.io.File

import sepia._

class IntDivisorSpec extends FlatSpec {
  import FastIntegerDivision._

  "7" should "have a multiplier of 613566757" in {
    assertResult(613566757)(getDivisor(7)._1)
  }

  "183" should "have a multiplier of 1713292919" in {
    assertResult(1713292919)(getDivisor(183)._1)
  }

  "813" should "have a multiplier of 1114683883" in {
    assertResult(1114683883)(getDivisor(813)._1)
  }

  "255" should "have a multiplier of 16843010" in {
    assertResult(16843010)(getDivisor(255)._1)
  }

  "3124" should "have a multiplier of 1336333789" in {
    assertResult(1336333789)(getDivisor(3124)._1)

  }
}
