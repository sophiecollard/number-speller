package model

import org.specs2.mutable.Specification


class NumberSpellerSpec extends Specification {

  "NumberSpeller" should {

    "only accept numbers between 0 and 999,999,999 in its constructor" in {
      NumberSpeller(-1) must throwA[IllegalArgumentException]
      NumberSpeller(0) must not(throwA[IllegalArgumentException])
      NumberSpeller(999999999) must not(throwA[IllegalArgumentException])
      NumberSpeller(Math.pow(10, 9).toInt) must throwA[IllegalArgumentException]
    }

    "spell out single-digit numbers" in {

      "0" in {
        NumberSpeller(0).spell must beRight("zero")
      }

      "8" in {
        NumberSpeller(8).spell must beRight("eight")
      }

    }

    "spell out teens" in {

      "13" in {
        NumberSpeller(13).spell must beRight("thirteen")
      }

    }

    "spell out two-digit numbers greater than 19" in {

      "20" in {
        NumberSpeller(20).spell must beRight("twenty")
      }

      "41" in {
        NumberSpeller(41).spell must beRight("fourty one")
      }

    }

    "spell out three-digit numbers" in {

      "100" in {
        NumberSpeller(100).spell must beRight("one hundred")
      }

      "105" in {
        NumberSpeller(105).spell must beRight("one hundred and five")
      }

      "200" in {
        NumberSpeller(200).spell must beRight("two hundred")
      }

      "314" in {
        NumberSpeller(314).spell must beRight("three hundred and fourteen")
      }

    }

    "spell out numbers between 1,000 and 999,999,999" in {

      "1789" in {
        NumberSpeller(1789).spell must beRight("one thousand seven hundred and eighty nine")
      }

      "2000" in {
        NumberSpeller(2000).spell must beRight("two thousand")
      }

      "945,781" in {
        NumberSpeller(945781).spell must beRight("nine hundred and fourty five thousand seven hundred and eighty one")
      }

      "123,456,789" in {
        NumberSpeller(123456789).spell must beRight {
          "one hundred and twenty three million four hundred and fifty six thousand seven hundred and eighty nine"
        }
      }

    }

  }

}
