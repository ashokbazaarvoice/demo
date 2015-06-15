package com.jbksoft.app;

/**
 * Created by ashok.agarwal on 6/10/15.
 */
public class PrimitiveDataTypeDemo {
    public static void main(String[] args){
        int a = 4;
        System.out.println(a/2.0);
        char c = 'a';
        int i = c;
        System.out.println(c+" : "+i+" : "+(int) c+" : "+(byte)c);
        System.out.println(2.0 * 4 / 5 + 6 / 4.0);
        System.out.println(4 - 1 + "x");
        //System.out.println("x" + 4 - 1);
        int aAsInt = 'a'; // evaluates to 97
        int x = 'a' + 2; // evaluates to 99
        char ch = (char)('a' + 2); // evaluates to 'c'
    }
}
