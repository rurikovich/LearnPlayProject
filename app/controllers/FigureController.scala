package controllers

import javax.inject.{Inject, Singleton}

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
        FigureManager.addFigure(name, pointList)
        Ok(s"Figure with  name=$name added successfully")
      }else{
        BadRequest(s"Failed to add figure with name=$name")
      }
    }
  }

}
