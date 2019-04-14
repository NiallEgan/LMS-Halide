package sepia

case class Bound(val lb: Int, val ub: Int,
								 mulLower: Double, mulHigher: Double) {
	// Inclusive bounds
	def join(other: Bound): Bound = {
		val newLb = if (lb < other.lb) lb else other.lb
		val newUb = if (ub > other.ub) ub else other.ub
		val newMulLower = if (mulLower < other.mulLower) mulLower else other.mulLower
		val newMulHigher = if (mulHigher > other.mulHigher) mulHigher else other.mulHigher
	  Bound(newLb, newUb, newMulLower, newMulHigher)
	}

	def add(other: Bound): Bound = {
		Bound(lb + other.lb, ub + other.ub, mulLower * other.mulLower, mulHigher * other.mulHigher)
	}

	val mulLo: Int = mulLower.toInt
	val mulHi: Int = mulHigher.toInt

	def width(): Int = (ub - lb) + 1
}

object Bound {
	// TODO: Change to inf here
	val zero = Bound(Int.MaxValue, Int.MinValue, 1, 1)
}
