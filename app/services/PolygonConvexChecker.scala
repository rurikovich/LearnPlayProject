package services

/**
  * @author RastegaevYO
  */
trait PolygonConvexChecker {

  def isConvex(points: List[(Double, Double)]): Boolean = {
    val n = points.length

    if (n < 3) {
      return false
    }

    var flag = 0
    points.zipWithIndex.foreach {
      case (p, i) =>
        val j = (i + 1) % n
        val k = (i + 2) % n
        val curP = points(j)
        val nextP = points(k)

        val z = (curP._1 - p._1) * (nextP._2 - curP._2) - (curP._2 - p._2) * (nextP._1 - curP._1)
        if (z < 0) {
          flag = flag | 1
        } else if (z > 0) {
          flag = flag | 2
        }

    }
    return if (flag == 3) false else flag != 0
  }

}
