package com.abc;

import org.apache.hadoop.hbase.util.Bytes;

import java.security.MessageDigest;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 4/3/15
 * Time: 2:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class MessageDigestDemo {
    public static void main(String[] args) throws Exception{
        // long
//
        long l = 1234567890L;
        byte[] lb = Bytes.toBytes(l);
        System.out.println("long bytes length: " + lb.length+" "+HBaseByteTest.byteToHexString(lb, 0, lb.length));   // returns 8

        String s = "" + l;
        byte[] sb = Bytes.toBytes(s);
        System.out.println("long as string length: " + sb.length+" "+HBaseByteTest.byteToHexString(sb, 0, sb.length));    // returns 10

// hash
//
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest = md.digest(Bytes.toBytes(s));
        System.out.println("md5 digest bytes length: " + digest.length+" "+HBaseByteTest.byteToHexString(digest, 0, digest.length));    // returns 16

        String sDigest = new String(digest);
        byte[] sbDigest = Bytes.toBytes(sDigest);
        System.out.println("md5 digest as string length: " + sbDigest.length+" "+HBaseByteTest.byteToHexString(sbDigest, 0, sbDigest.length));    // returns 26


        l = 123L;
        lb = Bytes.toBytes(l);
        System.out.println("long bytes length: " + lb.length+" "+HBaseByteTest.byteToHexString(lb, 0, lb.length));   // returns 8

         s = "" + l;
        sb = Bytes.toBytes(s);
        System.out.println("long as string length: " + sb.length+" "+HBaseByteTest.byteToHexString(sb, 0, sb.length));    // returns 3
    }
}
