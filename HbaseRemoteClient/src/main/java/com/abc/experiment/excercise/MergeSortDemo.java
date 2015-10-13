package com.abc.experiment.excercise;

import java.util.Arrays;

/**
 * Created by ashok.agarwal on 5/24/15.
 */
public class MergeSortDemo {
    public static void main(String[] args) {
        int[] intArray = {2, 6, 10, 7, 8, 5, 9};
        System.out.println(Arrays.toString(intArray));
        sort2(intArray, 0, 6);
        System.out.println(Arrays.toString(intArray));
    }

    public static void sort(int[] intArray, int start, int end){
        int length = end - start;
//        if(length < 1){
//            return;
//        }
//        if(length == 1){
//            // arrange two numbers or swap them in increasing order
//            if(intArray[start] > intArray[end])
//                swap(intArray, start, end);
//            return;
//        }
        // Get the index of the element which is in the middle
        int mid = start+end >> 1;
        if(length >> 1 != 0){
             mid = (start+end >> 1)+1;
        }
        System.out.println(Arrays.toString(intArray)+ " : "+start+ " : "+end + " : "+mid);
        sort(intArray, start, mid-1);
        sort(intArray, mid, end);
        // call merge for above two partial sort from start to end
        merge(intArray, start, end, mid);
    }
    static void swap(int[] intArray, int from, int to){
        int value = intArray[from];
        intArray[from] = intArray[to];
        intArray[to] = value;
    }

    static void merge(int[] intArray, int start, int end, int mid){
        System.out.println("merge : "+Arrays.toString(intArray)+ " : "+start+ " : "+end + " : "+mid);
        while (mid <= end && start <= mid) {
            if (intArray[start] > intArray[mid]) {
                swap(intArray, start, mid);
                mid++;
            } else {
                start++;
            }
        }

//        for(int i = start ; i < mid; i++ ) {
//            if (i+mid < end && intArray[i] > intArray[i+mid])
//                swap(intArray, i, i+mid );
//        }
    }

//    public static void sort(int[] intArray, int start, int end){
//        if(end > start) {
//            int mid = start + ((end - start) / 2);
//            System.out.println(Arrays.toString(intArray) + " : " + start + " : " + end + " : " + mid);
//            sort(intArray, start, mid);
//            sort(intArray, mid+1, end);
//            // call merge for above two partial sort from start to end
//            merge(intArray, start, end, mid);
//        }
//    }


    public static void sort2(int[] intArray, int start, int end){
        if(end > start) {
            int mid = ((end + start) / 2);
            System.out.println(Arrays.toString(intArray) + " : " + start + " : " + end + " : " + mid);
            sort2(intArray, start, mid);
            sort2(intArray, mid + 1, end);
            // call merge for above two partial sort from start to end
            merge2(intArray, start, end, mid + 1);
        }
    }

    static void merge2(int[] intArray, int start, int end, int mid) {
        System.out.println("merge : " + Arrays.toString(intArray) + " : " + start + " : " + end + " : " + mid);
//        int length = end - start;
        int j = start;
        while (start <= mid-1 && mid <= end) {
            if (intArray[start] > intArray[mid]) {
                swap(intArray, start, mid);
                //mid++;
            } else {
                start++;
            }
//            j++;
            System.out.println("merge - while : " + Arrays.toString(intArray) + " : " + start + " : " + end + " : " + mid);
        }
        if(start == mid-1) { // Copy rest of first half
            if (intArray[start] > intArray[mid]) {
                swap(intArray, j, start);
            }
            j++;
            start++;
        }
//
//        while(mid < end) { // Copy rest of right half
//            swap(intArray, j, mid);
//            j++;
//        }
    }
}
