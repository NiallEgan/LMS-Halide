import org.scalatest.FlatSpec
import scala.collection.mutable.ListBuffer

import sepia._

trait GradProg extends TestPipeline {
	override def prog(in: Rep[Array[Array[Int]]]): Rep[Unit] = {
		val f: Func =
			((x: Rep[Int], y: Rep[Int]) => x + y / 2) withDomain (5, 3)

		// This is for testing purposes only
		registerStage("f", f)
	}
}

trait BlurredGradProg extends TestPipeline {
	override def prog(in: Rep[Array[Array[Int]]]): Rep[Unit] = {
		val f: Func =
			((x: Rep[Int], y: Rep[Int]) => x + y / 2) withDomain (5, 3)

		val g: Func =
				((x: Rep[Int], y: Rep[Int]) =>
					(f(x, y) + f(x-1, y) + f(x, y-1) + f(x-1, y-1)) / 4) withDomain (5, 3)

		// This is for testing purposes only
		registerStage("f", f)
		registerStage("g", g)

	}
}

trait CompilerInstance extends ScheduleCompiler
											 with PipelineWithSchedManipulations with DslExp
											 with AstOps {
	self =>
    val codegen = new DslGenC {
      val IR: self.type = self
    }

	def ev(in: Rep[Array[Array[Int]]]) {
		prog(in)
		evalSched(sched)
	}

	def compile() {
		codegen.emitSource(ev, "pipeline",
			new java.io.PrintWriter(System.out))
	}
}

class CompilerSpec extends FlatSpec {
	"The grad program" should "return the default tree" in {
	 	val gradProg =
	 		new GradProg with CompilerInstance with TestAstOps
	 	gradProg.compile()
	 	val correctAst: ScheduleNode[String, String] =
	 		new RootNode(List(
	 			new StorageNode("f",List(
	 				new LoopNode("y", "f", Sequential, List(
	 					new LoopNode("x", "f", Sequential, List(
	 						new ComputeNode("f", List())
	 					))
	 				))
	 			))
	 		))
	 	assertResult(gradProg.scheduleRep)(correctAst)

		val gradGraph = new PipelineGraph with BlurredGradProg
		gradGraph.analyse()
	}
}
