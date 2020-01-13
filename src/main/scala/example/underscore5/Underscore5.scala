package example.underscore5

sealed trait Result[A]
final case class Success[A](s: A) extends Result[A]
final case class Failure[A](f: String) extends Result[A]

sealed trait LinkedList[A] {
  def apply(n: Int): Result[A] = {
    // if (n < 0) throw new Exception("Subzero index")
    if (n < 0) Failure("Subzero Index") else
      this match {
        // case End() => throw new Exception("Index out of bounds")
        case End() => Failure("Index out of bounds")
        case Pair(h, t) => {
          // if (n == 0) h else t(n - 1)
          if (n == 0) Success(h) else t(n - 1)
        }
      }
  }

  def fold[B](end: B, f: (A, B) => B): B = {
    this match {
      case End() =>  end
      case Pair(hd, tl) => f(hd, tl.fold(end, f))
    }
  }

  def length: Int = {
    def f[A] = (_: A, acc: Int) => acc + 1
    this.fold(0, f)
  }

  // short:
  // def length: Int = fold(0, (_, a) => a + 1)

  def lengthWithoutFold: Int = {
    this match {
      case End() => 0
      case Pair(h, t) => 1 + t.lengthWithoutFold
    }
  }
  def contains(el: A): Boolean = {
    this match {
      case End() => false
      case Pair(h, t) => if (h == el) true else t.contains(el)
    }
  }
}

final case class End[A]() extends LinkedList[A]
final case class Pair[A](head: A, tail: LinkedList[A]) extends LinkedList[A]

// final case class IntList() extends LinkedList[Int] {
//   def sum: Int = {
//     def s = (v: Int, a: Int) => v + a
//     this.fold(0, s)
//   }
// }

