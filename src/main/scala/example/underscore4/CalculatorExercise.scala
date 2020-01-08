package example.underscore4

sealed trait Expression {
  def eval: Result = 
    this match {
      case Addition(l, r) => 
        l.eval match {
          case Failure(s) => Failure(s)
          case Success(ll) => 
            r.eval match {
              case Failure(s) => Failure(s)
              case Success(rr) => Success(rr + ll)
            }
        }
      case Subtraction(l, r) => 
        l.eval match {
          case Failure(s) => Failure(s)
          case Success(ll) => 
            r.eval match {
              case Failure(s) => Failure(s)
              case Success(rr) => Success(ll - rr)
            }
        }
      case Division(l, r) => 
        l.eval match {
          case Failure(s) => Failure(s)
          case Success(ll) => 
            r.eval match {
              case Failure(s) => Failure(s)
              case Success(rr) => 
                if (rr == 0) 
                  Failure("Division by 0")
                else
                  Success(ll / rr)
            }
        }
      case SquareRoot(x) => 
        x.eval match {
          case Failure(s) => Failure(s)
          case Success(xx) => 
            if (xx < 0)
              Failure("Sqrt of negative number")
            else
              Success(Math.sqrt(xx))
        }
      case Number(v) => Success(v) 
    }
}

sealed trait Result 
final case class Success(result: Double) extends Result
final case class Failure(error: String) extends Result

final case class Addition(left: Expression, right: Expression) extends Expression
final case class Subtraction(left: Expression, right: Expression) extends Expression
final case class Division(left: Expression, right: Expression) extends Expression
final case class SquareRoot(value: Expression) extends Expression
final case class Number(value: Double) extends Expression


