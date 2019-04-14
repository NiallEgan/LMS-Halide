import sepia._
import org.scalatest.FlatSpec
import scala.math._
import collection.mutable.ListBuffer

trait EdgeFilter extends TestPipeline {
  def simpleConvolution(k: List[List[Rep[Int]]],
                        input: Func[Float]): Func[UChar] = {
    final_func { (x: Rep[Int], y: Rep[Int]) => {
      (k(0)(0) * input(x-1, y-1) + k(0)(1) * input(x, y-1) + k(0)(2) * input(x+1, y-1) +
      k(1)(0) * input(x-1, y)   + k(1)(1) * input(x, y)   + k(1)(2) * input(x+1, y) +
      k(2)(0) * input(x-1, y+1) + k(2)(1) * input(x, y+1) + k(2)(2) * input(x+1, y+1)).map(repFloatToRepChar)
    }}
  }

    def twoDConvolution(xKernel: List[Int], yKernel: List[Int], input: Func[Float]): (Func[Float], Func[Float]) = {
    val convolvex = func { (x: Rep[Int], y: Rep[Int]) =>
      xKernel(0) * input(x-1, y)  + input(x, y) * xKernel(1) + input(x+1, y) * xKernel(2)
    }
    val convolvey = func { (x: Rep[Int], y: Rep[Int]) =>
      convolvex(x, y-1) * yKernel(0) + convolvex(x, y)  * yKernel(1) + convolvex(x, y+1) * yKernel(2)
    }

    (convolvex, convolvey)
  }

  val sigma: Float = 1.5f

  def kernel(x: Int): Rep[Float] =
    (exp(-x * x / (2 * sigma * sigma)) / (sqrt(2*Pi) * sigma)).toFloat


