package com.jbksoft.hfile2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.HFileOutputFormat;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 6/26/14
 * Time: 3:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class HBaseDriverV2 {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        args = new GenericOptionsParser(conf, args).getRemainingArgs();

        conf.set("hbase.table.name", args[2]);

        Job job = new Job(conf, "HBase Bulk Import Example");
        job.setJarByClass(HBaseDriverV2.class);

        Scan scan = new Scan();
        scan.addFamily(Bytes.toBytes("cf"));
        scan.setCaching(10000);  // 1 is the default in Scan, which will be bad for MapReduce jobs
        scan.setCacheBlocks(false);

        TableMapReduceUtil.initTableMapperJob(
                args[0],
                scan,
                HBaseKVMapperV2.class,
                ImmutableBytesWritable.class,
                KeyValue.class,
                job);


        HTable hTable = new HTable(args[2]);

        // Auto configure partitioner and reducer
        HFileOutputFormat.configureIncrementalLoad(job, hTable);

        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.waitForCompletion(true);
    }
}
