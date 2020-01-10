package example.underscore4

object json {
  sealed trait Json {
    def print: String = {
      def quote(s: String): String =
        '"'.toString ++ s ++ '"'.toString
      def arrayToString(a: JsonArrayCell): String = 
        a match {
          case JsonArrayCell(h, t @ JsonArrayCell(_, _)) => 
            s"${h.print}, ${arrayToString(t)}"
          case JsonArrayCell(h, JsonArrayEnd) => h.print
        }
      def objectToString(o: JsonObjectCell): String =
        o match {
          case JsonObjectCell(k, v, t @ JsonObjectCell(_, _, _)) =>
            s"${quote(k)}: ${v.print}, ${objectToString(t)}"
          case JsonObjectCell(k, v, JsonObjectEnd) =>
            s"${quote(k)}: ${v.print}"
        }
      this match {
        case JsonString(s) => quote(s)
        case JsonBool(b) => b.toString
        case JsonInt(n) => n.toString
        case JsonDouble(d) => d.toString
        case a @ JsonArrayCell(_, _) => arrayToString(a)
        case JsonArrayEnd  => "[]"
        case o @ JsonObjectCell(_, _,  _) => objectToString(o)
        case JsonObjectEnd => "{}"
      }
    }
  }
  final case class JsonString(string: String) extends Json
  final case class JsonBool(bool: Boolean) extends Json
  final case class JsonInt(int: Int) extends Json
  final case class JsonDouble(double: Double) extends Json

  // Note: we extend the Json trait with another sealed trait, and then add implementation
  sealed trait JsonArray extends Json
  final case class JsonArrayCell(head: Json, tail: JsonArray) extends JsonArray
  final case object JsonArrayEnd extends JsonArray

  sealed trait JsonObject extends Json
  final case class JsonObjectCell(key: String, value: Json, tail: JsonObject) extends JsonObject
  final case object JsonObjectEnd extends JsonObject 
}
