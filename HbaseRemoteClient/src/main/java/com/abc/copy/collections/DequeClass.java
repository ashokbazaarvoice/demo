package com.abc.copy.collections;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 10/14/14
 * Time: 7:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class DequeClass {
    public static void main(String[] args) {
        Deque dequeA = new LinkedList();
        Deque dequeB = new ArrayDeque(); // Resizeable array

        dequeA.add     ("element 1"); //add element at tail
        dequeA.addFirst("element 2"); //add element at head
        dequeA.addLast ("element 3"); //add element at tail

        dequeA.add("element 0");
        dequeA.add("element 1");
        dequeA.add("element 2");

        //access via Iterator
        Iterator iterator = dequeA.iterator();
        while(iterator.hasNext()){
            String element = (String) iterator.next();
        }

        //access via new for-loop
        for(Object object : dequeA) {
            String element = (String) object;
        }
    }
}
