package spark.example

/**
 * Created by ashok.agarwal on 7/8/15.
 */
object Demo {
  def main(args: Array[String]): Unit ={
    val str: String = " Hello "
    val (myVar1: Int, myVar2: String) = Pair(40, "Foo")
    println( "Returned Value : " + addInt(5,7) );

    var x = 10;

    if( x == 10 ){
      println("Value of X is 10");
    }else if( x == 20 ){
      println("Value of X is 20");
    }else if( x == 30 ){
      println("Value of X is 30");
    }else{
      println("This is else statement");
    }
  }

  def addInt( a:Int, b:Int ) : Int = {
    var sum:Int = 0
    sum = a + b

    return sum
  }
}
