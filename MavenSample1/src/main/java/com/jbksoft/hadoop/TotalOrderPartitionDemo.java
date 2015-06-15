package com.jbksoft.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.lib.partition.InputSampler;
import org.apache.hadoop.mapreduce.lib.partition.TotalOrderPartitioner;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 9/12/14
 * Time: 10:45 AM
 * To change this template use File | Settings | File Templates.
 */
public class TotalOrderPartitionDemo {

    private static final Logger LOG = Logger.getLogger(TotalOrderPartitionDemo.class);

    public static void main(String args[]) {
        try {

            int numReduceTasks = 2;

            Configuration conf = new Configuration();
            Job job = new Job(conf, "DictionarySorter");
            job.setJarByClass(TotalOrderPartitionDemo.class);

            job.setMapperClass(SortMapper.class);
            job.setReducerClass(SortReducer.class);

            job.setNumReduceTasks(numReduceTasks);
            job.setInputFormatClass(TextInputFormat.class);
            job.setOutputFormatClass(TextOutputFormat.class);

            job.setOutputKeyClass(LongWritable.class);
            job.setOutputValueClass(Text.class);
            job.setSortComparatorClass(SortKeyComparator.class);

            FileInputFormat.setInputPaths(job, args[0]);
            FileOutputFormat.setOutputPath(job, new Path(args[2]
                    + ".dictionary.sorted." + getCurrentDateTime()));
            job.setPartitionerClass(TotalOrderPartitioner.class);

            Path inputDir = new Path(args[1]);
            Path partitionFile = new Path(inputDir, "partitioning");
            TotalOrderPartitioner.setPartitionFile(job.getConfiguration(),
                    partitionFile);

            double pcnt = 10.0;
            int numSamples = numReduceTasks;
            int maxSplits = numReduceTasks - 1;
            if (0 >= maxSplits) {
                maxSplits = Integer.MAX_VALUE;
            }

            InputSampler.Sampler sampler = new InputSampler.RandomSampler(pcnt,
                    numSamples, maxSplits);
            InputSampler.writePartitionFile(job, sampler);


            job.waitForCompletion(true);
        } catch (InterruptedException ex) {
            LOG.error(ex);
        } catch (ClassNotFoundException ex) {
            LOG.error(ex);
        } catch (IOException ex) {
            LOG.error(ex);
        }
    }

    private static String getCurrentDateTime() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        return sdf.format(d);
    }

    class SortMapper extends Mapper<LongWritable, Text, LongWritable, Text> {

        @Override
        protected void map(LongWritable key, Text value, Context context)
                throws IOException, InterruptedException {
            String val = value.toString();
            if (val != null && !val.isEmpty() && val.length() >= 5) {
                String[] splits = val.split(",");
                context.write(new LongWritable(Long.parseLong(splits[1])),
                        new Text(splits[0] + "," + splits[2]));
            }
        }

    }

    class SortReducer extends
            Reducer<LongWritable, Text, Text, LongWritable> {

        @Override
        protected void reduce(LongWritable key, Iterable<Text> value, Context context)
                throws IOException, InterruptedException {
            for(Text val : value) {
                context.write(new Text(val + "," + key), null);
            }
        }

    }

    class SortKeyComparator extends WritableComparator {

        protected SortKeyComparator() {
            super(LongWritable.class, true);
        }

        /**
         * Compares in the descending order of the keys.
         */
        @Override
        public int compare(WritableComparable a, WritableComparable b) {
            LongWritable o1 = (LongWritable) a;
            LongWritable o2 = (LongWritable) b;
            if(o1.get() < o2.get()) {
                return 1;
            }else if(o1.get() > o2.get()) {
                return -1;
            }else {
                return 0;
            }
        }

    }


}
