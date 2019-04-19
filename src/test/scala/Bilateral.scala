import org.scalatest.FlatSpec
import scala.collection.mutable.ListBuffer
import java.io.File
import scala.math._
import sepia._

trait BilateralFilter extends TestPipeline {
  class ThreeDBuffer(w: Rep[Int], h: Rep[Int], d: Int) {
    val arr = array_obj_new[Float](w * h * d)

    for(i <- 0 until w * h * d) arr(i) = 0

    def update(x: Rep[Int], y: Rep[Int], i: Rep[Int], v: Rep[Float]) = {
      arr(i + d * (x + w * y)) = v
    }

    def apply(x: Rep[Int], y: Rep[Int], i: Rep[Int]) = {
      // Maybe will want to clamp here or something?
      arr(i + d * (x + w * y))
    }
  }

  override def prog(in: Input, width: Rep[Int], height: Rep[Int]): Rep[Unit] = {
    val sigma_s = 1.5f
    val sigma_r = 1.5f
    val w = new ThreeDBuffer(width, height, 256)
    val wi = new ThreeDBuffer(width, height, 256)

    val populate_w = func[Int] { (x: Rep[Int], y: Rep[Int]) =>
      w(x, y, repCharToRepInt(in(x, y).red)) = 1
      unit(0)
    }

    val populate_wi = func[Int] { (x: Rep[Int], y: Rep[Int]) =>
      val i = repCharToRepInt(in(x, y).red)
      wi(x, y, i) = i
      unit(0)
    }

    def kernel(x: Int): Rep[Float] =
      (exp(-x * x / (2 * sigma_s * sigma_s)) / (sqrt(2*Pi) * sigma_s)).toFloat
    def kernel2(x: Int): Rep[Float] =
      (exp(-x * x / (2 * sigma_r * sigma_r)) / (sqrt(2*Pi) * sigma_s)).toFloat

    def blur_z(f: ThreeDBuffer) = (x: Rep[Int], y: Rep[Int], z: Rep[Int]) => {
      kernel2(0) * f(x, y, z) +
                 kernel2(1) * (f(x, y, z-1) + f(x, y, z+1)) +
                 kernel2(2) * (f(x, y, z-2) + f(x, y, z+2)) +
                 kernel2(3) * (f(x, y, z-3) + f(x, y, z+3))
    }
    val blur_x_wi = toFunc ((x: Rep[Int], y: Rep[Int]) => {
       val bl = blur_z(wi)
       for(z <- 3 until 253) {
         val b = kernel(0) * bl(x, y, z) +
                  kernel(1) * (bl(x-1, y, z) + bl(x+1, y, z)) +
                  kernel(2) * (bl(x-2, y, z) + bl(x+2, y, z)) +
                  kernel(3) * (bl(x-3, y, z) + bl(x+3, y, z))
        wi(x, y, z) = b
       }
       populate_wi(x-3, y) + populate_wi(x+3, y)
    }, ((3, width-3), (3, height-3)))

    val blur_y_wi = toFunc((x: Rep[Int], y: Rep[Int]) => {
      for (z <- 3 until 253) {
        val b = kernel(0) * wi(x, y, z) +
        kernel(1) * (wi(x, y-1, z) + wi(x, y+1, z)) +
        kernel(2) * (wi(x, y-2, z) + wi(x, y+2, z)) +
        kernel(3) * (wi(x, y-3, z) + wi(x, y+3, z))
        wi(x, y, z) = b
      }
      populate_wi(x, y-3) + populate_wi(x, y+3)
    }, ((3, width-3), (3, height-3))

    val blur_x_w = toFunc ((x: Rep[Int], y: Rep[Int]) => {
       val bl = blur_z(w)
       for(z <- 3 until 253) {
         val b = kernel(0) * bl(x, y, z) +
                  kernel(1) * (bl(x-1, y, z) + bl(x+1, y, z)) +
                  kernel(2) * (bl(x-2, y, z) + bl(x+2, y, z)) +
                  kernel(3) * (bl(x-3, y, z) + bl(x+3, y, z))
        w(x, y, z) = b
       }
       populate_w(x-3, y) + populate_w(x+3, y)
    }, ((3, width-3), (3, height-3)))

    val blur_y_w = final_func[Int] { (x: Rep[Int], y: Rep[Int]) =>
      for (z <- 0 until 256) {
        val b = kernel(0) * w(x, y, z) +
        kernel(1) * (w(x, y-1, z) + w(x, y+1, z)) +
        kernel(2) * (w(x, y-2, z) + w(x, y+2, z)) +
        kernel(3) * (w(x, y-3, z) + w(x, y+3, z))
        w(x, y, z) = b
      }
      populate_w(x, y-3) + populate_w(x, y+3)
    }

    blur_x_w.computeRoot()
    blur_y_wi.computeRoot()
    blur_x_wi.computeRoot()
    populate_wi.computeRoot()
    populate_w.computeRoot()
  }
}

class BilateralFilterTest extends FlatSpec {
	"Running BilateralFilter test" should "make a correct program" in {
		val lap = new BilateralFilter with CompilerInstance with TestAstOps

		println("Completed analysis")
		val lapProgAnalysis = new BilateralFilter with TestPipelineAnalysis

		lap.compile(lapProgAnalysis.getBoundsGraph, "bilateral")
	}
}
