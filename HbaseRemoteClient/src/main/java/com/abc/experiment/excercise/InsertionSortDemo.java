package com.abc.experiment.excercise;

import java.util.Arrays;

/**
 * Created by ashok.agarwal on 5/24/15.
 */
public class InsertionSortDemo {
    public static void main(String[] args) {
        int[] intArray = {2, 6, 3, 7, 8, 5, 9};
        System.out.println(Arrays.toString(intArray));
        sort(intArray);
        System.out.println(Arrays.toString(intArray));
    }
    public static void sort(int[] intArray){
        int length = intArray.length;
        for(int i = 1; i < length-1; i++){
            //insert intArray[i] into sorted list
            int tmp = intArray[i];
            for(int x = i-1; x >= 0; x--){
                if(tmp > intArray[x]) {
                    //insert
                    intArray[x+1] = tmp;
                    break;
                }else{
                   //shift element
                    intArray[x+1]= intArray[x];
                }
            }
        }
    }

}
