package spark.example

/**
 * Created by ashok.agarwal on 7/8/15.
 */
class Greetings {
  object Hello {
    def main (args: Array[String]) {
      println("Hello, world!")
    }
  }

  object Goodbye {
    def main (args: Array[String]) {
      println("Goodbye, world!")
    }
  }

  object SayIt {
    def main (args: Array[String]) {
      args.foreach(println)
    }
  }
}
