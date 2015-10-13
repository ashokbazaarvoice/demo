package com.jbksoft.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 10/22/14
 * Time: 5:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestHBase {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.set("mapreduce.framework.name", "local");
        conf.set("fs.default.name", "file:///");

        HBaseConfiguration.addHbaseResources(conf);

        //HBaseTestingUtility utility = new HBaseTestingUtility();

        HBaseAdmin admin = new HBaseAdmin(conf);
        try {
            HTable table = new HTable(conf, "test-table");
            Put put = new Put(Bytes.toBytes("test-key"));
            put.add(Bytes.toBytes("cf"), Bytes.toBytes("q"), Bytes.toBytes("value"));
            table.put(put);
        } finally {
            admin.close();
        }
    }
}
