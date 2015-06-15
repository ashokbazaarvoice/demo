package com.bazaarvoice.consus;

//import com.bazaarvoice.consus.mapreduce.ConsusCsvImportReducer;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.apache.hadoop.mrunit.types.Pair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by ashok.agarwal on 3/7/14.
 */
public class ConsusCsvImportReducerTest {
    ReduceDriver<Text, IntWritable, ImmutableBytesWritable, Writable> reduceDriver;

    @Before
    public void setUp() {
//        ConsusCsvImportReducer reducer = new ConsusCsvImportReducer();
//        reduceDriver = ReduceDriver.newReduceDriver(reducer);
    }

    @Test
    public void testHBaseInsert() throws IOException {

        String strKey = "reviews/avon-es/2014-01-01";


        List<IntWritable> list = new ArrayList<IntWritable>();
        list.add(new IntWritable(1));
        list.add(new IntWritable(1));
        list.add(new IntWritable(1));

        // since in our case all that reducer is doing is appending all the
        // records that the mapper sends it, we should get the following back
        int expectedOutput = 1 + 1 + 1;

        // Setup Input, mimic what mapper would have passed to the reducer and
        // run test
        reduceDriver.withInput(new Text(strKey), list);
        // run the reducer and get its output
        List<Pair<ImmutableBytesWritable, Writable>> result = reduceDriver
                .run();

        String rowKey = Bytes.toString(result.get(0).getFirst().get());
        String[] keyParts = rowKey.split("/");

        // extract key from result and verify
        Assert.assertEquals(keyParts[0], "avon-es");

        // extract value for CF/QUALIFIER and verify
        Put a = (Put) result.get(0).getSecond();
//        String c = Bytes.toString(a.get(CF, QUALIFIER).get(0).getValue());
//        assertEquals(expectedOutput, c);
    }
}
