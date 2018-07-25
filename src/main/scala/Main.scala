import model.NumberSpeller

import scala.io.StdIn


object Main {

  def main(args: Array[String]): Unit = {
    println("Please provide a number between 0 and 999,999,999")
    val number = StdIn.readInt()

    try {
      val spelledNumber = NumberSpeller(number).spell
      println(s"$number is spelled '$spelledNumber'")
    } catch {
      case e: IllegalArgumentException =>
        println(e.getMessage)
    }
  }

}
