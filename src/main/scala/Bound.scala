package sepia

case class Bound(val lb: Int, val ub: Int,
								 mulLower: Int, mulHigher: Int,
							   divLower: Int, divHigher: Int) {
	// Inclusive bounds
	def join(other: Bound): Bound = {
		val newLb = if (lb < other.lb) lb else other.lb
		val newUb = if (ub > other.ub) ub else other.ub
		val newMulLower = if (mulLower < other.mulLower) mulLower else other.mulLower
		val newMulHigher = if (mulHigher > other.mulHigher) mulHigher else other.mulHigher
		val newDivLower = if (divLower < other.divLower) divLower else other.divLower
		val newDivHigher = if (divHigher > other.divHigher) divHigher else other.divHigher
	  Bound(newLb, newUb, newMulLower, newMulHigher, newDivLower, newDivHigher)
	}

	def add(other: Bound): Bound = {
		Bound(lb + other.lb, ub + other.ub,
			    mulLower * other.mulLower, mulHigher * other.mulHigher,
					divLower * other.divLower, divHigher * other.divHigher)
	}

	// dry
  def ceil(n: Int, d: Int): Int = {
		if (n % d == 0) n / d
		else n / d + 1
	}

	def width(): Int = (ub * ceil(mulHigher,  divHigher) - lb * ceil(mulLower, divLower)) + 1
}

object Bound {
	// TODO: Change to inf here
	// Not actually zero, in that not identity!!!
	// More like bottom
	val zero = Bound(Int.MaxValue, Int.MinValue, Int.MaxValue, Int.MinValue, Int.MaxValue, Int.MinValue)
}
