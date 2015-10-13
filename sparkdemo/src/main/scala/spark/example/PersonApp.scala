package spark.example

/**
 * Created by ashok.agarwal on 7/8/15.
 */
object PersonApp {
  def main (args: Array[String]) {
    val johnSmith = new Person("John", "Smith", 37, "linguist")
    val janeDoe = new Person("Jane", "Doe", 34, "computer scientist")
    val johnDoe = new Person("John", "Doe", 43, "philosopher")
    val johnBrown = new Person("John", "Brown", 28, "mathematician")

    val people = List(johnSmith, janeDoe, johnDoe, johnBrown)
    people.foreach(person => println(person.greet(true)))
  }
}
