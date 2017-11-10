package services

import pojos.Figure

import scala.collection.mutable.ListBuffer

object FigureManager extends FigureService with PolygonConvexChecker {

  var figures = new ListBuffer[Figure]

  override def addFigure(name: String, points: List[ Double]): Unit = {
    figures += Figure(name, points)
  }


}

