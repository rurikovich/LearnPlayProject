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
      val name="testname"
      val addPointsRequest = route(app, FakeRequest(POST, "/addFigure").withJsonBody(Json.parse(s"""{ "name": "$name" }"""))).get

      status(addPointsRequest) mustBe Status.OK
      contentAsString(addPointsRequest) must include(s"""Hello, List  with  name="${name}""")
    }

  }

}
