package com.abc.experiment;

import com.google.common.base.Joiner;
import com.google.common.collect.ArrayTable;
import com.google.common.collect.Table;

import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!".substring(2,4)); // ll
        System.out.println( "Hello World!".substring(2,5)); // llo

        int[] letter = new int[5];
        System.out.println(Arrays.toString(letter));
        ++letter[0];
        letter[1]++;
        System.out.println(Arrays.toString(letter));
        // Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 5
        //letter[5] = 5;

        String[] myString = new String[5];
        myString[0] = "Ram";

        // ++myString[0]; ++ cannot be applied to string

        myString[1] = "Sita";
        for (String st : myString) {
            System.out.println(st);
        }

        int i = 2;
        myString[i++] = "RamSita";

        System.out.println(Arrays.toString(myString) + " "+i); // set content at index 2. Value will get change to 3

        myString[--i] = "SitaRam";

        System.out.println(Arrays.toString(myString) + " "+i); // i is 3, value will get change to 2, then value at array index 2 sets.

        int[][] my2D = {{1,2},{3,4},{5,6}};
        System.out.println(Arrays.toString(my2D)+" : "+my2D.length + " is 3 but "+my2D[0].length + " is 2.");

//        Table<Integer, Integer, String> table =  ArrayTable.create(my2D);


        // using bits or bytes for calculations
        byte b = 0;
        byte b1 = 4;

        b = (byte)(b | b1); // Here OR is setting value into byte b as b is blank; if b is not blank then it will
                            // mean setting/turning on the bits by bitmask b1;

        // You can "set" a bit in a number by "or"-ing with another number with that bit (and only that bit) set to 1.

        //  10000001 | 00100000 = 10100001 /* turned on bit 5 */
        //  10000001 | 1 << 5 = 10100001 /* did the same thing

        System.out.println(b);

        //  You can turn off a bit by anding with a binary number of all 1's, except for the bit to be set.
        //  01010101 & ~(1<<2) == 01010101 & 11111011 == 01010001
        //  in this the bit b2 is turned off

        // for turning any bit on OR it and turning it off AND it with 0
        // for finding the content of the bits at positions, AND it with bitmask having bits 1 for those positions.

        System.out.println(b1&0x7f); // Here using AND we are extracting bytes content as the bit length of the holder is 7 so 0x7f (111 1111)
        System.out.println("$$$$$$$$$$$$$$$$$ "+Integer.toBinaryString(0x7f)+" : "+Integer.toBinaryString(0x1));
        System.out.println(b1&0x1); // Here extracting value of just one bit and discarding other bits.
                                    // As 0x1 is 1(bitmask with only 1 b0 set) but it can be also written 0000 0001 but b1 is 0000 0100.
                                    // 0000 0100 & 0000 0001 => 0000 0000 if it was 1 then it will give 1 and it was 0 so got 0
                                    // in this calculations are done on b0 only so result is 0

        b = 1;
        System.out.println(b&0x1); // 0000 0001 & 0000 0001 => 0000 0001 it was 1 so got 1

        System.out.println(1&0x1); // Consider 1 is one bit(Like holding gender, true-false),
                                    // Here using AND we are extracting bit content

        int v = 0;      // we want to find the sign of v
        int sign;   // the result goes here

        // CHAR_BIT is the number of bits per byte (normally 8).
//        sign = -(v < 0);  // if v < 0 then -1, else 0.

        // the reason is the sign bit is 0 for positive numbers and 1 for negative numbers

        sign = v < 0 ? -1: 0;
//        // or, to avoid branching on CPUs with flag registers (IA32):
//        sign = -(int)((unsigned int)((int)v) >> (sizeof(int) * CHAR_BIT - 1));
//        // or, for one less instruction (but not portable):
//        sign = v >> (sizeof(int) * CHAR_BIT - 1);


        int x = 7, y= 100;               // input values to compare signs

        boolean f = ((x ^ y) < 0); // true iff x and y have opposite signs as the numbers in bit 32 will have b31 as 0

        System.out.println(x+"("+Integer.toBinaryString(x)+")"+" and "+y+"("+Integer.toBinaryString(y)+")"+" have opposite signs : "+f);
        System.out.println(Integer.toBinaryString(x^y));
        int r;  // the result goes here

//        r = y ^ ((x ^ y) & -(x < y)); // min(x, y)

        //int v; // count the number of bits set in v
        int c; // c accumulates the total bits set in v

        v=7;
        for (c = 0; v>0; v >>= 1) // v >>= 1  means v = v >> 1
        {
            c += v & 1;
        }

        System.out.println(c);

        v &= v - 1; // clear the least significant bit set
    }


}
