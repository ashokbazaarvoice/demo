package com.abc.idea;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 5/15/14
 * Time: 11:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class SmallestDemo {
    public static void main(String[] args) {
        int[] input = {4, 7, 3, 2, 5, 9, 6, 1};
        print(input);
//        System.out.println(getSmallest(input));
//        System.out.println(getBiggest(input));
//        System.out.println("insertionSort");
//        print(insertionSort(input));
//        int[] input1 = {4, 7, 3, 2, 5, 9, 6, 1};
//        System.out.println("selectionSort");
//        print(selectionSort(input1));
//        int[] input2 = {4, 7, 3, 2, 5, 9, 6, 1};
//        System.out.println("bubbleSort");
//        print(bubbleSort(input2));
    }

    static void print(int[] input) {
        for (int i : input) {
            System.out.print(i + ", ");
        }
        System.out.println();
    }

    static int getSmallest(int[] input) {
        int result = input[0];
        for (int i = 1; i < input.length; i++) {
            if (input[i] < result)
                result = input[i];
        }
        return result;
    }

    static int getBiggest(int[] input) {
        int result = input[0];
        for (int i = 1; i < input.length; i++) {
            if (input[i] > result)
                result = input[i];
        }
        return result;
    }

    // insert the next element to left sorted sub list (creating sorted ascending list)
    static int[] insertionSort(int[] input) {
        for (int i = 1; i < input.length; i++) {
            //System.out.println("i : "+i);
            int n = i;
            for (int m = i - 1; m > -1; m--) {
                // System.out.println(m);
                if (input[n] > input[m])
                    continue;
                else {
                    int temp = input[m];
                    input[m] = input[n];
                    input[n] = temp;
                    n = m;
                }
                print(input);
            }
            //print(input);
        }
        return input;
    }

    // insert the next element to left sorted sub list (creating sorted ascending list)
    static int[] insertionSort3(int[] input) {
        for (int i = 1; i < input.length; i++) {
            //System.out.println("i : "+i);
            int result = input[i];
            int m;
            for (m = i - 1; m > -1; m--) {
                // System.out.println(m);
                if (input[m] > result) {
                    input[m + 1] = input[m];
                }
                print(input);
            }
            if (m == -1)
                input[m + 1] = result;
            else
                input[m + 2] = result;
            //print(input);
        }
        return input;
    }

    private static int[] insertionSort2(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int valueToSort = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] > valueToSort) {
                arr[j] = arr[j - 1];
                j--;
                print(arr);
            }
            arr[j] = valueToSort;
            //print(arr);
        }
        return arr;
    }

    // traverse un sorted list and select smallest/largest element and shift it to right/left, leading to sorted sub list on right/left.
    static int[] selectionSort(int[] input) {
        for (int i = 0; i < input.length; i++) {
            int result = i;
            //System.out.println("i : "+i);
            for (int n = i + 1; n < input.length; n++) {
                //System.out.println(n);
                if (input[n] < input[result]) {
                    result = n;
                }
                print(input);
            }
            int temp = input[result];
            input[result] = input[i];
            input[i] = temp;
            //print(input);
        }
        return input;
    }

    // traverse unsorted list and swap two adjacent numbers in order, leading to smallest/largest on to extreme right
    static int[] bubbleSort(int[] input) {

        for (int i = 0; i < input.length; i++) {
            for (int n = 1; n < input.length - i; n++) {
                if (input[n - 1] > input[n]) {
                    int temp = input[n];
                    input[n] = input[n - 1];
                    input[n - 1] = temp;
                }
                print(input);
            }
            //print(input);
        }
        return input;
    }

    // traverse unsorted list and swap two adjacent numbers in order, leading to smallest/largest on to extreme right
    static int[] mergeSort(int[] input) {
        int size = input.length;
        if (size <= 1)
            return input;
        else {

        }
        return input;
    }
}
