package com.jbksoft.app;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 4/5/15
 * Time: 1:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class EmptyWhileDemo {
    public static void main(String[] args){
        int i = 0;
        while(++i < 10);
        System.out.println(i);
        int j;
        for(j =0; j<10;j++);
        System.out.println(j);

        for (i = 1, j = 5; i <= 5 && j > 0; i = i - 1, j = j-1) {
            System.out.println("Inside For Loop");
        }
    }
}
