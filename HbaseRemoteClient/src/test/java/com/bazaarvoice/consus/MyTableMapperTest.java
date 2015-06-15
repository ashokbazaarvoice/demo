package com.bazaarvoice.consus;

import au.com.bytecode.opencsv.CSVReader;
import com.abc.MyTableMapper;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 8/6/14
 * Time: 6:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyTableMapperTest {


    MyTableMapper mapper;

    MapDriver<ImmutableBytesWritable, Result, Text, IntWritable> mapDriver;

    Configuration config;

    String path;

    static String[] CSV = {
            "\"2014-03-31\",\"GEORGE\",\"W\",\"BUSH\",\"USA\"",
            "\"2014-03-31\",\"SUSAN\",\"B\",\"ANTHONY\",\"USA\""
    };

    @Before
    public void setup()
            throws Exception {
        path = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        config = HBaseConfiguration.create();
        setConfig(config);

        mapper = new MyTableMapper();
        mapDriver = MapDriver.newMapDriver(mapper);
        mapDriver.setConfiguration(config);
    }


    public void setConfig(Configuration config) {
        config.set("startDate", "2014-03-03T00:00:00Z");
        config.set("period_in_days", "7");
        config.set("outputPath", path + "data");
    }

    @Test
    public void testMap1Input1Output()
            throws Exception {

        mapDriver.withInput(getKey(CSV[0]), getResult(CSV[0]));
        mapDriver.withOutput(new Text("GEORGE"),
                new IntWritable(1));
        mapDriver.runTest();

    }

    public ImmutableBytesWritable getKey(String csvRecord)
            throws Exception {
        CSVReader csvReader = new CSVReader(new StringReader(csvRecord), ',');
        String[] csvCells = csvReader.readNext();

        // Key of record from Hbase
        String key = csvCells[1] + "/" + csvCells[2] + "/" + csvCells[3];
        ImmutableBytesWritable rowKey = new ImmutableBytesWritable(key.getBytes());
        return rowKey;
    }

    public Result getResult(String csvRecord)
            throws Exception {

        final byte[] COL_FAMILY = "CF".getBytes();
        final byte[] FIRST_NAME_COL_QUALIFIER = "fn".getBytes();
        final byte[] MIDDLE_NAME_COL_QUALIFIER = "mi".getBytes();
        final byte[] LAST_NAME_COL_QUALIFIER = "ln".getBytes();

        CSVReader csvReader = new CSVReader(new StringReader(csvRecord), ',');
        String[] csvCells = csvReader.readNext();

        ImmutableBytesWritable key = getKey(csvRecord);

        List<KeyValue> kvs = new ArrayList<KeyValue>();
        kvs.add(new KeyValue(key.get(), COL_FAMILY, FIRST_NAME_COL_QUALIFIER, Bytes.toBytes(csvCells[1])));
        kvs.add(new KeyValue(key.get(), COL_FAMILY, FIRST_NAME_COL_QUALIFIER, Bytes.toBytes(csvCells[2])));
        kvs.add(new KeyValue(key.get(), COL_FAMILY, FIRST_NAME_COL_QUALIFIER, Bytes.toBytes(csvCells[3])));

        return keyValueToResult(kvs);

    }

    protected Result keyValueToResult(List<KeyValue> kvs) {
        KeyValue[] kvsArray = kvs.toArray(new KeyValue[0]);
        Arrays.sort(kvsArray, KeyValue.COMPARATOR);
        List<KeyValue> kvsSorted = Arrays.asList(kvsArray);
        return new Result(kvsSorted);
    }

    public Result getResultV2(String csvRecord)
            throws Exception {
        MockHTable mockHTable = MockHTable.create();

        final byte[] COL_FAMILY = "CF".getBytes();
        final byte[] FIRST_NAME_COL_QUALIFIER = "fn".getBytes();
        final byte[] MIDDLE_NAME_COL_QUALIFIER = "mi".getBytes();
        final byte[] LAST_NAME_COL_QUALIFIER = "ln".getBytes();

        CSVReader csvReader = new CSVReader(new StringReader(csvRecord), ',');
        String[] csvCells = csvReader.readNext();

        ImmutableBytesWritable key = getKey(csvRecord);

        Put put = new Put(key.get());
        put.add(COL_FAMILY, FIRST_NAME_COL_QUALIFIER, Bytes.toBytes(csvCells[1]));
        put.add(COL_FAMILY, FIRST_NAME_COL_QUALIFIER, Bytes.toBytes(csvCells[2]));
        put.add(COL_FAMILY, FIRST_NAME_COL_QUALIFIER, Bytes.toBytes(csvCells[3]));
        mockHTable.put(put);


        for (  final List<KeyValue> entries : put.getFamilyMap().values()) {
            for (    final KeyValue kv : entries) {
                //Assert.assertTrue(put2.has(kv.getFamily(),kv.getQualifier(),kv.getTimestamp(),kv.getValue()));
                System.out.println(kv.getFamily()+" : "+kv.getQualifier()+" : "+kv.getTimestamp()+" : "+kv.getValue());
            }
        }
//        for (  final byte[] family : put.getFamilyMap().keySet()) {
////            Assert.assertTrue(put2.getFamilyMap().containsKey(family));
//        }
//        Assert.assertEquals(put.getFamilyMap().size(),put2.getFamilyMap().size());

        return mockHTable.get(new Get(key.get()));

    }

}
