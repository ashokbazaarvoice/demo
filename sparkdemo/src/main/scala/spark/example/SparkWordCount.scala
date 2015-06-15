package spark.example

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

object SparkWordCount {
  def main(args: Array[String]) {
    val logFile = "/Users/ashok.agarwal/dev/simplesparkapp/data/inputfile.txt"
    val sc = new SparkContext(new SparkConf().setAppName("Spark Count").setMaster("local"))
    //val threshold = args(1).toInt
    val threshold = 2

    // split each document into words
    val tokenized = sc.textFile(logFile, 2).cache().flatMap(_.split(" "))

    // count the occurrence of each word
    val wordCounts = tokenized.map((_, 1)).reduceByKey(_ + _)

    // filter out words with less than threshold occurrences
    val filtered = wordCounts.filter(_._2 >= threshold)

    // count characters
    val charCounts = filtered.flatMap(_._1.toCharArray).map((_, 1)).reduceByKey(_ + _)

    System.out.println(charCounts.collect().mkString(", "))
  }
}
