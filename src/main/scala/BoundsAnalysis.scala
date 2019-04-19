package sepia

object BoundsAnalysis {
  def boundsForProdInCon(boundsGraph: CallGraph,
  											 prod: Int, cons: Int, v: String): Option[Bound] = {
  	if (prod == cons) {
  		//Some(boundsGraph.getBound(prod, cons, v))
      Some(Bound(0, 0, 1, 1, 1, 1))
  	} else {
  		// Get the bounds for the producers of cons
      val (validProducers, nextBounds) =
        (for (otherProd <- boundsGraph.producersOf(cons);
             x = boundsForProdInCon(boundsGraph, prod, otherProd, v)
             if !x.isEmpty) yield (otherProd, x.get)).unzip
      // If none of the producers eventually get to prod, return none
      if (nextBounds.isEmpty) None
      else {
        // We now need to adjust the bounds we got (vertical join)
        val adjustedBounds =
          (validProducers zip nextBounds).map{
            case (p, b) => b add boundsGraph.getBound(p, cons, v)
          }
        // And now collect them all together (horizontal join)
        Some(adjustedBounds.fold(Bound.zero)(_ join _))
      }
  	}
  }
}
