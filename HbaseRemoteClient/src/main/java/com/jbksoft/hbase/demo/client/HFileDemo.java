package com.jbksoft.hbase.demo.client;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 6/26/14
 * Time: 2:52 PM
 * To change this template use File | Settings | File Templates.
 */

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class HFileDemo {
    private static class HFileMapper extends Mapper<ImmutableBytesWritable, KeyValue, Text, Text> {

        public void map(ImmutableBytesWritable key, KeyValue keyValue, Context context) throws IOException, InterruptedException {
            Text row = new Text(key.get());
            String column = Bytes.toString(keyValue.getQualifier());
            String value = Bytes.toString(keyValue.getValue());
            Text outputValue = new Text(column + ": " + value);
            context.write(row, outputValue);
        }
    }

    public static void main(String[] args) throws Exception {
        String path = "/Users/woody/hfile";
        Job job = new Job();
        job.setMapperClass(HFileMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setNumReduceTasks(0);
        //job.setInputFormatClass(HFileInputFormat.class);
        FileInputFormat.addInputPath(job, new Path(path));
        FileOutputFormat.setOutputPath(job, new Path("/tmp/hfile"));

        job.waitForCompletion(true);
    }
}

