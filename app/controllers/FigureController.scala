package controllers

import javax.inject.{Inject, Singleton}

import play.api.mvc.{AbstractController, ControllerComponents}

/**
  * @author RastegaevYO
  */

@Singleton
class FigureController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def addFigure() = Action {
    request => {
      val text = request.body.asText.getOrElse("")
      val value = s"Hello, List  with  name=$text"
      println(value)
      Ok(value)
    }
  }

}
