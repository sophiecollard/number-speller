package model

import org.specs2.mutable.Specification


class OrderOfMagnitudeRangeSpec extends Specification {

  "OrderOfMagnitudeRange" should {

    "take a number and extract the digits within its range" in {
      val n = 123456789
      OrderOfMagnitudeRange.One.extractDigits(n)      must beRight((Digit.values(7), Digit.values(8), Digit.values(9)))
      OrderOfMagnitudeRange.Thousand.extractDigits(n) must beRight((Digit.values(4), Digit.values(5), Digit.values(6)))
      OrderOfMagnitudeRange.Million.extractDigits(n)  must beRight((Digit.values(1), Digit.values(2), Digit.values(3)))
      OrderOfMagnitudeRange.Thousand.extractDigits(999) must beRight((Digit.Zero, Digit.Zero, Digit.Zero))
    }

  }

}
