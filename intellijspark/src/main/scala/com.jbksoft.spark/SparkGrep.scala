package com.bazaarvoice.spark

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

/**
 * Created by ashok.agarwal on 5/27/15.
 *
 * program argument: local[*] src/main/scala/com/bazaarvoice/spark/SparkGrep.scala val
 */
object SparkGrep {
  def main(args: Array[String]) {
    if (args.length < 3) {
      System.err.println("Usage: SparkGrep <host> <input_file> <match_term>")
      System.exit(1)
    }
    val conf = new SparkConf().setAppName("SparkGrep").setMaster(args(0))
    val sc = new SparkContext(conf)
    val inputFile = sc.textFile(args(1), 2).cache()
    val matchTerm : String = args(2)
    val numMatches = inputFile.filter(line => line.contains(matchTerm)).count()
    println("%s lines in %s contain %s".format(numMatches, args(1), matchTerm))
    System.exit(0)
  }
}