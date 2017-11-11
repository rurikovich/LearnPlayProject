package services

trait FigureService {

  def addFigure(name: String, points: List[ Double]): Unit

  def findIntersectionsWithFirures(pointList: List[(Double, Double)]):Set[String]
}
