package model


sealed trait OrderOfMagnitudeRange {
  val suffix: Option[String]
  protected val lowerFactor: Int
  protected val upperFactor: Int

  private def extract(n: Int): Int = {
    n % Math.pow(10, upperFactor).toInt / Math.pow(10, lowerFactor).toInt
  }

  def extractDigits(n: Int): Either[Throwable, Tuple3[Digit, Digit, Digit]] = {
    import Digit._

    extract(n).toString.map(s => Digit.values(s.asDigit)).reverse.toList match {
      case ones :: Nil                     => Right((Zero, Zero, ones))
      case ones :: tens :: Nil             => Right((Zero, tens, ones))
      case ones :: tens :: hundreds :: Nil => Right((hundreds, tens, ones))
      case _                               => Left(throw new RuntimeException("Impossible state"))
    }
  }
}


object OrderOfMagnitudeRange {

  case object One extends OrderOfMagnitudeRange {
    val suffix: Option[String] = None
    val lowerFactor = 0
    val upperFactor = 3
  }

  case object Thousand extends OrderOfMagnitudeRange {
    val suffix: Option[String] = Some("thousand")
    val lowerFactor = 3
    val upperFactor = 6
  }

  case object Million extends OrderOfMagnitudeRange {
    val suffix: Option[String] = Some("million")
    val lowerFactor = 6
    val upperFactor = 9
  }

}
