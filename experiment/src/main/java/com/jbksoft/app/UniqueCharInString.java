package com.jbksoft.app;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 3/31/15
 * Time: 12:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class UniqueCharInString {

    public static void main(String[] args) {
        // Integer max value is 2 billion in other word 2^32 (2 raised to power 32), range is 2^31 -1 to -2^31
        // The int data type is a 32-bit signed two's complement integer.
        // It has a minimum value of -2,147,483,648 ( -2^31) and a maximum value of 2,147,483,647(2^31 - 1) (inclusive)

        System.out.println(Integer.MAX_VALUE+":"+Integer.toBinaryString(Integer.MAX_VALUE) + //// 2 147 483 647 : 0111 1111 1111 1111 1111 1111 1111 1111
                " \n " + Integer.toHexString(15)+ " : "+Integer.toOctalString(15) // f : 17
                +" \n "+ Float.toHexString(15.5f) ); // need to understand

        System.out.println(Integer.MIN_VALUE+" : "+Integer.toBinaryString(Integer.MIN_VALUE)); // - 2 147 483 648 : 1000 0000 0000 0000 0000 0000 0000 0000

        System.out.println(Integer.MAX_VALUE+":"+Long.parseLong("ff", 16) + " " + Integer.parseInt("1111111111111111111111111111111", 2));

        System.out.println(-8+" : "+Integer.toBinaryString(-8)+":"+(-8 & Integer.MAX_VALUE)+" : "+Integer.toBinaryString(-8 & Integer.MAX_VALUE)+ " "+(Integer.MAX_VALUE-8));

        System.out.println("hello".substring(1,3)); // el

        System.out.println(Arrays.toString("hello world".split(" ")));
        System.out.println(Arrays.toString("hello world".split("\\s")));
        System.out.println(Arrays.toString("hello world".split("\\s+")));

        for (char c : "Hello".toCharArray()) {
            int n = c; // notice this char to int
            System.out.println(c+ " "+(int)c + " " + n);
        }
        int number = 2; //0010

        //example of bitwise unary complement operator (~)
        System.out.println(" value of number before: " + number);
        System.out.println(" value of number after negation: " + ~number + " "+Integer.toBinaryString(~number));
        System.out.println(" value of number after 2's complment : " + Integer.toBinaryString(-number));

        //So, 2 is the binary value  00000010
        //And ~2 flips the bits so the value is now:

        //11111101
        //Which, is the binary representation of -3.

        System.out.println((1<<2)+" "+Integer.toBinaryString(1<<2)); // when 001 shifts two position left then it becomes 00100
        System.out.println((8>>2)+" "+Integer.toBinaryString(8>>2)); // when 001000 shifts two position right then it becomes 0010
        System.out.println(1<<-2);
        System.out.println("****** "+Integer.toBinaryString(1073741824)); // 100 0000 0000 0000 0000 0000 0000 0000 .... 1 is now at 31 position.
        System.out.println(1<<3);
        System.out.println("@@@@@ "+Integer.toBinaryString(-8)+" "+Integer.toBinaryString(-4));
        System.out.println(-8>>1);
        System.out.println(1<<26);

        System.out.println("##### "+Integer.toBinaryString(128));
        System.out.println(Integer.toBinaryString(-128));
        System.out.println(Integer.toBinaryString(-25));
        System.out.println(Integer.toBinaryString(25));
        System.out.println(getUniqueChar("Hello"));
        System.out.println(isUniqueChars("Hello"));
        getUniqueCharSet("Hello");
    }

    // method returning array of unique chars in provided string
    public static char[] getUniqueChar(String str) {
        char[] c = str.toCharArray();
//        Set ch = new HashSet(Arrays.asList(c));
//        char[] cnew = Chars.toArray(ch);

//        char[] cnew = new char[ch.size()];
//        int i=0;
//        for(Object character : ch.toArray()) {
//            cnew[i] = ;
//            i++;
//        }
        Set<Character> ch = new HashSet<Character>();
        for (char chr : c) {
            ch.add(chr);
        }
        char[] cnew = new char[ch.size()];
        int i = 0;
        for (Character character : ch) {
            cnew[i] = character;
            i++;
        }
        return cnew;
    }


    public static void getUniqueCharSet(String str) {
        char[] c = str.toCharArray();
        Set<Character> ch = new HashSet<Character>();
        for (char chr : c) {
            ch.add(chr);
        }
//        char[] cnew = new char[ch.size()];
//        int i = 0;
//        for (Character character : ch) {
//            cnew[i] = character;
//            i++;
//        }
//        return cnew;
        System.out.println(Arrays.toString(ch.toArray()));
        //return ch;
    }

    //For simplicity, assume char set is ASCII (if not, we need to
    // increase the storage size The rest of the logic would be the same)
    // NOTE: This is a great thing to point out to your interviewer!
    public static boolean isUniqueChars2(String str) {
        boolean[] char_set = new boolean[256];
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i);
            if (char_set[val]) {
                return false;
            }
            char_set[val] = true;
        }
        return true;
    }

    public static void getUniqueChars2(String str) {
        boolean[] char_set = new boolean[256];
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i);
            if (char_set[val]) {
                continue;
            }
            char_set[val] = true;
            System.out.println(str.charAt(i));
        }

    }



    public static boolean isUniqueChars(String str) {
        int checker = 0;
        for (int i = 0; i < str.length(); ++i) {
            int val = str.charAt(i) - 'a';
            System.out.println(str.charAt(i)+" "+val+" "+ (1 << val));
            if ((checker & (1 << val)) > 0) {
                return false;
            }
            checker |= (1 << val);

        }
        return true;
    }
}