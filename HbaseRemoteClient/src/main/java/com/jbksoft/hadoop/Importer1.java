package com.jbksoft.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.Random;

/**
 * writes random access logs into hbase table
 * <p/>
 * userID_count => {
 * details => {
 * page
 * }
 * }
 *
 * @author sujee ==at== sujee.net
 */
public class Importer1 {

    public static void main(String[] args) throws Exception {

        String[] pages = {"/", "/a.html", "/b.html", "/c.html"};

        Configuration hbaseConfig = HBaseConfiguration.create();
        HTable htable = new HTable(hbaseConfig, "access_logs");
        htable.setAutoFlush(false);
        htable.setWriteBufferSize(1024 * 1024 * 12);

        int totalRecords = 10;
        int maxID = totalRecords / 1;
        Random rand = new Random();
        System.out.println("importing " + totalRecords + " records ....");
        for (int i = 0; i < totalRecords; i++) {
            int userID = rand.nextInt(maxID) + 1;
            byte[] rowkey = Bytes.add(Bytes.toBytes(userID), Bytes.toBytes(i));
            System.out.println(" userID " +userID + " i "+i+" Bytes.toBytes(userID) "+Bytes.toStringBinary(Bytes.toBytes(userID))+" Bytes.toBytes(i) "+Bytes.toStringBinary(Bytes.toBytes(i))+" rowkey "+Bytes.toStringBinary(rowkey));

            String randomPage = pages[rand.nextInt(pages.length)];
            Put put = new Put(rowkey);
            System.out.println(userID + ":" + i + ":" + rowkey + ":" + randomPage);
            put.add(Bytes.toBytes("details"), Bytes.toBytes("page"), Bytes.toBytes(randomPage));
            htable.put(put);
        }
        htable.flushCommits();
        htable.close();
        System.out.println("done");
    }
}
