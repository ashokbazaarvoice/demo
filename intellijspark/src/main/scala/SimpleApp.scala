/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 10/8/14
 * Time: 4:49 PM
 * To change this template use File | Settings | File Templates.
 */

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

object SimpleApp {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("Simple Hello World Application")
    val sc = new SparkContext(conf)
    val file = sc.textFile("hdfs://ip-10-102-39-193.qa.us-west-2.nexus:9000/user/aagarwal/demo1/input").cache()
    //val counts = file.flatMap(lambda line: line.split("\\s+")).map(lambda word: (word, 1)).reduceByKey(lambda a, b: a + b)
    val counts = file.flatMap(line => line.split(" ")).map(word => (word, 1)).reduceByKey(_+_)
    counts.saveAsTextFile("hdfs://ip-10-102-39-193.qa.us-west-2.nexus:9000/user/aagarwal/demo1/output")
  }
}
