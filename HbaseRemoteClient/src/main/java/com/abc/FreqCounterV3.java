package com.abc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.MultiTableInputFormat;
import org.apache.hadoop.hbase.mapreduce.TableOutputFormat;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.hbase.util.Base64;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Job;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FreqCounterV3 {

    public static class Reducer1 extends TableReducer<ImmutableBytesWritable, IntWritable, ImmutableBytesWritable> {

        public void reduce(ImmutableBytesWritable key, Iterable<IntWritable> values, Context context)
                throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable val : values) {
                sum += val.get();
            }

            Put put = new Put(key.get());
            put.add(Bytes.toBytes("details"), Bytes.toBytes("total"), Bytes.toBytes(sum));
            System.out.println(String.format("stats :   key : %d,  count : %d", Bytes.toInt(key.get()), sum));
            context.write(key, put);
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();

        Job job = new Job(conf, "Hbase_FreqCounter1");
        job.setJarByClass(FreqCounterV3.class);

        List<Scan> scans = new ArrayList<Scan>();

        Scan scan1 = new Scan();
        scan1.setAttribute(Scan.SCAN_ATTRIBUTES_TABLE_NAME, "access_logs_1".getBytes());
        scans.add(scan1);

        Scan scan2 = new Scan();
        scan2.setAttribute(Scan.SCAN_ATTRIBUTES_TABLE_NAME, "access_logs_2".getBytes());
        scans.add(scan2);

        job.setInputFormatClass(MultiTableInputFormat.class);
        job.setMapperClass(Mapper1.class);

        job.setMapOutputKeyClass(ImmutableBytesWritable.class);
        job.setMapOutputValueClass(IntWritable.class);

        conf = job.getConfiguration();

        List<String> scanStrings = new ArrayList();

        for (Scan scan : scans) {
            String strScan = convertScanToString(scan);
            System.out.println(strScan);
            scanStrings.add(strScan);
        }
        conf.setStrings("hbase.mapreduce.scans", (String[])scanStrings.toArray(new String[scanStrings.size()]));

        HBaseConfiguration.merge(conf, HBaseConfiguration.create(conf));

//        TableMapReduceUtil.initTableMapperJob(scans, com.abc.Mapper1.class, ImmutableBytesWritable.class,
//                IntWritable.class, job);
//
//        TableMapReduceUtil.initTableReducerJob("summary_user", Reducer1.class, job);

        job.setOutputFormatClass(TableOutputFormat.class);
        job.setReducerClass(Reducer1.class);
        conf.set("hbase.mapred.outputtable", "summary_user");
        job.setOutputKeyClass(ImmutableBytesWritable.class);
        job.setOutputValueClass(Writable.class);

       // System.exit(job.waitForCompletion(true) ? 0 : 1);
    }

    static String convertScanToString(Scan scan)
            throws IOException
    {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(out);
        scan.write(dos);
        return Base64.encodeBytes(out.toByteArray());
    }

}