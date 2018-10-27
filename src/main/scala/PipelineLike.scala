package sepia

trait PipelineLike extends Dsl {
	class Dim(val max: Rep[Int]) {
		private var value: Option[Rep[Int]] = None

		def v: Rep[Int] = value match {
			case Some(x) => x
			case None => throw new InvalidSchedule("Unbound variable")
		}

		// TODO: Why is this not working with _= syntax?
		def v_=(new_val: Rep[Int]) = {
			value = Some(new_val)
		}
	}

	abstract class PipelineStage(f: (Rep[Int], Rep[Int]) => Rep[Int],
								 dom: (Int, Int)) {

		// TODO: This is not very nice with the mutable buffer etc
		// Revisit when thought a bit more about nodes for function
		// application

		val x: Dim
		val y: Dim

		var buffer: Option[Rep[Array[Array[Int]]]]

	    def apply(x: Rep[Int], y: Rep[Int]): Rep[Int]

	    def compute(): Rep[Int]

	    def storeInBuffer(v: Rep[Int]): Unit
	}
}