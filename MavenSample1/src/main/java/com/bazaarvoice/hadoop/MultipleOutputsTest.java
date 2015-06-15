package com.bazaarvoice.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.SnappyCodec;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.LazyOutputFormat;

import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;

public class MultipleOutputsTest {

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Path inputDir = new Path("/Users/ashok.agarwal/dev/github/MavenSample1/inputSimple");
        Path outputDir = new Path("/Users/ashok.agarwal/dev/github/MavenSample1/outputSimple2");

        Configuration conf = new Configuration();
//        conf.set("mapred.map.child.java.opts", "-Djava.library.path='/Users/ashok.agarwal/dev-tools/hadoop-2.0.0-mr1-cdh4.1.2/lib/native/'");
//
//        conf.setBoolean("mapred.compress.map.output", true);
//        conf.set("mapred.map.output.compression.codec", "org.apache.hadoop.io.compress.SnappyCodec");
//
//        conf.setBoolean("mapred.output.compress", true);
//        conf.set("mapred.output.compression.type", "FILE");
//        conf.setClass("mapred.output.compression.codec", SnappyCodec.class,
//                CompressionCodec.class);

        // conf.set("csv.extension",".csv");

        Job job = new Job(conf);
        job.setJarByClass(MultipleOutputsTest.class);
        job.setJobName("MultipleOutputs Test");

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        job.setMapperClass(MultipleOutputMapper.class);
        job.setReducerClass(MultipleOutputReducer.class);

        FileInputFormat.setInputPaths(job, inputDir);
        FileOutputFormat.setOutputPath(job, outputDir);

        LazyOutputFormat.setOutputFormatClass(job, BVTextOuputFormat.class);

        job.waitForCompletion(true);
    }
}
