package com.jbksoft.app.excercise;

import java.util.LinkedList;

/**
 * Created by ashok.agarwal on 6/14/15.
 */
public class MiddleElementLinkedList {

    public static void main(String args[]) {
        LinkedList l1 = new LinkedList();
        l1.add(2);
        l1.add(3);
        l1.add(1);
        l1.add(6);
        l1.add(8);
        System.out.println("Middle Element " + middleElementTwo(l1));

        System.out.println("Loop: Middle Element " + middleElementLoop(l1));
    }

    private static String middleElementLoop(LinkedList l1) {
        int end = l1.size() - 1;
        int start = 0;

        while (start > end) {
            start++;
            end--;
        }
        if (start == end)
            return (String) l1.get(start);
        else
            return (String) l1.get(start - 1);
    }

    private static String middleElementTwo(LinkedList l1) {
        int mid = (l1.size()) / 2;
        return (l1.get(mid).toString());
    }

}
