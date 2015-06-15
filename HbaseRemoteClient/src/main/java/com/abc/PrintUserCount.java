package com.abc; /**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 4/25/14
 * Time: 11:33 AM
 * To change this template use File | Settings | File Templates.
 */
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.util.Bytes;

public class PrintUserCount {

    public static void main(String[] args) throws Exception {

        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.master", "192.168.120.105:60010");
        conf.set("hbase.zookeeper.quorum.", "192.168.120.105");
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        HTable htable = new HTable(conf, "summary_user");

        Scan scan = new Scan();
        ResultScanner scanner = htable.getScanner(scan);
        Result r;
        while (((r = scanner.next()) != null)) {
            ImmutableBytesWritable b = r.getBytes();
            byte[] key = r.getRow();
            int userId = Bytes.toInt(key);
            byte[] totalValue = r.getValue(Bytes.toBytes("details"), Bytes.toBytes("total"));
            int count = Bytes.toInt(totalValue);

            System.out.println("key: " + userId+ ",  count: " + count);
        }
        scanner.close();
        htable.close();
    }
}
