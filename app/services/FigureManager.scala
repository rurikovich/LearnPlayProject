package services

import java.lang.Math.signum

import pojos.Figure

import scala.collection.mutable.ListBuffer

object FigureManager extends FigureService with PolygonConvexChecker {

  val figures = new ListBuffer[Figure]

  override def addFigure(name: String, points: List[Double]): Unit = {
    figures += Figure(name, points)
  }

  def findIntersectionWithFirures(pointList: List[(Double, Double)]): Set[String] = {
    var figuresNames = Set[String]()

    for (point <- pointList) {
      for (f <- figures.toList) {
        val fPoints = f.points.grouped(2).map { case List(a, b) => (a, b) }.toList

        // выпуклый многоугольник можно разбить на треугольники
        // сочетанием одной его вершины со всеми остальными попарно
        // Например для (в1,в2,в3,в4,в5)->(в1,в2,в3) (в1,в3,в4) (в1,в4,в5)
        val p1 = fPoints.head
        for (List(p2, p3) <- fPoints.tail.sliding(2)) {
          val isPointInTriangle: Boolean = checkTriangleContainsPoint(p1, p2, p3, point)
          if (isPointInTriangle) {
            figuresNames += f.name
          }
        }
      }
    }
    figuresNames
  }

  def checkTriangleContainsPoint(p1: (Double, Double), p2: (Double, Double), p3: (Double, Double), point: (Double, Double)): Boolean = {
    val exp1 = (p1._1 - point._1) * (p2._2 - p1._2) - (p2._1 - p1._1) * (p1._2 - point._2)
    val exp2 = (p2._1 - point._1) * (p3._2 - p2._2) - (p3._1 - p2._1) * (p2._2 - point._2)
    val exp3 = (p3._1 - point._1) * (p1._2 - p3._2) - (p1._1 - p3._1) * (p3._2 - point._2)

    return (signum(exp1) == signum(exp2) && signum(exp2) == signum(exp3)) || (exp1 * exp2 * exp3 == 0)
  }

}

