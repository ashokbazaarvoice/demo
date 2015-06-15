package com.jbksoft.app.excercise;

import java.util.Arrays;

/**
 * Created by ashok.agarwal on 5/23/15.
 */
public class BubbleSortDemo {
    public static void main(String[] args) {
        int[] intArray = {2, 6, 3, 7, 8, 5, 9};
        System.out.println(Arrays.toString(intArray));
        sort(intArray);
        System.out.println(Arrays.toString(intArray));
    }

    public static void sort(int[] intArray){
        int length = intArray.length;
        while(length > 0){
            for(int i = 1; i < length - 1; i++) {
                //swap the number if it is greater
                if(intArray[i] < intArray[i-1])
                    swap(intArray,i-1, i);
            }
            length--;
        }
    }
    static void swap(int[] intArray, int from, int to){
        int value = intArray[from];
        intArray[from] = intArray[to];
        intArray[to] = value;
    }
}
