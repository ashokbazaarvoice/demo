package com.abc;

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
public class ImporterV2 {

    public static void main(String[] args) throws Exception {
        String[] pages = {"/", "/a.html", "/b.html", "/c.html"};
        int totalRecords = 10;
        int maxID = 5;
        Configuration hbaseConfig = HBaseConfiguration.create();
        HTable htable = new HTable(hbaseConfig, "access_logs_1");
        ImporterV2 importerV2 = new ImporterV2();
        importerV2.insertData(htable,totalRecords,maxID, pages);
        htable = new HTable(hbaseConfig, "access_logs_2");
        importerV2.insertData(htable,totalRecords,maxID, pages);
        System.out.println("done");
    }

    public void insertData(HTable htable, int totalRecords, int maxID, String[] pages) throws Exception {


        htable.setAutoFlush(false);
        htable.setWriteBufferSize(1024 * 1024 * 12);

        Random rand = new Random();
        System.out.println("importing " + totalRecords + " records ....");
        for (int i = 1; i <= totalRecords; i++) {
            int userID = rand.nextInt(maxID) + 1;
            byte[] rowkey = Bytes.add(Bytes.toBytes(userID), Bytes.toBytes(i));

            String randomPage = pages[rand.nextInt(pages.length)];
            Put put = new Put(rowkey);
            System.out.println(userID + ":" + i + ":" + rowkey + ":" + randomPage);
            put.add(Bytes.toBytes("details"), Bytes.toBytes("page"), Bytes.toBytes(randomPage));
            htable.put(put);
        }
        htable.flushCommits();
        htable.close();
    }
}
