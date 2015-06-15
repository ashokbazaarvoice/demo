package com.jbksoft.app.excercise;

import java.util.Arrays;

/**
 * Created by ashok.agarwal on 5/25/15.
 */
public class MergeSortDemoV2 {
    public static void main(String[] args) {
        int[] intArray = {2, 6, 10, 7, 8, 5, 9};
        System.out.println(Arrays.toString(intArray));
        int[] intTempArray = new int[intArray.length];
        sort(intArray, intTempArray, 0, 6);
        System.out.println(Arrays.toString(intArray));
    }

    public static void sort(int[] intArray, int[] intTempArray, int start, int end) {
        if (end > start) {
            int mid = ((end + start) / 2);
            System.out.println(Arrays.toString(intArray) + " : " + start + " : " + end + " : " + mid);
            sort(intArray, intTempArray, start, mid);
            sort(intArray, intTempArray, mid + 1, end);
            // call merge for above two partial sort from start to end
            merge1(intArray, intTempArray, start, mid, end);
        }
    }

    static void merge(int[] intArray, int[] intTempArray, int start, int mid, int end) {
        int num = end - start + 1;
        int j = start;
        int leftEnd = mid - 1;
        while (start <= leftEnd && mid <= end) {
            if (intArray[start] < intArray[mid]) {
                intTempArray[j] = intArray[start];
                start++;
            } else {
                intTempArray[j] = intArray[mid];
                mid++;
            }
            j++;
            System.out.println("merge - while : " + Arrays.toString(intTempArray) + " : " + start + " : " + end + " : " + mid);
        }
        while (start <= leftEnd) { // Copy rest of first half
            intTempArray[j++] = intArray[start++];
        }

        while (mid <= end) { // Copy rest of right half
            intTempArray[j++] = intArray[mid++];
        }

        // Copy tmp back
        for (int i = 0; i < num; i++, end--)
            intArray[end] = intTempArray[end];
    }

    static void merge1(int[] a, int[] aux, int lo, int mid, int hi) {
        int num = hi - lo + 1;
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid)
                aux[k] = a[j++];
            else if (j > hi)
                aux[k] = a[i++];
            else if (a[j] < a[i])
                aux[k] = a[j++];
            else
                aux[k] = a[i++];
        }

        // Copy tmp back
        for (int x = 0; x < num; x++, hi--)
            a[hi] = aux[hi];
    }
}
