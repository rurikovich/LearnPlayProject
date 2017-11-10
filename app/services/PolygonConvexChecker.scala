package services

/**
  * @author RastegaevYO
  */
trait PolygonConvexChecker {

  def isConvex(points: List[(Double, Double)]): Boolean = {


    val points = List((1, 2), (22, 3), (4, 2), (6, 2))
    val n = points.length
    var flag = 0

    points.zipWithIndex.foreach { case (p, i) =>
      val j = (i + 1) % n
      val k = (i + 2) % n

      val curP = points(j)
      val nextP = points(k)

      val z = (curP._1 - p._1) * (nextP._2 - curP._2) - (curP._2 - p._2) * (nextP._1 - curP._1)

      if (z < 0)
        flag = flag | 1
      else if (z > 0)
        flag = flag | 2

      if (flag == 3){
        false
      }

      if (flag != 0)
        true
      else
        false

        println(s"$p i=$i j=$j  k=$k  z=$z")
    }


    false
  }

}
