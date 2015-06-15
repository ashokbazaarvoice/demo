package com.jbksoft.hadoop.reduceside.join;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 4/22/14
 * Time: 1:48 PM
 * To change this template use File | Settings | File Templates.
 */
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.DelegatingInputFormat;
import org.apache.hadoop.mapreduce.lib.input.DelegatingMapper;
import org.apache.hadoop.mapreduce.lib.output.LazyOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 yarn jar $PLAY_AREA/HadoopSamples.jar com.jbksoft.hadoop.reduceside.join.ReduceSideJoinV2 <user_posts_path> <users_likes_path> <output_path>
 yarn jar $PLAY_AREA/HadoopSamples.jar com.jbksoft.hadoop.reduceside.join.ReduceSideJoinV2 inputforjoin/employee.txt inputforjoin/salary.txt reduceSideJoin
 */
public class ReduceSideJoinV2 extends Configured implements Tool{


    public int run(String[] args) throws Exception {
        Configuration conf = getConf();
        conf.set("mapred.input.dir.mappers", new Path(args[0])+";com.jbksoft.hadoop.reduceside.join.ReduceSideJoinUsersPostsMapper,"+new Path(args[1])+";com.jbksoft.hadoop.reduceside.join.ReduceSideJoinUsersLikesMapper");
        conf.set("mapred.input.dir.formats", new Path(args[0])+";org.apache.hadoop.mapreduce.lib.input.TextInputFormat,"+new Path(args[1])+";org.apache.hadoop.mapreduce.lib.input.TextInputFormat");
        Job job = Job.getInstance(conf, this.getClass().getName());
        job.setJarByClass(getClass());

        job.setInputFormatClass(DelegatingInputFormat.class);
        job.setMapperClass(DelegatingMapper.class);

        job.setReducerClass(ReduceSideJoinReducer.class);
        TextOutputFormat.setOutputPath(job, new Path(args[2]));

        LazyOutputFormat.setOutputFormatClass(job, TextOutputFormat.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        return job.waitForCompletion(true) ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new ReduceSideJoinV2(), args);
        System.exit(exitCode);
    }
}