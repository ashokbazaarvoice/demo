package com.bazaarvoice.hadoop;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

import java.io.IOException;

/**
 * Created by ashok.agarwal on 2/24/14.
 */
public class MultipleOutputReducer extends Reducer<Text, Text, NullWritable, Text> {
    MultipleOutputs<NullWritable, Text> mos;

    public void setup(Context context) {
        mos = new MultipleOutputs(context);
    }

    public void reduce(Text key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {
        for (Text value : values) {
            String str = value.toString();
            String[] items = str.split("\t");
            mos.write(NullWritable.get(), new Text(items[1]), "fruit/fruit");
            mos.write(NullWritable.get(), new Text(items[2]), "color/color");
        }
    }

    protected void cleanup(Context context)
            throws IOException, InterruptedException {
        mos.close();
    }
}