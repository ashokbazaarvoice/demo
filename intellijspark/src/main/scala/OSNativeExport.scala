import org.apache.avro.generic.GenericData
import org.apache.avro.mapred.AvroKey
import org.apache.avro.mapreduce.AvroKeyInputFormat
import org.apache.hadoop.io.{NullWritable, LongWritable, Text}
import org.apache.hadoop.mapreduce.Job
import org.apache.hadoop.mapreduce.lib.input.{TextInputFormat, FileInputFormat}
import org.apache.spark.{SparkContext, SparkConf}

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 10/7/14
 * Time: 3:16 PM
 * To change this template use File | Settings | File Templates.
 */
object OSNativeExport {

  def main(args : Array[String]){

    if (args.length < 1) {
      System.err.println("Usage: OSNativeExport <file>")
      System.exit(1)
    }

    val inPath = args(0)

    val sparkConf = new SparkConf().setAppName("Spark OS Native Export")
    val sc = new SparkContext(sparkConf)
//
//    val conf = new Job()
//    FileInputFormat.setInputPaths(conf, inPath)
//    val records = sc.newAPIHadoopRDD(conf.getConfiguration,
//      classOf[AvroKeyInputFormat[GenericData.Record]],
//      classOf[AvroKey[GenericData.Record]],
//      classOf[NullWritable])
//
//    val dstClientProductId = records.map(myMap)
//
////    val file = sc.textFile(inPath)
//
//
//  }
//
//  def myMap(x : GenericData.Record) : Int = {
//
//    val dstClientId = x._1.datum.get("cid");
//    val dstClientProductId = x._1.datum.get("cid")+"/"+x._1.datum.get("pid");
//
////    if (newChildren.length == 0) {
////      Literal(null, e.dataType)
////    } else if (newChildren.length == 1) {
////      newChildren(0)
////    } else {
////      Coalesce(newChildren)
////    }
//  }
}
