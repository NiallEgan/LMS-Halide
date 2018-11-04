import scala.collection.immutable.{Map, HashMap}

import sepia._

trait TestPipeline extends Pipeline {
	private var registeredFunctions: Map[Func, String] =
		new HashMap()

	def registerFunction(s: String, f: Func): Unit = {
		registeredFunctions = registeredFunctions + (f -> s)
	}

	def asString(f: Func): String = {
		registeredFunctions(f)
	}
}
