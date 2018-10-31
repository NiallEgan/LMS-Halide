import org.scalatest.FlatSpec

import sepia._

trait GradProg extends TestPipeline {
	override def prog(): Rep[Unit] = {
		val f =
		(x: Rep[Int], y: Rep[Int]) => x + y //  withDomain (5, 5)

		// This is for testing purposes only
		registerStage("f", f)
	}
}

trait CompilerInstance extends ScheduleCompiler with Pipeline
		with DslExp with AstOps {
	self =>
    val codegen = new DslGenC {
      val IR: self.type = self
    }

	def ev(in: Rep[Unit]) {
		evalSched(sched)
	}

	def compile() {
		prog()
		println(sched)
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
	}
}
