package com.jbksoft.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.io.compress.SplittableCompressionCodec;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.LineRecordReader;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class MyInputFormatDemo {
    /**
     * The map class of WordCount.
     */
    public static class TokenCounterMapper extends
            Mapper<Object, Text, Text, IntWritable> {

        private final static IntWritable one = new IntWritable(1);
        private Text word = new Text();

        public void map(Object key, Text value, Context context)
                throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(value.toString());
            while (itr.hasMoreTokens()) {
                word.set(itr.nextToken());
                context.write(word, one);
            }
        }
    }

    /**
     * The reducer class of WordCount
     */
    public static class TokenCounterReducer extends
            Reducer<Text, IntWritable, Text, IntWritable> {
        public void reduce(Text key, Iterable<IntWritable> values,
                           Context context) throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable value : values) {
                sum += value.get();
            }
            context.write(key, new IntWritable(sum));
        }
    }

    /**
     * The main entry point.
     */
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        System.out.println("args: "+Arrays.toString(args));
        String[] otherArgs = new GenericOptionsParser(conf, args)
                .getRemainingArgs();
        Job job = new Job(conf, "Example Hadoop 0.20.1 WordCount");
        job.setJarByClass(MyInputFormatDemo.class);

        job.setMapperClass(TokenCounterMapper.class);
        job.setReducerClass(TokenCounterReducer.class);

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

//        job.setNumReduceTasks(2);
//        The number of map and reduce slots on each TaskTracker node is controlled by the mapreduce.tasktracker.map.tasks.maximum
// and mapreduce.tasktracker.reduce.tasks.maximum Hadoop properties in the mapred-site.xml file. These parameters
// define the maximum number of concurrently occupied slots on a TaskTracker node and determine the degree of concurrency on each TaskTracker.


//        mapreduce.tasktracker.map.tasks.maximum: <%= Math.min(Math.ceil(numOfCores * 1.0),Math.ceil(maxPartition*0.66*totalMem/1000)) %>
//        mapreduce.tasktracker.reduce.tasks.maximum: <%= Math.min(Math.ceil(numOfCores * 0.5),Math.ceil(maxPartition*0.33*totalMem/1000)) %>
        
        System.out.println("otherArgs: "+Arrays.toString(otherArgs));

        FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
        FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }

}

class TextInputFormat extends FileInputFormat<LongWritable, Text> {

    @Override
    public RecordReader<LongWritable, Text>
    createRecordReader(InputSplit split,
                       TaskAttemptContext context) {
        String delimiter = context.getConfiguration().get(
                "textinputformat.record.delimiter");
        byte[] recordDelimiterBytes = null;
        if (null != delimiter)
            recordDelimiterBytes = delimiter.getBytes();
        return new LineRecordReader(recordDelimiterBytes);
    }

    @Override
    protected boolean isSplitable(JobContext context, Path file) {
        final CompressionCodec codec =
                new CompressionCodecFactory(context.getConfiguration()).getCodec(file);
        if (null == codec) {
            return true;
        }
        return codec instanceof SplittableCompressionCodec;
    }

    @Override
    public java.util.List<InputSplit> getSplits(JobContext job) throws IOException {
        long minSize = Math.max(getFormatMinSplitSize(), getMinSplitSize(job));
        long maxSize = getMaxSplitSize(job);

        // generate splits
        List<InputSplit> splits = new ArrayList<InputSplit>();
        List<FileStatus> files = listStatus(job);
        for (FileStatus file: files) {
            Path path = file.getPath();
            long length = file.getLen();
            if (length != 0) {
                FileSystem fs = path.getFileSystem(job.getConfiguration());
                BlockLocation[] blkLocations = fs.getFileBlockLocations(file, 0, length);
                if (isSplitable(job, path)) {
                    long blockSize = file.getBlockSize();
                    long splitSize = computeSplitSize(blockSize, minSize, maxSize);

                    long bytesRemaining = length;
                    System.out.println(bytesRemaining+" "+splitSize+ " "+((double) bytesRemaining)/splitSize);
                    while (((double) bytesRemaining)/splitSize > 1.1) {
                        int blkIndex = getBlockIndex(blkLocations, length-bytesRemaining);
                        System.out.println(blkIndex);
                        splits.add(makeSplit(path, length-bytesRemaining, splitSize,
                                blkLocations[blkIndex].getHosts()));
                        bytesRemaining -= splitSize;
                    }

                    if (bytesRemaining != 0) {
                        int blkIndex = getBlockIndex(blkLocations, length-bytesRemaining);
                        splits.add(makeSplit(path, length-bytesRemaining, bytesRemaining,
                                blkLocations[blkIndex].getHosts()));
                    }
                } else { // not splitable
                    splits.add(makeSplit(path, 0, length, blkLocations[0].getHosts()));
                }
            } else {
                //Create empty hosts array for zero length files
                splits.add(makeSplit(path, 0, length, new String[0]));
            }
        }
        // Save the number of input files for metrics/loadgen
        job.getConfiguration().setLong(NUM_INPUT_FILES, files.size());
        //LOG.debug("Total # of splits: " + splits.size());
        System.out.println("Total # of splits: " + splits.size());
        return splits;
    }

}