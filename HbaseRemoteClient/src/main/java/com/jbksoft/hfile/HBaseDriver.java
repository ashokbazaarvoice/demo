package com.jbksoft.hfile;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.HFileOutputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 6/26/14
 * Time: 3:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class HBaseDriver {
    public static void main(String[] args) throws Exception {
        Configuration conf = HBaseConfiguration.create();
        args = new GenericOptionsParser(conf, args).getRemainingArgs();

        // Load hbase-site.xml
        //HBaseConfiguration.addHbaseResources(conf);

//        conf.set("hbase.table.name", "hfile_test");

        Job job = new Job(conf, "HBase Bulk Import Example");
        job.setJarByClass(HBaseDriver.class);

        job.setMapperClass(HBaseKVMapper.class);
        job.setMapOutputKeyClass(ImmutableBytesWritable.class);
        job.setMapOutputValueClass(KeyValue.class);

        job.setInputFormatClass(TextInputFormat.class);

        HTable hTable = new HTable("hfile_test");

        // Auto configure partitioner and reducer
        HFileOutputFormat.configureIncrementalLoad(job, hTable);

//        job.setPartitionerClass(TotalOrderPartitioner.class);
//        job.setOutputKeyClass(ImmutableBytesWritable.class);
//        job.setOutputValueClass(KeyValue.class);
//        job.setOutputFormatClass(HFileOutputFormat.class);
//        job.setReducerClass(KeyValueSortReducer.class);

//        FileInputFormat.addInputPath(job, new Path("/Users/ashok.agarwal/dev/MavenSample1/inputforjoin/employee.txt"));
//        FileOutputFormat.setOutputPath(job, new Path(" HBaseTest1/output"));

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[0]));

        job.waitForCompletion(true);

//        job = new Job(conf);
//        FileInputFormat.addInputPath(job, new Path(args[1]));
//        HFileOutputFormat.configureIncrementalLoad(job, hTable);
//        job.waitForCompletion(true);
    }
}
