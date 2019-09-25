import org.scalatest.FlatSpec
import scala.collection.mutable.ListBuffer
import java.io.File

import sepia._

trait SplitComputeAtInner extends TestPipeline {
  override def prog(in: Input, w: Rep[Int], h: Rep[Int]): Rep[Unit] = {
    val g = func[Short] {
      (x: Rep[Int], y: Rep[Int]) => in(x, y) / 1.toShort
    }
    val i = final_func[Short] {
      (x: Rep[Int], y: Rep[Int]) => g(x, y)
    }

    i.split("y", "y_outer", "y_inner", 4)
    g.computeAt(i, "y_inner")


    registerFunction("g", g)
    registerFunction("i", i)
  }
}

trait SplitComputeAtOuter extends TestPipeline {
  override def prog(in: Input, w: Rep[Int], h: Rep[Int]): Rep[Unit] = {
    val g = func[Short] {
      (x: Rep[Int], y: Rep[Int]) => in(x, y) / 1.toShort
    }
    val i = final_func[Short] {
      (x: Rep[Int], y: Rep[Int]) => g(x, y)
    }

    i.split("x", "x_outer", "x_inner", 4)
    g.computeAt(i, "x_outer")


    registerFunction("g", g)
    registerFunction("i", i)
  }
}

trait SplitComputeAtInnerStoreAtOuter extends TestPipeline {
  override def prog(in: Input, w: Rep[Int], h: Rep[Int]): Rep[Unit] = {
    val g = func[Short] {
      (x: Rep[Int], y: Rep[Int]) => in(x, y) / 1.toShort
    }
    val i = final_func[Short] {
      (x: Rep[Int], y: Rep[Int]) => g(x, y)
    }

    i.split("y", "y_outer", "y_inner", 4)
    g.computeAt(i, "y_inner")
    g.storeAt(i, "y_outer")

    registerFunction("g", g)
    registerFunction("i", i)
  }
}

trait SplitStoreAtOuterComputeAtInner extends TestPipeline {
  override def prog(in: Input, w: Rep[Int], h: Rep[Int]): Rep[Unit] = {
    val g = func[Short] {
      (x: Rep[Int], y: Rep[Int]) => in(x, y) / 1.toShort
    }
    val i = final_func[Short] {
      (x: Rep[Int], y: Rep[Int]) => g(x, y)
    }

    i.split("y", "y_outer", "y_inner", 4)
    g.storeAt(i, "y_outer")
    g.computeAt(i, "y_inner")

    registerFunction("g", g)
    registerFunction("i", i)
  }
}


trait TileComputeAtOuter extends TestPipeline {
  override def prog(in: Input, w: Rep[Int], h: Rep[Int]): Rep[Unit] = {
    val g = func[Short] {
      (x: Rep[Int], y: Rep[Int]) => in(x, y) / 1.toShort
    }
    val i = final_func[Short] {
      (x: Rep[Int], y: Rep[Int]) => g(x, y)
    }

    i.tile("x", "y", "x_outer", "y_outer", "x_inner", "y_inner", 4, 4)
    g.computeAt(i, "y_outer")

    registerFunction("g", g)
    registerFunction("i", i)
  }
}

trait TileComputeAtYInnerStoreAtXOuter extends TestPipeline {
  override def prog(in: Input, w: Rep[Int], h: Rep[Int]): Rep[Unit] = {
    val g = func[Short] {
      (x: Rep[Int], y: Rep[Int]) => in(x, y) / 1.toShort
    }
    val i = final_func[Short] {
      (x: Rep[Int], y: Rep[Int]) => g(x, y)
    }

    i.tile("x", "y", "x_outer", "y_outer", "x_inner", "y_inner", 4, 4)
    g.computeAt(i, "y_inner")
    g.storeAt(i, "x_outer")

    registerFunction("g", g)
    registerFunction("i", i)
  }
}

