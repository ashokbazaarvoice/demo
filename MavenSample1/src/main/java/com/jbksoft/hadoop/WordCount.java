package com.jbksoft.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.IOException;

/**
 * Created by ashok.agarwal on 2/21/14.
 * inputforcache/input outputforcache
 */
public class WordCount {

    static enum Counters {
        INPUT_WORDS
    }

    static public class WordCountMapper extends
            Mapper<LongWritable, Text, Text, LongWritable> {
        final private static LongWritable ONE = new LongWritable(1);
        private Text tokenValue = new Text();

        @Override
        protected void map(LongWritable offset, Text text, Context context)
                throws IOException, InterruptedException {
            System.out.println("Hello for all from map");
            for (String token : text.toString().split("\\s+")) {
                tokenValue.set(token);
                context.write(tokenValue, ONE);
                context.getCounter(Counters.INPUT_WORDS).increment(1);
            }
        }
    }

    static public class WordCountReducer extends
            Reducer<Text, LongWritable, Text, LongWritable> {
        private LongWritable total = new LongWritable();

        long totalWords = 0;

        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            //below will not give correct counter value
            totalWords = context.getCounter(Counters.INPUT_WORDS).getValue();
            System.out.println("$$$$$$ Configure "+totalWords);
        }

        @Override
        protected void reduce(Text token, Iterable<LongWritable> counts,
                              Context context) throws IOException, InterruptedException {
            System.out.println("Hello for all from reduce");
            System.out.println("$$$$$$ reduce "+totalWords);
            long n = 0;
            for (LongWritable count : counts)
                n += count.get();
            total.set(n);
            context.write(token, total);
        }
    }

    public static void main(String[] args) throws Exception {
        final Configuration conf = new Configuration();

        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        if (otherArgs.length != 2)
        {
            System.err.println("Usage: wordcount <inputFile> <outputDir>");
            System.exit(2);
        }

        System.out.println("Conf: " + conf);

        final Job job = new Job(conf, "word count");
        job.setJarByClass(WordCount.class);

        job.setMapperClass(WordCountMapper.class);
        job.setCombinerClass(WordCountReducer.class);
        job.setReducerClass(WordCountReducer.class);

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        FileInputFormat.setInputPaths(job, new Path(otherArgs[0]));
        FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));

        // job.submit(); But this wont wait for the job to complete.
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }

    /**
     * Created by ashok.agarwal on 2/24/14.
     */
//    public static class MyTextOutputFormat extends FileOutputFormat<WritableComparable<?>, Writable> {
//        @Override
//        public org.apache.hadoop.mapreduce.RecordWriter<WritableComparable<?>, Writable> getRecordWriter(TaskAttemptContext arg0) throws IOException, InterruptedException {
//            //get the current path
//            Path path = FileOutputFormat.getOutputPath(arg0);
//            //create the full path with the output directory plus our filename
//            Path fullPath = new Path(path, "result.txt");
//
//            //create the file in the file system
//            FileSystem fs = path.getFileSystem(arg0.getConfiguration());
//            FSDataOutputStream fileOut = fs.create(fullPath, arg0);
//
//            //create our record writer with the new file
//            return new MyCustomRecordWriter(fileOut);
//        }
//    }
}
