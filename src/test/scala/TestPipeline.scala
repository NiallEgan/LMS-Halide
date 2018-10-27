import scala.collection.immutable.{Map, HashMap}

import sepia._

trait TestPipeline extends Pipeline {
	private var registeredStages: Map[String, Func] = 
		new HashMap()

	def registerStage(s: String, f: Func): Unit = {
		registeredStages = registeredStages + (s -> f)
	}

	def getStage(s: String): Func = {
		registeredStages(s)
	}
}