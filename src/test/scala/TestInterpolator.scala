import org.scalatest.FlatSpec
import scala.collection.mutable.ListBuffer
import java.io.File
import scala.math._
import sepia._

trait Interpolator extends TestPipeline {
	override def prog(in: Input, w: Rep[Int], h: Rep[Int]): Rep[Unit] = {
		val f = final_func[Short] { (x: Rep[Int], y: Rep[Int]) =>
			in(2 * x + 1, y) - in (2 * x - 1, y)
		}

		registerFunction("f", f)
	}
}

trait DownSampler extends TestPipeline {
  override def prog(in: Input, w: Rep[Int], h: Rep[Int]): Rep[Unit] = {
    val downx = func[Float] ((x: Rep[Int], y: Rep[Int]) =>
      (in(2*x-1, y) + 3.0f * (in(2*x, y) + in(2*x+1, y)) + in(2*x+2, y)) / 8.0f
    )
    val downy = final_func[Float] ((x: Rep[Int], y: Rep[Int]) =>
      (downx(x, 2*y-1) + 3.0f * (downx(x, 2*y) + downx(x, 2*y+1)) + downx(x, 2*y+2)) / 8.0f
    )
  }
}

trait UpSampler extends TestPipeline {
  override def prog(in: Input, w: Rep[Int], h: Rep[Int]): Rep[Unit] = {
    val upx = func[Float] ((x: Rep[Int], y: Rep[Int]) =>
       0.25f * in(x/2 + 2 * (x % 2) - 1, y) + 0.75f * in(x / 2, y)
       // + 2 * (x % 2
    )
    val upy = final_func[Float] ((x: Rep[Int], y: Rep[Int]) =>
      0.25f * upx(x, y / 2  + 2 * (y % 2) - 1) + 0.75f * upx(x, y / 2)
      // + 2 * (x % 2
    )
	}
}

trait UpDownSampler extends TestPipeline {
	override def prog(in: Input, w: Rep[Int], h: Rep[Int]): Rep[Unit] = {
		val upx = func[Float] {(x: Rep[Int], y: Rep[Int]) =>
			 0.25f * in(x/2 - 1, y) + 0.75f * in(x/2, y)
			 // + 2 * (x % 2
		}
		val upy = func[Float] {(x: Rep[Int], y: Rep[Int]) =>
			0.25f * upx(x, y / 2 - 1) + 0.75f * upx(x, y / 2)
			// + 2 * (x % 2
		}


		val downx = func[Float] {(x: Rep[Int], y: Rep[Int]) =>
			(upy(2*x-1, y) + 3.0f * (upy(2*x, y) + upy(2*x+1, y)) + upy(2*x+2, y)) / 8.0f
		}
		val downy = func[Float] {(x: Rep[Int], y: Rep[Int]) =>
			(downx(x, 2*y-1) + 3.0f * (downx(x, 2*y) + downx(x, 2*y+1)) + downx(x, 2*y+2)) / 8.0f
		}

		val diff = final_func[Float] { (x: Rep[Int], y: Rep[Int]) =>
			downy(x, y) - in(x, y)
		}
		upy.computeRoot()

	}
}

trait BilinearSampling extends TestPipeline {
	val sigma = 1.5f
	def kernel(x: Int): Rep[Float] =
    (exp(-x * x / (2 * sigma * sigma)) / (sqrt(2*Pi) * sigma)).toFloat

	def downsample(f: Func[Float]): Func[Float] = {
		val downx = func[Float] ((x: Rep[Int], y: Rep[Int]) =>
			(f(2*x-1, y) + 3.0f * (f(2*x, y) + f(2*x+1, y)) + f(2*x+2, y)) / 8.0f
		)
		downx addName "downx"
		val downy = func[Float] ((x: Rep[Int], y: Rep[Int]) =>
			(downx(x, 2*y-1) + 3.0f * (downx(x, 2*y) + downx(x, 2*y+1)) + downx(x, 2*y+2)) / 8.0f
		)
		downy
	}

	def upsample(f: Func[Float]): Func[Float] = {
		val upx = func[Float] ((x: Rep[Int], y: Rep[Int]) =>
			 0.25f * f(x/2 - 1 + 2 * (x % 2), y) + 0.75f * f(x/2, y)
			 // + 2 * (x % 2
		)
		upx addName "upx"
		val upy = func[Float] ((x: Rep[Int], y: Rep[Int]) =>
			0.25f * upx(x, y / 2 - 1 + 2 * (y % 2)) + 0.75f * upx(x, y / 2)
			// + 2 * (x % 2
		)
		upy addName "upy"
		upy
	}

}

trait GaussianPyramid extends TestPipeline with BilinearSampling {
	val levels = 3
	override def prog(in: Input, w: Rep[Int], h: Rep[Int]): Rep[Unit] = {
		val pyramid = new ListBuffer[(Func[Float], Func[Float])]
		val floatInput: Func[Float] = func[Float] { (x: Rep[Int], y: Rep[Int]) =>
			in(x, y).map(repCharToRepFloat(_))
		}

		for (j <- 0 until levels: Range) {
			val g = if (j == 0) floatInput else downsample(pyramid(j-1)._2)
			val blur_y = func[Float] { (x: Rep[Int], y: Rep[Int]) =>
					(kernel(0) * g(x, y) +
					 kernel(1) * (g(x, y-1) + g(x, y+1)) +
					 kernel(2) * (g(x, y-2) + g(x, y+2)) +
					 kernel(3) * (g(x, y-3) + g(x, y+3)))
			}

			val blur_x = func[Float] { (x: Rep[Int], y: Rep[Int]) =>
				 (kernel(0) * blur_y(x, y) +
					kernel(1) * (blur_y(x-1, y) + blur_y(x+1, y)) +
					kernel(2) * (blur_y(x-2, y) + blur_y(x+2, y)) +
					kernel(3) * (blur_y(x-3, y) + blur_y(x+3, y)))
			}

			pyramid += ((blur_y, blur_x))
		}

		val f = final_func[Float] { (x: Rep[Int], y: Rep[Int]) =>
			pyramid(levels-1)._2(x, y)
		}

		for (j <- levels - 1 to 0 by -1) {
			val (blur_y, blur_x) = pyramid(j)
			blur_x.computeRoot()
			blur_y.computeRoot()
		}
		//pyramid.reverse.foreach(_.computeRoot())
	}
}

