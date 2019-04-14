package sepia


import ch.ethz.acl.passera.unsigned.{UByte, UInt, ULong, UShort}


object FastIntegerDivision {
  def ilog(d: Short) = {
    var result = 0
    var i: Int = d.toInt
    while (i != 0) {
      result += 1
      i = i >>> 1
    }

    result
  }

  def getDivisor(d: Short): (Short, Int, Int) = {
    val l: Int = ilog(d)
    val x: Int = 65536 / d
    val y: Int = ((1 << l) - d)
    val m: Short = (x * y + 1).toShort

    val sh1: Int = l min 1
    val sh2: Int = l-1 max 0

    (m, sh1, sh2)
  }
}
