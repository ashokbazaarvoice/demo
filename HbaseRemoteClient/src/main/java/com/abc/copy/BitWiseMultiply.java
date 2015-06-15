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
        System.out.println(bitwiseMultiply(5,5));
        System.out.println(Add(3,5));
    }
    public static int bitwiseMultiply(int a, int b) {
        if (a ==0  || b == 0) {
            return 0 ;
        }


        if (a == 1 ) {
            return b ;
        } else if (b == 1) {
            return a ;
        }


        int result = 0 ; // not needed, just for test
        int initA = a ;
        boolean isORNeeded = false ;
        while (b != 0 ) {

            if (b == 1) {
                break ;
            }
            if ((b & 1) == 1) {// carry needed, odd number
                result = Add(result,initA) ; // test, not needed
                isORNeeded = true ;

            }

            a <<= 1 ; // double the a
            b >>= 1 ; // half the b
            System.out.println("a=["+a+"], b=["+b+"], result=["+result+"]") ;
        }

        return (isORNeeded ? (a | initA) : a)  ; // a + result ;

    }

    static int Add(int x, int y)
    {
        // Iterate till there is no carry
        while (y != 0)
        {
            // carry now contains common set bits of x and y
            int carry = x & y;

            // Sum of bits of x and y where at least one of the bits is not set
            x = x ^ y;

            // Carry is shifted by one so that adding it to x gives the required sum
            y = carry << 1;
        }
        return x;
    }
}
