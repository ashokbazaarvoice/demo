package com.bazaarvoice.consus;

import au.com.bytecode.opencsv.CSVReader;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.joda.time.DateTime;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.NavigableMap;


/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 7/18/14
 * Time: 4:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class PutTest {

    static Configuration config;
    public static void main(String[] args)
            throws Exception {
        config =  HBaseConfiguration.create();
        String csvCells =  "\"2014-03-31\",\"catalog:avon-es::product::33259\",\"avon-es\",\"33259\",\"5\",\"costco\",\"Costco\",\"20072\",\"review:costco:16976260\",\"1327849161\"";
        byte[] rowKey = getKey(csvCells);

        //MockHTable mockHTable = MockHTable.create();

//        saveDate(csvCells, rowKey,mockHTable);

//        Get get = new Get(rowKey);
//
//        Result value = mockHTable.get(get);

//        System.out.println(_buildCSVFromHBaseResult(value));
    }

    public static byte[] getKey(String csvLine) throws Exception {
        CSVReader csvReader = new CSVReader( new StringReader(csvLine), ',', '"', '\\');
        String [] colsOrigin = csvReader.readNext();

        // escaping the escape characters added by Consus-tools
        int len = colsOrigin.length;
        String[] csvCells = new String[len];
        for(int i=0; i<len; i++) {
            csvCells[i] = org.apache.commons.lang.StringEscapeUtils.unescapeJava(colsOrigin[i]);
        }

        // Key of record from Hbase
        String destClientId = csvCells[2];
        String destExternalId = csvCells[3];
        String srcClientId = csvCells[5];
        String legacyReviewId = csvCells[8].split(":")[2];
        String key = destClientId + "/" + destExternalId + "/" + srcClientId + "/" + legacyReviewId;

        System.out.println(key);

        byte[] rowKey = key.getBytes();
        return rowKey;
    }

//    private static String _buildCSVFromHBaseResult(Result result){
//        byte[] cf = SyndicatedReviewDAO.REVIEWS_CF;
//
//        String q = "\"";    // double quote
//        String qc = "\",";  // double quote + comma
//        String feedDate = "2014-03-31";
//
//        String cid = Bytes.toString(result.getValue(cf, SyndicatedReviewDAO.CLIENT_COL));
//        String xid = Bytes.toString(result.getValue(cf, SyndicatedReviewDAO.EXTERNALID_COL));
//        String pid = Bytes.toString(result.getValue(cf, SyndicatedReviewDAO.PRODUCT_ID_COL));
//
//        String scid =  Bytes.toString(result.getValue(cf, SyndicatedReviewDAO.SOURCE_CLIENT_COL));
//        String sxid = Bytes.toString(result.getValue(cf, SyndicatedReviewDAO.SOURCE_EXTERNALID_COL));
//        String spid = Bytes.toString(result.getValue(cf, SyndicatedReviewDAO.SOURCE_PRODUCT_ID_COL));
//
//        String lrid = Bytes.toString(result.getValue(cf, SyndicatedReviewDAO.LEGACY_REVIEW_ID_COL));
//        String rate = Bytes.toString(result.getValue(cf, SyndicatedReviewDAO.RATING_COL));
//
//        Long fsd = Bytes.toLong(result.getValue(cf, SyndicatedReviewDAO.FIRST_SYNDICATED_DATE_COL));
//        Long lsd = Bytes.toLong(result.getValue(cf, SyndicatedReviewDAO.LAST_SYNDICATED_DATE_COL));
//        String rt  = "PRR";
//
//        return q + feedDate + qc +
//                q + pid + qc +
//                q + cid + qc +
//                q + xid + qc +
//                q + rate + qc +
//                q + scid + qc +
//                q + spid + qc +
//                q + sxid + qc +
//                q + "review:" + scid + ":" + lrid + qc
//                + q + fsd/1000 +  q+
//                q + lsd/1000 +  q // last column has no comma
//                ;
//    }
//
//    public static void saveDate(String csvLine, byte [] rowKey, HTableInterface hTable) throws Exception {
//        CSVReader csvReader = new CSVReader( new StringReader(csvLine), ',', '"', '\\');
//        String [] colsOrigin = csvReader.readNext();
//
//        // escaping the escape characters added by Consus-tools
//        int len = colsOrigin.length;
//        String[] csvCells = new String[len];
//        for(int i=0; i<len; i++) {
//            csvCells[i] = org.apache.commons.lang.StringEscapeUtils.unescapeJava(colsOrigin[i]);
//        }
//
//        long ts = (new DateTime()).getMillis();
//
//        //new KeyValue(rowkey, PROP_COLUMN_FAMILY, PROP_COLUMN_FAMILY, bytes.toByteArray())
//        Put put = new Put(rowKey);
//        put.add(SyndicatedReviewDAO.REVIEWS_CF, SyndicatedReviewDAO.LEGACY_REVIEW_ID_COL,Bytes.toBytes(csvCells[8].split(":")[2]));
//        put.add(SyndicatedReviewDAO.REVIEWS_CF, SyndicatedReviewDAO.RATING_COL, ts, Bytes.toBytes(csvCells[4]));
//
//        SimpleDateFormat pattern = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = pattern.parse(csvCells[0]);
//        long milliseconds = date.getTime();
//
//        put.add(SyndicatedReviewDAO.REVIEWS_CF, SyndicatedReviewDAO.LAST_SYNDICATED_DATE_COL, ts, Bytes.toBytes(milliseconds));
//        put.add(SyndicatedReviewDAO.REVIEWS_CF, SyndicatedReviewDAO.FIRST_SYNDICATED_DATE_COL, ts, Bytes.toBytes(milliseconds));
//        put.add(SyndicatedReviewDAO.REVIEWS_CF, SyndicatedReviewDAO.CLIENT_COL, ts,  Bytes.toBytes(csvCells[2]));
//        put.add(SyndicatedReviewDAO.REVIEWS_CF, SyndicatedReviewDAO.PRODUCT_ID_COL, ts, Bytes.toBytes(csvCells[1]));
//        put.add(SyndicatedReviewDAO.REVIEWS_CF, SyndicatedReviewDAO.EXTERNALID_COL, ts, Bytes.toBytes(csvCells[3]));
//        put.add(SyndicatedReviewDAO.REVIEWS_CF, SyndicatedReviewDAO.SOURCE_CLIENT_COL, ts, Bytes.toBytes(csvCells[5]));
//
//        String spid =  "catalog:" + csvCells[5] + "::product:" + csvCells[7];
//
//        put.add(SyndicatedReviewDAO.REVIEWS_CF, SyndicatedReviewDAO.SOURCE_PRODUCT_ID_COL, ts, Bytes.toBytes(spid));
//        put.add(SyndicatedReviewDAO.REVIEWS_CF, SyndicatedReviewDAO.SOURCE_EXTERNALID_COL, ts, Bytes.toBytes(csvCells[7]));
//        put.add(SyndicatedReviewDAO.REVIEWS_CF, SyndicatedReviewDAO.REVIEW_TYPE_COL, ts, Bytes.toBytes("PRR"));
//        hTable.put(put);
//    }
}
