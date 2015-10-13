package com.abc.copy;

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
        int A = 0x003c;
        int b = 13;	/* 13 = 0000 1101 */
        int B = 0x000d;
        int c = 0;
        int C = 0b10; // feature of java 8

        System.out.println(Integer.toBinaryString(a)+" "+A+" "+C);
        System.out.println(Integer.toBinaryString(b)+" "+B);

        c = a & b;       /* 12 = 0000 1100 */
        System.out.println("a & b = " + c + " ("+Integer.toBinaryString(c)+")");

        c = a | b;       /* 61 = 0011 1101 */
        System.out.println("a | b = " + c + " ("+Integer.toBinaryString(c)+")");

        c = a ^ b;       /* 49 = 0011 0001 */
        System.out.println("a ^ b = " + c + " ("+Integer.toBinaryString(c)+")");

        c = ~a;          /*-61 = 1100 0011 */ // Note negation turn number negative but 1 less(means -1 adds to the negative number)
        System.out.println("~a = " + c + " ("+Integer.toBinaryString(c)+")");
        //1111 1111 1111 1111 1111 1111 1100 0011

        c = -a;          /*-60 = 1111 1111 1111 1111 1111 1111 1100 0100 */
        System.out.println("-a = " + c + " ("+Integer.toBinaryString(c)+")");

        c = (~a)+1;          /*-60 = 1111 1111 1111 1111 1111 1111 1100 0100 */
        System.out.println("(~a)+1 = " + c + " ("+Integer.toBinaryString(c)+")");

        c = a << 2;     /* 240 = 1111 0000 */
        System.out.println("a << 2 = " + c + " ("+Integer.toBinaryString(c)+")");

        c = a >> 2;     /* 15 = 1111 */
        System.out.println("a >> 2  = " + c + " ("+Integer.toBinaryString(c)+")");

        c = a >>> 2;     /* 15 = 0000 1111 */
        System.out.println("a >>> 2 = " + c + " ("+Integer.toBinaryString(c)+")");

        c = -a >>> 2;     /*-60 = 0011 1111 1111 1111 1111 1111 1111 0001 */
        System.out.println("-a >>> 2 = " + c + " ("+Integer.toBinaryString(c)+")");

        c = -a >> 2;     /*-60 = 1111 1111 1111 1111 1111 1111 1111 0001 */
        System.out.println("-a >> 2 = " + c + " ("+Integer.toBinaryString(c)+")");

        int bitmask = 0x000F;     //   1111
        int val = 0x2222;         // 10 0010 0010 0010
        System.out.println(bitmask+ " ("+Integer.toBinaryString(bitmask)+")"+" "+val+ " ("+Integer.toBinaryString(val)+")");
        // prints "2"
        System.out.println(val & bitmask);

        System.out.println("Is 2 even number : "+isEven(2));
        System.out.println("Is 3 even number : "+isEven(3));
    }

    static boolean isEven(int x){
        return (0x0001&x) == 0;
    }

    // remember pos is starting from 0 like b0, b1, b2,... b7
    static int setBit(int n, int pos){
        return n | (1<<pos);
    }

    static int unSetBit(int n, int pos){
        return n & ~(1<<pos);
    }

    static boolean isBitSet(int n, int pos){
        return (n & (1<<pos)) > 0;
    }

    // flip the nth bit if set then unset or vice versa
    static int toggleBit(int n, int pos){
        return n  ^ (1<<pos);
    }

    //Turn off the rightmost 1-bit, so just 1 bit at any position but rightmost
    static int toggleBit(int n){
        return n  & (n-1);
    }

    // Isolate the rightmost 1-bit, so all bit other then rightmost 1-bit will be turned 0 and result will be just
    // one bit set in the new number.
    static int isolateBit(int n){
        return n  & (-n);
    }

    // all bits to the rightmost 1-bit are set to 1
}
