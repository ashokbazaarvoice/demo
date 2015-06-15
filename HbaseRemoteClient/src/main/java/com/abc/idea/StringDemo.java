package com.abc.idea;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 3/30/15
 * Time: 10:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class StringDemo {
    public static void main(String[] args){
        int[] a = new int[5];
        a[0]=1;
        System.out.println(a.length+" "+ Arrays.toString(a));

        String[] str = new String[10];
        System.out.println(str.length+" "+ Arrays.toString(str));

        String s = new String("Hello");
        System.out.println(s.concat(" all"));
        System.out.println(s);

        StringBuffer sb = new StringBuffer("Hello");
        System.out.println(sb.append(" all"));
        System.out.println(sb);

        StringBuilder sbr = new StringBuilder("Hello");
        System.out.println(sbr.append(" all"));
        System.out.println(sbr);
    }
}
