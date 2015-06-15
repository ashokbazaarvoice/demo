package com.jbksoft.app;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 4/12/15
 * Time: 8:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class BitDemo {
    // the bits
    public static void main(String[] args) {
        System.out.println("hello");
        System.out.println(50>>1);
        System.out.println(50<<1);
        System.out.println("hello");
        System.out.println(getBit(4, 1));


        int bitmask = 0x000F; // the bitmask means 0000 1111 this if ANDed then it will hide the content of bits with zero(b7 to b4) and show (b3 to b0).
                              // same bitmask when ORed then it will set/make the content(b3 to b0) change but the zero(b7 to b4) will be opaque.

        // To set or turn on a flag bit(s)

//        flags = flags | MASK;

        // or, more succintly

//        flags |= MASK;

        int val = 0x2222;
        System.out.println(bitmask+" : "+val);// 15 : 8738
        System.out.println(Integer.toBinaryString(bitmask)+" : "+Integer.toBinaryString(val)); // 1111 : 10 0010 0010 0010
        // prints "2" : mask has covered the content of bytes where the was zero in mask and only visible content is for 1s.
        // Note the b13 to b4 are default are 0 for the bit mask.
        System.out.println(val & bitmask);


        //Check if number is positive or negative

        val = -10;
        System.out.println(" val in binary: "+Integer.toBinaryString(val));
        if (val >> 31 == 0) {
            System.out.println("positive");
        } else {
            System.out.println("negative");
        }

        // Make negative number positive or vice versa
        System.out.println("Positive number : "+((~val)+1));
        System.out.println("Positive number : "+((val^0xFFFFFFFF)+1));


        // Math.abs(): returns positive for any integer(even negatives) like |-3| = 3


    }

    // b7 to b0 the index is 0 to 7

    static boolean getBit(int n, int index) {
        return ( (n & (1 << index) ) > 0);
    }
    // b here true then set
    // false then unset.
    int setBit(int n, int index, boolean b) {
        if(b)
            return (n | (1 << index)) ;
        else {
            int mask = ~(1 << index);
            return n & mask;
        }
    }
}
