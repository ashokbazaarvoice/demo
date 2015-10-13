package com.jbksoft.hadoop;

import com.google.common.base.Charsets;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.*;

import java.util.List;
import java.util.UUID;

/**
 * Created by ashok.agarwal on 2/27/14.
 */
public class HBaseDemo {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello");
        new HBaseDemo().testSimple();
//        Configuration config = HBaseConfiguration.create();
//        byte[] tablename = Bytes.toBytes("access_logs");
//        // Run some operations — a put, a get, and a scan — against the table.
//        HTable table = new HTable(config, tablename);
//        //    byte [] row1 = Bytes.toBytes("row1");
//        //Put p1 = new Put(row1);
//        //   byte [] databytes = Bytes.toBytes("data");
//        // p1.add(databytes, Bytes.toBytes("1"), Bytes.toBytes("value1"));
//        // table.put(p1);
//        // Get g = new Get(row1);
//        // Result result = table.get(g);
//        // System.out.println("Get: " + result);
//        Scan scan = new Scan();
//        ResultScanner scanner = table.getScanner(scan);
//        try {
//            for (Result scannerResult : scanner) {
//                System.out.println("Scan: " + scannerResult);
//
//            }
//        } finally {
//            scanner.close();
//        }
    }

  public void testSimple(){
        final Put put=new Put(UUID.randomUUID().toString().getBytes(Charsets.UTF_8));
        put.add("family1".getBytes(Charsets.UTF_8),"qualifier1".getBytes(Charsets.UTF_8),System.currentTimeMillis(),"data1".getBytes(Charsets.UTF_8));
        put.add("family2".getBytes(Charsets.UTF_8),"qualifier2".getBytes(Charsets.UTF_8),System.currentTimeMillis(),"data2".getBytes(Charsets.UTF_8));
        put.add("family3".getBytes(Charsets.UTF_8),"qualifier3".getBytes(Charsets.UTF_8),System.currentTimeMillis(),"data3".getBytes(Charsets.UTF_8));
//        final byte[] data=new BinaryConverter.PutToBinary().apply(put);
//        final Put put2=new BinaryConverter.StreamToPut().apply(new ByteArrayInputStream(data));
//        Assert.assertNotNull(put2);
//        Assert.assertArrayEquals(put.getRow(),put2.getRow());
      for (  final List<KeyValue> entries : put.getFamilyMap().values()) {
          for (    final KeyValue kv : entries) {
              //Assert.assertTrue(put2.has(kv.getFamily(),kv.getQualifier(),kv.getTimestamp(),kv.getValue()));
//              System.out.println(kv.getFamily()+" : "+kv.getQualifier()+" : "+kv.getTimestamp()+" : "+kv.getValue());
              System.out.print(new String(kv.getRow()) + " ");
              System.out.print(new String(kv.getFamily()) + ":");
              System.out.print(new String(kv.getQualifier()) + " ");
              System.out.print(kv.getTimestamp() + " ");
              System.out.println(new String(kv.getValue()));
          }
      }
        for (  final byte[] family : put.getFamilyMap().keySet()) {
//            Assert.assertTrue(put2.getFamilyMap().containsKey(family));
        }
//        Assert.assertEquals(put.getFamilyMap().size(),put2.getFamilyMap().size());
    }
}
