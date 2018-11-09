import sepia._


trait GradProg extends TestPipeline {
	override def prog(in: Rep[Array[Array[Int]]]): Rep[Unit] = {
		val f: Func =
			((x: Rep[Int], y: Rep[Int]) => x + y) withDomain (5, 3)

		// This is for testing purposes only
		registerFunction("f", f)
	}
}

trait BlurredGradProg extends TestPipeline {
	override def prog(in: Rep[Array[Array[Int]]]): Rep[Unit] = {
		val f: Func =
			((x: Rep[Int], y: Rep[Int]) => x + y) withDomain (5, 3)

		val g: Func =
				((x: Rep[Int], y: Rep[Int]) =>
					(f(x-1, y) + f(x, y-1) + f(x-1, y-1) + f(x, y)) / 4) withDomain (5, 3)

		// This is for testing purposes only
		registerFunction("f", f)
		registerFunction("g", g)
	}
}

trait BlurredGradProgComputeAt extends TestPipeline {
	override def prog(in: Rep[Array[Array[Int]]]): Rep[Unit] = {
		val f: Func =
			((x: Rep[Int], y: Rep[Int]) => x + y) withDomain (5, 5)

		val g: Func =
				((x: Rep[Int], y: Rep[Int]) =>
					(f(x+1, y) + f(x, y+1) + f(x+1, y+1) + f(x, y)) / 4) withDomain (4, 4)

		f.computeAt(g, "y")
		// This is for testing purposes only
		registerFunction("f", f)
		registerFunction("g", g)
	}
}

trait FunkyBoundsProg extends TestPipeline {
	override def prog(in: Rep[Array[Array[Int]]]): Rep[Unit] = {
		val f: Func =
			((x: Rep[Int], y: Rep[Int]) => 30 + x) withDomain (5, 5)

		val g: Func =
			((x: Rep[Int], y: Rep[Int]) =>
			f(x-4, y+3) + f(x+10, y) + f(x-11, y+12) + f(x, y-40*2)) withDomain (5, 5) // N.B. These are not valid domains

		registerFunction("f", f)
		registerFunction("g", g)
	}
}

trait ThreeStageBoxBlur extends TestPipeline {
	override def prog(in: Rep[Array[Array[Int]]]): Rep[Unit] = {
		val f: Func =
			((x: Rep[Int], y: Rep[Int]) => in(x, y)) withDomain(5, 5)
		val g: Func =
			((x: Rep[Int], y: Rep[Int]) => (f(x, y) + f(x+1, y) + f(x-1, y)) / 3) withNZDomain((1, 4), (0, 5))
		val h: Func =
			((x: Rep[Int], y: Rep[Int]) => (g(x, y) + g(x, y+1) + g(x, y-1)) / 3) withNZDomain((1, 4), (1, 4))

		registerFunction("f", f)
		registerFunction("g", g)
		registerFunction("h", h)
	}
}
