import org.scalatestplus.play._
import services.FigureManager

/**
  * Unit tests can run without a full Play application.
  */
class FigureManagerUnitSpec extends PlaySpec {

  "FigureManager" should {

    "check polygon is convex" in {
      FigureManager.isConvex(List((1, 1), (3, 1), (2, 3), (1, 2))) must equal(true)
      FigureManager.isConvex(List((1, 1), (3, 1), (2, 2), (2, 3), (1, 2))) must equal(false)
    }

    "check location of point inside the triangle" in {
      FigureManager.checkTriangleContainsPoint((4, 1), (6, 3), (4, 4), (5, 3)) must equal(true)
      FigureManager.checkTriangleContainsPoint((4, 1), (6, 3), (4, 4), (5, 9)) must equal(false)
    }

  }

}
