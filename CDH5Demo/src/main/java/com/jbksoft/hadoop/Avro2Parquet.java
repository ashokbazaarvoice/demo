package com.jbksoft.hadoop;

/**
 * Created by ashok.agarwal on 5/13/15.
 */
import java.io.IOException;
import java.io.InputStream;

import org.apache.avro.Schema;
import org.apache.avro.mapreduce.AvroKeyInputFormat;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import parquet.avro.AvroParquetOutputFormat;
import parquet.avro.AvroSchemaConverter;
import parquet.hadoop.metadata.CompressionCodecName;

import org.apache.avro.generic.GenericRecord;
import org.apache.avro.mapred.AvroKey;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Mapper;

public class Avro2Parquet extends Configured implements Tool {

    public int run(String[] args) throws Exception {
        Path schemaPath = new Path(args[0]);
        Path inputPath = new Path(args[1]);
        Path outputPath = new Path(args[2]);

        Job job = new Job(getConf());
        job.setJarByClass(getClass());
        Configuration conf = job.getConfiguration();

        FileSystem fs = FileSystem.get(conf);
        InputStream in = fs.open(schemaPath);
        Schema avroSchema = new Schema.Parser().parse(in);

        System.out.println(new AvroSchemaConverter().convert(avroSchema).toString());

        FileInputFormat.addInputPath(job, inputPath);
        job.setInputFormatClass(AvroKeyInputFormat.class);
        job.setOutputFormatClass(AvroParquetOutputFormat.class);
        AvroParquetOutputFormat.setOutputPath(job, outputPath);
        AvroParquetOutputFormat.setSchema(job, avroSchema);
        AvroParquetOutputFormat.setCompression(job, CompressionCodecName.SNAPPY);
        AvroParquetOutputFormat.setCompressOutput(job, true);

    /* Impala likes Parquet files to have only a single row group.
     * Setting the block size to a larger value helps ensure this to
     * be the case, at the expense of buffering the output of the
     * entire mapper's split in memory.
     *
     * It would be better to set this based on the files' block size,
     * using fs.getFileStatus or fs.listStatus.
     */
        AvroParquetOutputFormat.setBlockSize(job, 500 * 1024 * 1024);

        job.setMapperClass(Avro2ParquetMapper.class);
        job.setNumReduceTasks(0);

        return job.waitForCompletion(true) ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new Avro2Parquet(), args);
        System.exit(exitCode);
    }

}
class Avro2ParquetMapper extends
        Mapper<AvroKey<GenericRecord>, NullWritable, Void, GenericRecord> {

    @Override
    protected void map(AvroKey<GenericRecord> key, NullWritable value,
                       Context context) throws IOException, InterruptedException {
        context.write(null, key.datum());
    }
}