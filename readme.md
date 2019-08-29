# LMS Halide

This repository contains the source code for a replica of [Halide](https://halide-lang.org/), a DSL for image processing, using the [*lightweight modular staging library*](https://scala-lms.github.io/).

The DSL uses multi-stage programming to generate efficent, low-level C code from high-level Scala.
Example DSL programs can be found in `/src/test/scala/TestPrograms.scala`, and the corresponding C code in  `/testOutput`.
In order to actually manipulate images, the output C code should be compiled with `/builder.c` - this requires that the `libpng` library is installed for PNG encoding / decoding.

# Example
The following is a DSL implementation of a simple box-blur:

```
trait TwoStageBoxBlurFast extends TestPipeline {
	override def prog(in: Input, w: Rep[Int], h: Rep[Int]): Rep[Unit] = {
		val f = func[Short] {
			(x: Rep[Int], y: Rep[Int]) => (in(x, y) + in(x+1, y) + in(x-1, y)) / 3.toShort
		}
		val g = final_func[Short] {
			(x: Rep[Int], y: Rep[Int]) => (f(x, y) + f(x, y+1) + f(x, y-1)) / 3.toShort
		}

    f.storeRoot()
    f.computeAt(g, "y")
    f.vectorize("x", 16)
    g.vectorize("x", 16)
  }
}
```
This example produces a 'schedule' where the `f` and `g` x-loops are vectorized, and `f` is stored globally but computed per y-coordinate of `g`.