trait TiletoreAtYOuterComputeAtXInnerS extends TestPipeline {
  override def prog(in: Input, w: Rep[Int], h: Rep[Int]): Rep[Unit] = {
    val g = func[Short] {
      (x: Rep[Int], y: Rep[Int]) => in(x, y) / 1.toShort
    }
    val i = final_func[Short] {
      (x: Rep[Int], y: Rep[Int]) => g(x, y)
    }

    i.tile("x", "y", "x_outer", "y_outer", "x_inner", "y_inner", 4, 4)
    g.storeAt(i, "y_outer")
    g.computeAt(i, "x_inner")

    registerFunction("g", g)
    registerFunction("i", i)
  }
}

trait TileStoreAtComputeAtSame extends TestPipeline {
  override def prog(in: Input, w: Rep[Int], h: Rep[Int]): Rep[Unit] = {
    val g = func[Short] {
      (x: Rep[Int], y: Rep[Int]) => in(x, y) / 1.toShort
    }
    val i = final_func[Short] {
      (x: Rep[Int], y: Rep[Int]) => g(x, y)
    }

    i.tile("x", "y", "x_outer", "y_outer", "x_inner", "y_inner", 4, 4)
    g.storeAt(i, "y_inner")
    g.computeAt(i, "y_inner")

    registerFunction("g", g)
    registerFunction("i", i)
  }
}


trait TileStoreAtXOuterComputeAtYInnerComputeAtY extends TestPipeline {
  override def prog(in: Input, w: Rep[Int], h: Rep[Int]): Rep[Unit] = {

    val g = func[Int] {
      (x: Rep[Int], y: Rep[Int]) => x + y
    }
    val h = func[Int] {
      (x: Rep[Int], y: Rep[Int]) => (g(x, y) + g(x+1, y) + g(x-1, y)) / 3.toInt
    }
    val i = final_func[Int] {
      (x: Rep[Int], y: Rep[Int]) => (h(x, y) + h(x, y+1) + h(x, y-1)) / 3.toInt
    }

    i.tile("x", "y", "x_outer", "y_outer", "x_inner", "y_inner", 16, 16)
    h.storeAt(i, "x_outer")
    h.computeAt(i, "y_inner")
    g.computeAt(h, "y")


    registerFunction("g", g)
    registerFunction("h", h)
    registerFunction("i", i)
  }
}

trait TileComputeAtOuterStoreAtInnerSplitStoreAtOuterComputeAtInner extends TestPipeline {
  override def prog(in: Input, w: Rep[Int], h: Rep[Int]): Rep[Unit] = {

    val g = func[Int] {
      (x: Rep[Int], y: Rep[Int]) => x + y
    }
    val h = func[Int] {
      (x: Rep[Int], y: Rep[Int]) => (g(x, y) + g(x+1, y) + g(x-1, y)) / 3.toInt
    }
    val i = final_func[Int] {
      (x: Rep[Int], y: Rep[Int]) => (h(x, y) + h(x, y+1) + h(x, y-1)) / 3.toInt
    }

    i.tile("x", "y", "x_outer", "y_outer", "x_inner", "y_inner", 16, 16)
    h.computeAt(i, "x_outer")
    h.storeAt(i, "y_outer")
    h.split("y", "y_outer", "y_inner", 8)
    g.storeAt(h, "y_outer")
    g.computeAt(h, "y_inner")

    registerFunction("g", g)
    registerFunction("h", h)
    registerFunction("i", i)
  }
}


//+++++*****+++++*****+++++*****+++++*****+++++*****+++++*****+++++*****+++++*****+++++*****+++++
//*****+++++*****+++++*****+++++*****+++++*****+++++*****+++++*****+++++*****+++++*****+++++*****



