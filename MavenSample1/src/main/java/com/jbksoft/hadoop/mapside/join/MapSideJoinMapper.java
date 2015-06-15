package com.jbksoft.hadoop.mapside.join;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 4/22/14
 * Time: 1:45 PM
 * To change this template use File | Settings | File Templates.
 */
import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.join.TupleWritable;

public class MapSideJoinMapper extends Mapper<Text, TupleWritable, NullWritable, Text> {

    private Text outValue = new Text();

    private StringBuilder valueBuilder = new StringBuilder();

    private String separator;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        separator = context.getConfiguration().get("separator");
    }


    @Override
    protected void map(Text key, TupleWritable value, Context context)
            throws IOException, InterruptedException {
        valueBuilder.append(key).append(separator);
        for (Writable writable : value) {
            valueBuilder.append(writable.toString()).append(separator);
        }
        valueBuilder.setLength(valueBuilder.length() - 1);
        outValue.set(valueBuilder.toString());
        context.write(NullWritable.get(), outValue);
        valueBuilder.setLength(0);
    }

}
