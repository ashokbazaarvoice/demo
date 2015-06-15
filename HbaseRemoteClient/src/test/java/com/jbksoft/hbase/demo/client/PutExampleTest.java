package com.jbksoft.hbase.demo.client;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HBaseTestingUtility;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.*;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created by ashok.agarwal on 2/28/14.
 */
public class PutExampleTest {

//    public static void main(String[] args) throws Exception {
//        //Configuration conf = HBaseConfiguration.create();
//        HBaseTestingUtility utility = new HBaseTestingUtility();
//        utility.startMiniCluster();
//        Configuration conf = utility.getConfiguration();
//        HTable table = new HTable(conf, "testtable");
//        Put put = new Put(Bytes.toBytes("row1"));
//        put.add(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"), Bytes.toBytes("val1"));
//        put.add(Bytes.toBytes("colfam1"), Bytes.toBytes("qual2"), Bytes.toBytes("val2"));
//        table.put(put);
//    }

    private static HBaseTestingUtility utility;


    @Before
    public void setup() throws Exception {
        utility = new HBaseTestingUtility();
        // -Dlog4j.configuration=/Users/ashok.agarwal/dev/com.abc.com.jbksoft.hbasetest.HBaseTest/src/test/resources/log4j.properties
        utility.startMiniCluster();
    }

    @Test
    public void testInsert() throws Exception {
        HTableInterface table = utility.createTable(Bytes.toBytes("testtable"),
                Bytes.toBytes("colfam1"));
        Put put = new Put(Bytes.toBytes("row1"));
        put.add(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"), Bytes.toBytes("val1"));
        put.add(Bytes.toBytes("colfam1"), Bytes.toBytes("qual2"), Bytes.toBytes("val2"));
        table.put(put);

        Get get = new Get(Bytes.toBytes("row1"));
        get.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"));
        Result result = table.get(get);
        Assert.assertEquals(Bytes.toString(result.getRow()), "row1");
        Assert.assertEquals(Bytes.toString(result.value()), "val1");

        Scan scan = new Scan();
        ResultScanner scanner = table.getScanner(scan);
        try {
            for (Result scannerResult : scanner) {
                for (KeyValue kv : scannerResult.raw()) {
                    System.out.print(new String(kv.getRow()) + " ");
                    System.out.print(new String(kv.getFamily()) + ":");
                    System.out.print(new String(kv.getQualifier()) + " ");
                    System.out.print(kv.getTimestamp() + " ");
                    System.out.println(new String(kv.getValue()));
                }

            }
        } finally {
            scanner.close();
        }
    }

    @After
    public void tearDown() {

        try {
            if (utility != null) {
                utility.shutdownMiniCluster();
                utility = null;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
