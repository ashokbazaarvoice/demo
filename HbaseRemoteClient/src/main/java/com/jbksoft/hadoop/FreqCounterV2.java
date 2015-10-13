package com.jbksoft.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.FirstKeyOnlyFilter;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.*;
import org.apache.hadoop.hbase.util.Base64;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Job;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;


public class FreqCounterV2 {

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

        conf.set("hbase.mapreduce.multipleinputtables.mappers","access_logs_1;com.jbksoft.hadoop.Mapper1,access_logs_2;com.jbksoft.hadoop.Mapper1");
        conf.set("hbase.mapreduce.multipleinputtables.formats", "access_logs_1;org.apache.hadoop.hbase.mapreduce.TableInputFormat,access_logs_2;org.apache.hadoop.hbase.mapreduce.TableInputFormat");

        Job job = new Job(conf, "Hbase_FreqCounter1");
        job.setJarByClass(FreqCounterV2.class);

        //MultiTableInputFormat

        Scan scan = new Scan();
        String columns = "details"; // comma seperated
        scan.addColumn(Bytes.toBytes(columns), Bytes.toBytes("page"));
        scan.setFilter(new FirstKeyOnlyFilter());
//        DelegatingInputFormat
        job.setInputFormatClass(DelegatingDBInputFormat.class);
        job.setMapperClass(DelegatingDBMapper.class);


//        job.setInputFormatClass(TableInputFormat.class);
        job.setMapOutputKeyClass(ImmutableBytesWritable.class);
        job.setMapOutputValueClass(IntWritable.class);
//        job.setMapperClass(com.jbksoft.hadoop.Mapper1.class);

        conf = job.getConfiguration();
        HBaseConfiguration.merge(conf, HBaseConfiguration.create(conf));
        //conf.set("hbase.mapreduce.inputtable", "access_logs");
        //conf.set("hbase.mapreduce.scan", convertScanToString(scan));

        job.setOutputFormatClass(TableOutputFormat.class);
        job.setReducerClass(Reducer1.class);
        conf.set("hbase.mapred.outputtable", "summary_user");
        job.setOutputKeyClass(ImmutableBytesWritable.class);
        job.setOutputValueClass(Writable.class);

        System.exit(job.waitForCompletion(true) ? 0 : 1);
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