trait LaplacePyramid extends TestPipeline with BilinearSampling {
	val levels = 3
	override def prog(in: Input, w: Rep[Int], h: Rep[Int]): Rep[Unit] = {
		val dynamicArray: Rep[Array[Int]] = array_obj_fromseq(List(200, 100, 300, 400, 500))
		val pyramid = new ListBuffer[(Func[Float], Func[Float])]
		val floatInput: Func[Float] = func[Float] { (x: Rep[Int], y: Rep[Int]) =>
			in(x, y).map(repCharToRepFloat(_))
		}

		for (j <- 0 until levels: Range) {
			val g = if (j == 0) floatInput else downsample(pyramid(j-1)._2)
			val blur_y = func[Float] { (x: Rep[Int], y: Rep[Int]) =>
					array_update(dynamicArray, 30, unit(50))
					(kernel(0) * g(x, y) +
					 kernel(1) * (g(x, y-1) + g(x, y+1)) +
					 kernel(2) * (g(x, y-2) + g(x, y+2)) +
					 kernel(3) * (g(x, y-3) + g(x, y+3)))
			}

			val blur_x = func[Float] { (x: Rep[Int], y: Rep[Int]) =>
				 (kernel(0) * blur_y(x, y) +
					kernel(1) * (blur_y(x-1, y) + blur_y(x+1, y)) +
					kernel(2) * (blur_y(x-2, y) + blur_y(x+2, y)) +
					kernel(3) * (blur_y(x-3, y) + blur_y(x+3, y)))
			}

			pyramid += ((blur_y, blur_x))
		}

		val lPyramid = new ListBuffer[Func[Float]]
		pyramid(levels-1)._2 +=: lPyramid
		for (j <- levels - 2 to 0 by -1: Range) {
			val us = upsample(pyramid(j+1)._2)
			val g = func[Float] { (x: Rep[Int], y: Rep[Int]) =>
				pyramid(j)._2(x, y) - us(x, y)
			}

			g addName f"laplacian pyramid $j"
			g +=: lPyramid
		}

		val fin = final_func[Float] ((x: Rep[Int], y: Rep[Int]) => lPyramid(0)(x, y))

		for (i <- levels-1 to 0) lPyramid(i).computeRoot()

		for (j <- levels - 1 to 0 by -1) {
			val (blur_y, blur_x) = pyramid(j)
			blur_x.computeRoot()
			blur_y.computeRoot()
		}
    //inGaussPyramid(1).computeRoot()
		//inGaussPyramid(0).computeRoot()
		//reverse.foreach(_.computeRoot())
	}
}

class GaussTest extends FlatSpec {
	"Running gauss test" should "make a gaussagon" in {
		val gauss = new GaussianPyramid with CompilerInstance with TestAstOps
		val gaussProgAnalysis = new GaussianPyramid with TestPipelineAnalysis

		gauss.compile(gaussProgAnalysis.getBoundsGraph, "gauss")
	}
}

class LaplaceTest extends FlatSpec {
	"Running Laplace test" should "make a correct program" in {
		val lap = new LaplacePyramid with CompilerInstance with TestAstOps

		println("Completed analysis")
		val lapProgAnalysis = new LaplacePyramid with TestPipelineAnalysis

		lap.compile(lapProgAnalysis.getBoundsGraph, "laplace")
	}
}


class InterpolatorTest extends FlatSpec {
	"The interpolator program" should "return the default tree" in {
		println("interpolator")
	 	val gradProg =
	 		new Interpolator with CompilerInstance with TestAstOps

		val gradProgAnalysis = new Interpolator with TestPipelineAnalysis

	 	gradProg.compile(gradProgAnalysis.getBoundsGraph, "interpolator")
	}

  "The downsampler" should "make a downsampling program" in {
    println("down sampler")

    val downSampler =
      new DownSampler with CompilerInstance with TestAstOps

    val downSamplerAnaylsis = new DownSampler with TestPipelineAnalysis

    downSampler.compile(downSamplerAnaylsis.getBoundsGraph, "downsample")
  }

  "The upsampler" should "make a upsampling program" in {
    println("up sampler")

    val upSampler =
      new UpSampler with CompilerInstance with TestAstOps

    val upSamplerAnalysis = new UpSampler with TestPipelineAnalysis
    upSampler.compile(upSamplerAnalysis.getBoundsGraph, "upsample")
  }

	"The downsampler with upsampler" should "make a program with correct bounds" in {
		println("updownsampler")

		val upSampler =
			new UpDownSampler with CompilerInstance with TestAstOps

		val upSamplerAnalysis = new UpDownSampler with TestPipelineAnalysis
		upSampler.compile(upSamplerAnalysis.getBoundsGraph, "updownsample")
	}
}
