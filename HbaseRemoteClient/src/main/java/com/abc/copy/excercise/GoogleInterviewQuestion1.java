package com.abc.copy.excercise;

/**
 * Created by ashok.agarwal on 6/30/15.
 * You are given four integers 'a', 'b', 'y' and 'x', where 'x' can only be either zero or one. Your task is as follows:

 If 'x' is zero assign value 'a' to the variable 'y', if 'x' is one assign value 'b' to the variable 'y'.

 You are not allowed to use any conditional operator (including ternary operator).
 Follow up: Solve the problem without utilizing arithmetic operators '+ - * /'.
 */
public class GoogleInterviewQuestion1 {
    public static void main(String[] args) {
        int a=0,b=0,y=0,x=0;
        y = (1 - x) * a + x * b;


//
//        if(x&1&true)
//            y=a;
//        else
//            y=b;
    }

//    int any_bits_to_one(unsigned int n) {
//        int result = 0, i;
//
//        for (i=0; !result && i < sizeof(unsigned int) * 8; i++)
//        result |= (n & (1<<i)) ? 1 : 0;
//
//        return result;
//    }
}