class TestScheduling extends FlatSpec {
  "The split and compute program" should "split i across y and compute g at y_inner" in {
    println("SplitComputeAtInner prog")

    val first = new SplitComputeAtInner with CompilerInstance with TestAstOps
    val firstA = new SplitComputeAtInner with TestPipelineAnalysis

    first.compile(firstA.getBoundsGraph, "splitCompute_simple")
    val correctAst: TNode =
      new TRootNode(List(
        new TStorageNode("i", List(
          new TLoopNode("y_outer", "i", Sequential(), List(
            new TLoopNode("y", "i", Sequential(), List(
              new TStorageNode("g", List(
                new TLoopNode("y", "g", Sequential(), List(
                  new TLoopNode("x", "g", Sequential(), List(
                    new TComputeNode("g", List())
                  ))
                )),
                new TLoopNode("x", "i", Sequential(), List(
                  new TComputeNode("i", List())
                ))
              ))
            ))
          ))
        ))
      ))
    assertResult(correctAst)(first.scheduleRep)
  }


  "The split and compute program" should "split i across x and compute g at x_outer" in {
    println("SplitComputeAtOuter prog")

    val second = new SplitComputeAtOuter with CompilerInstance with TestAstOps
    val secondA = new SplitComputeAtOuter with TestPipelineAnalysis

    second.compile(secondA.getBoundsGraph, "splitCompute_simple2")
    val correctAst: TNode =
      new TRootNode(List(
        new TStorageNode("i", List(
          new TLoopNode("y", "i", Sequential(), List(
            new TLoopNode("x_outer", "i", Sequential(), List(
              new TStorageNode("g", List(
                new TLoopNode("y", "g", Sequential(), List(
                  new TLoopNode("x", "g", Sequential(), List(
                    new TComputeNode("g", List())
                  ))
                )),
                new TLoopNode("x", "i", Sequential(), List(
                  new TComputeNode("i", List())
                ))
              ))
            ))
          ))
        ))
      ))
    assertResult(correctAst)(second.scheduleRep)
  }


  "The split compute and store program" should "split i across y, compute g at y_inner and store it at y_outer" in {
    println("SplitComputeAtInnerStoreAtOuter prog")

    val third = new SplitComputeAtInnerStoreAtOuter with CompilerInstance with TestAstOps
    val thirdA = new SplitComputeAtInnerStoreAtOuter with TestPipelineAnalysis

    third.compile(thirdA.getBoundsGraph, "splitComputeStore")
    val correctAst: TNode =
      new TRootNode(List(
        new TStorageNode("i", List(
          new TLoopNode("y_outer", "i", Sequential(), List(
            new TStorageNode("g", List(
              new TLoopNode("y", "i", Sequential(), List(
                new TLoopNode("y", "g", Sequential(), List(
                  new TLoopNode("x", "g", Sequential(), List(
                    new TComputeNode("g", List())
                  ))
                )),
                new TLoopNode("x", "i", Sequential(), List(
                  new TComputeNode("i", List())
                ))
              ))
            ))
          ))
        ))
      ))
    assertResult(correctAst)(third.scheduleRep)
  }

  "The split store and compute program" should "split i across y, store it at y_outer and compute g at y_inner and" in {
    println("SplitStoreAtOuterComputeAtInner prog")

    val fourth = new SplitStoreAtOuterComputeAtInner with CompilerInstance with TestAstOps
    val fourthA = new SplitStoreAtOuterComputeAtInner with TestPipelineAnalysis

    fourth.compile(fourthA.getBoundsGraph, "splitComputeStore2")
    val correctAst: TNode =
      new TRootNode(List(
        new TStorageNode("i", List(
          new TLoopNode("y_outer", "i", Sequential(), List(
            new TStorageNode("g", List(
              new TLoopNode("y", "i", Sequential(), List(
                new TLoopNode("y", "g", Sequential(), List(
                  new TLoopNode("x", "g", Sequential(), List(
                    new TComputeNode("g", List())
                  ))
                )),
                new TLoopNode("x", "i", Sequential(), List(
                  new TComputeNode("i", List())
                ))
              ))
            ))
          ))
        ))
      ))
    assertResult(correctAst)(fourth.scheduleRep)
  }

