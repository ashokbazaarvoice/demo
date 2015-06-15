package com.jbksoft.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;

/**
 * Created by ashok.agarwal on 5/13/15.
 */
public class WordCount extends Configured implements Tool {

    public int run(String[] args) throws Exception {
        Configuration conf = getConf();
        Job job = Job.getInstance();

        return 0;
    }

    public static void main(String[] args) throws Exception {
        int status = ToolRunner.run(new Configuration(), new WordCount(), args);
        System.exit(status);
    }
}
