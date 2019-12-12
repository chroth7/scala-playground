import org.scalatest._
import example.underscore5._

class U5Spec extends FunSuite with DiagrammedAssertions {
  test("Can draw a circle") {
    assert("H")).equals("H"))
    assert(Draw(Circle(2, CreateColor(1,1,1))).equals("Helles Kreisli mit Radius 2.0"))
    assert(Draw(Circle(2, CreateColor(1000,1000,1000))).equals("Dunkles Kreisli mit Radius 2.0"))
  }
}
