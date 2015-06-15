package com.jbksoft.app;

import com.google.common.primitives.Chars;
import com.google.common.primitives.Ints;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ashok.agarwal on 6/10/15.
 */
public class ArrayDemo {
    public static void main( String[] args ){
        char[] c = "Hello".toCharArray();
        Set ch = new HashSet(Arrays.asList(c));
        char[] cnew = Chars.toArray(ch); // Waste as the ch has become word not array
        System.out.println(ch+" : "+Arrays.asList(c));//+Arrays.toString(cnew)

        ch = new HashSet(Chars.asList(c));
        System.out.println(Chars.asList(c)+" : "+ch);

        int[] integerArray = new int[]{3, 5, 7, -3};
        int indexOf = Ints.indexOf(integerArray, 7);
        System.out.println("index of "+7+" in "+Ints.asList(integerArray)+" is "+indexOf);

        long[] input = {1,2,3,4,5};
        //List<Long> = Arrays.asList(input); //Total failure to even compile!

    }
}
