package com.bazaarvoice.hadoop;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 6/23/14
 * Time: 1:50 PM
 * To change this template use File | Settings | File Templates.
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class HadoopReducerIteratorTest {

    private static class MaxNumMapper extends Mapper<LongWritable, Text, Text, LongWritable> {
        @Override
        public void map(LongWritable pKey, Text pValue, Context pContext)
                throws IOException, InterruptedException {
            String line = pValue.toString();
            String[] tokens = line.split(" ");
            Long number = Long.parseLong(tokens[1]);
            pContext.write(new Text(tokens[0]), new LongWritable(number));
        }
    }

    private static class MaxNumReducerWrong1 extends Reducer<Text, LongWritable, Text, LongWritable> {
        @Override
        public void reduce(Text pKey, Iterable<LongWritable> pValues, Context pContext)
                throws IOException, InterruptedException {
            System.out.println("----------" + pKey + "----------");
            for (LongWritable aNum : pValues) {
                System.out.println("first iteration: " + aNum);
            }
            for (LongWritable aNum : pValues) {
                System.out.println("second iteration: " + aNum);
            }
        }
    }

    private static class MaxNumReducerWrong2 extends Reducer<Text, LongWritable, Text, LongWritable> {
        @Override
        public void reduce(Text pKey, Iterable<LongWritable> pValues, Context pContext)
                throws IOException, InterruptedException {
            System.out.println("----------" + pKey + "----------");
            ArrayList<LongWritable> cache = new ArrayList<LongWritable>();
            for (LongWritable aNum : pValues) {
                System.out.println("first iteration: " + aNum);
                cache.add(aNum);
            }
            int size = cache.size();
            for (int i = 0; i < size; ++i) {
                System.out.println("second iteration " + i + ": " + cache.get(i));
            }
        }
    }

    private static class MaxNumReducer extends Reducer<Text, LongWritable, Text, LongWritable> {
        @Override
        public void reduce(Text pKey, Iterable<LongWritable> pValues, Context pContext)
                throws IOException, InterruptedException {
            System.out.println("----------" + pKey + "----------");
            ArrayList<LongWritable> cache = new ArrayList<LongWritable>();
            for (LongWritable aNum : pValues) {
                System.out.println("first iteration: " + aNum);
                LongWritable writable = new LongWritable();
                writable.set(aNum.get());
                cache.add(writable);
            }
            int size = cache.size();
            for (int i = 0; i < size; ++i) {
                System.out.println("second iteration: " + cache.get(i));
            }
        }
    }

    private static class MaxNumReducer2 extends Reducer<Text, LongWritable, Text, LongWritable> {
        @Override
        public void reduce(Text pKey, Iterable<LongWritable> pValues, Context pContext)
                throws IOException, InterruptedException {
            System.out.println("----------" + pKey + "----------");
            ArrayList<LongWritable> cache = new ArrayList<LongWritable>();
            Iterator<LongWritable> iterator = pValues.iterator();
            while (iterator.hasNext()) {
                LongWritable writable = iterator.next();
                System.out.println("MaxNumReducer2 first iteration: " + writable);
                cache.add(new LongWritable(writable.get()));
            }
            int size = cache.size();
            for (int i = 0; i < size; ++i) {
                System.out.println("MaxNumReducer2 second iteration: " + cache.get(i));
            }
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("Number of arguments: " + args.length);
            System.out.println("Usage: MaxNumHadoop <input folder path> <output folder path>");
            System.exit(1);
        }
        Job job = new Job();
        job.setJarByClass(HadoopReducerIteratorTest.class);
        job.setJobName("Max Number of Year");

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setMapperClass(MaxNumMapper.class);
//        job.setReducerClass(MaxNumReducerWrong1.class);
//        job.setReducerClass(MaxNumReducerWrong2.class);
        job.setReducerClass(MaxNumReducer.class);
//        job.setReducerClass(MaxNumReducer2.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        System.exit(job.waitForCompletion(true)?0:1);
    }

}