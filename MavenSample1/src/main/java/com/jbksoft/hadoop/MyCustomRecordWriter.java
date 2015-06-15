package com.jbksoft.hadoop;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by ashok.agarwal on 2/24/14.
 */
public class MyCustomRecordWriter extends RecordWriter<WritableComparable<?>, Writable> {

    private DataOutputStream out;

    public MyCustomRecordWriter(DataOutputStream out) {
        super();
        this.out = out;
    }

    @Override
    public void write(WritableComparable<?> key, Writable value) throws IOException, InterruptedException {
        //out.writeBytes(key.toString() + ": "+value.toString());
        out.writeBytes(value.toString() + "\r\n");
    }

    @Override
    public void close(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        out.close();
    }
}
