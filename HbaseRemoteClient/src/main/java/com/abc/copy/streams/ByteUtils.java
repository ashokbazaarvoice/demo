package com.abc.copy.streams;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 7/7/14
 * Time: 6:12 PM
 * To change this template use File | Settings | File Templates.
 *
 * To convert reference to byte array is just do .getBytes() like "Hello HBase".getBytes("UTF-16") but for int.getBytes
 * not possible so there are below three methods which can convert primitives to bye array.
 * or try https://code.google.com/p/guava-libraries/wiki/PrimitivesExplained#Byte_conversion_methods
 */
public class ByteUtils {
    private static ByteBuffer buffer = ByteBuffer.allocate(Long.SIZE);

    public static byte[] longToBytes(long x) {
        buffer.putLong(0, x);
        return buffer.array();
    }

    public static long bytesToLong(byte[] bytes) {
        buffer.put(bytes, 0, bytes.length);
        buffer.flip();//need flip
        return buffer.getLong();
    }


    public static byte[] toBytes(long val) {
        byte [] b = new byte[8];
        for (int i = 7; i > 0; i--) {
            b[i] = (byte) val;
            val >>>= 8;
            System.out.println(val);
        }
        b[0] = (byte) val;
        return b;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Hello Hbase".getBytes());
        long l = 127l;
        System.out.println(toBytes(l)+ "     "+Arrays.toString(toBytes(l)));
        l = 128l;
        System.out.println(toBytes(l)+ "     "+Arrays.toString(toBytes(l)));
        l = 256l;
        System.out.println(toBytes(l)+ "     "+Arrays.toString(toBytes(l)));
        l = 257l;
        System.out.println(toBytes(l)+ "     "+Arrays.toString(toBytes(l)));
        System.out.println(intToByteArray(125)+ "     "+Arrays.toString(intToByteArray(125)));
    }

    //Using BigInteger:

    private byte[] bigIntToByteArray( final int i ) {
        BigInteger bigInt = BigInteger.valueOf(i);
        return bigInt.toByteArray();
    }
    //Using DataOutputStream:

    private static byte[] intToByteArray ( final int i ) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
//        dos.writeLong(i); this is where we tell how to convert data into long, int etc.
        dos.writeInt(i);
        dos.flush();
        return bos.toByteArray();
    }
    //Using ByteBuffer:

    public byte[] intToBytes( final int i ) {
        ByteBuffer bb = ByteBuffer.allocate(4);
        bb.putInt(i);
        return bb.array();
    }
}
