import org.scalatest._
import example.underscore5._

class U5Spec extends FunSuite with DiagrammedAssertions {
  test("LinkedList") {
    val ll1 = Pair(1, Pair(5, End()))
    val ll2 = Pair(1, Pair(5, Pair(10, End())))
    assert(ll1.length.equals(2))
    assert(ll2.length.equals(3))

    assert(ll1.contains(1).equals(true))
    assert(ll1.contains(2).equals(false))
    // does not compile:
    // assert(ll1.contains("2").equals(false))
    
    assert(ll1(0) == Success(1))
    assert(ll1(1) == Success(5))
    assert(ll1(123) == Failure("Index out of bounds"))
    assert(ll1(-123) == Failure("Subzero Index"))
    // assert(try {
    //   ll1(2) 
    //   false
    // } catch {
    //   case e: Exception => true
    // })

    // SUM INLINE
    assert(ll1.fold(0, (v, a: Int) => v + a) == 6)
    assert(ll2.fold(0, (v, a: Int) => v + a) == 16)
  }

  test("IntList") {
    // val il1: IntList = Pair(1, Pair(5, End()))
    // assert(il1.sum() == 6)
  }
}
