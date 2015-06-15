package com.jbksoft.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.StringUtils;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 4/22/14
 * Time: 11:41 AM
 * To change this template use File | Settings | File Templates.
 * bin/hadoop jar /usr/joe/wordcount.jar org.myorg.WordCountCache -Dwordcount.case.sensitive=true inputforcache/input outputforcache -skip inputforcache/patterns.txt
 */
public class WordCountCache extends Configured implements Tool {
    public static class Map extends
            Mapper<LongWritable, Text, Text, IntWritable> {

        static enum Counters {
            INPUT_WORDS
        }

        private final static IntWritable one = new IntWritable(1);
        private Text word = new Text();
        private boolean caseSensitive = true;
        private Set<String> patternsToSkip = new HashSet<String>();

        private long numRecords = 0;
        private String inputFile;

        public void setup(Context context) {
            Configuration config = context.getConfiguration();
            caseSensitive = config.getBoolean("wordcount.case.sensitive", true);
            inputFile = config.get("map.input.file");
            System.out.println("wordcount.case.sensitive : "+caseSensitive);
            System.out.println("map.input.file : "+inputFile);
            System.out.println("wordcount.skip.patterns : "+config.getBoolean("wordcount.skip.patterns",false));

            if (config.getBoolean("wordcount.skip.patterns", false)) {
                Path[] patternsFiles = new Path[0];
                try {
                    patternsFiles = DistributedCache.getLocalCacheFiles(config);
                } catch (IOException ioe) {
                    System.err
                            .println("Caught exception while getting cached files: "
                                    + StringUtils.stringifyException(ioe));
                }
                for (Path patternsFile : patternsFiles) {
                    parseSkipFile(patternsFile);
                }
            }
        }

        private void parseSkipFile(Path patternsFile) {
            try {
                BufferedReader fis = new BufferedReader(new FileReader(
                        patternsFile.toString()));
                String pattern = null;
                while ((pattern = fis.readLine()) != null) {
                    patternsToSkip.add(pattern);
                    System.out.println(pattern);
                }
            } catch (IOException ioe) {
                System.err
                        .println("Caught exception while parsing the cached file '"
                                + patternsFile
                                + "' : "
                                + StringUtils.stringifyException(ioe));
            }
        }

        public void map(LongWritable key, Text value,
                        Context context)
                throws IOException, InterruptedException {
            String line = (caseSensitive) ? value.toString() : value.toString()
                    .toLowerCase();

            for (String pattern : patternsToSkip) {
                line = line.replaceAll(pattern, "");
            }

            StringTokenizer tokenizer = new StringTokenizer(line);
            while (tokenizer.hasMoreTokens()) {
                word.set(tokenizer.nextToken());
                context.write(word, one);
                context.getCounter(Counters.INPUT_WORDS).increment(1);
            }

            if ((++numRecords % 1) == 0) {
                context.setStatus("Finished processing " + numRecords
                        + " records " + "from the input file: " + inputFile);
            }
        }
    }

    public static class Reduce extends
            Reducer<Text, IntWritable, Text, IntWritable> {
        public void reduce(Text key, Iterator<IntWritable> values,
                           Context context)
                throws IOException, InterruptedException {
            int sum = 0;
            while (values.hasNext()) {
                sum += values.next().get();
            }
            context.write(key, new IntWritable(sum));
        }
    }

    public int run(String[] args) throws Exception {
        List<String> other_args = new ArrayList<String>();
        for (int i = 0; i < args.length; ++i) {
            System.out.println("For Loop : "+args[i]);
            if ("-m".equals(args[i])) {
                //getConf().setNumMapTasks(Integer.parseInt(args[++i]));
            } else if ("-r".equals(args[i])) {
                //getConf().setNumReduceTasks(Integer.parseInt(args[++i]));
            }
            if ("-skip".equals(args[i])) {
                URI uri = new Path(args[++i]).toUri();
                DistributedCache
                        .addCacheFile(uri, getConf());
                getConf().setBoolean("wordcount.skip.patterns", true);
                System.out.println(args[i]);
            } else {
                other_args.add(args[i]);
            }
        }
        Job job = new Job(getConf(), "wordcount");
        job.setJarByClass(WordCountCache.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        job.setMapperClass(Map.class);
        job.setCombinerClass(Reduce.class);
        job.setReducerClass(Reduce.class);

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        FileInputFormat.setInputPaths(job, new Path(other_args.get(0)));
        FileOutputFormat.setOutputPath(job, new Path(other_args.get(1)));

        return (job.waitForCompletion(true) ? 0 : 1);
    }

    public static void main(String[] args) throws Exception {
        for(String str: args)
            System.out.println(str);
        // -Dwordcount.case.sensitive=true will automatically set in config by toolrunner
        int res = ToolRunner.run(new Configuration(), new WordCountCache(),
                args);
        System.exit(res);
    }

}
