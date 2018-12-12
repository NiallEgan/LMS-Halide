package sepia

//  todo: remove this class

class Edge(val bounds: Map[String, Bound])

class CallGraph(a: Map[Int, List[Int]], ws: Map[(Int, Int), Edge]) {
  private val adjList: Map[Int, List[Int]] = a
  private val weights: Map[(Int, Int), Edge] = ws

  def getBound(producer: Int, consumer: Int, v: String) = {
    weights((consumer, producer)).bounds(v)
  }

  def isProducerConsumer(producer: Int, consumer: Int) = {
    weights.contains((consumer, producer))
  }

  def producersOf(f: Int) = adjList(f)

  def getBoundWithDefault(producer: Int, consumer: Int,
                          v: String, default: Bound) = {
    weights((consumer, producer)).bounds.getOrElse(v, default)
  }
}

object CallGraph {
  def buildWeights(m: Map[Int, Map[Int, Map[String, Bound]]]) = {
    m.flatMap({
      case (k1, v1) => v1.map({
        case (k2, v2) => (k1, k2) -> new Edge(v2)
      })
    })
  }

  def buildAdj(m: Map[Int, Map[Int, Map[String, Bound]]],
               src: Int): Map[Int, List[Int]] = {
    if (!m.contains(src) || m(src).isEmpty) {  // src doesn't call anything (or is in)
      Map(src -> List())
    } else {
      val adjList = m(src).flatMap({
        case (k, v) => buildAdj(m, k)
      })

      adjList + (src -> m(src).keys.toList)
    }
  }

  def graphFromMap(m: Map[Int, Map[Int, Map[String, Bound]]], node: Int): CallGraph = {
    val weights = buildWeights(m)

    val adjList = buildAdj(m, node)
    new CallGraph(adjList, weights)
  }
}
