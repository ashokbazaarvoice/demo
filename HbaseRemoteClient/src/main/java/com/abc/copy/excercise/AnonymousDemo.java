package com.abc.copy.excercise;

/**
 * Created by ashok.agarwal on 7/2/15.
 */
public class AnonymousDemo {
    public static void main(String[] args){
        MyInterface myInterface = new MyInterface() {
            @Override
            public void run() {
                System.out.println("Hello");
            }
        };
    }
}

interface MyInterface {
    void run();
}
