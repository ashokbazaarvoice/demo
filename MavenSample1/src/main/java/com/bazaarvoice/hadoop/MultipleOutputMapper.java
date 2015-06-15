package com.bazaarvoice.hadoop;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by ashok.agarwal on 2/24/14.
 */
public class MultipleOutputMapper extends Mapper<LongWritable, Text, Text, Text> {
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        String line = value.toString();
        String[] tokens = line.split("\t");
        context.write(new Text(tokens[1]), value);
    }
}