import sepia._


trait GradProg extends TestPipeline {
	override def prog(in: Buffer, w: Rep[Int], h: Rep[Int]): Rep[Unit] = {
		val f: Func[Int] = (x: Rep[Int], y: Rep[Int]) => x + y

		f.realize()
		// This is for testing purposes only
		registerFunction("f", f)
	}
}

trait BlurredGradProg extends TestPipeline {
	override def prog(in: Buffer, w: Rep[Int], h: Rep[Int]): Rep[Unit] = {
		val f: Func[Int] =
			(x: Rep[Int], y: Rep[Int]) => x + y

		val g: Func[Int] =
			(x: Rep[Int], y: Rep[Int]) =>
				(f(x+1, y-1) + f(x+1, y) + f(x+1, y+1) +
				 f(x,   y-1) + f(x,   y) + f(x,   y+1) +
				 f(x-1, y-1) + f(x-1, y) + f(x-1, y+1)) / 9
		g.realize()

		// This is for testing purposes only
		registerFunction("f", f)
		registerFunction("g", g)
	}
}

trait BlurredGradProgComputeAt extends TestPipeline {
	override def prog(in: Buffer, w: Rep[Int], h: Rep[Int]): Rep[Unit] = {

		val f: Func[Int] =
			(x: Rep[Int], y: Rep[Int]) => x + y

		val g: Func[Int] =
				(x: Rep[Int], y: Rep[Int]) =>
					(f(x+1, y-1) + f(x+1, y) + f(x+1, y+1) +
					 f(x,   y-1) + f(x,   y) + f(x,   y+1) +
				   f(x-1, y-1) + f(x-1, y) + f(x-1, y+1)) / 9

		f.computeAt(g, "y")

		g.realize()

		// This is for testing purposes only
		registerFunction("f", f)
		registerFunction("g", g)
	}
}

trait FunkyBoundsProg extends TestPipeline {
	override def prog(in: Buffer, w: Rep[Int], h: Rep[Int]): Rep[Unit] = {
		val f: Func[Int] = (x: Rep[Int], y: Rep[Int]) => 30 + x

		val g: Func[Int] = (x: Rep[Int], y: Rep[Int]) =>
			f(x-4, y+3) + f(x+10, y) + f(x-11, y+12) + f(x, y-40*2)

		g.realize()
		registerFunction("f", f)
		registerFunction("g", g)
	}
}

trait ThreeStageBoxBlur extends TestPipeline {
	override def prog(in: Buffer, w: Rep[Int], h: Rep[Int]): Rep[Unit] = {
		val f: Func[Int] = (x: Rep[Int], y: Rep[Int]) => in(x, y)
		val g: Func[Int] =
			(x: Rep[Int], y: Rep[Int]) => (f(x, y) + f(x+1, y) + f(x-1, y)) / 3
		val i: Func[Int] =
			(x: Rep[Int], y: Rep[Int]) => (g(x, y) + g(x, y+1) + g(x, y-1)) / 3


		i.realize()
		registerFunction("f", f)
		registerFunction("g", g)
		registerFunction("i", i)
	}
}

trait ThreeStageBoxBlurWithComputeAt extends TestPipeline {
	override def prog(in: Buffer, w: Rep[Int], h: Rep[Int]): Rep[Unit] = {
		val f: Func[Int] = (x: Rep[Int], y: Rep[Int]) => in(x, y)
		val g: Func[Int] =
			(x: Rep[Int], y: Rep[Int]) => (f(x, y) + f(x+1, y) + f(x-1, y)) / 3
		val i: Func[Int] =
			(x: Rep[Int], y: Rep[Int]) => (g(x, y) + g(x, y+1) + g(x, y-1)) / 3

		// TODO: What about 'middle Func[Int]tions'?
		f.computeAt(i, "y")

		i.realize()

		registerFunction("f", f)
		registerFunction("g", g)
		registerFunction("i", i)
	}
}

trait ThreeStageBoundsAnalysisExample extends TestPipeline {
	override def prog(in: Buffer, w: Rep[Int], h: Rep[Int]): Rep[Unit] = {
		val f: Func[Int] = (x: Rep[Int], y: Rep[Int]) => x + y
		val g: Func[Int] = (x: Rep[Int], y: Rep[Int]) => f(x-1, y+1) + f(x+1, y-1)
		val i: Func[Int] = (x: Rep[Int], y: Rep[Int]) => g(x-1, y+1) + g(x+1, y-1)

		i.realize()

		registerFunction("f", f)
		registerFunction("g", g)
		registerFunction("i", i)
	}
}

trait IDProg extends TestPipeline {
	override def prog(in: Buffer, w: Rep[Int], h: Rep[Int]): Rep[Unit] = {
		val f: Func[Int] = (x: Rep[Int], y: Rep[Int]) => in(x, y)

		f.realize()
		registerFunction("f", f)
	}
}

