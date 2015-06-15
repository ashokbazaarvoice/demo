package com.jbksoft.hbase.demo.client;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 4/24/14
 * Time: 11:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class EqualsDemo {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        Object ob = new Object();

        if (ob == null || EqualsDemo.class != ob.getClass())
            System.out.println("False");
        else
            System.out.println("True");

        if(ob instanceof EqualsDemo)
            System.out.println("True");
        else
            System.out.println("False");
    }
}
