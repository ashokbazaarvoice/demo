package com.jbksoft.hadoop;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 9/11/14
 * Time: 10:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class MyMultiOutputReducer extends Reducer<Text, Text, NullWritable, Text> {
    public MultipleOutputs<NullWritable, Text> mos;

    public void setup(Context context) {
        mos = new MultipleOutputs(context);
    }

    public void reduce(Text key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {
        for (Text value : values) {
            mos.write(NullWritable.get(), value, key.toString());
        }
    }

    protected void cleanup(Context context)
            throws IOException, InterruptedException {
        mos.close();
    }

}

