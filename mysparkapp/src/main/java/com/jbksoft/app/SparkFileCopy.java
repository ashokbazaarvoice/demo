package com.jbksoft.app;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 4/15/15
 * Time: 11:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class SparkFileCopy {

    public static void main(String[] args) throws Exception {

        if (args.length < 2) {
            System.err.println("Usage: SparkFileCopy <file> <file>");
            System.exit(1);
        }

        //-Dspark.master=spark://10.xxx.xxx.xxx:43191

        //SparkConf sparkConf = new SparkConf().setAppName("SparkFileCopy").setMaster("local");

        SparkConf sparkConf = new SparkConf().setAppName("SparkFileCopy");
        JavaSparkContext ctx = new JavaSparkContext(sparkConf);
        JavaRDD<String> lines = ctx.textFile(args[0]);
        lines.saveAsTextFile(args[1]);
        System.out.println("Copied file from " + args[0] + " to " + args[1]);
        ctx.stop();


//        sparkConf.set(TableInputFormat.INPUT_TABLE, "tablename");
//        JavaPairRDD<ImmutableBytesWritable, Result> data =
//        ctx.newAPIHadoopRDD(sparkConf, TableInputFormat.class,ImmutableBytesWritable.class, Result.class);
    }
}
