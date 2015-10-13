package com.abc.experiment.excercise;

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
       // l1.add(7);
        System.out.println(l1.size() + " : " + l1.get(1));
        System.out.println("Middle Element " + middleElementTwo(l1));

        System.out.println("Loop: Middle Element " + middleElementLoop(l1));
        System.out.println("Loop: Middle Element " + middleElementLoopV2(l1));
    }

    // approach 1
    private static String middleElementLoop(LinkedList l1) {
        int end = l1.size();
        int start = 0;
//        System.out.println(start+" : "+end);
        while (start < end) {
            start++;
            end--;
//            System.out.println(start+" : "+end);
        }
        if (start == end)
            return l1.get(start).toString();
        else
            return l1.get(start - 1).toString();
    }

    // approach 2
    private static String middleElementLoopV2(LinkedList l1) {
        int end = l1.size() - 1;
        int start = 0;
        int startFast = 0;

        while (startFast < end) {
            start++;
            startFast = startFast+2;
        }

        return l1.get(start).toString();

    }

    private static String middleElementTwo(LinkedList l1) {
        int mid = (l1.size()) / 2;
        return (l1.get(mid).toString());
    }

}
