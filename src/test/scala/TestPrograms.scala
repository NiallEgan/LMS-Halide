import sepia._


trait GradProg extends TestPipeline {
	override val width: Rep[Int] = 5
	override val height: Rep[Int] = 5

	override def prog(in: Buffer): Rep[Unit] = {
		val f: Func =
			((x: Rep[Int], y: Rep[Int]) => x + y) withDomain (5, 3)

		f.realize()
		// This is for testing purposes only
		registerFunction("f", f)
	}
}

trait BlurredGradProg extends TestPipeline {
	override val width: Rep[Int] = 5
	override val height: Rep[Int] = 5

	override def prog(in: Buffer): Rep[Unit] = {
		val f: Func =
			((x: Rep[Int], y: Rep[Int]) => x + y) withDomain (5, 3)

		val g: Func =
				((x: Rep[Int], y: Rep[Int]) =>
					(f(x-1, y) + f(x, y-1) + f(x-1, y-1) + f(x, y)) / 4) withDomain (5, 3)

		g.realize()

		// This is for testing purposes only
		registerFunction("f", f)
		registerFunction("g", g)
	}
}

trait BlurredGradProgComputeAt extends TestPipeline {
	override val width: Rep[Int] = 5
	override val height: Rep[Int] = 5

	override def prog(in: Buffer): Rep[Unit] = {

		val f: Func =
			((x: Rep[Int], y: Rep[Int]) => x + y) withDomain (5, 5)

		val g: Func =
				((x: Rep[Int], y: Rep[Int]) =>
					(f(x+1, y) + f(x, y+1) + f(x+1, y+1) + f(x, y)) / 4) withDomain (4, 4)

		f.computeAt(g, "y")

		g.realize()

		// This is for testing purposes only
		registerFunction("f", f)
		registerFunction("g", g)
	}
}

trait FunkyBoundsProg extends TestPipeline {
	override val width: Rep[Int] = 5
	override val height: Rep[Int] = 5

	override def prog(in: Buffer): Rep[Unit] = {
		val f: Func =
			((x: Rep[Int], y: Rep[Int]) => 30 + x) withDomain (5, 5)

		val g: Func =
			((x: Rep[Int], y: Rep[Int]) =>
			f(x-4, y+3) + f(x+10, y) + f(x-11, y+12) + f(x, y-40*2)) withDomain (5, 5) // N.B. These are not valid domains

		g.realize()
		registerFunction("f", f)
		registerFunction("g", g)
	}
}

trait ThreeStageBoxBlur extends TestPipeline {
	override val width: Rep[Int] = 5
	override val height: Rep[Int] = 5

	override def prog(in: Buffer): Rep[Unit] = {
		val f: Func =
			((x: Rep[Int], y: Rep[Int]) => in(x, y)) withDomain(5, 5)
		val g: Func =
			((x: Rep[Int], y: Rep[Int]) => (f(x, y) + f(x+1, y) + f(x-1, y)) / 3) withNZDomain((1, 4), (0, 5))
		val h: Func =
			((x: Rep[Int], y: Rep[Int]) => (g(x, y) + g(x, y+1) + g(x, y-1)) / 3) withNZDomain((1, 4), (1, 4))


		h.realize()
		registerFunction("f", f)
		registerFunction("g", g)
		registerFunction("h", h)
	}
}

trait ThreeStageBoxBlurWithComputeAt extends TestPipeline {
	override val width: Rep[Int] = 5
	override val height: Rep[Int] = 5

	override def prog(in: Buffer): Rep[Unit] = {
		val f: Func =
			((x: Rep[Int], y: Rep[Int]) => in(x, y) / 2) withDomain(5, 5)
		val g: Func =
			((x: Rep[Int], y: Rep[Int]) => (f(x, y) + f(x+1, y) + f(x-1, y)) / 3) withNZDomain((1, 4), (0, 5))
		val h: Func =
			((x: Rep[Int], y: Rep[Int]) => (g(x, y) + g(x, y+1) + g(x, y-1)) / 3) withNZDomain((1, 4), (1, 4))

		// TODO: What about 'middle functions'?
		f.computeAt(h, "y")

		h.realize()

		registerFunction("f", f)
		registerFunction("g", g)
		registerFunction("h", h)
	}
}

trait ThreeStageBoundsAnalysisExample extends TestPipeline {
	override val width: Rep[Int] = 6
	override val height: Rep[Int] = 6

	override def prog(in: Buffer): Rep[Unit] = {
		val f: Func =
			((x: Rep[Int], y: Rep[Int]) => x + y) withDomain(6, 6)
		val g: Func =
			((x: Rep[Int], y: Rep[Int]) => f(x-1, y+1) + f(x+1, y-1)) withNZDomain ((1, 5), (1, 5))
		val h: Func =
			((x: Rep[Int], y: Rep[Int]) => g(x-1, y+1) + g(x+1, y-1)) withNZDomain ((2, 4), (2, 4))

		h.realize()

		registerFunction("f", f)
		registerFunction("g", g)
		registerFunction("h", h)
	}
}

trait IDProg extends TestPipeline {
	override val width: Rep[Int] = 5
	override val height: Rep[Int] = 5

	override def prog(in: Buffer): Rep[Unit] = {
		val f: Func =
			((x: Rep[Int], y: Rep[Int]) => x + y) withDomain(5, 6)

		f.realize()
		registerFunction("f", f)
	}
}
