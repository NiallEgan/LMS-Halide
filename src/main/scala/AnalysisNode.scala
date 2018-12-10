package sepia

class AnalysisNode(val label: Int,
                   val ns: List[AnalysisNode])

class Edge(val bounds: Map[String, Bound])

class CallGraph(a: Map[Int, AnalysisNode], ws: Map[(Int, Int), Edge]) {
  private val adjList: Map[Int, AnalysisNode] = a
  private val weights: Map[(Int, Int), Edge] = ws

  def getBound(caller: Int, callee: Int, v: String) = {
    weights((caller, callee)).bounds(v)
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

  def buildAdj(m: Map[Int, Map[Int, Map[String, Bound]]], src: Int): Map[Int, AnalysisNode] = {
    if (m(src).isEmpty) {  // src doesn't call anything
      Map(src -> new AnalysisNode(src, List()))
    } else {
      val adjList = m(src).flatMap({
        case (k, v) => buildAdj(m, k)
      })

      val neighbours = adjList.filterKeys(m(src).keySet.contains(_)).values.toList
      val node = new AnalysisNode(src, neighbours)
      adjList + (src -> node)
    }
  }

  def graphFromMap(m: Map[Int, Map[Int, Map[String, Bound]]], node: Int): CallGraph = {
    val weights = buildWeights(m)

    val adjList = buildAdj(m, node)
    new CallGraph(adjList, weights)
  }
}
