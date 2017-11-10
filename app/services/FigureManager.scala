package services

import pojos.Figure

import scala.collection.mutable.ListBuffer

object FigureManager extends FigureService with PolygonConvexChecker {

  private var figures = new ListBuffer[Figure]

  override def addFigure(name: String, points: List[(Double, Double)]): Unit = {
      figures += Figure(name, points)
  }
}

