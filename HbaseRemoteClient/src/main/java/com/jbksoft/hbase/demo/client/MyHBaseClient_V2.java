package com.jbksoft.hbase.demo.client;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 12/19/14
 * Time: 3:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyHBaseClient_V2 {

        private static final String  HBASE_ROOT_DIR  = "hbase.rootdir";
        private static final String  HBASE_ZOOKEEPER = "hbase.zookeeper.quorum";
        private static final String  TABLE_NAME      = "table";
        private static final String  CLIENT_NAME     = "client";
        private static final Logger  LOG             = Logger.getLogger(MyHBaseClient.class.getName());

        private static String        outputPath = System.getProperty("user.dir");
        private static Properties    prop;
        private static Configuration conf;

        public MyHBaseClient_V2() {
            try {
//            prop = new Properties();
//            prop.load(MyHBaseClient.class.getClassLoader().getResourceAsStream("test.properties"));
                conf = HBaseConfiguration.create();
                conf.set(HBASE_ROOT_DIR, "hdfs://cdh4-master-0.mag.jbksoft.com:8020/hbase");
                conf.set(HBASE_ZOOKEEPER, "cdh4hb-support-0.mag.jbksoft.com");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static void main(String[] args) throws IOException {
            MyHBaseClient hbaseHelper = new MyHBaseClient();


//        hbaseHelper.hasDataForClient("avon-es","consus_nativereviews_test");
            System.out.println("===========get one record========");
            hbaseHelper.getOneRecord("consus_nativereviews_test", "007jamesbondfragrances/test0/review:007jamesbondfragrances:01a7191b-7bf4-556d-93e6-e7c59e35aff0");

//        hbaseHelper.getCSVFromHbase("consus_syndicated_reviews_test", "avon-es");
//        String out = hbaseHelper.getCatalogResultByRowKey("catalog:avon-es::brand::6x9n2h1aqjwkqou0z8pqr2ych", "consus_catalog_test");
//        Log.info(out);
        }

        public boolean hasDataForClient(String clientName, String tableName) throws IOException {
            HTable table = new HTable(conf, tableName);
            Scan scan = getScanner(tableName, clientName);
            if ( null == scan ) {
                LOG.info("Either there are no data or the table is invalid!");
                return false;
            }
            ResultScanner rs    = table.getScanner(scan);
            if ( ! rs.next().isEmpty() ) {
                LOG.info(tableName + " has data for client : " + clientName);
                return true;
            }  else {
                LOG.info(tableName + " does not have data for client : " + clientName);
                return false;
            }
        }

        public  Scan getScanner(String tableName, String clientName) {
            Scan scan;
            if ( null == clientName || clientName.isEmpty() ) {
                scan = new Scan();
            } else if ( tableName.contains("catalog") ) {
                String startString = "catalog:" + clientName;
                scan = new Scan(startString.getBytes(), getEndRow(startString));
            } else {
                scan = new Scan(clientName.getBytes(), getEndRow(clientName));
            }
            return scan;
        }

        private   byte[] getEndRow(String starRow) {
            String retString = starRow.substring(0, starRow.length() - 1);
            int charValue = starRow.substring(starRow.length() - 1).charAt(0);
            String newString = String.valueOf((char) (charValue + 1));
            return retString.concat(newString).getBytes();
        }

        public static void getOneRecord(String tableName, String rowKey) throws IOException {
            HTable table = new HTable(conf, tableName);
            Get get = new Get(rowKey.getBytes());
            Result rs = table.get(get);
            for (KeyValue kv : rs.raw()) {
                System.out.print(new String(kv.getRow()) + " ");
                System.out.print(new String(kv.getFamily()) + ":");
                System.out.print(new String(kv.getQualifier()) + " ");
                System.out.print(kv.getTimestamp() + " ");
                System.out.println(new String(kv.getValue()));
            }
        }



    }
