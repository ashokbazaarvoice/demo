package com.bazaarvoice.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 9/24/14
 * Time: 7:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class ToolRunnerDemo extends Configured implements Tool {
    public static void main(String[] args) throws Exception {
        for (String str : args) {
            System.out.println(str);
        }
        // -Dwordcount.case.sensitive=true will automatically set in config by toolrunner
        int res = ToolRunner.run(new Configuration(), new ToolRunnerDemo(), args);
        System.exit(res);
    }

    public int run(String[] args) throws Exception {

        Job job = new Job(getConf(), "wordcount");
        job.setJarByClass(WordCountCache.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        job.setMapperClass(WordCountCache.Map.class);
        job.setCombinerClass(WordCountCache.Reduce.class);
        job.setReducerClass(WordCountCache.Reduce.class);

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        return (job.waitForCompletion(true) ? 0 : 1);
    }
}
