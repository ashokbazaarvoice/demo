package com.abc.copy;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 5/7/14
 * Time: 12:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class BitWiseMultiply {

    public static void main(String[] args) {

        bitwiseMul(5, 5);
        bitwiseMul(5, 4);
        System.out.println(Mul(5, 5));
        System.out.println(Add(3, 5));
    }


    static int Add(int x, int y) {
        // Iterate till there is no carry
        while (y != 0) {
            // carry now contains common set bits of x and y
            int carry = x & y;

            // Sum of bits of x and y where at least one of the bits is not set
            x = x ^ y;

            // Carry is shifted by one so that adding it to x gives the required sum
            y = carry << 1;
        }
        return x;
    }

    public static int Mul(int a, int b) {
        int r = 0;
        while (b != 0) {
            if ((b & 1) != 0) {
                r = r + a;
            }
            a = a << 1;
            b = b >> 1;
        }
        return r;
    }

    public static void bitwiseMul(int n1, int n2) {
    /* This value will hold n1 * 2^i for varying values of i.  It will
     * start off holding n1 * 2^0 = n1, and after each iteration will
     * be updated to hold the next term in the sequence.
     */
        int a = n1;

    /* This value will be used to read the individual bits out of n2.
     * We'll use the shifting trick to read the bits and will maintain
     * the invariant that after i iterations, b is equal to n2 >> i.
     */
        int b = n2;

    /* This value will hold the sum of the terms so far. */
        int result = 0;

    /* Continuously loop over more and more bits of n2 until we've
     * consumed the last of them.  Since after i iterations of the
     * loop b = n2 >> i, this only reaches zero once we've used up
     * all the bits of the original value of n2.
     */
        while (b != 0)
        {
            System.out.println("Enter a=[" + a + "], b=[" + b + "], result=[" + result + "]");
        /* Using the bitwise AND trick, determine whether the ith
         * bit of b is a zero or one.  If it's a zero, then the
         * current term in our sum is zero and we don't do anything.
         * Otherwise, then we should add n1 * 2^i.
         */
            if ((b & 1) != 0)
            {
            /* Recall that a = n1 * 2^i at this point, so we're adding
             * in the next term in the sum.
             */
                result = result + a;
            }

        /* To maintain that a = n1 * 2^i after i iterations, scale it
         * by a factor of two by left shifting one position.
         */
            a <<= 1;

        /* To maintain that b = n2 >> i after i iterations, shift it
         * one spot over.
         */
            b >>= 1;

            System.out.println("Exit a=[" + a + "], b=[" + b + "], result=[" + result + "]");
        }

        System.out.println(result);
    }
}
