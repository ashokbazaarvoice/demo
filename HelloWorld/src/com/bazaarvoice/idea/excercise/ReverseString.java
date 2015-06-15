package com.bazaarvoice.idea.excercise;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 6/15/14
 * Time: 12:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class ReverseString {

    public static void main(String[] args){
        String str = "Test";
        char[] chars = str.toCharArray();
        for(char c:chars)
            System.out.println(c);

        StringBuffer sb = new StringBuffer();
        for(int i = chars.length-1; i > -1;i--)
            sb.append(chars[i]);
        System.out.println(sb);
        ReverseString rs = new ReverseString();
        System.out.println(rs.reverse(chars));
    }

    public String reverse(char[] chars){
        if(chars.length == 1 )
            return String.valueOf(chars[0]);
        char[] charNew = Arrays.copyOf(chars, chars.length-1);
        return chars[chars.length-1]+reverse(charNew);
    }


    public String reverse2(String str){
        //if(str.length()== 1)
            return str;
        //return str.substring(0, )
    }

}
