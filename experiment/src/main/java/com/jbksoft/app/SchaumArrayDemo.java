package com.jbksoft.app;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 4/20/15
 * Time: 1:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class SchaumArrayDemo {
    public static void main(String[] args){
        int[][] array = new int[3][4];
        System.out.println(array.length+" "+array[0].length);

        int[] array2 = {2,3,4,5, 7,8,9,10};
        int[] array3 = array2;
        int[] array4 = array2.clone();
        array4[6] = 100;
        System.out.println( Arrays.toString(array2));
        System.out.println("Alias Demo :"+ Arrays.toString(array3));
        System.out.println("Clone Demo :"+ Arrays.toString(array4));

        int[] arr0 = new int[]{1,2};

        int arr1[] = { 0, 1, 2, 3, 4, 5 };
        int arr2[] = { 0, 10, 20, 30, 40, 50 };

        System.arraycopy(arr1, 2, arr2, 0, 4);

        System.out.println(Arrays.toString(arr2));

        int[] src = new int[] {1, 2, 3, 4, 5};
        int b1[] = Arrays.copyOfRange(src, 0, 2);

        System.out.println(Arrays.toString(b1));
//
//
//        int[] copy = new int[src.length-1];
//        System.arraycopy(src, 1, copy, 0, src.length-1);
//        System.out.println(Arrays.toString(copy));

//        int[] src = new int[] {1, 2};
        int[] copy = myMethod(src);
        Arrays.sort(copy);
        System.out.println(Arrays.toString(copy));
    }

    public static int[] myMethod(int[] array){
        int i = array.length;
        if(i==1)
            return new int[]{-1, array[i-1]};
        else {
            int[] copy = new int[i-1];
            System.arraycopy(array,1,copy,0,i-1);
            return combine(array[0], myMethod(copy));
        }
    }

    public static int[] combine(int element, int[] array){
        int[] copy = new int[2*array.length];
        System.arraycopy(array,0,copy,0,array.length);
        for(int i = 0; i < array.length; i++){
            if(array[i] == -1) {
                copy[array.length + i] = element;
            } else {
                copy[array.length + i] = Integer.valueOf(element + "" + array[i]);
            }
        }
        return copy;
    }


//    ArrayList<ArrayList<Integer>> getSubsets(ArrayList<Integer> set, int index) {
//        ArrayList<ArrayList<Integer>> allsubsets;
//        if(set.size() == index) { //--- when size of set is zero so here index will index-1
//            allsubsets = new ArrayList<ArrayList<Integer>>();
//            allsubsets.add(new ArrayList<Integer>());
//        } else {
//            allsubsets = getSubsets(set, set.size() -)
//        }
//        return allsubsets;
//    }
}
