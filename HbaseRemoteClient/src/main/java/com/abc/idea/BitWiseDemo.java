package com.abc.idea;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 3/17/15
 * Time: 2:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class BitWiseDemo {

    public static void main(String args[]) {
        int a = 60;	/* 60 = 0011 1100 */
        int b = 13;	/* 13 = 0000 1101 */
        int c = 0;

        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toBinaryString(b));

        c = a & b;       /* 12 = 0000 1100 */
        System.out.println("a & b = " + c + " ("+Integer.toBinaryString(c)+")");

        c = a | b;       /* 61 = 0011 1101 */
        System.out.println("a | b = " + c + " ("+Integer.toBinaryString(c)+")");

        c = a ^ b;       /* 49 = 0011 0001 */
        System.out.println("a ^ b = " + c + " ("+Integer.toBinaryString(c)+")");

        c = ~a;          /*-61 = 1100 0011 */
        System.out.println("~a = " + c + " ("+Integer.toBinaryString(c)+")");
        //1111 1111 1111 1111 1111 1111 1100 0011

        c = a << 2;     /* 240 = 1111 0000 */
        System.out.println("a << 2 = " + c + " ("+Integer.toBinaryString(c)+")");

        c = a >> 2;     /* 15 = 1111 */
        System.out.println("a >> 2  = " + c + " ("+Integer.toBinaryString(c)+")");

        c = a >>> 2;     /* 15 = 0000 1111 */
        System.out.println("a >>> 2 = " + c + " ("+Integer.toBinaryString(c)+")");

        int bitmask = 0x000F;     //   1111
        int val = 0x2222;         // 10 0010 0010 0010
        System.out.println(bitmask+ " ("+Integer.toBinaryString(bitmask)+")"+" "+val+ " ("+Integer.toBinaryString(val)+")");
        // prints "2"
        System.out.println(val & bitmask);
    }
}
