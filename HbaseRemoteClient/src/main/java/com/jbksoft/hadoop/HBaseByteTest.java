package com.jbksoft.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.util.StringUtils;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 12/3/14
 * Time: 1:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class HBaseByteTest {
    public static Configuration configuration;
    private static final String  HBASE_ROOT_DIR  = "hbase.rootdir";
    private static final String  HBASE_ZOOKEEPER = "hbase.zookeeper.quorum";

    public static void main(String[] args) throws Exception {

//        Configuration configuration;
//
//        configuration = HBaseConfiguration.create();
//
//        configuration.set(HBASE_ROOT_DIR, "hdfs://cdh4-master-0.mag.jbksoft.com:8020/hbase");
//        configuration.set(HBASE_ZOOKEEPER, "cdh4hb-support-0.mag.jbksoft.com");

//        configuration.set("hbase.zookeeper.property.clientPort", "2181");
//        configuration.set("hbase.zookeeper.quorum", "cdh4hb-master-1.mag.jbksoft.com,cdh4hb-support-0.mag.jbksoft.com,cdh4hb-master-0.mag.jbksoft.com");
//        configuration.set("hbase.master", "cdh4hb-master-1.mag.jbksoft.com:60000");
//        configuration.set("hbase.master.info.bindAddress", "cdh4hb-master-1.mag.jbksoft.com");
//        configuration.set("hbase.master.info.port", "60010");

//        configuration.addResource(new Path("hbase-site.xml"));

//        String tablename = "consus_banyan_client_dev";
//
//        System.out.println("===========get one record========");
//        com.jbksoft.hadoop.HBaseByteTest.getOneRecord(tablename, "007jamesbondfragrances");

//        System.out.println("===========show all record========");
//        com.jbksoft.hadoop.HBaseByteTest.getAllRecord(tablename);

        System.out.println(Bytes.toStringBinary("Hello HBase".getBytes("UTF-8")));

        System.out.println(Bytes.toStringBinary(hexToBytes("Hello HBase")));

        System.out.println(Bytes.toString(((byte [])"Hello HBase".getBytes())));
        System.out.println(Arrays.toString("Hello HBase".getBytes()));
        System.out.println(Arrays.toString("Hello HBase".getBytes("UTF-8")));
        System.out.println(Arrays.toString("Hello HBase".getBytes("UTF-16")));

        System.out.println((int)'H');
        System.out.println(String.format("%04x", (int) 'H'));

        System.out.println(Bytes.toString("\\x48\\x65\\x6c\\x6c\\x6f\\x20\\x48\\x42\\x61\\x73\\x65".getBytes()));
       // System.out.println(Bytes.toStringBinary(Bytes.toBytes("Hello HBase","UTF-8")));
        System.out.println(Bytes.toStringBinary(Bytes.toBytes("Hello HBase")));

        byte[] bArr = Bytes.toBytesBinary("Hello HBase");

        System.out.println("#### "+Arrays.toString(bArr)+" "+byteToHexString(bArr, 0, bArr.length)+ " ##### "+ StringUtils.byteToHexString(bArr));
        System.out.println(Bytes.toLong(Bytes.toBytesBinary("\\x00\\x00\\x01F\\xEA\\x124\\x00")));
        System.out.println(Bytes.toLong(Bytes.toBytesBinary("\\x00\\x00\\x00\\x00\\x00\\x00\\x08\\xD4")));
        System.out.println(Bytes.toString(Bytes.toBytesBinary("\\x07product\\x01")));
        System.out.println(Bytes.toString(Bytes.toBytes("\\x48\\x65\\x6c\\x6c\\x6f\\x20\\x48\\x42\\x61\\x73\\x65")));
        System.out.println( Long.MAX_VALUE + " : " + Bytes.toStringBinary(Bytes.toBytes(Long.MAX_VALUE)));
        System.out.println(Bytes.toInt(Bytes.toBytesBinary("\\x00\\x00\\x03\\xE9"))); //1001

        bArr = BigInteger.valueOf(67163).toByteArray();

        System.out.println("#### "+Arrays.toString(bArr)+" "+byteToHexString(bArr,  0, bArr.length )+ " ##### "+ StringUtils.byteToHexString(bArr));

        ByteBuffer bb = ByteBuffer.allocate(4);
        bb.putInt(67163);
        bArr = bb.array();

        System.out.println("#### "+Arrays.toString(bArr)+" "+byteToHexString(bArr,  0, bArr.length )+ " ##### "+ StringUtils.byteToHexString(bArr));

        bArr = Bytes.toBytes(67163);

        System.out.println("#### "+Arrays.toString(bArr)+" "+byteToHexString(bArr,  0, bArr.length )+ " ##### "+ StringUtils.byteToHexString(bArr));

        System.out.println("___________________________________");

        bArr = "Hello HBase".getBytes();
        System.out.println("#### "+Arrays.toString(bArr)+" "+byteToHexString(bArr, 0, bArr.length)+ " ##### "+ StringUtils.byteToHexString(bArr));

        // -------- Bytes.toBytes() : converting objects to bytes ------
        int i = 10000;
        byte [] intBytes = Bytes.toBytes(i);
        System.out.println ("int " + i + " in bytes : " + Arrays.toString(intBytes)+" OR "+Bytes.toStringBinary(intBytes));

        float f = (float) 999.993;
        byte [] floatBytes = Bytes.toBytes(f);
        System.out.println ("float " + f + " in bytes : " + Arrays.toString(floatBytes)+" OR "+Bytes.toStringBinary(floatBytes));

        String st = "foobar";
        byte [] stringBytes = Bytes.toBytes(st);
        System.out.println ("string " + st + " in bytes : " + Arrays.toString(stringBytes)+" OR "+Bytes.toStringBinary(stringBytes) );

        /* output:
        int 10000 in bytes : [0, 0, 39, 16]
        float 999.993 in bytes : [68, 121, -1, -115]
        string foobar in bytes : [102, 111, 111, 98, 97, 114]
        */

        // ----------------- String Utils : bytes <--> hex ---------
        String s = "aj89y1_xxy";
        byte[] b = s.getBytes();
        String hex = StringUtils.byteToHexString(b);
        byte[] b2 = StringUtils.hexStringToByte(hex);
        String s2 = new String(b2);
        System.out.println(s + " --> " + hex + " <-- " + s2);

        /* output:
        aj89y1_xxy --> 616a383979315f787879 <-- aj89y1_xxy
        */
    }

    /**
     * Get a row
     */
    public static void getOneRecord(String tableName, String rowKey) throws IOException {
        HTable table = new HTable(configuration, tableName);
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

    /**
     * Scan (or list) a table
     */
    public static void getAllRecord(String tableName) {
        try {
            HTable table = new HTable(configuration, tableName);
            Scan s = new Scan();
            ResultScanner ss = table.getScanner(s);
            for (Result r : ss) {
                for (KeyValue kv : r.raw()) {
                    System.out.print(new String(kv.getRow()) + " ");
                    System.out.print(new String(kv.getFamily()) + ":");
                    System.out.print(new String(kv.getQualifier()) + " ");
                    System.out.print(kv.getTimestamp() + " ");
                    System.out.println(new String(kv.getValue()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] hexToBytes(char[] hex) {
        //System.out.println(hex);
        int length = hex.length / 2;
        byte[] raw = new byte[length];
        for (int i = 0; i < length; i++) {
            int high = Character.digit(hex[i * 2], 16);
            int low = Character.digit(hex[i * 2 + 1], 16);
            int value = (high << 4) | low;
            if (value > 127)
                value -= 256;
            raw[i] = (byte) value;
        }
        return raw;
    }

    public static byte[] hexToBytes(String hex) {
        return hexToBytes(hex.toCharArray());
    }

    public static String byteToHexString(byte[] bytes, int start, int end) {
        if (bytes == null) {
            throw new IllegalArgumentException("bytes == null");
        }
        StringBuilder s = new StringBuilder();
        for(int i = start; i < end; i++) {
            s.append("\\x"+String.format("%02x", bytes[i]));
        }
        return s.toString();
    }
}
