package controllers

import java.lang.Math._
import javax.inject.{Inject, Singleton}

import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}
import services.FigureManager

/**
  * @author RastegaevYO
  */

@Singleton
class FigureController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {


  def addFigure() = Action {
    request => {
      val json = request.body.asJson.get

      val name = (json \ "name").get.toString()
      val points = (json \ "points").get

      val koordsList = points.as[List[Double]]
      val pointList: List[(Double, Double)] = koordsList.grouped(2).map { case List(a, b) => (a, b) }.toList

      if (FigureManager.isConvex(pointList)) {
        FigureManager.addFigure(name, koordsList)
        Ok(s"Figure with  name=$name added successfully")
      } else {
        BadRequest(s"Failed to add figure with name=$name")
      }
    }
  }

  def getFigures = Action {
    val json = Json.toJson(FigureManager.figures.toList)
    Ok(json)
  }

  def checkRoute = Action {
    request => {
      val json = request.body.asJson.get
      val koordsList = (json \ "points").get.as[List[Double]]
      val pointList: List[(Double, Double)] = koordsList.grouped(2).map { case List(a, b) => (a, b) }.toList

      var figures = Set[String]()

      for (point <- pointList) {
        for (f <- FigureManager.figures.toList) {
          val fPoints = f.points.grouped(2).map { case List(a, b) => (a, b) }.toList

          // выпуклый многоугольник можно разбить на треугольники
          // сочетанием одной его вершины со всеми остальными попарно
          // Например для (в1,в2,в3,в4,в5)->(в1,в2,в3) (в1,в3,в4) (в1,в4,в5)
          val p1 = fPoints.head
          for (List(p2, p3) <- fPoints.tail.sliding(2)) {
            val isPointInTriangle: Boolean = checkTriangleContainsPoint(p1, p2, p3, point)
            if (isPointInTriangle) {
              figures += f.name
            }
          }
        }
      }

      Ok(s"CheckRoute find figures ${figures mkString ","}")
    }
  }

  def checkTriangleContainsPoint(p1: (Double, Double), p2: (Double, Double), p3: (Double, Double), point: (Double, Double)): Boolean = {
    val exp1 = (p1._1 - point._1) * (p2._2 - p1._2) - (p2._1 - p1._1) * (p1._2 - point._2)
    val exp2 = (p2._1 - point._1) * (p3._2 - p2._2) - (p3._1 - p2._1) * (p2._2 - point._2)
    val exp3 = (p3._1 - point._1) * (p1._2 - p3._2) - (p1._1 - p3._1) * (p3._2 - point._2)

    return (signum(exp1) == signum(exp2) && signum(exp2) == signum(exp3)) || (exp1 * exp2 * exp3 == 0)
  }

}
