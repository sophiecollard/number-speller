package model

import org.specs2.mutable.Specification


class OrderOfMagnitudeRangeSpec extends Specification {

  "OrderOfMagnitudeRange" should {

    "take a number and extract the digits within its range" in {
      val n = 123456789
      OrderOfMagnitudeRange.One.extract(n) ==== 789
      OrderOfMagnitudeRange.Thousand.extract(n) ==== 456
      OrderOfMagnitudeRange.Million.extract(n) ==== 123
      OrderOfMagnitudeRange.Thousand.extract(999) ==== 0
    }

  }

}
