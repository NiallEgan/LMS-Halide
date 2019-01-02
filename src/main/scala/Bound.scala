package sepia

case class Bound(val lb: Int, val ub: Int) {
	// Inclusive bounds
	def join(other: Bound): Bound = {
		val newLb = if (lb < other.lb) lb else other.lb
		val newUb = if (ub > other.ub) ub else other.ub
	  Bound(newLb, newUb)
	}

	def add(other: Bound): Bound = {
		Bound(lb + other.lb, ub + other.ub)
	}

	def width(): Int = (ub - lb) + 1
}

object Bound {
	// TODO: Change to inf here
	val zero = Bound(Int.MaxValue, Int.MinValue)
}
