package example.underscore5

sealed trait Color {
  def r: Int
  def g: Int
  def b: Int
  def isLight(): Boolean = (r + g + b) / 2 < 380
}

