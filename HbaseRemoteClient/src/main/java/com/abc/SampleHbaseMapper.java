package com.abc;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 7/17/14
 * Time: 4:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class SampleHbaseMapper extends TableMapper<Text, IntWritable> {

    public void map(ImmutableBytesWritable row, Result value, Context context) {
        // Your mapper-code
    }
}