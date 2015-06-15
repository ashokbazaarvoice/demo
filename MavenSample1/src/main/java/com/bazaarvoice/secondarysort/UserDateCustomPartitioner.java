package com.bazaarvoice.secondarysort;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 6/26/14
 * Time: 10:32 PM
 * To change this template use File | Settings | File Templates.
 */
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;

public class UserDateCustomPartitioner extends Partitioner<UserDate, Text> {

   // HashPartitioner<Text, Text> hashPartitioner = new HashPartitioner<Text, Text>();
    Text newKey = new Text();

    @Override
    public int getPartition(UserDate key, Text value, int numReduceTasks) {
        System.out.println("### in UserDateCustomPartitioner ");
        try {
            // Execute the default partitioner over the first part of the key
            newKey.set(key.getUserId());
            //return hashPartitioner.getPartition(newKey, value, numReduceTasks);
            return (newKey.hashCode() & Integer.MAX_VALUE) % numReduceTasks;
        } catch (Exception e) {
            e.printStackTrace();
            return (int) (Math.random() * numReduceTasks); // this would return
            // a random value in
            // the range
            // [0,numReduceTasks)
        }
    }

}
