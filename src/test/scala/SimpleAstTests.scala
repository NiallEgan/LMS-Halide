import org.scalatest.FlatSpec
import scala.collection.mutable.ListBuffer
import java.io.File

import sepia._

trait CompilerInstance extends ScheduleCompiler
											 with PipelineForCompiler with DslExp
											 with AstOps {
	self =>
    val codegen = new DslGenC {
      val IR: self.type = self
    }

	def ev(boundsGraph: Map[Int, Map[Int, Map[String, Bound]]])
				(in: Rep[Array[UShort]], out: Rep[Array[UShort]], w: Rep[Int], h: Rep[Int]) = {
		compiler_prog(in, out, w, h)
		evalSched(sched, boundsGraph)
		println(sched)

		assignOutArray(out)
	}

	def compile(boundsGraph: Map[Int, Map[Int, Map[String, Bound]]], progname: String) = {
		codegen.emitSourceMut(ev(boundsGraph), "pipeline",
			new java.io.PrintWriter(new File(f"testOutput/$progname.c")))
	}
}

class CompilerSpec extends FlatSpec {
	"The grad program" should "return the default tree" in {
	 	val gradProg =
	 		new GradProg with CompilerInstance with TestAstOps

		val gradProgAnalysis = new GradProg with TestPipelineAnalysis

	 	gradProg.compile(gradProgAnalysis.getBoundsGraph, "simple_grad")
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
			assertResult(correctAst)(gradProg.scheduleRep)
	}

	"The blurred grad program" should "return the default tree, with f inlined" in {
		val gradProg =
			new BlurredGradProg with CompilerInstance with TestAstOps

		val gradProgAnalysis = new BlurredGradProg with TestPipelineAnalysis

		gradProg.compile(gradProgAnalysis.getBoundsGraph, "blurred_grad")
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
			assertResult(correctAst)(gradProg.scheduleRep)
	}

	"The blurred grad prog with computeAt" should "deinline f and move it" in {
		val gradProg =
			new BlurredGradProgComputeAt with CompilerInstance with TestAstOps
		val gradProgAnalysis = new BlurredGradProgComputeAt with TestPipelineAnalysis
		gradProg.compile(gradProgAnalysis.getBoundsGraph, "blurred_grad_compute_at")

		val correctAst: ScheduleNode[String, String] =
			new RootNode(List(
				new StorageNode("g",List(
					new LoopNode("y", "g", Sequential, List(
						new StorageNode("f", List(
							new LoopNode("y", "f", Sequential, List(
								new LoopNode("x", "f", Sequential, List(
									new ComputeNode("f", List())
								))
							)),
							new LoopNode("x", "g", Sequential, List(
								new ComputeNode("g", List())
							))
						))
					))
				))
			))

			assertResult(correctAst)(gradProg.scheduleRep)
	}

	"The three stage box blur program" should "return the default tree, with f & g inlined" in {
		val gradProg =
			new ThreeStageBoxBlur with CompilerInstance with TestAstOps

		val gradProgAnalysis = new ThreeStageBoxBlur with TestPipelineAnalysis

		println("Three stage box blur:")
		gradProg.compile(gradProgAnalysis.getBoundsGraph, "three_stage_box_blur")
		val correctAst: ScheduleNode[String, String] =
			new RootNode(List(
				new StorageNode("i",List(
					new LoopNode("y", "i", Sequential, List(
						new LoopNode("x", "i", Sequential, List(
							new ComputeNode("i", List())
						))
					))
				))
			))
			assertResult(correctAst)(gradProg.scheduleRep)
	}

	"The three stage blur with computeAt" should "inline g, but precompute f" in {
		println("Three stage box blur with computeAt:")
		val blurProg =
			new ThreeStageBoxBlurWithComputeAt with CompilerInstance with TestAstOps
		val blurProgAnalysis = new ThreeStageBoxBlurWithComputeAt with TestPipelineAnalysis
		blurProg.compile(blurProgAnalysis.getBoundsGraph, "three_stage_box_blur_with_compute_at")

		val correctAst: ScheduleNode[String, String] =
			new RootNode(List(
				new StorageNode("i",List(
					new LoopNode("y", "i", Sequential, List(
						new StorageNode("f", List(
							new LoopNode("y", "f", Sequential, List(
								new LoopNode("x", "f", Sequential, List(
									new ComputeNode("f", List())
								))
							)),
							new LoopNode("x", "i", Sequential, List(
								new ComputeNode("i", List())
							))
						))
					))
				))
			))

		assertResult(correctAst)(blurProg.scheduleRep)
	}

	"IDProg" should "create a simple prog" in {
		println("IDProg: ")
		val blurProg =
			new IDProg with CompilerInstance with TestAstOps
		val blurProgAnalysis = new IDProg with TestPipelineAnalysis
		blurProg.compile(blurProgAnalysis.getBoundsGraph, "id_prog")
	}

	"TwoStageBlur" should "make a blurring progam (I need better testing)" in {
		val blurProg =
			new TwoStageBoxBlur with CompilerInstance with TestAstOps
		val blurProgAnalysis = new TwoStageBoxBlur with TestPipelineAnalysis
		blurProg.compile(blurProgAnalysis.getBoundsGraph, "two_stage_blur")
	}
	"OneStageBoxBlur" should "make a blurring progam (I need better testing)" in {
		val blurProg =
			new OneStageBoxBlur with CompilerInstance with TestAstOps
		val blurProgAnalysis = new OneStageBoxBlur with TestPipelineAnalysis
		blurProg.compile(blurProgAnalysis.getBoundsGraph, "one_stage_blur")
	}

}
