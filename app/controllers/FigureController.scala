package controllers

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

      var figures: Set[String] = FigureManager.findIntersectionsWithFirures(pointList)

      FigureManager.lastRoute = koordsList

      Ok(s"CheckRoute find figures ${figures mkString ","}")
    }
  }

  def getLastRoute = Action {
    Ok(Json.toJson(FigureManager.lastRoute))
  }


}
