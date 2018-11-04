import org.scalatest.FlatSpec
import scala.collection.mutable.ListBuffer

import sepia._

trait CompilerInstance extends ScheduleCompiler
											 with PipelineWithSchedManipulations with DslExp
											 with AstOps {
	self =>
    val codegen = new DslGenC {
      val IR: self.type = self
    }

	def ev(bounds: Map[Int, Map[Int, (Bound, Bound)]])(in: Rep[Array[Array[Int]]]) = {
		prog(in)
		evalSched(sched, bounds)
	}

	def compile(bounds: Map[Int, Map[Int, (Bound, Bound)]]) = {
		codegen.emitSource(ev(bounds), "pipeline",
			new java.io.PrintWriter(System.out))
	}
}

class CompilerSpec extends FlatSpec {
	"The grad program" should "return the default tree" in {
	 	val gradProg =
	 		new GradProg with CompilerInstance with TestAstOps

		val gradProgAnalysis = new GradProg with TestPipelineAnalysis

	 	gradProg.compile(gradProgAnalysis.getIdInputBounds)
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

	"The blurred grad program" should "return the default tree, with f inlined" in {
		val gradProg =
			new BlurredGradProg with CompilerInstance with TestAstOps

		val gradProgAnalysis = new BlurredGradProg with TestPipelineAnalysis

		gradProg.compile(gradProgAnalysis.getIdInputBounds)
		val correctAst: ScheduleNode[String, String] =
			new RootNode(List(
				new StorageNode("g",List(
					new LoopNode("y", "g", Sequential, List(
						new LoopNode("x", "g", Sequential, List(
							new ComputeNode("g", List())
						))
					))
				))
			))
		assertResult(gradProg.scheduleRep)(correctAst)
	}
}
