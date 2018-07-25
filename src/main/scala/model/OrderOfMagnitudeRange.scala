package model


sealed trait OrderOfMagnitudeRange {
  val suffix: Option[String]
  protected val lowerFactor: Int
  protected val upperFactor: Int

  def extract(n: Int): Int = {
    n % Math.pow(10, upperFactor).toInt / Math.pow(10, lowerFactor).toInt
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
