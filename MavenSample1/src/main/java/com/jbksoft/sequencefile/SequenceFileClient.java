package com.jbksoft.sequencefile;

import org.apache.commons.io.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 9/19/14
 * Time: 3:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class SequenceFileClient {

    private static Configuration conf = new Configuration();

    /**
     * Convert the lines of text in a file to binary and write to a Hadoop
     * sequence file.
     *
     * @param dataFile File containing lines of text
     * @param sequenceFileName Name of the sequence file to create
     *
     * @throws IOException
     */
    public static void writeToSequenceFile(File dataFile, String sequenceFileName) throws IOException {

        IntWritable key = null;
        BytesWritable value = null;

//        conf.set("fs.defaultFS", hadoopFS);
        Path path = new Path(sequenceFileName);

        if ((conf != null) && (dataFile != null) && (dataFile.exists())) {
            SequenceFile.Writer writer = SequenceFile.createWriter(conf, SequenceFile.Writer.file(path),
//                    SequenceFile.Writer.compression(SequenceFile.CompressionType.RECORD, new GzipCodec()),
                    SequenceFile.Writer.keyClass(IntWritable.class), SequenceFile.Writer.valueClass(BytesWritable.class));

            List<String> lines = FileUtils.readLines(dataFile);

            for (int i = 0; i < lines.size(); i++) {
                value = new BytesWritable(lines.get(i).getBytes());
                key = new IntWritable(i);
                writer.append(key, value);
            }
            IOUtils.closeStream(writer);
        }
    }

    /**
     * Read a Hadoop sequence file on HDFS.
     *
     * @param sequenceFileName Name of the sequence file to read
     *
     * @throws IOException
     */
    public static void readSequenceFile(String sequenceFileName) throws IOException {
//        conf.set("fs.defaultFS", hadoopFS);
        Path path = new Path(sequenceFileName);
        SequenceFile.Reader reader = new SequenceFile.Reader(conf, SequenceFile.Reader.file(path));
        Text key = (Text) ReflectionUtils.newInstance(reader.getKeyClass(), conf);
        LongWritable value = (LongWritable) ReflectionUtils.newInstance(reader.getValueClass(), conf);
        while (reader.next(key, value)) {
            System.out.println("key : " + key.toString() + " - value : " + value.toString());
        }
        IOUtils.closeStream(reader);
    }

    public static void main(String[] args){

        try {

            readSequenceFile("/Users/ashok.agarwal/dev/github/MavenSample1/outputforseqfile/part-r-00000");

//            writeToSequenceFile(new File("/Users/ashok.agarwal/dev/github/MavenSample1/inputforcache/input/file02.txt"),"/Users/ashok.agarwal/dev/github/MavenSample1/inputseqfile/file.seq");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
