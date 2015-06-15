package com.jbksoft.hbase.demo.client;

import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.KeyValueTestUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 7/17/14
 * Time: 4:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class SampleHBaseMapperTest {

    MapDriver<ImmutableBytesWritable, Result, Text, IntWritable> mapDriver;

    @Before
    public void setUp() {
        SampleHbaseMapper mapper = new SampleHbaseMapper();
        mapDriver = MapDriver.newMapDriver(mapper);
    }

    @Test
    public void testSampleHBaseMapperHasOutputOnError() {

        String test_key = "12345";
        Text expectedOutput = new Text(test_key + ",Error Message");

        // Setup Test Record(key, result list)
        ImmutableBytesWritable key = new ImmutableBytesWritable(Bytes.toBytes(test_key));
        ArrayList<KeyValue> list = new ArrayList<KeyValue>();
        KeyValue k1 = KeyValueTestUtil.create(test_key, "colfam1", "col1", 100L, "1234657890");
        KeyValue k2 = KeyValueTestUtil.create(test_key, "colfam2", "col1", 100L, "Error Inducing Input");

        list.add(k1);
        list.add(k2);
        Result result = new Result(list);

        // Setup Input, Expected output and run test
        mapDriver.withInput(key, result);
        mapDriver.withOutput(expectedOutput, new IntWritable(1));
        mapDriver.runTest();
    }
}
