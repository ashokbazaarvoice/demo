package com.jbksoft.hadoop;

import org.apache.hadoop.hbase.util.Bytes;

import java.security.MessageDigest;

/**
 * Created by ashok.agarwal on 4/30/15.
 */
public class HBaseRowKey {
    public static void main(String[] args) throws Exception {
        // long
//
        long l = 1234567890L;
        byte[] lb = Bytes.toBytes(l);
        System.out.println("long bytes length: " + lb.length);   // returns 8

        String s = String.valueOf(l);
        byte[] sb = Bytes.toBytes(s);
        System.out.println("long as string length: " + sb.length);    // returns 10

// hash
//
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest = md.digest(Bytes.toBytes(s));
        System.out.println("md5 digest bytes length: " + digest.length);    // returns 16

        String sDigest = new String(digest);
        byte[] sbDigest = Bytes.toBytes(sDigest);
        System.out.println("md5 digest as string length: " + sbDigest.length);    // returns 26
    }
}
