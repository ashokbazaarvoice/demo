package com.abc.idea;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 6/11/14
 * Time: 11:43 AM
 * To change this template use File | Settings | File Templates.
 */
import java.util.*;

public class MergerSort
{
    public static void main(String[] args)
    {
        //Integer[] a = {2, 6, 3, 5, 1};
        int[] a = {2, 6, 3, 5, 1};
        mergeSort(a);
        System.out.println(Arrays.toString(a));
    }

    public static void mergeSort(int[] a)
    {
        mergeSort(a, 0,  a.length - 1);
    }


    private static void mergeSort(int[] a, int left, int right)
    {
        if( left < right )
        {
            int center = (left + right) / 2;
            mergeSort(a,  left, center);
            mergeSort(a, center + 1, right);
            merge(a, left, center + 1, right);
        }
    }


    private static void merge(int[ ] a, int left, int right, int rightEnd )
    {
        int leftEnd = right - 1;
        int k = left;
        int num = rightEnd - left + 1;
        int[] tmp = new int[a.length];

        while(left <= leftEnd && right <= rightEnd)
            if(a[left]<=a[right])
                tmp[k++] = a[left++];
            else
                tmp[k++] = a[right++];

        while(left <= leftEnd)    // Copy rest of first half
            tmp[k++] = a[left++];

        while(right <= rightEnd)  // Copy rest of right half
            tmp[k++] = a[right++];

        // Copy tmp back
        for(int i = 0; i < num; i++, rightEnd--)
            a[rightEnd] = tmp[rightEnd];
    }

    public static int[] mergeSort2(int[] list) {
        if (list.length <= 1) {
            return list;
        }

        // Split the array in half
        int[] first = new int[list.length / 2];
        int[] second = new int[list.length - first.length];
        System.arraycopy(list, 0, first, 0, first.length);
        System.arraycopy(list, first.length, second, 0, second.length);

        // Sort each half
        mergeSort(first);
        mergeSort(second);

        // Merge the halves together, overwriting the original array
        merge(first, second, list);
        return list;
    }

    private static void merge(int[] first, int[] second, int [] result) {
        // Merge both halves into the result array
        // Next element to consider in the first array
        int iFirst = 0;
        // Next element to consider in the second array
        int iSecond = 0;

        // Next open position in the result
        int j = 0;
        // As long as neither iFirst nor iSecond is past the end, move the
        // smaller element into the result.
        while (iFirst < first.length && iSecond < second.length) {
            if (first[iFirst] < second[iSecond]) {
                result[j] = first[iFirst];
                iFirst++;
            } else {
                result[j] = second[iSecond];
                iSecond++;
            }
            j++;
        }
        // copy what's left
        System.arraycopy(first, iFirst, result, j, first.length - iFirst);
        System.arraycopy(second, iSecond, result, j, second.length - iSecond);
    }
}