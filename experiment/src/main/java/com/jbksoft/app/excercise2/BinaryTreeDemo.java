package com.jbksoft.app.excercise2;

import com.jwetherell.algorithms.data_structures.CompactSuffixTrie;

/**
 * Created by ashok.agarwal on 6/12/15.
 */
public class BinaryTreeDemo {
}

// BinaryTree is using int then comparison becomes easy but when T (generic class) then we need it to extends Comparable
// So easy is take primitive numbers for data field in node.
class BinaryTree<T extends Comparable<? super T>>{

// class BinaryTree<T extends Comparable>{

    Node<T> root;
    boolean isEmpty(){
        return false;
    }
    int size(){
        return 0;
    }

    Node add(Node<T> node, T data){
        if(node==null){
            node = new Node(data,null, null);
        }
        if(data.compareTo(node.data) < 0) {
            node.left = add(root.left, data);
        }

        return node;
    }

}
class Node<T> implements Comparable<T>{

    Node left;
    Node right;
    T data;

    Node(T data, Node left, Node right) {
        this.left = left;
        this.right = right;
        this.data = data;
    }

    @Override
    public int compareTo(T o) {
        return 0;
    }
}
