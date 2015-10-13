package spark.example

import java.io.StringWriter

import _root_.au.com.bytecode.opencsv.CSVWriter
import com.fasterxml.jackson.databind.{JsonNode, ObjectMapper}
import org.apache.spark.{SparkContext, SparkConf}

import scala.collection.mutable

/**
 * Created by ashok.agarwal on 7/8/15.
 */
object CountBrands {
  def main(args: Array[String]): Unit = {
    val file = "s3n://emodb-us-east-1/stash/cert/2015-06-23-00-00-00/review-kohls/review-kohls-*.json.gz"
    val conf = new SparkConf().setAppName("BVIO extract reviews").setMaster("local")
    val outpath = "review-sample-extracted.csv"

    //    val file = args(0)
    //    val conf = new SparkConf().setAppName("Ashok BVIO extract reviews")
    //    val outpath = args(1)
    val mapper = new ObjectMapper()
    //    mapper.registerModule(DefaultScalaModule)

    /**
     * Performance experiements
     */
    // 1. Using Kryo Serializer to improve performance
    //conf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    // 2. 2-3 tasks per core, has 8 nodes, each nodes has 8 core so set this to 8*8*3 = 192 or 8*8*2 = 128
    //conf.set("spark.default.parallelism", "org.apache.spark.serializer.KryoSerializer")
    // 3. Akka threads for communication, default is 4
    //conf.set("spark.akka.threads", "8")

    val sc = new SparkContext(conf)
    var data = sc.textFile(file).cache()
    val parsedData = data.map(line => mapper.readTree(line))
      .filter(node => node.has("text") && node.has("contentCodes") && containsPF(node))
      .map(node => {
      // content codes
      //      val codes = new mutable.HashMap[String, String]
      //      if (node.has("contentCodes")) {
      //        val iterator = node.get("contentCodes").elements()
      //        while (iterator.hasNext) {
      //          val c = iterator.next().asText()
      //          codes += ((c, c))
      //        }
      //      }
      val sw: StringWriter = new StringWriter
      val writer: CSVWriter = new CSVWriter(sw, ',', '"', '\\', "")
      //      if (codes.contains("PF")) {
      val ss: Array[String] = Array(node.get("legacyInternalId").asText(),
        //(if (codes.contains("PF")) "PF" else "XX"),
        "PF",
        node.get("text").asText().replaceAll("[\r\n|\r]", " "), node.get("status").asText(), node.get("client").asText())
      writer.writeNext(ss)
      writer.flush
      sw.toString
      //      } else {
      //        return
      //      }
      //      node.get("legacyInternalId").asText() +
      //      (if (codes.contains("PF")) "PF" else "XX") +
      //      //StringEscapeUtils.escapeJava(node.get("text").asText()) +
      //      node.get("text").asText().replace("\t|\r|\n|\\r|\\n", " ") +
      //      node.get("status").asText() +
      //      node.get("client").asText() +
      //      codes.keySet.mkString(" ")
    }).filter(line => line.trim().length > 1).saveAsTextFile(outpath)
    //val tokenizedLines = parsedData.map(Tokenizer.tokenize).filter(_.nonEmpty)filter(lambda x: len(x) > 0).saveAsTextFile(outpath)

    //    val counts = parsedData.count()
    //    println("Counts: " + counts)

    //Console.withOut(new PrintStream("review-sample-extracted.csv", "UTF-8")){
    //parsedData.collect().foreach(l => println(l))}
    //    Console.withOut(new PrintStream("ThaiBrandCounts2014-all-cached-sorted.csv", "UTF-8")){counts.toSeq.sortBy(-_._2).foreach(x => println(x._1 + "\t" + x._2))}
  }

  def containsPF(node: JsonNode) : Boolean = {
    val codes = new mutable.HashMap[String, String]
    if (node.has("contentCodes")) {
      val iterator = node.get("contentCodes").elements()
      while (iterator.hasNext) {
        val c = iterator.next().asText()
        codes += ((c, c))
      }
    }
    return codes.contains("PF")
  }
}
