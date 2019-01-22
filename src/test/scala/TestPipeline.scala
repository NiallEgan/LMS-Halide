import scala.collection.immutable.{Map, HashMap}

import sepia._

trait TestPipeline extends Pipeline {
	private var registeredFunctions: Map[Func[_], String] =
		new HashMap()
	var tidToFunc: Map[Int, Func[_]] = Map()
	private var curId = 0

	def registerFunction(s: String, f: Func[_]): Unit = {
		tidToFunc += curId -> f
		curId += 1
		registeredFunctions = registeredFunctions + (f -> s)
	}

	def asString(f: Func[_]): String = {
		registeredFunctions(f)
	}
}
