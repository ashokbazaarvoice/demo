package com.abc.idea.excercise;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 6/20/14
 * Time: 8:45 AM
 * To change this template use File | Settings | File Templates.
 */
public class BinaryTreeDemo {

    class Node {
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public Node root;

    public Node insert(Node node, int data){
        if(node==null)
           node = new Node(data);
        if(data <= node.data)
            node.left = insert(node.left, data);
        else
            node.right = insert(node.right, data);
        return node;
    }

    public boolean lookup(Node node, int data){
        if(node==null)
            return false;
        if(data == node.data)
            return true;
        if(data < node.data)
            return lookup(node.left, data);
        else
            return lookup(node.right, data);
    }

    public int size(Node node){
        if(node==null)
            return 0;
        else
            return (size(node.left)+1+size(node.right));
    }

    public int maxDepth(Node node){
        if(node==null)
            return 0;
        else
            return Math.max(maxDepth(node.left), maxDepth(node.right))+1;
    }

    //TODO: This has to be remembered
    public int maxValue(Node node){
        Node current = node;
        while(current.right!= null)
            current = current.right;
        return current.data;
    }

    public void printTree(Node node){
        if(node==null)
            return;
        printTree(node.left);
        System.out.print(node.data + "  ");
        printTree(node.right);
    }

    public boolean hasPathSum(Node node, int sum){
        if(node==null)
            return(sum == 0);
        else {
            int newSum = sum - node.data;
            return (hasPathSum(node.left, newSum) || hasPathSum(node.right, newSum));
        }
    }

    public boolean isBST(Node node,int MIN ,int MAX){
        if(node == null)
            return true;
        if(node.data > MIN && node.data < MAX)
            return true;
        else {
            boolean leftOk = isBST(node.left, node.data, MAX);
            if(!leftOk) return false;
            boolean rightOk = isBST(node.right, MIN, node.data);
            return rightOk;
        }
    }

    public void traverse(Node node)
    {
        if(node == null)
            System.out.println("Empty tree");
        else
        {
            Queue<Node> q= new LinkedList<Node>();
            q.add(node);
            while(q.peek() != null)
            {
                Node temp = q.remove();
                System.out.println(temp.data);
                if(temp.left != null)
                    q.add(temp.left);
                if(temp.right != null)
                    q.add(temp.right);
            }
        }
    }



    public void breadth(Node root) {
        Queue<Node> queue = new LinkedList<Node>() ;
        if (root == null)
            return;
        queue.clear();
        queue.add(root);
        while(!queue.isEmpty()){
            Node node = queue.remove();
            System.out.print(node.data + " ");
            if(node.left != null) queue.add(node.left);
            if(node.right != null) queue.add(node.right);
        }

    }

}
