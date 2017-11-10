package services

trait FigureService {

  def addFigure(name: String, points: List[(Double, Double)]): Unit

}
