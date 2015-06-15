import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 10/8/14
 * Time: 11:54 AM
 * To change this template use File | Settings | File Templates.
 */
object SyndicationRelation {

  def main(args: Array[String]){

    val sc = new SparkContext(new SparkConf().setAppName("Syndication Relation"))

    val relation = sc.textFile(args(0)).map { line =>
      val fields = line.split(",")
      if (fields.length > 11) {
        // format: (cid,pid,srcCid)
        (fields(1)+","+fields(2), fields(4), fields(10))
      }
    }

    relation.saveAsTextFile(args(1))

//    val inputfile = sc.textFile(args(0))


    //val relation = inputfile.map(line => line.split(",")).map(line => ((line(1).toString + "/"+ line(2).toString), line(4).toString)).reduceByKey((a, b) => a +","+ b)

//    val wordCounts = inputfile.flatMap(line => line.split(" "))
//                          .map(word => (word, 1))
//                           .reduceByKey((a, b) => a + b)

    //inputfile.map(l => l.split(",")).map(l => (l(1)+"/"+l(2), l(4))).reduceByKey(_+_, 40)


//    val relation_1 = sc.textFile(args(0)).map { line =>
//      val fields = line.split(",")
//      if (fields.length > 10) {
//        // format: (cid,pid,srcCid)
//        (fields(1)+","+fields(2), fields(4), fields(10))
//      }
//    }.collect.toMap



  }

  def relate(rdd: RDD[String]): RDD[String]= {
    val enTuples = rdd.map(line => line.split(","))
    enTuples.map(line => ((line(1).toString + "/"+ line(2).toString)+line(4).toString))
  }

  def doStuff(rdd: RDD[String]): RDD[String] = { rdd.map(x => "prefix" + x) }

  def func1(s: String): String = {
    s.toLowerCase()
  }

  def doStuff_func1(rdd: RDD[String]): RDD[String] = { rdd.map(func1) }



}
