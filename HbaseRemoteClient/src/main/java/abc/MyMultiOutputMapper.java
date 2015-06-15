package abc;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 9/11/14
 * Time: 11:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class MyMultiOutputMapper extends Mapper<LongWritable, Text, Text, Text> {
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        String line = value.toString();
        String[] tokens = line.split(",");
        context.write(new Text(tokens[1]), new Text(tokens[2]));
    }
}
