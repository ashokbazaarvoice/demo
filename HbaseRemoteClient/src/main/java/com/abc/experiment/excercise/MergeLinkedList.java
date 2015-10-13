package com.abc.experiment.excercise;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ashok.agarwal on 6/14/15.
 */
public class MergeLinkedList {

    static List<Integer> merge(List<Integer> list1, List<Integer> list2) {
        List<Integer> list = new LinkedList<Integer>();
        int k1 = 0;
        int k2 = 0;
        while (k1 < list1.size() && k2 < list2.size()) {
            if (list1.get(k1) < list2.get(k2)) {
                list.add(list1.get(k1++));
            } else {
                list.add(list2.get(k2++));
            }
        }

        while (k1 < list1.size()) {
            list.add(list1.get(k1++));
        }

        while (k2 < list2.size()) {
            list.add(list2.get(k2++));
        }

        return list;
    }

    public static void main(String[] args) {
        List<Integer> list1 = new LinkedList<Integer>();
        List<Integer> list2 = new LinkedList<Integer>();


        list1.add(0);
        list1.add(2);
        list1.add(4);
        list1.add(6);
        list2.add(1);
        list2.add(3);
        list2.add(5);


        System.out.println(merge(list1, list2));


    }

}
