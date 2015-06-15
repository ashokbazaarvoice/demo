package com.jbksoft.hbase.demo.client;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 6/9/14
 * Time: 2:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoadWithTableDescriptorExample {
    public static void main(String[] args) throws IOException { Configuration conf = HBaseConfiguration.create();
//        FileSystem fs = FileSystem.get(conf);
//        Path path = new Path(fs.getUri() + Path.SEPARATOR + "test.jar");
//        HTableDescriptor htd = new HTableDescriptor("testtable"); htd.addFamily(new HColumnDescriptor("colfam1")); htd.setValue("COPROCESSOR$1", path.toString() +
//                "|" + RegionObserverExample.class.getCanonicalName() + "|" + Coprocessor.Priority.USER);
//        HBaseAdmin admin = new HBaseAdmin(conf); admin.createTable(htd);
//        System.out.println(admin.getTableDescriptor(Bytes.toBytes("testtable")));
    }

}
