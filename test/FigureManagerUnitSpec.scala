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
      FigureManager.checkTriangleContainsPoint((1, 1), (3, 1), (2, 3), (5, 1)) must equal(false)
    }

    "find interseption with polygones" in {
      FigureManager.addFigure("polygon1", List(1, 1, 3, 1, 2, 3, 1, 2))
      FigureManager.addFigure("triangle1", List(4, 1, 6, 3, 5, 4))
      FigureManager.addFigure("polygon2", List(3, 2, 4, 2, 5, 3, 4, 4, 3, 3))

      FigureManager.findIntersectionWithFirures(List((1.5, 1.5), (2.5, 2.5), (4.9, 3))) must equal(Set("polygon1", "triangle1", "polygon2"))
      FigureManager.findIntersectionWithFirures(List((5, 1), (5.5, 3), (6, 5))) must equal(Set("triangle1"))
      FigureManager.findIntersectionWithFirures(List((5, 1), (6, 2), (7, 5))) must equal(Set())
    }

  }

}
