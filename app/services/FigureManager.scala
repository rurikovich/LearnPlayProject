package services

import pojos.Figure

import scala.collection.mutable.ListBuffer

object FigureManager extends FigureService with PolygonConvexChecker {

  val figures = new ListBuffer[Figure]
  var lastRoute: List[Double] = List.empty

  override def addFigure(name: String, points: List[Double]): Unit = {
    figures += Figure(name, points)
  }

  def findIntersectionsWithFirures(pointList: List[(Double, Double)]): Set[String] = {
    figures.toList.filter(f => {
      val p1 = f.getGroupedPoints.head
      f.getGroupedPoints.tail.sliding(2).exists(p2p3 => {
        val p2 = p2p3(0)
        val p3 = p2p3(1)
        pointList.exists(point => checkTriangleContainsPoint(p1, p2, p3, point))
      })
    }).map(f => f.name).toSet
  }

  def checkTriangleContainsPoint(p1: (Double, Double), p2: (Double, Double), p3: (Double, Double), point: (Double, Double)): Boolean = {
    val exp1 = (p1._1 - point._1) * (p2._2 - p1._2) - (p2._1 - p1._1) * (p1._2 - point._2)
    val exp2 = (p2._1 - point._1) * (p3._2 - p2._2) - (p3._1 - p2._1) * (p2._2 - point._2)
    val exp3 = (p3._1 - point._1) * (p1._2 - p3._2) - (p1._1 - p3._1) * (p3._2 - point._2)

    return (exp1 >= 0 && exp2 >= 0 && exp3 >= 0) || (exp1 <= 0 && exp2 <= 0 && exp3 <= 0)
  }

}

