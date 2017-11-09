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
      val json = request.body.asJson.get

      val name = (json \ "name").get
      val value = s"Hello, List  with  name=${name}"
      Ok(value)
    }
  }

}
