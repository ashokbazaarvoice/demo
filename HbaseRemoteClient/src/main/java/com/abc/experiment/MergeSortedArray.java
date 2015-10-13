package com.abc.experiment;

import java.util.Arrays;

/**
 * Created by ashok.agarwal on 6/8/15.
 */
public class MergeSortedArray {

    public static void main(String[] args) {
        int[] a = {1,3,5,0,0,0,0};
        int[] b = {2,4,6,8};
        merge(a,b);
        System.out.println(Arrays.toString(a));
    }

    public static void merge(int[] a, int[] b) {
        int i = 0, j = 0;
        int elementLenA = a.length - b.length;
        while(i < a.length && j < b.length) {
            if (a[i] > b[j]) {
                System.arraycopy(a, i, a, i + 1, a.length - i-1);
                a[i] = b[j];
                j++;
                System.out.println(Arrays.toString(a));
            } else
                i++;
        }
        System.out.println(i+" : "+j+" : "+elementLenA+" : "+b.length+" : "+Arrays.toString(a));
        if(i < a.length)
            System.out.println(i);
        if(j < b.length) {
            //System.out.println(i+" : "+j+" : "+elementLenA+" : "+b.length+" : "+Arrays.toString(a));
            System.arraycopy(b, j, a, elementLenA + j, b.length - j);
        }
    }
}