trait TwoStageBoxBlur extends TestPipeline {
	override def prog(in: Buffer, w: Rep[Int], h: Rep[Int]): Rep[Unit] = {
		val g: Func[Int] =
			(x: Rep[Int], y: Rep[Int]) => (in(x, y) + in(x+1, y) + in(x-1, y)) / 3
		val i: Func[Int] =
			(x: Rep[Int], y: Rep[Int]) => (g(x, y) + g(x, y+1) + g(x, y-1)) / 3


		i.realize()
		registerFunction("g", g)
		registerFunction("i", i)
	}
}

trait OneStageBoxBlur extends TestPipeline {
	override def prog(in: Buffer, w: Rep[Int], h: Rep[Int]): Rep[Unit] = {
		val i: Func[Int] =
			(x: Rep[Int], y: Rep[Int]) => (in(x, y) + in(x, y+1) + in(x, y-1) +
																			in(x-1, y-1) + in(x-1, y) + in(x-1, y+1) +
																			in(x+1, y-1) + in(x+1, y) + in(x+1, y+1)) / 9
		i.realize()
		registerFunction("i", i)
	}
}

trait OneStageBoxBlurVectorized extends TestPipeline {
	override def prog(in: Buffer, w: Rep[Int], h: Rep[Int]): Rep[Unit] = {
		val i: Func[Double] =
			(x: Rep[Int], y: Rep[Int]) => (in(x, y) + in(x, y+1) + in(x, y-1) +
																			in(x-1, y-1) + in(x-1, y) + in(x-1, y+1) +
																			in(x+1, y-1) + in(x+1, y) + in(x+1, y+1)) / 9.0

		i.vectorize("x", 4)
		i.realize()

		registerFunction("i", i)
	}
}

trait Cropper extends TestPipeline {
	override def prog(in: Buffer, w: Rep[Int], h: Rep[Int]): Rep[Unit] = {
		val f: Func[Int] =
			((x: Rep[Int], y: Rep[Int]) => in(x, y)) withNZDomain((1, w-1), (1, h-1))

		f.realize()
	}
}

trait TwoStageBoxBlurStoreAt extends TestPipeline {
	override def prog(in: Buffer, w: Rep[Int], h: Rep[Int]): Rep[Unit] = {
		val g: Func[Int] =
			(x: Rep[Int], y: Rep[Int]) => (in(x, y) + in(x+1, y) + in(x-1, y)) / 3
		val i: Func[Int] =
			(x: Rep[Int], y: Rep[Int]) => (g(x, y) + g(x, y+1) + g(x, y-1)) / 3

		g.computeAt(i, "x")
		g.storeAt(i, "y")
		i.realize()

		registerFunction("i", i)
		registerFunction("g", g)

	}
}

trait TwoStageBoxBlurStoreAtReflected extends TestPipeline {
	override def prog(in: Buffer, w: Rep[Int], h: Rep[Int]): Rep[Unit] = {
		val g: Func[Int] =
			(x: Rep[Int], y: Rep[Int]) => (in(x, y-1) + in(x, y) + in(x, y+1)) / 3
		val i: Func[Int] =
			(x: Rep[Int], y: Rep[Int]) => (g(x-1, y) + g(x, y) + g(x+1, y)) / 3

		g.computeAt(i, "x")
		g.storeAt(i, "y")
		i.realize()

		registerFunction("i", i)
		registerFunction("g", g)

	}
}

trait TwoStageBoxBlurComputeAtX extends TestPipeline {
	override def prog(in: Buffer, w: Rep[Int], h: Rep[Int]): Rep[Unit] = {
		val g: Func[Int] =
			(x: Rep[Int], y: Rep[Int]) => (in(x, y) + in(x+1, y) + in(x-1, y)) / 3
		val i: Func[Int] =
			(x: Rep[Int], y: Rep[Int]) => (g(x, y) + g(x, y+1) + g(x, y-1)) / 3

		g.computeAt(i, "x")
		i.realize()

		registerFunction("i", i)
		registerFunction("g", g)

	}
}

trait BlurredGradStoreRoot extends TestPipeline {
	override def prog(in: Buffer, w: Rep[Int], h: Rep[Int]): Rep[Unit] = {
		val f: Func[Int] = (x: Rep[Int], y: Rep[Int]) => x + y

		val g: Func[Int] =
			(x: Rep[Int], y: Rep[Int]) => (f(x, y) + f(x, y+1) + f(x+1, y) + f(x+1, y+1)) / 4

		f.computeAt(g, "y")
		f.storeRoot()

		g.realize()
		registerFunction("f", f)
		registerFunction("g", g)

	}
}

trait TwoStageBoxBlurComputeRoot extends TestPipeline {
	override def prog(in: Buffer, w: Rep[Int], h: Rep[Int]): Rep[Unit] = {
		val g: Func[Int] =
			(x: Rep[Int], y: Rep[Int]) => (in(x, y) + in(x+1, y) + in(x-1, y)) / 3
		val i: Func[Int] =
			(x: Rep[Int], y: Rep[Int]) => (g(x, y) + g(x, y+1) + g(x, y-1)) / 3

		g.computeRoot()
		i.realize()

		registerFunction("i", i)
		registerFunction("g", g)
	}
}

