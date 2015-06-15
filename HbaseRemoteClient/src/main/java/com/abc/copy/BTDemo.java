package com.abc.copy;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 4/23/14
 * Time: 6:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class BTDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.insert(8);
        binaryTree.insert(7);
        binaryTree.insert(3);
        binaryTree.insert(5);
        binaryTree.insert(4);
        binaryTree.insert(10);
        binaryTree.insert(9);
        binaryTree.insert(11);
        binaryTree.printTree();
    }
}
