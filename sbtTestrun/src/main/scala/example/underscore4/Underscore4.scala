package example.underscore4

sealed trait Color {
  def r: Int
  def g: Int
  def b: Int
  def isLight(): Boolean = (r + g + b) / 2 < 380
}

final case object Red extends Color {
  val r = 255
  val g = 0
  val b = 0
}

final case object Green extends Color {
  val r = 0
  val g = 255
  val b = 0
}

final case object Blue extends Color {
  val r = 0
  val g = 0
  val b = 255
}

final case class CreateColor(rr: Int, gg: Int, bb: Int) extends Color {
  def minmax(n: Int) = n.max(0).min(255)
  val r = minmax(rr)
  val g = minmax(gg)
  val b = minmax(bb)
}

sealed trait Shape {
  def sides: Int
  def perimeter: Double 
  def area: Double
  def color: Color
}

sealed trait Rectangular extends Shape {
  override def sides = 4
  def width: Double
  def height: Double
  def perimeter = 2 * width + 2 * height
  def area = width * height
}

final case class Circle(radius: Double, color: Color) extends Shape {
  val sides = 1
  val perimeter = 2 * math.Pi * radius
  val area = math.Pi * radius * radius
}

final case class Rectangle(width: Double, height: Double, color: Color) extends Rectangular

final case class Square(side: Double, color: Color) extends Rectangular {
  val width = side
  val height = side
}

object Draw {
  def colorString(color: Color): String = 
    color match {
      case Red => "Rotes"
      case Blue => "Blaues"
      case Green => "Gruenes"
      case CreateColor(_, _, _) => if (color.isLight) "Helles" else "Dunkles"
    }

  def apply(shape: Shape) = 
    shape match {
      case Circle(radius, color) => colorString(color) + " Kreisli mit Radius " + radius
      case Rectangle(width, height, color) => colorString(color) + " Rechteck mit Breite " + width + " und Hoehe " + height
      case Square(side, color) => colorString(color) + " Quadrat mit Seite " + side
    }
}

// Division example
sealed trait DivisionResult 
final case object Infinite extends DivisionResult
final case class Finite(n: Int) extends DivisionResult

final case object divide extends DivisionResult {
  def apply(up: Int, down: Int): DivisionResult =
    down match {
      case 0 => Infinite
      case _ => Finite(up / down)
    }
}

// Traffic light ADT -- SUM type
sealed trait TrafficLight {
  def next: TrafficLight = 
    this match {
      case RedLight => GreenLight
      case GreenLight => YellowLight
      case YellowLight => RedLight
    }
}
final case object RedLight extends TrafficLight
final case object YellowLight extends TrafficLight
final case object GreenLight extends TrafficLight

// Calculator ADT -- SUM type
sealed trait CalculatorResult 
final case class SuccessCalc(n: Int) extends CalculatorResult
final case class FailureCalc(s: String) extends CalculatorResult

final case object Calculator {
  def +(calc: CalculatorResult, n: Int): CalculatorResult = 
    calc match {
      case SuccessCalc(nn) => SuccessCalc(n + nn)
      case FailureCalc(s) => FailureCalc(s)
    }
  def -(calc: CalculatorResult, n: Int): CalculatorResult = 
    calc match {
      case SuccessCalc(nn) => SuccessCalc(n - nn)
      case FailureCalc(s) => FailureCalc(s)
    }
  def /(calc: CalculatorResult, n: Int): CalculatorResult = 
    calc match {
      case FailureCalc(s) => FailureCalc(s)
      case SuccessCalc(nn) => if (n == 0) FailureCalc("Division by zero") else SuccessCalc(nn / n)
    }
}

// Bottled water ADT -- size (int), source (well, spring, tap), cabonated (bool)
sealed trait WaterSource
final case object well extends WaterSource
final case object spring extends WaterSource
final case object tap extends WaterSource

sealed trait Water {
  def size: Int
  def source: WaterSource
  def carbonated: Boolean
}


// Recursive Data Types
sealed trait IntList {
  def length: Int =
    this match {
      case End => 0
      case Pair(_,t) => 1 + t.length
   }
  def product: Int =
    this match {
      case End => 1
      case Pair(h,t) => h * t.product
   }
  def double: IntList =
    this match {
      case End => End
      case Pair(h,t) => Pair(2 * h, t.double)
   }
}
final case object End extends IntList
final case class Pair(head: Int, tail: IntList) extends IntList

// Tree
sealed trait Tree {
  def sum: Int =
    this match {
      case Leaf(n) => n
      case Node(l, r) => l.sum + r.sum
    }
}
final case class Leaf(n: Int) extends Tree
final case class Node(left: Tree, right: Tree) extends Tree
