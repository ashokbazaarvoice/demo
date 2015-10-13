package com.abc.copy;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 10/21/14
 * Time: 3:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class LinkedListDemo {

    class Node {
        int data;
        Node next;
        Node(int data){
            this.data = data;
            this.next = null;
        }
    }

    public static Node head;

    public Node add(Node node, int data){
//        System.out.println("i m here");
        if(node==null) {
            node = new Node(data);
        }
        else
            node.next = add(node.next, data);
        return node;
    }

    public Node remove(Node node, int data){
        if(node==null)
            return null;
        else {
            if(node.data == data)
                return node.next;
            else
                return remove(node.next, data);
        }
    }

    public Node removeNode(Node currnode, int data){
        if(currnode==null)
            return currnode;
        else {
            if(currnode.data == data){
                //replace the pointer
                currnode = currnode.next;
                return currnode;
            } else {
                Node head = currnode;
                while(currnode.next != null){
                    if(currnode.next.data == data) {
                        currnode.next = currnode.next.next;
                        return head;
                    }
                    currnode = currnode.next;
                }
                return head;
            }
        }
    }



    public boolean removeNode(Node currnode, Node prevnode, int data){
        if(currnode==null)
            return false;
        else {
            if(currnode.data == data){
                //replace the pointer
                prevnode.next = currnode.next;
                return true;
            }
            else
                return removeNode(currnode.next, currnode, data);
        }
    }

    public void printList(Node head){
        if(head==null)
            return;
        else {
            System.out.println(head.data);
            printList(head.next);
        }
    }

    public static void main(String[] args){
        LinkedListDemo linkedListDemo = new LinkedListDemo();
        head = linkedListDemo.add(head, 3);
        head = linkedListDemo.add(head, 2);
        head = linkedListDemo.add(head, 3);
        head = linkedListDemo.add(head, 4);
        linkedListDemo.printList(head);

        head = linkedListDemo.removeNode(head, 2);
        linkedListDemo.printList(head);

//        linkedListDemo.removeNode(head, null, 2);
//        linkedListDemo.printList(head);

    }
}
