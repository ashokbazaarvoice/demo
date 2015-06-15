package com.abc;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Writable;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 4/25/14
 * Time: 2:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class Mapper1 extends TableMapper<ImmutableBytesWritable, Writable> {

    private int numRecords = 0;
    private static final Writable one = new IntWritable(1);

    @Override
    public void map(ImmutableBytesWritable row, Result values, Context context) throws IOException {
        // extract userKey from the compositeKey (userId + counter)
        ImmutableBytesWritable userKey = new ImmutableBytesWritable(row.get(), 0, Bytes.SIZEOF_INT);
        try {
            System.out.println(Bytes.toInt(row.get(), 0, Bytes.SIZEOF_INT));
            context.write(userKey, one);
        } catch (InterruptedException e) {
            throw new IOException(e);
        }
        numRecords++;
        if ((numRecords % 10000) == 0) {
            context.setStatus("mapper processed " + numRecords + " records so far");
        }
    }
}
