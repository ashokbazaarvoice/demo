package abc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.LazyOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 9/11/14
 * Time: 11:29 AM
 * To change this template use File | Settings | File Templates.
 */
public class MyMultiOutputTest {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Path inputDir = new Path(args[0]);
        Path outputDir = new Path(args[1]);

        Configuration conf = new Configuration();

        Job job = new Job(conf);
        job.setJarByClass(MyMultiOutputTest.class);
        job.setJobName("My MultipleOutputs Demo");

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        job.setMapperClass(MyMultiOutputMapper.class);
        job.setReducerClass(MyMultiOutputReducer.class);

        FileInputFormat.setInputPaths(job, inputDir);
        FileOutputFormat.setOutputPath(job, outputDir);

        LazyOutputFormat.setOutputFormatClass(job, TextOutputFormat.class);

        job.waitForCompletion(true);
    }
}
