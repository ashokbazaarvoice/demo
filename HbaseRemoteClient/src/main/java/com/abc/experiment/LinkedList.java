package com.abc.experiment;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 4/5/15
 * Time: 1:56 PM
 * To change this template use File | Settings | File Templates.
 */
public interface LinkedList<T> {
    /**
     * The node is created and assigned to node.next if previous node is not null;
     * Node start/head = new Node(22);
     * head.next = new Node(33);
     * head.next.next = new Node(44);
     * head.next.next.next = new Node(55);
     *
     * @param t
     */
    void add(T t);
}
