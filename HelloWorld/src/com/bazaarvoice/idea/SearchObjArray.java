package com.bazaarvoice.idea;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 6/12/14
 * Time: 1:31 PM
 * To change this template use File | Settings | File Templates.
 */

public class SearchObjArray {
    public static void main(String [] args) {
        String [] sa = {"one", "two", "three", "four"};
        Arrays.sort(sa);
        for(String s : sa)
            System.out.print(s + " ");
        System.out.println("\none = "+ Arrays.binarySearch(sa,"one"));  // #2
        System.out.println("now reverse sort");
        ReSortComparator rs = new ReSortComparator();
        Arrays.sort(sa,rs);
        for(String s : sa)
            System.out.print(s + " ");
        System.out.println("\none = " + Arrays.binarySearch(sa,"one"));  // #4
        System.out.println("one = " + Arrays.binarySearch(sa,"one",rs));  // #5
}
    static class ReSortComparator implements Comparator<String> {
        public int compare(String a, String b) {
            return b.compareTo(a);
        }
    }
}
