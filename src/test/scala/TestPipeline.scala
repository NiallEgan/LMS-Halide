import scala.collection.immutable.{Map, HashMap}

import sepia._

trait TestPipeline extends Pipeline {
	private var registeredFunctions: Map[Func, String] =
		new HashMap()
	var tidToFunc: Map[Int, Func] = Map()
	private var curId = 0

	def registerFunction(s: String, f: Func): Unit = {
		tidToFunc += curId -> f
		curId += 1
		registeredFunctions = registeredFunctions + (f -> s)
	}

	def asString(f: Func): String = {
		registeredFunctions(f)
	}
}