  override def prog(in: Input, w: Rep[Int], h: Rep[Int]) = {
    val floatInput = func[Float] { (x: Rep[Int], y: Rep[Int]) =>
      val p = in(x, y).map(repCharToRepFloat(_))
      val avg = (p.red + p.green + p.blue) / 3
      RGBVal(avg, avg, avg)
    }

    val blur_y = func[Float] { (x: Rep[Int], y: Rep[Int]) =>
        (kernel(0) * floatInput(x, y) +
         kernel(1) * (floatInput(x, y-1) + floatInput(x, y+1)) +
         kernel(2) * (floatInput(x, y-2) + floatInput(x, y+2)) +
         kernel(3) * (floatInput(x, y-3) + floatInput(x, y+3)))
    }

    val blur_x = func[Float] { (x: Rep[Int], y: Rep[Int]) =>
       (kernel(0) * blur_y(x, y) +
        kernel(1) * (blur_y(x-1, y) + blur_y(x+1, y)) +
        kernel(2) * (blur_y(x-2, y) + blur_y(x+2, y)) +
        kernel(3) * (blur_y(x-3, y) + blur_y(x+3, y)))
    }

    val gxx = List(-1, 0, 1)
    val gxy = List(1, 2, 1)

    val gyx = List(1, 2, 1)
    val gyy = List(-1, 0, 1)

    val (fgxx, fgxy) = twoDConvolution(gxx, gxy, blur_x)
    val (fgyx, fgyy) = twoDConvolution(gyx, gyy, blur_x)


    val grad = func { (x: Rep[Int], y: Rep[Int]) =>
      val a: RGBVal[Float] = fgyy(x, y) * fgyy(x, y)
      val b: RGBVal[Float] = fgxy(x, y) * fgxy(x, y)
      val sobel = (a + b).map((x: Rep[Float]) => Math.sqrt(repFloatToRepDouble(x)))
      sobel.map((x: Rep[Double]) => tern[Short](x < 70.0, 0, 1))
      //sobel
    }

    val edge_thin_first = func {(x: Rep[Int], y: Rep[Int]) =>
      val p: List[Rep[Short]] = List(
      grad(x, y-1).red, grad(x+1, y-1).red,
      grad(x+1, y).red, grad(x+1, y+1).red, grad(x, y+1).red, grad(x-1, y+1).red,
      grad(x-1, y).red, grad(x-1, y-1).red)
      val n: Rep[Int] = repShortToRepInt(p(0) + p(1) + p(2) + p(3) + p(4) + p(5) + p(6) + p(7))
      val s_buffer = ListBuffer[Rep[Short]]()
      for (i <- 0 until 7: Range) s_buffer += tern[Short](p(i) != p(i+1), 1, 0)
      val s = s_buffer(0) + s_buffer(1) + s_buffer(2) + s_buffer(3) + s_buffer(4) +
        s_buffer(5) + s_buffer(6)
      val should_remove: Rep[Boolean] = (grad(x, y).red == unit(1)) && (2 <= n && n <= 6) &&
          s == 1 && (p(0) * p(2) * p(4) == 0) && (p(2) * p(4) * p(6) == 0) && p(5) != 0

      tern[Short](should_remove || grad(x, y).red == 0, 0, 1)
    }

    val edge_thin_stages = ListBuffer[Func[Short]]()
    edge_thin_stages += edge_thin_first
    val NTHINS = 100
    for (i <- 0 until NTHINS: Range) {
      val prev = edge_thin_stages(i)
      val f = func {(x: Rep[Int], y: Rep[Int]) =>
        val p: List[Rep[Short]] = List(
        prev(x, y-1).red, prev(x+1, y-1).red,
        prev(x+1, y).red, prev(x+1, y+1).red, prev(x, y+1).red, prev(x-1, y+1).red,
        prev(x-1, y).red, prev(x-1, y-1).red)
        val n: Rep[Int] = repShortToRepInt(p(0) + p(1) + p(2) + p(3) + p(4) + p(5) + p(6) + p(7))
        val s_buffer = ListBuffer[Rep[Short]]()
        for (i <- 0 until 7: Range) s_buffer += tern[Short](p(i) != p(i+1), 1, 0)
        val s = s_buffer(0) + s_buffer(1) + s_buffer(2) + s_buffer(3) + s_buffer(4) +
          s_buffer(5) + s_buffer(6)
        val should_remove: Rep[Boolean] = (prev(x, y).red == unit(1)) && (2 <= n && n <= 6) &&
            s == 1 && (p(0) * p(2) * p(6) == 0) && (p(0) * p(4) * p(6) == 0) && p(5) != 0

        tern[Short](should_remove || prev(x, y).red == 0, 0, 1)
      }
      edge_thin_stages += f
    }


    val back_to_rgb = final_func { (x: Rep[Int], y: Rep[Int]) =>
      edge_thin_stages(NTHINS)(x, y) * 255
    }
    for (i <- 0 until NTHINS: Range) edge_thin_stages(NTHINS-i).computeRoot()

    fgyy.computeRoot()
    fgyx.computeRoot()
    fgxy.computeRoot()
    fgxx.computeRoot()
    blur_x.computeRoot() // order matters... :(
    blur_y.computeRoot()
    floatInput.computeRoot()
    //edge_detection.computeRoot()
    blur_x.vectorize("x", 8)
    blur_y.vectorize("x", 8)
    fgyy.vectorize("x", 8)
    fgyx.vectorize("x", 8)
    fgxy.vectorize("x", 8)
    fgxx.vectorize("x", 8)
    //back_to_rgb.vectorize("x", 8)
    //edge_detection.vectorize("x", 8)
    //blur_x.split("x", "x_outer", "x_inner", 8)
  }
}

class EdgeFilterTest extends FlatSpec {
  "LowPassEdgeFilter" should "make a crude edge detection filter" in {
    println("edge detection")
    val blurProg =
      new EdgeFilter with CompilerInstance with TestAstOps
    val blurProgAnalysis = new EdgeFilter with TestPipelineAnalysis
    blurProg.compile(blurProgAnalysis.getBoundsGraph, "edge_filter")
  }
}
