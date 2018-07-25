package model

import Digit._

import utils.CollectionImplicits._


final case class NumberSpeller(value: Int) {

  import NumberSpeller._

  require(isValid(value), invalidErrorMessage)

  def spell: String = {
    val words = wordsForRange(value, OrderOfMagnitudeRange.Million) ++
      wordsForRange(value, OrderOfMagnitudeRange.Thousand) ++
      wordsForRange(value, OrderOfMagnitudeRange.One)

    words match {
      case Nil => Zero.name
      case _   => words.mkString(" ")
    }
  }

  private def wordsForRange(n: Int, range: OrderOfMagnitudeRange): List[String] = {
    val digits = range.extract(n).toString.map(s => Digit.values(s.asDigit)).reverse.toList match {
      case ones :: Nil                     => (Zero, Zero, ones)
      case ones :: tens :: Nil             => (Zero, tens, ones)
      case ones :: tens :: hundreds :: Nil => (hundreds, tens, ones)
      case _                               => throw new RuntimeException("Impossible state")
    }

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

    val words: List[String] =
      if (mostSignificantDigitWords.nonEmpty && leastSignificantDigitsWords.nonEmpty) {
        mostSignificantDigitWords ++ List("and") ++ leastSignificantDigitsWords
      } else {
        mostSignificantDigitWords ++ leastSignificantDigitsWords
      }

    words
  }

}


object NumberSpeller {

  def isValid(value: Int): Boolean = {
    value >= 0 && value <= 999999999
  }

  val invalidErrorMessage: String = "The value must be a number between 0 and 999,999,999 included"

}
