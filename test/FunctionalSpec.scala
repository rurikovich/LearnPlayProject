import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.http.Status
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._


/**
  * Functional tests start a Play application internally, available
  * as `app`.
  */
class FunctionalSpec extends PlaySpec with GuiceOneAppPerSuite {

  "FigureController" should {

    "correct json" in {
      val name = "testname"
      val points = List(1, 2, 1, 4, 2, 2)
      val jsonTemplate = s"""{ "name": "$name", "points":[${points mkString ","}] }"""
      val addPointsRequest = route(app, FakeRequest(POST, "/addFigure").withJsonBody(Json.parse(jsonTemplate))).get

      status(addPointsRequest) mustBe Status.OK
      contentAsString(addPointsRequest) must include(s"""Figure with  name="$name" added successfully""")
    }

    "incorrect json" in {
      val name = "testname"
      val points = List(1, 1, 3,1,3,1,3,3,2,2)
      val jsonTemplate = s"""{ "name": "$name", "points":[${points mkString ","}] }"""
      val addPointsRequest = route(app, FakeRequest(POST, "/addFigure").withJsonBody(Json.parse(jsonTemplate))).get

      status(addPointsRequest) mustBe Status.BAD_REQUEST
      contentAsString(addPointsRequest) must include(s"""Failed to add figure with name="$name"""")
    }

  }

}
