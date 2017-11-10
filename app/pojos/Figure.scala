package pojos

import play.api.libs.json.{JsValue, Json, Writes}

case class Figure(name: String, points: List[Double])

object Figure {
  implicit val writer = new Writes[Figure] {
    def writes(t: Figure): JsValue = {
      Json.obj("name" -> t.name, "points" -> t.points)
    }
  }
}


