package model


sealed trait Digit {
  val name: String
  val teenName: String
}


object Digit {

  case object Zero extends Digit {
    val name = "zero"
    val teenName = "ten"
  }

  case object One extends Digit {
    val name = "one"
    val teenName = "eleven"
  }

  final case class Other(name: String, teenName: String, tensPrefix: String) extends Digit

  val values: Map[Int, Digit] = Map(
    0 -> Zero,
    1 -> One,
    2 -> Other("two",   "twelve",    "twenty"),
    3 -> Other("three", "thirteen",  "thirty"),
    4 -> Other("four",  "fourteen",  "fourty"),
    5 -> Other("five",  "fifteen",   "fifty"),
    6 -> Other("six",   "sixteen",   "sixty"),
    7 -> Other("seven", "seventeen", "seventy"),
    8 -> Other("eight", "eighteen",  "eighty"),
    9 -> Other("nine",  "nineteen",  "ninety")
  )

}