  "The tile compute program" should "tiles i, and computes g at y_outer" in {
    println("TileComputeAtOuter prog")

    val fifth = new TileComputeAtOuter with CompilerInstance with TestAstOps
    val fifthA = new TileComputeAtOuter with TestPipelineAnalysis

    fifth.compile(fifthA.getBoundsGraph, "tileCompute_simple")
    val correctAst: TNode =
      new TRootNode(List(
        new TStorageNode("i", List(
          new TLoopNode("y_outer", "i", Sequential(), List(
            new TStorageNode("g", List(
              new TLoopNode("y", "g", Sequential(), List(
                new TLoopNode("x", "g", Sequential(), List(
                  new TComputeNode("g", List())
                ))
              )),
              new TLoopNode("x_outer", "i", Sequential(), List(
                new TLoopNode("y", "i", Sequential(), List(
                  new TLoopNode("x", "i", Sequential(), List(
                    new TComputeNode("i", List())
                  ))
                ))
              ))
            ))
          ))
        ))
      ))
    assertResult(correctAst)(fifth.scheduleRep)
  }


  "The tile compute and store program" should "tiles i, computes g at y_inner and stores at y_outer" in {
    println("TileComputeAtYInnerStoreAtXOuter prog")

    val sixth = new TileComputeAtYInnerStoreAtXOuter with CompilerInstance with TestAstOps
    val sixthA = new TileComputeAtYInnerStoreAtXOuter with TestPipelineAnalysis

    sixth.compile(sixthA.getBoundsGraph, "tileComputeStore")
    val correctAst: TNode =
      new TRootNode(List(
        new TStorageNode("i", List(
          new TLoopNode("y_outer", "i", Sequential(), List(
            new TLoopNode("x_outer", "i", Sequential(), List(
              new TStorageNode("g", List(
                new TLoopNode("y", "i", Sequential(), List(
                  new TLoopNode("y", "g", Sequential(), List(
                    new TLoopNode("x", "g", Sequential(), List(
                      new TComputeNode("g", List())
                    ))
                  )),
                  new TLoopNode("x", "i", Sequential(), List(
                    new TComputeNode("i", List())
                  ))
                ))
              ))
            ))
          ))
        ))
      ))
    assertResult(correctAst)(sixth.scheduleRep)
  }


  "The tile store and compute program" should "tiles i, store g at y_inner and compute it at x_inner" in {
    println("TiletoreAtYOuterComputeAtXInnerS prog")

    val seventh = new TiletoreAtYOuterComputeAtXInnerS with CompilerInstance with TestAstOps
    val seventhA = new TiletoreAtYOuterComputeAtXInnerS with TestPipelineAnalysis

    seventh.compile(seventhA.getBoundsGraph, "tileComputeStore2")
    val correctAst: TNode =
      new TRootNode(List(
        new TStorageNode("i", List(
          new TLoopNode("y_outer", "i", Sequential(), List(
            new TStorageNode("g", List(
              new TLoopNode("x_outer", "i", Sequential(), List(
                new TLoopNode("y", "i", Sequential(), List(
                  new TLoopNode("x", "i", Sequential(), List(
                    new TLoopNode("y", "g", Sequential(), List(
                      new TLoopNode("x", "g", Sequential(), List(
                        new TComputeNode("g", List())
                      ))
                    )),
                    new TComputeNode("i", List())
                  ))
                ))
              ))
            ))
          ))
        ))
      ))
    assertResult(correctAst)(seventh.scheduleRep)
  }


