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

  test("Calculator") {
    assert(Calculator./(SuccessCalc(4), 2).equals(SuccessCalc(2)))
    assert(Calculator./(SuccessCalc(4), 0).equals(FailureCalc("Division by zero")))
    assert(Calculator./(FailureCalc("FailFast"), 0).equals(FailureCalc("FailFast")))
  }

  test("Lists") {
    val exList = Pair(1, Pair(2, Pair(3, End)))
    assert(exList.length.equals(3))
    assert(exList.tail.length.equals(2))
    assert(End.length.equals(0))

    assert(exList.product.equals(6))
    assert(exList.tail.product.equals(6))
    assert(End.product.equals(1))

    val doubleList = Pair(2, Pair(4, Pair(6, End)))
    assert(exList.double.equals(doubleList))
    assert(exList.tail.double.equals(doubleList.tail))
    assert(End.double.equals(End))
  }

  test("Tree") {
    val tree = 
      Node( 
        Node( 
          Node(Leaf(1), 
            Node(Leaf(2), Leaf(3))
            ), Leaf(4)), 
        Node(Leaf(5), Leaf(6)))
    assert(tree.sum.equals(21))
  }
}
