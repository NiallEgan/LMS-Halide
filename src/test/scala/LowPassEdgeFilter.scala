import sepia._
import scala.math._

trait EdgeFilter extends TestPipeline {
  val sigma: Float = 1.5f

  /*def kernel(x: Int): Double =
    exp(-x * x / (2 * sigma * sigma)) / (sqrt(2*Pi) * sigma)


  override def prog(in: Buffer, w: Rep[Int], h: Rep[Int]) = {
    val blur_y: Func = (x: Rep[Int], y: Rep[Int]) =>
        (kernel(0) * in(x, y) +
         kernel(1) * (in(x, y-1) + in(x, y+1)) +
         kernel(2) * (in(x, y-2) + in(x, y+2)) +
         kernel(3) * (in(x, y-3) + in(x, y+3)))

    val blur_x: Func = (x: Rep[Int], y: Rep[Int]) =>
       (kernel(0) * in(x, y) +
        kernel(1) * (in(x-1, y) + in(x+1, y)) +
        kernel(2) * (in(x-2, y) + in(x+2, y)) +
        kernel(3) * (in(x-3, y) + in(x+3, y)))
  }*/
}
