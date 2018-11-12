package sepia

object BoundsAnalysis {
  def boundsForProdInCon(boundsGraph: Map[Int, Map[Int, Map[String, Bound]]],
  											 prod: Int, cons: Int, v: String): Option[Bound] = {
    // TODO: Re-write this function
  	if (boundsGraph(cons).contains(prod)) {
  		Some(boundsGraph(cons)(prod)(v))
  	} else {
      // cons = ... newCons(...)...
  		var curBound: Option[Bound] = None
  		for (newCons <- boundsGraph(cons).keys) {
  			val newConsBounds = boundsGraph(cons)(newCons).getOrElse(v, Bound.zero)
  			// TODO: Circles!
  			boundsForProdInCon(boundsGraph, prod, newCons, v).foreach({ b =>
  					curBound = Some(curBound.getOrElse(Bound.zero) join (b add newConsBounds))
  			})
  		}
  		curBound
  	}
  }
}
