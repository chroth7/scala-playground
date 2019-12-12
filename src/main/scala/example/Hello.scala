package example

import scala.concurrent._, duration._
import core.Weather
import underscore4._

// object Hello extends App {
//   val w = Await.result(Weather.weather, 10.seconds)
//   println(s"Hello! The weather in New York is $w.")
//   Weather.http.close()
// }

object Hello extends App {
  println(Draw(Circle(2, CreateColor(1,1,1))))
  println(Draw(Circle(2, CreateColor(223,223,223))))
  println(Draw(Square(2, Red)))
}
