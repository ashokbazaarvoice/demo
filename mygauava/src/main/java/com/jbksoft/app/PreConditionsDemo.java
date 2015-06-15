package com.jbksoft.app;

import com.google.common.base.Preconditions;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 3/20/15
 * Time: 5:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class PreConditionsDemo {

    public static void main(String[] str){
        String s = "Hello";
       // print(s);
        print(null);
    }

    public static void print(Object obj){
       // Preconditions.checkArgument(obj!=null);
        Preconditions.checkNotNull(obj, "Invalid argument", obj);
        System.out.println(obj);
    }
}
