import org.scalatest.FlatSpec
import scala.collection.mutable.ListBuffer
import java.io.File
import scala.math._
import sepia._

trait BilateralFilter2 extends TestPipeline {
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

  def clamp(v: Rep[Float], low: Rep[Float], high: Rep[Float]): Rep[Float] = {
    ordering_max(low, ordering_min(v, high))
  }
  override def prog(in: Input, width: Rep[Int], height: Rep[Int]): Rep[Unit] = {
    val s_sigma = 8
    val r_sigma = 0.1f
    val depth = 12
    val w = new ThreeDBuffer(width, height, depth)
    val wi = new ThreeDBuffer(width, height, depth)
    val hist = List(w, wi)

    val blurred_xz = List(new ThreeDBuffer(width, height, depth),
                       new ThreeDBuffer(width, height, depth))
    val blurred = List(new ThreeDBuffer(width, height, depth),
                       new ThreeDBuffer(width, height, depth))


    val clamped = (x: Rep[Int], y: Rep[Int]) => {
      val p = in(x, y).map(repCharToRepFloat(_))
      //val gray = (0.3f * p.red + 0.59f * p.green + 0.11f * p.blue +0.5f) / 255.0f
      val gray = p.red / 255.0f
      //val gray = (p.red + p.green + p.blue) / 3.0f / 255.0f
      gray
    }

    val constructGrid = func[Int]( (x: Rep[Int], y: Rep[Int]) => {
      for (ry <- 0 until s_sigma: Rep[Range]) { // q: until?
        for (rx <- 0 until s_sigma: Rep[Range]) {
          val v = clamped(x * s_sigma + rx - s_sigma/2, y * s_sigma + ry - s_sigma / 2)
          val zi = repFloatToRepInt(clamp(v, 0.0f, 1.0f) * 10 + 0.5f)

          hist(0)(x, y, zi) = hist(0)(x, y, zi) + v
          hist(1)(x, y, zi) = hist(1)(x, y, zi) + 1.0f
        }
      }
      unit(0)
    })


    def blurz(x: Rep[Int], y: Rep[Int], z: Rep[Int], c: Int) = {
      val zBound: Rep[Int] = ordering_max(0, ordering_min(z, depth-1))
      val xBound: Rep[Int] = ordering_max(0, ordering_min(x, width-1))
      val yBound: Rep[Int] = ordering_max(0, ordering_min(y, height-1))

      hist(c)(xBound, yBound, zBound-2) +
      hist(c)(xBound, yBound, zBound-1) * 4 +
      hist(c)(xBound, yBound, zBound) * 6 +
      hist(c)(xBound, yBound, zBound+1) * 4 +
      hist(c)(xBound, yBound, zBound+2)
    }
    val blurxz = toFunc((x: Rep[Int], y: Rep[Int]) => {
     for(c <- 0 until 2: Range) {
        for (z <- 0 until depth) {
          val v = blurz(x-2, y, z, c) +
                  blurz(x-1, y, z, c) * 4 +
                  blurz(x,   y, z, c) * 6 +
                  blurz(x+1, y, z, c) * 4 +
                  blurz(x+2, y, z, c)
          blurred_xz(c)(x, y, z) = v
        }
      }
      unit(0)
    }, ((0, width), (0, height)))

    val blury = toFunc((x: Rep[Int], y: Rep[Int]) => {
      for (c <- 0 until 2: Range) {
        for (z <- 0 until depth) {
          val v = blurred_xz(c)(x, y-2, z) +
                  blurred_xz(c)(x, y-1, z) * 4 +
                  blurred_xz(c)(x, y, z) * 6 +
                  blurred_xz(c)(x, y+1, z) * 4 +
                  blurred_xz(c)(x, y+2, z)
          blurred(c)(x, y, z) = v
        }
      }
      unit(0)
    }, ((0, width), (0, height)))

    def lerp(a: Rep[Float], b: Rep[Float], c: Rep[Float]) = {
      a * (1-c) + b * c
    }
    val interpolated = new ListBuffer[Func[Float]]
    for (c <- 0 until 2: Range) {
      val i = toFunc((x: Rep[Int], y: Rep[Int]) => {
        //val t = clamp(clamped(x, y), 0.0f, 1.0f)
        val t = clamped(x, y)
        val zv = t * (1.0f / r_sigma)
        val zi = repFloatToRepInt(zv)
        val zf = zv - repIntToRepFloat(zi)
        val xf = repIntToRepFloat(x % s_sigma) / s_sigma.toFloat
        val yf = repIntToRepFloat(y % s_sigma) / s_sigma.toFloat
        val xi = x / s_sigma
        val yi = y / s_sigma
        val h = blurred(c)
        lerp(lerp(lerp(h(xi, yi, zi), h(xi+1, yi, zi), xf),
                  lerp(h(xi, yi+1, zi), h(xi+1, yi+1, zi), xf), yf),
             lerp(lerp(h(xi, yi, zi+1), h(xi+1, yi, zi+1), xf),
                  lerp(h(xi, yi+1, zi+1), h(xi+1, yi+1, zi+1), xf), yf), zf)
      }, ((0, width), (0, height)))
      interpolated += i
    }

    val f = toFunc[Float]((x: Rep[Int], y: Rep[Int]) => {
      val v0 = interpolated(0)(x, y)
      val v1 = interpolated(1)(x, y)
      val v = 255.0f * (v0 / v1) + 0.5f
      v
    }, ((0, width), (0, height)))

    val g = final_func[Float]((x: Rep[Int], y: Rep[Int]) => {
      f(x, y)
    })


    interpolated(1).computeRoot()
    interpolated(0).computeRoot()
    blury.computeRoot()
    blurxz.computeRoot()
    constructGrid.computeRoot()
    clamped.computeRoot()
  }
}

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
    val sigma_s = 8f
    val sigma_r = 3f
    val w = new ThreeDBuffer(width, height, 256)
    val wi = new ThreeDBuffer(width, height, 256)

    val intensity = func[Int] { (x: Rep[Int], y: Rep[Int]) =>
      val p = in(x, y).map(repCharToRepFloat(_))
      val gray = repFloatToRepInt(0.21f * p.red + 0.72f * p.green + 0.07f * p.blue)
      RGBVal(gray, gray, gray)
    }

    val populate_w = func[Int] { (x: Rep[Int], y: Rep[Int]) =>
      w(x, y, intensity(x, y).red) = 1
      unit(0)
    }

    val populate_wi = func[Int] { (x: Rep[Int], y: Rep[Int]) =>
      val i = intensity(x, y).red
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
    }, ((3, width-3), (0, height)))

    val blur_y_wi = toFunc((x: Rep[Int], y: Rep[Int]) => {
      for (z <- 3 until 253) {
        val b = kernel(0) * wi(x, y, z) +
        kernel(1) * (wi(x, y-1, z) + wi(x, y+1, z)) +
        kernel(2) * (wi(x, y-2, z) + wi(x, y+2, z)) +
        kernel(3) * (wi(x, y-3, z) + wi(x, y+3, z))
        wi(x, y, z) = b
      }
      populate_wi(x, y-3) + populate_wi(x, y+3)
    }, ((3, width-3), (3, height-3)))

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
    }, ((3, width-3), (0, height)))

    val blur_y_w = toFunc ((x: Rep[Int], y: Rep[Int]) => {
      for (z <- 0 until 256) {
        val b = kernel(0) * w(x, y, z) +
        kernel(1) * (w(x, y-1, z) + w(x, y+1, z)) +
        kernel(2) * (w(x, y-2, z) + w(x, y+2, z)) +
        kernel(3) * (w(x, y-3, z) + w(x, y+3, z))
        w(x, y, z) = b
      }
      populate_w(x, y-3) + populate_w(x, y+3)
    }, ((3, width-3), (3, height-3)))

    val output = final_func[Float] { (x: Rep[Int], y: Rep[Int]) =>
      val i = intensity(x, y).red
      val v = wi(x, y, i) / w(x, y, i)
      RGBVal(v, v, v)
    }

    blur_y_w.computeRoot()
    blur_x_w.computeRoot()
    blur_y_wi.computeRoot()
    blur_x_wi.computeRoot()
    populate_wi.computeRoot()
    populate_w.computeRoot()
    intensity.computeRoot()
  }
}

class BilateralFilterTest extends FlatSpec {
	"Running BilateralFilter test" should "make a correct program" in {
		val lap = new BilateralFilter2 with CompilerInstance with TestAstOps

		println("Completed analysis")
		val lapProgAnalysis = new BilateralFilter2 with TestPipelineAnalysis

		lap.compile(lapProgAnalysis.getBoundsGraph, "bilateral")
	}
}
