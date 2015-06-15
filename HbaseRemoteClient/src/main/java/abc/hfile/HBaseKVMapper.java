package abc.hfile;

import au.com.bytecode.opencsv.CSVParser;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 6/26/14
 * Time: 3:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class HBaseKVMapper extends
        Mapper<LongWritable, Text, ImmutableBytesWritable, KeyValue> {

    final static byte[] SRV_COL_FAM = "cf".getBytes();
    final static int NUM_FIELDS = 7;

    CSVParser csvParser = new CSVParser();
//    String tableName = "";

    ImmutableBytesWritable hKey = new ImmutableBytesWritable();
    KeyValue kv;

    /** {@inheritDoc} */
    @Override
    protected void setup(Context context) throws IOException,
            InterruptedException {
        Configuration c = context.getConfiguration();
//        tableName = c.get("hbase.table.name");
    }

    /** {@inheritDoc} */
    @Override
    protected void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {

        String[] fields = null;

        try {
            fields = csvParser.parseLine(value.toString());
        } catch (Exception ex) {
            context.getCounter("HBaseKVMapper", "PARSE_ERRORS").increment(1);
            return;
        }

        if (fields.length != NUM_FIELDS) {
            context.getCounter("HBaseKVMapper", "INVALID_FIELD_LEN").increment(1);
            return;
        }


        //10001,1953-09-02,Georgi,Facello,M,1986-06-26,d005
        // Key: e.g. "10001:d005"
        hKey.set(String.format("%s:%s", fields[0], fields[6])
                .getBytes());

        // Service columns
        if (!fields[0].equals("")) {
            kv = new KeyValue(hKey.get(), SRV_COL_FAM,
                    "empid".getBytes(), fields[0].getBytes());
            context.write(hKey, kv);
        }

        if (!fields[1].equals("")) {
            kv = new KeyValue(hKey.get(), SRV_COL_FAM,
                    "dob".getBytes(), fields[1].getBytes());
            context.write(hKey, kv);
        }

        if (!fields[2].equals("")) {
            kv = new KeyValue(hKey.get(), SRV_COL_FAM,
                    "fn".getBytes(), fields[2].getBytes());
            context.write(hKey, kv);
        }

        if (!fields[3].equals("")) {
            kv = new KeyValue(hKey.get(), SRV_COL_FAM,
                    "ln".getBytes(), fields[3].getBytes());
            context.write(hKey, kv);
        }

        if (!fields[4].equals("")) {
            kv = new KeyValue(hKey.get(), SRV_COL_FAM,
                    "g".getBytes(), fields[4].getBytes());
            context.write(hKey, kv);
        }

        if (!fields[5].equals("")) {
            kv = new KeyValue(hKey.get(), SRV_COL_FAM,
                    "doj".getBytes(), fields[5].getBytes());
            context.write(hKey, kv);
        }

        if (!fields[6].equals("")) {
            kv = new KeyValue(hKey.get(), SRV_COL_FAM,
                    "did".getBytes(), fields[6].getBytes());
            context.write(hKey, kv);
        }
        context.getCounter("HBaseKVMapper", "NUM_MSGS").increment(1);

    }
}
