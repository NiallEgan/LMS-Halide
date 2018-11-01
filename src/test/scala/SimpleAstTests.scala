import org.scalatest.FlatSpec

import sepia._

trait GradProg extends TestPipeline {
	override def prog(in: Rep[Array[Array[Int]]]): Rep[Unit] = {
		val f: Func =
			((x: Rep[Int], y: Rep[Int]) => x + y / 2) withDomain (5, 3)

		// This is for testing purposes only
		registerStage("f", f)
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

trait PipelineGraph extends FuncExp with DslExp with Pipeline {
	// This will be the start of the toolkit to analyse the call graph
	def toFunc(f: (Rep[Int], Rep[Int]) => Rep[Int], dom: (Int, Int)): Func = {
		// TODO: Add f to a list of funcs
		mkFunc(f, dom)
	}
}

class GradGraph extends GradProg with PipelineGraph

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
	}
}
