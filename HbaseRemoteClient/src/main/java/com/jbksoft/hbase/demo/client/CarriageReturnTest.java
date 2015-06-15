package com.jbksoft.hbase.demo.client;

import org.apache.commons.codec.binary.Hex;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 10/20/14
 * Time: 2:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class CarriageReturnTest {
    public static void main(String[] args) {
        String str = "This is a string.\nThis is a long string.\r\nThis is a string.\rThis is a long string.";
        str = str.replaceAll("(\r\n|\r)", "\n");
        System.out.println(str);

        int i = 10000;
        byte [] intBytes = Bytes.toBytes(i);
        System.out.println ("int " + i + " in bytes : " + Arrays.toString(intBytes)+" "+ intBytes);

        float f = (float) 999.993;
        byte [] floatBytes = Bytes.toBytes(f);
        System.out.println ("float " + f + " in bytes : " + Arrays.toString(floatBytes));

        String s = "foobar";
        byte [] stringBytes = Bytes.toBytes(s);
        System.out.println ("string " + s + " in bytes : " + Arrays.toString(stringBytes));

        byte[] buff = "\r".getBytes();
        System.out.println(buff);


        for(byte c : buff) {
            System.out.format("%d ", c);
        }
        System.out.println();
        System.out.println(Hex.encodeHexString(buff));

    }
}
