package com.bazaarvoice.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 9/29/14
 * Time: 2:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class BookAvroMR extends Configured implements Tool {

    public static void main(String[] args) throws Exception {
        int status = ToolRunner.run(new Configuration(), new BookAvroMR(), args);
        System.exit(status);
    }

    @Override
    public int run(String[] args)
            throws Exception {

        if (args.length != 2) {
            System.out.println("Usage: BookAvroMR <input_dir> <output_dir>");
            return -1;
        }

        Job job = new Job(getConf());
        job.setJarByClass(BookAvroMR.class);
        job.setJobName("book avro mapreduce job");

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));



        boolean success = job.waitForCompletion(true);
        return success ? 0 : 1;
    }
}
