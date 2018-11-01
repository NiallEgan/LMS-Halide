trait PipelineAnalysis extends FuncExp with DslExp
										with SymbolicArray2DOpsExp with Pipeline {
	// Before we pass the program to the staged interpreter, we must
  // first do some analysis on
	val funcs: ListBuffer[(Rep[Int], Rep[Int]) => Rep[Int]] = new ListBuffer()
	def toFunc(f: (Rep[Int], Rep[Int]) => Rep[Int], dom: (Int, Int)): Func = {
		funcs += f
		mkFunc(f, dom)
	}

  def getFunctionInputTransformations(e: Exp[_]): List[...] {
    e match {
      case Def(v) => match v {
        case FuncApplication(func, xExpr, yExpr) => analyseInputTransformations(func, xExpr, yExpr)
        case SomethingElse(... otherStuff) => otherStuff.flatMap(getFunctionInputTransformations)

      }
      case Const(_) => List()


    }

  }

	def analyse() = {
		val x = newSymbolicArray(0, 0) // TODO: We want this to be recognizable from other arrays
		prog(x)
		for (f <- funcs.toList) {
      println(f)
			println(f(0, 4)) // TODO: We want symbolic ints here.
			f(0, 4) match {
				case Def(v) => println(v)
        // TODO: Traverse the graph. How? What are we looking for?
        // We want to know about the operations performed on the inputs to the functions

				case Const(v) => println(v)
			}
		}
		println(globalDefsCache)
	}
}
