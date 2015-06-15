package com.jbksoft.bytelevel;

import org.apache.hadoop.io.*;
import org.apache.hadoop.util.StringUtils;

import java.io.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.Matchers.is;
/**
 * Created by ashok.agarwal on 6/5/15.
 */
public class HadoopSerializationHelper {
    public static byte[] serialize(Writable writable) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DataOutputStream dataOut = new DataOutputStream(out);
        writable.write(dataOut);
        dataOut.close();
        return out.toByteArray();
    }
    public static byte[] deserialize(Writable writable, byte[] bytes)
            throws IOException {
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        DataInputStream dataIn = new DataInputStream(in);
        writable.readFields(dataIn);
        dataIn.close();
        return bytes;
    }
    public static void main(String[] args) throws Exception {
        IntWritable writable = new IntWritable(163);
        byte[] bytes = serialize(writable);
        assertEquals(bytes.length, 4);
        System.out.println(bytes.length+" : "+ StringUtils.byteToHexString(bytes)+" : "+Integer.toString(163, 16)+ " : "+byteToHexString(bytes, 0, bytes.length));

        IntWritable newWritable = new IntWritable();
        deserialize(newWritable, bytes);
        assertEquals(newWritable.get(), 163);

        VIntWritable vint = new VIntWritable(163);
        bytes = serialize(vint);
        //assertEquals(bytes.length, 4);
        System.out.println(bytes.length+" : "+ StringUtils.byteToHexString(bytes)+ " : "+byteToHexString(bytes, 0, bytes.length));
        System.out.println(Integer.parseInt("8f", 16)+" : "+Integer.toBinaryString(143)); // 128+15 = 143

        BytesWritable b = new BytesWritable(new byte[] { 3, 5 });
        bytes = null;
        bytes = serialize(b);
        assertEquals(StringUtils.byteToHexString(bytes), "000000020305");
        System.out.println(byteToHexString(bytes, 0, bytes.length));
        assertThat(b.getLength(), is(2));
        assertThat(b.getBytes().length, is(2));

        Text text = new Text("hadoop");
        bytes = serialize(text);
        assertThat(StringUtils.byteToHexString(bytes), is("066861646f6f70"));
        System.out.println(byteToHexString(bytes, 0, bytes.length));
        for(byte b1 : bytes)
            System.out.println((char)b1);
        System.out.println(bytes[0]);
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
