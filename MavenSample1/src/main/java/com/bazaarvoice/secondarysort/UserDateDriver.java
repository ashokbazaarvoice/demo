package com.bazaarvoice.secondarysort;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 6/26/14
 * Time: 10:25 PM
 * To change this template use File | Settings | File Templates.
 */
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class UserDateDriver {

    private static final Log log = LogFactory.getLog(UserDateDriver.class);

    public static void main(String[] args) throws Exception {

        final Configuration conf = new Configuration();

        args = new GenericOptionsParser(conf, args).getRemainingArgs();

        final Job job = new Job(conf,
                "Count the items from bugs bunny data on hdfs in \"input\" path.");

        job.setJarByClass(UserDateDriver.class);

        job.setMapperClass(UserDateMapper.class);

        // job.setCombinerClass(UserDateReducer.class);
        job.setReducerClass(UserDateReducer.class);

        job.setGroupingComparatorClass(UserDateGroupingComparator.class);
        job.setPartitionerClass(UserDateCustomPartitioner.class);
        job.setSortComparatorClass(UserDateComparator.class);

        job.setMapOutputKeyClass(UserDate.class);
        job.setMapOutputValueClass(Text.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        FileInputFormat.setInputPaths(job, new Path("inputUserItem"));
        FileOutputFormat.setOutputPath(job, new Path("output1"));

        // FileInputFormat.setInputPaths(job, new Path(args[0]));
        // FileOutputFormat.setOutputPath(job, new Path(args[1]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);

    }

}
