package com.abc.experiment.excercise;

import java.util.Arrays;

/**
 * Created by ashok.agarwal on 5/23/15.
 */
public class SelectionSortDemo {

    public static void main(String[] args){
        int[] intArray = {2,6,3,7,8,5,9};
        System.out.println(Arrays.toString(intArray));
        InPlace(intArray);
        System.out.println(Arrays.toString(intArray));
//        System.out.println(Arrays.toString(Sort(intArray)));
        int[] intArray2 = new int[0];
        InPlace(intArray2);
        System.out.println(Arrays.toString(intArray2));
        int[] intArray3 = {1};
        InPlace(intArray3);
        System.out.println(Arrays.toString(intArray3));
    }

    public static void InPlace(int[] intArray){
        int length = intArray.length;
        if(length < 2)
            return;
        while(length > 0){
            int tmp = 0;
            for(int i = 1; i < length; i++){
                if(intArray[i] > intArray[tmp])
                    tmp = i;
            }
            // swap tmp with element on nth end
            swap(intArray, tmp, length-1);
            length--;
        }
    }

    static void swap(int[] intArray, int from, int to){
        int value = intArray[from];
        intArray[from] = intArray[to];
        intArray[to] = value;
    }

    // TODO tough
    public static int[] Sort(int[] intArray){
        int length = intArray.length;
        int[] newArray = new int[length];
        while(length > 0){
            int tmp = 0;
            for(int i = 1; i < length; i++){
                if(intArray[i] > intArray[tmp]) {
                    tmp = i;
                }
//                System.out.print(length+" : "+i+" : "+intArray[tmp]+"; ");
            }
            newArray[length-1] = intArray[tmp];
            // TODO remove element tmp,
            //intArray[tmp]=0; // do not set zero as 5(element) got lost [0, 2, 3, 6, 7, 8, 9]

            length--;
        }
        return newArray;
    }
}
