package com.abc;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

/**
 * Created by ashok.agarwal on 6/14/15.
 */
public class SimpleApp {
    public static void main(String[] args) {
        String logFile = "/Users/ashok.agarwal/dev/simplesparkapp/data/inputfile.txt"; // Should be some file on your system
        JavaSparkContext sc = new JavaSparkContext("local", "Simple App",
                "/Users/ashok.agarwal/dev-tools/spark-1.1.0-bin-cdh4/bin", new String[]{"target/sparkwordcount-0.0.1-SNAPSHOT.jar"});
        JavaRDD<String> logData = sc.textFile(logFile).cache();

        long numAs = logData.filter(new Function<String, Boolean>() {
            public Boolean call(String s) { return s.contains("a"); }
        }).count();

        long numBs = logData.filter(new Function<String, Boolean>() {
            public Boolean call(String s) { return s.contains("b"); }
        }).count();

        System.out.println("Lines with a: " + numAs + ", lines with b: " + numBs);
    }
}
