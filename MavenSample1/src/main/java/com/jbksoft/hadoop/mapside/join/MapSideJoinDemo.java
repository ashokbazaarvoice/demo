package com.jbksoft.hadoop.mapside.join;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 4/22/14
 * Time: 1:45 PM
 * To change this template use File | Settings | File Templates.
 */
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.KeyValueLineRecordReader;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.join.CompositeInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

//  inputforjoin/employee.txt inputforjoin/salary.txt compositejoin
public class MapSideJoinDemo {

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        String separator = ",";
        conf.set(KeyValueLineRecordReader.KEY_VALUE_SEPERATOR, separator);
        conf.set("separator", separator);

        Job job = Job.getInstance(conf, "CompositeJoin");
        job.setJarByClass(MapSideJoinDemo.class);

        Path joinP1 = new Path(args[0]);
        Path joinP2 = new Path(args[1]);
        Path out = new Path(args[2]);

        job.setInputFormatClass(CompositeInputFormat.class);
        job.setMapperClass(MapSideJoinMapper.class);
        job.getConfiguration().set(CompositeInputFormat.JOIN_EXPR,
                CompositeInputFormat.compose("inner", KeyValueTextInputFormat.class, joinP1, joinP2));
        job.setNumReduceTasks(0);
        TextOutputFormat.setOutputPath(job, out);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}