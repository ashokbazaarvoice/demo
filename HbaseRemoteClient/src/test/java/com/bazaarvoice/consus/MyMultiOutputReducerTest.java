package com.bazaarvoice.consus;

import com.abc.MyMultiOutputReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.apache.hadoop.mrunit.types.Pair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 8/6/14
 * Time: 6:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyMultiOutputReducerTest {

    MockOSReducer reducer;
    ReduceDriver<Text, Text, NullWritable, Text> reduceDriver;
    Configuration config;
    Map<String, List<Text>> outputCSVFiles;
    static String[] CSV = {
            "9/8/14,MSFT,47",
            "9/8/14,ORCL,40",
            "9/8/14,GOOG,577",
            "9/8/14,AAPL,100.4",
            "9/9/14,MSFT,46",
            "9/9/14,ORCL,41",
            "9/9/14,GOOG,578"
    };

    class MockOSReducer extends MyMultiOutputReducer {

        private Map<String, List<Text>> multipleOutputMap;

        public MockOSReducer(Map<String, List<Text>> map) {
            super();
            multipleOutputMap = map;
        }

        @Override
        public void setup(Reducer.Context context) {
            mos = new MultipleOutputs<NullWritable, Text>(context) {
                @Override
                public void write(NullWritable key, Text value, String outputFileName)
                        throws java.io.IOException, java.lang.InterruptedException {
                    List<Text> outputs = multipleOutputMap.get(outputFileName);
                    if (outputs == null) {
                        outputs = new ArrayList<Text>();
                        multipleOutputMap.put(outputFileName, outputs);
                    }
                    outputs.add(new Text(value));
                }
            };
            config = context.getConfiguration();
        }
    }

    @Before
    public void setup()
            throws Exception {
        config = new Configuration();
        outputCSVFiles = new HashMap<String, List<Text>>();
        reducer = new MockOSReducer(outputCSVFiles);
        reduceDriver = ReduceDriver.newReduceDriver(reducer);
        reduceDriver.setConfiguration(config);
    }

    @Test
    public void testReduceInput1Output()
            throws Exception {
        List<Text> list = new ArrayList<Text>();
        list.add(new Text("47"));
        list.add(new Text("46"));
        list.add(new Text("48"));
        reduceDriver.withInput(new Text("MSFT"), list);
        reduceDriver.runTest();

        // print(outputCSVFiles);

        Map<String, List<Text>> expectedCSVOutput = new HashMap<String, List<Text>>();

        List<Text> outputs = new ArrayList<Text>();

        outputs.add(new Text("47"));
        outputs.add(new Text("46"));
        outputs.add(new Text("48"));

        expectedCSVOutput.put("MSFT", outputs);

        validateOutputList(outputCSVFiles, expectedCSVOutput);

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


    static void print(Map<String, List<Text>> outputCSVFiles) {
//        System.out.println(outputCSVFiles.size());

        for (String key : outputCSVFiles.keySet()) {
            List<Text> valueList = outputCSVFiles.get(key);
//            System.out.println(valueList.size());
            for (Text pair : valueList) {
                System.out.println("OUTPUT " + key + " = " + pair.toString());
            }
        }
    }

    protected void validateOutputList(Map<String, List<Text>> actuals,
                                      Map<String, List<Text>> expects) {

        List<String> removeList = new ArrayList<String>();

        for (String key : expects.keySet()) {
            removeList.add(key);
            List<Text> expectedValues = expects.get(key);
            List<Text> actualValues = actuals.get(key);

            int expectedSize = expectedValues.size();
            int actualSize = actualValues.size();
            int i = 0;

            Assert.assertEquals("Number of output CSV files is " + actualSize + " but expected " + expectedSize,
                    actualSize, expectedSize);

            while (expectedSize > i || actualSize > i) {
                if (expectedSize > i && actualSize > i) {
                    Text expected = expectedValues.get(i);
                    Text actual = actualValues.get(i);

                    Assert.assertTrue("Expected CSV content is " + expected.toString() + "but got " + actual.toString(),
                            expected.equals(actual));

                }
                i++;
            }
        }
    }
}
