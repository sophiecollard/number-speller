import model.NumberSpeller

import scala.io.StdIn


object Main {

  def main(args: Array[String]): Unit = {
    while (true) {
      println("Please provide a number between 0 and 999,999,999")
      val number = StdIn.readInt()

      try {
        val spelledNumber = NumberSpeller(number).spell
        spelledNumber match {
          case Right(words) =>
            println(s"$number is spelled '$words'")
          case Left(e) =>
            println(e.getMessage)
        }
      } catch {
        case e: IllegalArgumentException =>
          println(e.getMessage)
      }
    }
  }

}
