package com.bazaarvoice.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.input.LineRecordReader;
import org.apache.hadoop.util.LineReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 4/28/14
 * Time: 5:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyNLineInputFormat extends FileInputFormat<LongWritable, Text> {

    @Override
    public List<InputSplit> getSplits(JobContext context) throws IOException {
        List<InputSplit> splits = new ArrayList<InputSplit>();
        int numLinesPerSplit = 2;
        for (FileStatus status : listStatus(context)) {
            splits.addAll(getSplitsForFile(status,
                    context.getConfiguration(), numLinesPerSplit));
        }
        return splits;
    }

    @Override
    public RecordReader<LongWritable, Text> createRecordReader(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
        context.setStatus(split.toString());
        return new LineRecordReader();
    }

    public static List<FileSplit> getSplitsForFile(FileStatus status,
                                                   Configuration conf, int numLinesPerSplit) throws IOException {
        List<FileSplit> splits = new ArrayList<FileSplit> ();
        Path fileName = status.getPath();
        if (status.isDirectory()) {
            throw new IOException("Not a file: " + fileName);
        }
        FileSystem fs = fileName.getFileSystem(conf);
        LineReader lr = null;
        try {
            FSDataInputStream in  = fs.open(fileName);
            lr = new LineReader(in, conf);
            Text line = new Text();
            int numLines = 0;
            long begin = 0;
            long length = 0;
            int num = -1;
            while ((num = lr.readLine(line)) > 0) {
                numLines++;
                length += num;
                if (numLines == numLinesPerSplit) {
                    splits.add(createFileSplit(fileName, begin, length));
                    begin += length;
                    length = 0;
                    numLines = 0;
                }
            }
            if (numLines != 0) {
                splits.add(createFileSplit(fileName, begin, length));
            }
        } finally {
            if (lr != null) {
                lr.close();
            }
        }
        return splits;
    }

    protected static FileSplit createFileSplit(Path fileName, long begin, long length) {
        return (begin == 0)
                ? new FileSplit(fileName, begin, length - 1, new String[] {})
                : new FileSplit(fileName, begin - 1, length, new String[] {});
    }
}
