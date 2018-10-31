import scala.collection.immutable.{Map, HashMap}

import sepia._

trait TestPipeline extends Pipeline {
	private var registeredStages: Map[Func, String] =
		new HashMap()

	def registerStage(s: String, f: Func): Unit = {
		registeredStages = registeredStages + (f -> s)
	}

	def getStage(f: Func): String = {
		registeredStages(f)
	}
}
