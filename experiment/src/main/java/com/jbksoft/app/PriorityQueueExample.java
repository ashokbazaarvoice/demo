package com.jbksoft.app;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by ashok.agarwal on 5/15/15.
 */
public class PriorityQueueExample {
    public static void main(String[] args){
        PriorityQueue priorityQueue = new PriorityQueue();
        priorityQueue.add("a");
        priorityQueue.add("g");
        priorityQueue.add("d");
        priorityQueue.add("b");
        System.out.println(Arrays.toString(priorityQueue.toArray()));
    }
}
