import org.scalatest._
import example.underscore4._

class U4Spec extends FunSuite with DiagrammedAssertions {
  test("Can draw a circle") {
    assert(Draw(Circle(2, Red)).equals("Rotes Kreisli mit Radius 2.0"))
    assert(Draw(Circle(2, CreateColor(1,1,1))).equals("Helles Kreisli mit Radius 2.0"))
    assert(Draw(Circle(2, CreateColor(1000,1000,1000))).equals("Dunkles Kreisli mit Radius 2.0"))
  }

  test("Divides properly") {
    assert(divide(1,2).equals(Finite(0)))
    assert(divide(1,0).equals(Infinite))
  }
}
