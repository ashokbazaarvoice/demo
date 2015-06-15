package com.jbksoft.hadoop.reduceside.join;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 4/22/14
 * Time: 1:49 PM
 * To change this template use File | Settings | File Templates.
 */
import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class ReduceSideJoinUsersLikesMapper extends Mapper<LongWritable, Text, Text, Text> {
    private final Text joinKey = new Text();
    private final Text outputValue = new Text();

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        System.out.println("ReduceSideJoinUsersLikesMapper : "+key.toString());
        System.out.println("ReduceSideJoinUsersLikesMapper : "+value.toString());
        joinKey.set(value.toString().split(",")[0]);
        outputValue.set("R" + value.toString());
        context.write(joinKey, outputValue);
    }

}