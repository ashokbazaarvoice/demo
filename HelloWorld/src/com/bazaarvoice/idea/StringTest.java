package com.bazaarvoice.idea;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 5/12/14
 * Time: 11:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class StringTest {

    public static void main(String[] args){
        String str1 = "Hello";
        String str2 = new String("Hello");
        String str3 = "Hello";

        System.out.println(str1.intern());
        System.out.println(str2.intern());
        System.out.println(str3.intern());
        if(str1==str2)
            System.out.println(true);
        else
            System.out.println(false);

        if(str1==str3)
            System.out.println(true);
        else
            System.out.println(false);

        if(str3==str2)
            System.out.println(true);
        else
            System.out.println(false);

        if("Hello"=="Hello")
            System.out.println(true);
        else
            System.out.println(false);
    }
}
