package com.jbksoft.hfile;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.io.hfile.CacheConfig;
import org.apache.hadoop.hbase.io.hfile.HFile;
import org.apache.hadoop.hbase.io.hfile.HFileScanner;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.DataInputBuffer;
import org.apache.hadoop.io.Text;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 9/10/14
 * Time: 4:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class HFileLookup {
//    public static void main(String[] args) throws IOException {
//        Configuration conf = new Configuration();
//        FileSystem fs = FileSystem.get(conf);
//        HFile.Reader hfileReader =
//                HFile.createReader(fs, new Path(args[0]), new CacheConfig(conf));
//        hfileReader.loadFileInfo();
//        HFileScanner hfileScanner = hfileReader.getScanner(false, false, false);
//        Rule rule = new Rule(args[1], "");
//        RuleWritable ruleWritable = RuleWritable.makeSourceMarginal(rule);
//        byte[] ruleBytes = Util.object2ByteArray(ruleWritable);
//        int success = hfileScanner.seekTo(ruleBytes);
//        if (success == 0) { // found the source rule
//            ArrayWritable targetsAndFeatures =
//                    Util.bytes2ArrayWritable(hfileScanner.getValue());
//            for (int i = 0; i < targetsAndFeatures.get().length; i++) {
//                GeneralPairWritable3 elt =
//                        (GeneralPairWritable3) targetsAndFeatures.get()[i];
//                String out =
//                        ruleWritable.toString() + " "
//                                + elt.getFirst().toString();
//                SortedMapWritable features = elt.getSecond();
//                for (Writable featureIndex: features.keySet()) {
//                    out += " " + features.get(featureIndex) + "@"
//                            + featureIndex;
//                }
//                System.err.println(out);
//            }
//        }
//        else {
//            System.err.println("Source " + ruleWritable.toString()
//                    + " not found");
//        }
//    }


    public static void main(String[] args) throws Exception {
        String hfileInput = args[0];
        String fileOutput = args[1];
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);

        BufferedWriter bw = new BufferedWriter(new FileWriter(fileOutput));
//        HFile.Reader hfileReader = new HFile.Reader(fs, new Path(hfileInput), null, false);

        HFile.Reader reader =
                HFile.createReader(fs, new Path(args[0]), new CacheConfig(conf));

        Map<byte[], byte[]> fileInfo = reader.loadFileInfo();

        for (Map.Entry<byte[], byte[]> e : fileInfo.entrySet()) {
            System.out.print( "    " + Bytes.toString(e.getKey()) + " = ");
            System.out.println(Bytes.toStringBinary(e.getValue()));
        }

//        HFileScanner scanner = hfileReader.getScanner();
        HFileScanner scanner = reader.getScanner(false, false, false);
//        KeyValueStatsCollector fileStats = null;
        scanner.seekTo();


        do {

            //System.out.println(scanner.getKey()+ " $$$$$ "+scanner.getValue());
            KeyValue kv = scanner.getKeyValue();
            System.out.print(new String(kv.getRow()) + " ");
            System.out.print(new String(kv.getFamily()) + ":");
            System.out.print(new String(kv.getQualifier()) + " ");
            System.out.print(kv.getTimestamp() + " ");
            System.out.println(new String(kv.getValue()));
//            ImmutableBytesWritable key = bytes2ImmutableBytesWritable(scanner.getKey());
//            KeyValue value = bytes2KeyValue(scanner.getValue());
//            for (int i = 0; i < value.get().length; i++) {
//
//                bw.write(key.getLeftHandSide()
//                        + " "
//                        + key.getSource()
//                        + " "
//                        + ((PairWritable3) value.get()[i]).first
//                        .getTarget());
//                Writable[] features =
//                        ((PairWritable3) value.get()[i]).second.get();
//                for (int j = 0; j < features.length; j++) {
//                    bw.write(" " + ((DoubleWritable) features[j]).get());
//                }
//                bw.write("\n");
//            }

//            System.out.println(key.toString()+ " $$$$$ "+value.toString());

//            Text txt = bytes2Writable(scanner.getKey());

        }
        while (scanner.next());

    }

    private static ImmutableBytesWritable bytes2ImmutableBytesWritable(ByteBuffer bytes) {
        DataInputBuffer in = new DataInputBuffer();
        in.reset(bytes.array(), bytes.arrayOffset(), bytes.limit());
        ImmutableBytesWritable value = new ImmutableBytesWritable();
        try {
            value.readFields(in);
        }
        catch (IOException e) {
            // Byte buffer is memory backed so no exception is possible. Just in
            // case chain it to a runtime exception
            throw new RuntimeException(e);
        }
        return value;
    }

    private static KeyValue bytes2KeyValue(ByteBuffer bytes) {
        DataInputBuffer in = new DataInputBuffer();
        in.reset(bytes.array(), bytes.arrayOffset(), bytes.limit());
        KeyValue value = new KeyValue();
        try {
            value.readFields(in);
        }
        catch (IOException e) {
            // Byte buffer is memory backed so no exception is possible. Just in
            // case chain it to a runtime exception
            throw new RuntimeException(e);
        }
        return value;
    }

    private static Text bytes2Writable(ByteBuffer bytes) throws IOException, ClassNotFoundException {
        BytesWritable res = bytes2BytesWritable(bytes);
        ByteArrayInputStream bais = new ByteArrayInputStream(res.getBytes());
        ObjectInputStream ois = new ObjectInputStream(bais);

        String line = ois.readObject().toString();
        System.out.println(line);

        DataInputBuffer in = new DataInputBuffer();
        in.reset(bytes.array(), bytes.arrayOffset(), bytes.limit());
        Text value = new Text();
        try {
            value.readFields(in);
        }
        catch (IOException e) {
            // Byte buffer is memory backed so no exception is possible. Just in
            // case chain it to a runtime exception
            throw new RuntimeException(e);
        }
        return value;
    }

    public static BytesWritable bytes2BytesWritable(ByteBuffer bytes) {
        byte[] bytesArr = new byte[bytes.limit()];
        for (int i = 0; i < bytes.limit(); i++) {
            bytesArr[i] = bytes.get(i);
        }
        BytesWritable res = new BytesWritable(bytesArr);
        return res;
    }
}