  "The tile store and compute program" should "tiles i and store and compute at same dim(y_inner)" in {
    println("TileStoreAtComputeAtSame prog")

    val eighth = new TileStoreAtComputeAtSame with CompilerInstance with TestAstOps
    val eighthA = new TileStoreAtComputeAtSame with TestPipelineAnalysis

    eighth.compile(eighthA.getBoundsGraph, "tileComputeStoreSame")
    val correctAst: TNode =
      new TRootNode(List(
        new TStorageNode("i", List(
          new TLoopNode("y_outer", "i", Sequential(), List(
            new TLoopNode("x_outer", "i", Sequential(), List(
              new TLoopNode("y", "i", Sequential(), List(
                new TStorageNode("g", List(
                  new TLoopNode("y", "g", Sequential(), List(
                    new TLoopNode("x", "g", Sequential(), List(
                      new TComputeNode("g", List())
                    ))
                  )),
                  new TLoopNode("x", "i", Sequential(), List(
                    new TComputeNode("i", List())
                  ))
                ))
              ))
            ))
          ))
        ))
      ))
    assertResult(correctAst)(eighth.scheduleRep)
  }


  "The tile store and compute 3 stage program" should "tiles i, store h at x_outer compute it at y_inner " +
    "and compute g at y of h" in {
    println("TileStoreAtXOuterComputeAtYInnerComputeAtY prog")

    val ninth = new TileStoreAtXOuterComputeAtYInnerComputeAtY with CompilerInstance with TestAstOps
    val ninthA = new TileStoreAtXOuterComputeAtYInnerComputeAtY with TestPipelineAnalysis

    ninth.compile(ninthA.getBoundsGraph, "tileComputeStoreSplit")
    val correctAst: TNode =
      new TRootNode(List(
        new TStorageNode("i", List(
          new TLoopNode("y_outer", "i", Sequential(), List(
            new TLoopNode("x_outer", "i", Sequential(), List(
              new TStorageNode("h", List(
                new TLoopNode("y", "i", Sequential(), List(
                  new TLoopNode("y", "h", Sequential(), List(
                    new TStorageNode("g", List(
                      new TLoopNode("y", "g", Sequential(), List(
                        new TLoopNode("x", "g", Sequential(), List(
                          new TComputeNode("g", List())
                        ))
                      )),
                      new TLoopNode("x", "h", Sequential(), List(
                        new TComputeNode("h", List())
                      ))
                    ))
                  )),
                  new TLoopNode("x", "i", Sequential(), List(
                    new TComputeNode("i", List())
                  ))
                ))
              ))
            ))
          ))
        ))
      ))
    assertResult(correctAst)(ninth.scheduleRep)
  }


  "The tile, split store and compute 3 stage program" should "tiles i, store h at x_outer, compute it at y_outer, " +
    "split it across y and store g at y_outer and compute it at y_inner" in {
    println("TileComputeAtOuterStoreAtInnerSplitStoreAtOuterComputeAtInner prog")

    val tenth = new TileComputeAtOuterStoreAtInnerSplitStoreAtOuterComputeAtInner with CompilerInstance with TestAstOps
    val tenthA = new TileComputeAtOuterStoreAtInnerSplitStoreAtOuterComputeAtInner with TestPipelineAnalysis

    tenth.compile(tenthA.getBoundsGraph, "tileComputeStoreSplit2")
    val correctAst: TNode =
      new TRootNode(List(
        new TStorageNode("i", List(
          new TLoopNode("y_outer", "i", Sequential(), List(
            new TStorageNode("h", List(
              new TLoopNode("x_outer", "i", Sequential(), List(
                new TLoopNode("y_outer", "h", Sequential(), List(
                  new TStorageNode("g", List(
                    new TLoopNode("y", "h", Sequential(), List(
                      new TLoopNode("y", "g", Sequential(), List(
                        new TLoopNode("x", "g", Sequential(), List(
                          new TComputeNode("g", List())
                        ))
                      )),
                      new TLoopNode("x", "h", Sequential(), List(
                        new TComputeNode("h", List())
                      ))
                    ))
                  ))
                )),
                new TLoopNode("y", "i", Sequential(), List(
                  new TLoopNode("x", "i", Sequential(), List(
                    new TComputeNode("i", List())
                  ))
                ))
              ))
            ))
          ))
        ))
      ))
    assertResult(correctAst)(tenth.scheduleRep)
  }
}