import sepia._
import scala.math._

trait EdgeFilter extends TestPipeline {
  def simpleConvolution(k: List[List[Rep[Int]]],
                        input: Func[Double]): Func[Short] = {
    final_func { (x: Rep[Int], y: Rep[Int]) => {
      k(0)(0) * input(x-1, y-1) + k(0)(1) * input(x, y-1) + k(0)(2) * input(x+1, y-1) +
      k(1)(0) * input(x-1, y)   + k(1)(1) * input(x, y)   + k(1)(2) * input(x+1, y) +
      k(2)(0) * input(x-1, y+1) + k(2)(1) * input(x, y+1) + k(2)(2) * input(x+1, y+1)
    }}
  }

  val sigma: Float = 1.5f

  def kernel(x: Int): Rep[Double] =
    exp(-x * x / (2 * sigma * sigma)) / (sqrt(2*Pi) * sigma)


  override def prog(in: Input, w: Rep[Int], h: Rep[Int]) = {
    val blur_y = func[Double] { (x: Rep[Int], y: Rep[Int]) =>
        (kernel(0) * in(x, y) +
         kernel(1) * (in(x, y-1) + in(x, y+1)) +
         kernel(2) * (in(x, y-2) + in(x, y+2)) +
         kernel(3) * (in(x, y-3) + in(x, y+3)))
    }

    val blur_x = func[Double] { (x: Rep[Int], y: Rep[Int]) =>
       (kernel(0) * blur_y(x, y) +
        kernel(1) * (blur_y(x-1, y) + blur_y(x+1, y)) +
        kernel(2) * (blur_y(x-2, y) + blur_y(x+2, y)) +
        kernel(3) * (blur_y(x-3, y) + blur_y(x+3, y)))
    }

    val edge_detection = simpleConvolution(
      List(List(0, 1, 0),
           List(1, -4, 1),
           List(0, 1, 0)),
      blur_x
    )
    blur_x.computeRoot()
  }
}