trait GradSplit extends TestPipeline {
	override def prog(in: Buffer, w: Rep[Int], h: Rep[Int]): Rep[Unit] = {
		val f: Func[Int] = (x: Rep[Int], y: Rep[Int]) => x + y

		f.split("x", "x_outer", "x_inner", 2)
		f.realize()

		registerFunction("f", f)
	}
}

trait DoubleGradSplit extends TestPipeline {
	override def prog(in: Buffer, w: Rep[Int], h: Rep[Int]): Rep[Unit] = {
		val f: Func[Int] = (x: Rep[Int], y: Rep[Int]) => x + y

		f.split("x", "x_outer", "x_inner", 2)
		f.split("y", "y_outer", "y_inner", 2)
		f.realize()

		registerFunction("f", f)
	}
}

trait TwoStageBoxBlurWithSplitVar extends TestPipeline {
	override def prog(in: Buffer, w: Rep[Int], h: Rep[Int]): Rep[Unit] = {
		val g: Func[Int] =
			(x: Rep[Int], y: Rep[Int]) => (in(x, y) + in(x+1, y) + in(x-1, y)) / 3
		val i: Func[Int] =
			(x: Rep[Int], y: Rep[Int]) => (g(x, y) + g(x, y+1) + g(x, y-1)) / 3

		g.computeAt(i, "y")
		g.split("y", "y_outer", "y_inner", 2)

		i.realize()
		registerFunction("g", g)
		registerFunction("i", i)
	}
}

trait TwoStageBoxBlurStoreRootSplit extends TestPipeline {
	override def prog(in: Buffer, w: Rep[Int], h: Rep[Int]): Rep[Unit] = {
		val f: Func[Int] =
			(x: Rep[Int], y: Rep[Int]) => (in(x, y) + in(x+1, y) + in(x-1, y)) / 3
		val g: Func[Int] =
			(x: Rep[Int], y: Rep[Int]) => (f(x, y) + f(x, y+1) + f(x, y-1)) / 3

		f.computeAt(g, "y")
		f.storeRoot()
		f.split("y", "y_outer", "y_inner", 2)

		g.realize()
		registerFunction("f", f)
		registerFunction("g", g)

	}
}

trait OneStageBoxBlurSplitLoopsReordered extends TestPipeline {
	override def prog(in: Buffer, w: Rep[Int], h: Rep[Int]): Rep[Unit] = {
		val i: Func[Int] =
			(x: Rep[Int], y: Rep[Int]) => (in(x, y) + in(x, y+1) + in(x, y-1) +
																			in(x-1, y-1) + in(x-1, y) + in(x-1, y+1) +
																			in(x+1, y-1) + in(x+1, y) + in(x+1, y+1)) / 9
		i.reorder("x", "y")

		i.realize()
		registerFunction("i", i)
	}
}

trait SimpleGradTiled extends TestPipeline {
	override def prog(in: Buffer, w: Rep[Int], h: Rep[Int]): Rep[Unit] = {
		val f: Func[Int] = (x: Rep[Int], y: Rep[Int]) => x + y

		/*f.split("y", "y_outer", "y_inner", 2)
		f.split("x", "x_outer", "x_inner", 2)
		f.reorder("y_inner", "x_outer")*/
		f.tile("x", "y", "x_outer", "y_outer", "x_inner", "y_inner", 2, 2)

		f.realize()

		registerFunction("f", f)
	}
}

trait FusedBlur extends TestPipeline {
	override def prog(in: Buffer, w: Rep[Int], h: Rep[Int]): Rep[Unit] = {
		val i: Func[Int] =
			(x: Rep[Int], y: Rep[Int]) => (in(x, y) + in(x, y+1) + in(x, y-1) +
																			in(x-1, y-1) + in(x-1, y) + in(x-1, y+1) +
																			in(x+1, y-1) + in(x+1, y) + in(x+1, y+1)) / 9
		i.fuse("xy", "y", "x")
		i.realize()
		registerFunction("i", i)
	}
}

trait TwoStageBlurInnerFused extends TestPipeline {
	override def prog(in: Buffer, w: Rep[Int], h: Rep[Int]): Rep[Unit] = {
		val f: Func[Int] =
			(x: Rep[Int], y: Rep[Int]) => (in(x, y) + in(x+1, y) + in(x-1, y)) / 3
		val g: Func[Int] =
			(x: Rep[Int], y: Rep[Int]) => (f(x, y) + f(x, y+1) + f(x, y-1)) / 3

		f.computeAt(g, "y")
		f.fuse("xy", "y", "x")
		g.realize()

		registerFunction("f", f)
		registerFunction("g", g)
	}
}
