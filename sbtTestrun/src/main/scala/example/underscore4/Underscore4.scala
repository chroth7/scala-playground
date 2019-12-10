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

