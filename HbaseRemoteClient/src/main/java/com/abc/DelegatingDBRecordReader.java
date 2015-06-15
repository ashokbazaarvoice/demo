package com.abc;

import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 4/25/14
 * Time: 2:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class DelegatingDBRecordReader <K, V> extends RecordReader<K, V> {
    RecordReader<K, V> originalRR;

    /**
     * Constructs the DelegatingRecordReader.
     *
     * @param split TaggegInputSplit object
     * @param context TaskAttemptContext object
     *
     * @throws java.io.IOException
     * @throws InterruptedException
     */
    @SuppressWarnings("unchecked")
    public DelegatingDBRecordReader(InputSplit split, TaskAttemptContext context)
            throws IOException, InterruptedException {
        System.out.println("com.abc.DelegatingDBRecordReader->constructor");
        // Find the InputFormat and then the RecordReader from the
        // TaggedInputSplit.
        TaggedDBInputSplit taggedInputSplit = (TaggedDBInputSplit) split;
        System.out.println("com.abc.DelegatingDBRecordReader->constructor"+taggedInputSplit.getInputFormatClass()+" : "+taggedInputSplit
                .getInputSplit()+" : "+taggedInputSplit.getMapperClass());
        InputFormat<K, V> inputFormat = (InputFormat<K, V>) ReflectionUtils
                .newInstance(taggedInputSplit.getInputFormatClass(), context
                        .getConfiguration());
        originalRR = inputFormat.createRecordReader(taggedInputSplit
                .getInputSplit(), context);
    }

    @Override
    public void close() throws IOException {
        originalRR.close();
    }

    @Override
    public K getCurrentKey() throws IOException, InterruptedException {
        return originalRR.getCurrentKey();
    }

    @Override
    public V getCurrentValue() throws IOException, InterruptedException {
        return originalRR.getCurrentValue();
    }

    @Override
    public float getProgress() throws IOException, InterruptedException {
        return originalRR.getProgress();
    }

    @Override
    public void initialize(InputSplit split, TaskAttemptContext context)
            throws IOException, InterruptedException {
        originalRR.initialize(((TaggedDBInputSplit) split).getInputSplit(), context);
    }

    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException {
        return originalRR.nextKeyValue();
    }

}
