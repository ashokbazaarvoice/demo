package com.abc.idea;

/**
 * Created by ashok.agarwal on 6/8/15.
 */
public class BinarySearchDemo {
    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        int start = 0;
        int end = a.length - 1; // 8
        int i = search(a, start, end, 5);
        //int i = search(a, 2);
        System.out.println(i);
    }

    public static int search(int[] a, int start, int end, int x) {
        System.out.println(start + " : " + end);
        if (start <= end) {
            int mid = (end + start) / 2; // start +(end - start) / 2
            if (a[mid] < x) {
                return search(a, mid + 1, end, x);
            } else if (a[mid] > x) {
                return search(a, start, mid - 1, x);
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static int search(int[] a, int x) {
        int start = 0;
        int end = a.length;
        while (start < end) {
            int mid = (end + start) / 2; // start +(end - start) / 2
            if (a[mid] < x) {
                start = mid + 1;
            } else if (a[mid] > x) {
                end = mid;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static int binarySearch(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2; // int mid = (lo + hi) / 2;
            if (key < a[mid])
                hi = mid - 1;
            else if (key > a[mid])
                lo = mid + 1;
            else
                return mid;
        }
        return -1;
    }

    // This will search first or last occurrence of key in sorted array having duplicate elements.
    public static int binarySearch2(int[] a, int key) {
        int lo = 0, result = -1;
        int hi = a.length - 1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2; // int mid = (lo + hi) / 2;
            if (key < a[mid])
                hi = mid - 1;
            else if (key > a[mid])
                lo = mid + 1;
            else {
                result = mid;
                //hi = mid - 1; // for first
                // lo = mid +1; //last occurrence
            }
        }
        return result;
    }

}
