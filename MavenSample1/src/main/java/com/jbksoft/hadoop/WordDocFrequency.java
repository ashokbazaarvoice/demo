package com.jbksoft.hadoop;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.DefaultCodec;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;


public class WordDocFrequency {

    public static enum MATCH_COUNTER {
        TOTAL_WORD_DOC
    };

    /**
     * Phase 1 Start: Map->Key<Word>,Value<Int> ->Reducer->Key<Word>,Sum(val)
     *
     * @author Pooja
     *
     */
    public static class MapWordCount extends
            Mapper<LongWritable, Text, Text, IntWritable> {
        private final static IntWritable one = new IntWritable(1);


        public void map(LongWritable key, Text value, Context context)
                throws IOException, InterruptedException {
            String line = value.toString();
            StringTokenizer tokenizer = new StringTokenizer(line);
            while (tokenizer.hasMoreTokens()) {
                context.write(new Text(tokenizer.nextToken()), one);
                context.getCounter(MATCH_COUNTER.TOTAL_WORD_DOC).increment(1);
            }
        }

    }

    public static class ReduceWordCount extends
            Reducer<Text, IntWritable, Text, IntWritable> {

        @Override
        public void reduce(Text key, Iterable<IntWritable> values, Context context)
                throws IOException, InterruptedException {
            int sum = 0;
            while (values.iterator().hasNext()) {
                sum+=values.iterator().next().get();
            }
            context.write(key, new IntWritable(sum));
        }
    }
    /**
     * Phase 1 End: Map->Key<Word>,Value<Int> ->Reducer->Key<Word>,Sum(val)
     *
     * @author Pooja
     *
     */
    /**
     * Phase 2 Start: Map->Key<Word>,Value<Value/totalCount>
     *
     * @author Pooja
     *
     */
    public static class MapTermFrequency extends
            Mapper<Text, IntWritable,Text,DoubleWritable> {

        public void map(Text key, IntWritable value, Context context)
                throws IOException, InterruptedException {

            Double totalWords=Double.parseDouble(context.getConfiguration().get("TOTAL_WORD_DOC"));
            context.write(key, new DoubleWritable(value.get()/totalWords));

        }

    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();

        conf.set("fs.file.impl",
                "com.conga.services.hadoop.patch.HADOOP_7682.WinLocalFileSystem");

        Job job = new Job(conf, "wordcount");
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        job.setMapperClass(MapWordCount.class);
        job.setReducerClass(ReduceWordCount.class);

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(SequenceFileOutputFormat.class);

        SequenceFileOutputFormat.setCompressOutput(job, true);
        SequenceFileOutputFormat.setOutputCompressionType(job,
                SequenceFile.CompressionType.BLOCK);
        SequenceFileOutputFormat.setOutputCompressorClass(job, DefaultCodec.class);

        FileInputFormat.addInputPath(job, new Path("data\\td_idf"));
        FileOutputFormat.setOutputPath(job, new Path("data\\output"));

        boolean isSuccessful=job.waitForCompletion(true);
        long counters= job.getCounters().findCounter(MATCH_COUNTER.TOTAL_WORD_DOC).getValue();
        conf.set("TOTAL_WORD_DOC", counters+"");
        if(isSuccessful){
            job = new Job(conf, "wordcount Frequency");
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(DoubleWritable.class);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(DoubleWritable.class);


            job.setMapperClass(MapTermFrequency.class);
            job.setNumReduceTasks(0);

            job.setInputFormatClass(SequenceFileInputFormat.class);
            job.setOutputFormatClass(TextOutputFormat.class);


            FileInputFormat.addInputPath(job, new Path("data\\output"));
            FileOutputFormat.setOutputPath(job, new Path("data\\output-termFrequency"));

            job.waitForCompletion(true);
        }
    }

}
