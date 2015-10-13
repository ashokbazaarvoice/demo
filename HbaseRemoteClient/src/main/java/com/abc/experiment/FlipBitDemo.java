package com.abc.experiment;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 4/12/15
 * Time: 9:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class FlipBitDemo {
    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(1 << 2));
        System.out.println(Integer.toBinaryString((1 << 6) - 1));

        // 0001 1100 ; i = 3 , j = 5

        // 0001 0000 => 0001 1111 ===>   (1<<(j-1)) -1

        // 0000 0100 => 0000 0011 ===>   (1<<(i-1)) -1

        //    ^         0001 1100 ===> XOR

        // ~            1110 0011 ===> bitmask

        // n & bitmask ===> will clear bits from j to i

        // m << i  ==>  clearing last i bits and moving content so that it can be ORed with clear bits

    }

    public void method(int n, int m, int j, int i){
        int left = (1 << (j+1)) - 1; // 0011 1111 j=5
        int right = (1 << (i+1)); // 0000 0100 ==== i = 1
        int tmp1 = m & left; // 0101 0101 & 0011 1111 => 0001 0101
        int tmp2 = m & right; // 0101 0101 & 0000 0011 => 0000 0001

    }
}
