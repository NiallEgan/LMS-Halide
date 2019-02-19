package sepia


import ch.ethz.acl.passera.unsigned.{UByte, UInt, ULong, UShort}


object FastIntegerDivision {
  def ilog(d: Int) = {
    var result = 0
    var i = d
    while (i != 0) {
      result += 1
      i >>>= 1
    }

    result
  }

  def getDivisor(d: Int): (Int, Int, Int) = {
    val l: Int = ilog(d)
    val x: Long = 4294967296L / d
    println(f"l: $l")
    println(f"x: $x")
    val y: Long = ((1 << l) - d)
    println(f"y: $y")
    val m: Int = (x * y + 1).toInt

    val sh1: Int = l min 1
    val sh2: Int = l-1 max 0

    (m, sh1, sh2)
  }
}
