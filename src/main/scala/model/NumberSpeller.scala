package model

import Digit._

import utils.CollectionImplicits._


final case class NumberSpeller(value: Int) {

  import NumberSpeller._

  require(isValid(value), invalidErrorMessage)

  def spell: Either[Throwable, String] = {
    for {
      wordsForMillions <- wordsForRange(value, OrderOfMagnitudeRange.Million)
      wordsForThousands <- wordsForRange(value, OrderOfMagnitudeRange.Thousand)
      wordsForOnes <- wordsForRange(value, OrderOfMagnitudeRange.One)
    } yield {
      val words = wordsForMillions ++ wordsForThousands ++ wordsForOnes
      words match {
        case Nil => Zero.name
        case _   => words.mkString(" ")
      }
    }
  }

  private def wordsForRange(n: Int, range: OrderOfMagnitudeRange): Either[Throwable, List[String]] = {
    for {
      digits <- range.extractDigits(n)
    } yield {
      val leastSignificantDigitsWords: List[String] = (digits._2, digits._3) match {
        case (Zero, Zero)                    => Nil
        case (Zero, ones)                    => ones.name :: (range.suffix :?: List.empty[String])
        case (One, ones)                     => ones.teenName :: (range.suffix :?: List.empty[String])
        case (Other(_, _, tensPrefix), Zero) => tensPrefix :: (range.suffix :?: List.empty[String])
        case (Other(_, _, tensPrefix), ones) => tensPrefix :: ones.name :: (range.suffix :?: List.empty[String])
      }

      val mostSignificantDigitWords: List[String] = digits._1 match {
        case Zero     => Nil
        case hundreds => hundreds.name :: "hundred" :: Nil
      }

      if (mostSignificantDigitWords.nonEmpty && leastSignificantDigitsWords.nonEmpty) {
        mostSignificantDigitWords ++ List("and") ++ leastSignificantDigitsWords
      } else {
        mostSignificantDigitWords ++ leastSignificantDigitsWords
      }
    }
  }

}


object NumberSpeller {

  def isValid(value: Int): Boolean = {
    value >= 0 && value <= 999999999
  }

  val invalidErrorMessage: String = "The value must be a number between 0 and 999,999,999 included"

}
