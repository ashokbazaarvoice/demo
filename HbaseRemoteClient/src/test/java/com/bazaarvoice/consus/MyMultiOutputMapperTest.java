package com.bazaarvoice.consus;

import com.abc.MyMultiOutputMapper;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.types.Pair;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 8/6/14
 * Time: 6:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyMultiOutputMapperTest {

    MyMultiOutputMapper mapper;
    MapDriver<LongWritable, Text, Text, Text> mapDriver;
    Configuration config;
    static String[] CSV = {
            "9/8/14,MSFT,47",
            "9/8/14,ORCL,40",
            "9/8/14,GOOG,577",
            "9/8/14,AAPL,100.4",
            "9/9/14,MSFT,46",
            "9/9/14,ORCL,41",
            "9/9/14,GOOG,578",
            "9/9/14,AAPL,101",
            "9/10/14,MSFT,48",
            "9/10/14,ORCL,39.5",
            "9/10/14,GOOG,577",
            "9/10/14,AAPL,100",
            "9/11/14,MSFT,47.5",
            "9/11/14,ORCL,41",
            "9/11/14,GOOG,588",
            "9/11/14,AAPL,99.8",
            "9/12/14,MSFT,46.69",
            "9/12/14,ORCL,40.5",
            "9/12/14,GOOG,576",
            "9/12/14,AAPL,102.5"
    };

    @Before
    public void setup()
            throws Exception {
        config = new Configuration();
        mapper = new MyMultiOutputMapper();
        mapDriver = MapDriver.newMapDriver(mapper);
        mapDriver.setConfiguration(config);
    }

    @Test
    public void testMapInput1Output()
            throws Exception {
        mapDriver.withInput(new LongWritable(), new Text(CSV[0]));
        mapDriver.withOutput(new Text("MSFT"), new Text("47"));
        mapDriver.runTest();
    }

    @Test
    public void testMapInput2Output()
            throws Exception {

        final List<Pair<LongWritable, Text>> inputs = new ArrayList<Pair<LongWritable, Text>>();
        inputs.add(new Pair<LongWritable, Text>(new LongWritable(), new Text(CSV[0])));
        inputs.add(new Pair<LongWritable, Text>(new LongWritable(), new Text(CSV[1])));

        final List<Pair<Text, Text>> outputs = new ArrayList<Pair<Text, Text>>();
        outputs.add(new Pair<Text, Text>(new Text("MSFT"), new Text("47")));
        outputs.add(new Pair<Text, Text>(new Text("ORCL"), new Text("40")));
//        mapDriver.withAll(inputs).withAllOutput(outputs).runTest();
    }

}
