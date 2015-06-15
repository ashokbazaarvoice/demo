package com.bazaarvoice.hadoop;

import java.io.IOException;
import java.util.*;

import org.apache.avro.Schema;
import org.apache.avro.Schema.Type;
import org.apache.avro.mapred.AvroWrapper;
import org.apache.avro.mapred.Pair;
import org.apache.avro.mapreduce.AvroJob;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * The classic WordCount example modified to output Avro Pair<CharSequence,
 * Integer> records instead of text.
 */




/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 9/29/14
 * Time: 2:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class WordCountAvro extends Configured implements Tool {

    public static class Map
            extends Mapper<LongWritable, Text, Text, IntWritable> {

        private final static IntWritable one = new IntWritable(1);
        private Text word = new Text();

        public void map(LongWritable key, Text value, Context context)
                throws IOException, InterruptedException {
            String line = value.toString();
            StringTokenizer tokenizer = new StringTokenizer(line);
            while (tokenizer.hasMoreTokens()) {
                word.set(tokenizer.nextToken());
                context.write(word, one);
            }
        }
    }

    public static class Reduce
            extends Reducer<Text, IntWritable,
            AvroWrapper<Pair<CharSequence, Integer>>, NullWritable> {

        public void reduce(Text key, Iterable<IntWritable> values,
                           Context context)
                throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable value : values) {
                sum += value.get();
            }
            context.write(new AvroWrapper<Pair<CharSequence, Integer>>
                    (new Pair<CharSequence, Integer>(key.toString(), sum)),
                    NullWritable.get());
        }
    }

    public int run(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Usage: AvroWordCount <input path> <output path>");
            return -1;
        }

        Job job = new Job(getConf());
        job.setJarByClass(WordCountAvro.class);
        job.setJobName("wordcount");

        // We call setOutputSchema first so we can override the configuration
        // parameters it sets
        AvroJob.setOutputKeySchema(job,
                Pair.getPairSchema(Schema.create(Type.STRING),
                        Schema.create(Type.INT)));
        job.setOutputValueClass(NullWritable.class);

        job.setMapperClass(Map.class);
        job.setReducerClass(Reduce.class);

        job.setInputFormatClass(TextInputFormat.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setSortComparatorClass(Text.Comparator.class);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.waitForCompletion(true);

        return 0;
    }

    public static void main(String[] args) throws Exception {
        int res =
                ToolRunner.run(new Configuration(), new WordCountAvro(), args);
        System.exit(res);
    }
}
