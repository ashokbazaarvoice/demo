package com.jbksoft.app.excercise_1;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 4/23/15
 * Time: 4:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class LinkedListDemo {
    public static void main(String[] args) {
        LinkedList l;
        MyLinkedList<String> list = new MySinglyLinkedListImpl<String>();
        list.addFirst("p");

        list.addFirst("a");
        list.addFirst("e");
        list.addFirst("h");
        System.out.println(list + " " +list.size());
        list.addLast("s");
        System.out.println(list+ " " +list.size());
        list.removeFirst();
        System.out.println("removeFirst : "+list+ " " +list.size());
        list.removeLast();
        System.out.println("removeLast : "+list+ " " +list.size());
        list.addLast("s");
        list.remove(2);
        System.out.println("addLast(\"s\") and remove(2) : "+list+ " " +list.size());
        list.add("p", 2);
        System.out.println("add(\"p\", 2) : "+list+ " " +list.size());
        list.removeLast();
        //list.removeLast();
        list.remove(2);
        System.out.println("removeLast and remove(2) : "+list+ " " +list.size());
        list.remove(0);
        System.out.println(list+ " " +list.size());
        //list.add("e",0);
        list.addFirst("e");
        list.add("p",1);
        System.out.println(list+ " " +list.size());
    }
}

class MyDoublyLinkedList<T> implements MyLinkedList<T>{
    Node<T> first;
    Node<T> last;
    int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void addFirst(T element) {
        if (isEmpty()) {
            first = new Node<T>(element, null, first);
            last = first;
        } else {
            first = new Node<T>(element, null, first);
            first.next.prev = first;
        }
        size++;
    }

    @Override
    public void addLast(T element) {
        if (isEmpty()) {
            first = new Node<T>(element, null, first);
            last = first;
        } else {
            Node<T> tmp = last;
            tmp.next = new Node<T>(element, tmp, null);
            last = tmp.next;
        }
        size++;
    }

    @Override
    public void add(T element, int pos) {
        // first check the pos is in first half or second half.
        // if first half then start from first else start from last.
        if(pos < 0 || pos > size())
            throw new NoSuchElementException();
        if(pos == size())
            addLast(element);
        else {
            Node<T> tmp = first;
            int i = 0;
            while(tmp.next != null && i < pos){
                tmp = tmp.next;
                i++;
            }
            tmp.next = new Node<T>(element, tmp, tmp.next);
            size++;
        }

    }

    @Override
    public void removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        first = first.next;
        first.prev = null;
        size--;
    }

    @Override
    public void removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();
        last = last.prev;
        last.next = null;
        size--;
    }

    @Override
    public void remove(int pos) {
        if (isEmpty() || pos < 0 || pos >= size())
            throw new NoSuchElementException();
        if (pos == (size()-1))
            removeLast();
        if (pos == 0 )
            removeFirst();
        else {
            Node<T> tmp = first;
            int i = 1;
            while(tmp.next.next != null && i < pos){
                tmp = tmp.next;
                i++;
            }
            Node<T> tmp1 = tmp;
            tmp.next = tmp.next.next;
            tmp.next.next.prev = tmp1;
            size--;
        }
    }

    @Override
    public void remove(T key) {
        if (isEmpty())
            throw new NoSuchElementException();
        if (first.data.equals(key)){
            removeFirst();
        }
        else {

            for (Node<T> tmp = first; tmp != null; tmp = tmp.next){
                if (tmp.data.equals(key)) {
                    // remove node
                    Node<T> prev = tmp.prev;
                    Node<T> next = tmp.next;

                    if (prev == null) {
                        first = next;
                    } else {
                        prev.next = next;
                        tmp.prev = null;
                    }

                    if (next == null) {
                        last = prev;
                    } else {
                        next.prev = prev;
                        tmp.next = null;
                    }
                    tmp.data = null;
                    size--;
                }
            }

//            Node<T> tmp = first;
//            while (tmp.next.next != null && !tmp.next.data.equals(key)){
//                tmp= tmp.next;
//            }

//            if (tmp.next.data.equals(key)) {
//                Node<T> tmp1 = tmp;
//                tmp.next = tmp.next.next;
//                tmp.next.next.prev = tmp1;
//            }
//            size--;
        }
    }

    @Override
    public T getFirst() {
        if(isEmpty())
            throw new NoSuchElementException();
        return first.data;
    }

    @Override
    public T getLast() {
        if(isEmpty())
            throw new NoSuchElementException();
        return last.data;
    }

    @Override
    public T get(int pos) {
        if(isEmpty())
            throw new NoSuchElementException();
        if(pos < (size >> 1)) {
            //traverse from start
            Node<T> tmp = first;
            for( int i = 0; i < pos; i++) {
                tmp = tmp.next;
            }
            return tmp.data;
        } else {
            // traverse from last
            Node<T> tmp = last;
            for( int i = size -1; i > pos; i--) {
                tmp = tmp.next;
            }
            return tmp.data;
        }
    }

    @Override
    public void append(MyLinkedList<T> myLinkedList) {
        int length = myLinkedList.size();
        Node<T> tmp = myLinkedList.getHead();
        for (int i = 0; i<length; i++) {
            addLast(tmp.data);
            tmp = tmp.next;
        }
    }

    @Override
    public MyLinkedList<T> merge(MyLinkedList<T> myLinkedList) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean contains(T element) {
        for (Node<T> x = first; x != null; x = x.next) {
            if (element.equals(x.data))
                return true;
        }
        return false;
    }

    @Override
    public MyLinkedList<T> reverse() {
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Node<T> getHead() {
        return null;
    }
}

class MySinglyLinkedListImpl<T> implements MyLinkedList<T>{
    Node<T> head = null;
    int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void addFirst(T element) {
        head = new Node<T>(element, head);
        size++;
    }

    @Override
    public void addLast(T element) {
        if(isEmpty()) {
            head = new Node<T>(element, head);
        }else {
            Node<T> tmp = head;
            while (tmp.next != null)
                tmp = tmp.next;
            tmp.next = new Node<T>(element, null);
        }
        size++;
    }

    // the pos is index so starting from zero and size is starting from one.
    @Override
    public void add(T element, int pos) {
        if(pos > size() || pos < 0)
            throw new NoSuchElementException();
        if(pos == size()) {
            addLast(element);
        }else if (pos == 0 || head == null) {
            addFirst(element);
        }else {
            Node<T> tmp = head;
            int i = 1;
            while (tmp.next.next != null && i < pos) {
                tmp = tmp.next;
                i++;
            }
            tmp.next = new Node<T>(element, tmp.next);
            size++;
        }
    }

    @Override
    public void removeFirst() {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }else {
            head = head.next;
            size--;
        }
    }

    @Override
    public void removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else if (head.next == null) {
            clear();
        } else {
            Node<T> tmp = head;
            while (tmp.next.next != null) {
                tmp = tmp.next;
            }
            tmp.next = null;
            size--;
        }
    }

    @Override
    public void remove(int pos) {
        if(pos > (size()-1) || pos < 0)
            throw new NoSuchElementException();
        if(pos == (size()-1)) {
            removeLast();
        }else if (head.next == null && pos == 0) {
            clear();
        }else if (head.next != null && pos == 0) {
            removeFirst();
        }else {
            Node<T> tmp = head;
            int i = 1;
            while (tmp.next.next != null && i < pos) {
                tmp = tmp.next;
                i++;
            }
            tmp.next = tmp.next.next;
            size--;
        }
    }

    @Override
    public void remove(T key){
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (head.data.equals(key)) {
            removeFirst();
        } else {
            Node<T> tmp = head;
            while (tmp.next != null && !tmp.next.data.equals(key)) {
                tmp = tmp.next;
            }
            if(tmp.next.data.equals(key)){
                tmp.next = tmp.next.next;
            }
            size--;
        }

    }

    @Override
    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return head.data;
    }

    @Override
    public T getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            Node<T> tmp = head;
            while (tmp.next != null) {
                tmp = tmp.next;
            }
            return tmp.data;
        }
    }

    @Override
    public T get(int pos) {
        if(pos > (size()-1) || pos < 0)
            throw new NoSuchElementException();
        if(pos == (size()-1)) {
            return getLast();
        }else {
            Node<T> tmp = head;
            int i = 0;
            while (tmp.next != null && i < pos) {
                tmp = tmp.next;
                i++;
            }
            return tmp.data;
        }
    }

    /**
     * Appends second linkedlist to tail
     * Complexity: O(n^2)
     */
    @Override
    public void append(MyLinkedList<T> myLinkedList) {
        Node<T> tmp = myLinkedList.getHead();
        while (tmp != null) {
            this.addLast(tmp.data);
            tmp = tmp.next;
            size++;
        }
    }
    /**
     * Appends second linkedlist to tail
     * Complexity: O(n^2)
     */
    @Override
    public MyLinkedList<T> merge(MyLinkedList<T> myLinkedList) {
        MyLinkedList<T> reverseLL = this.reverse();
        Node<T> tmp = reverseLL.getHead();
        while (tmp != null) {
            myLinkedList.addFirst(tmp.data);
            tmp = tmp.next;
           // myLinkedList.size()++;
        }
        return this;
    }

    /**
     * Returns a deep copy of the list
     * Complexity: O(n^2)
     */
    public MyLinkedList<T> copy1() {
        MyLinkedList<T> twin = new MySinglyLinkedListImpl<T>();
        Node<T> tmp = head;
        while (tmp != null) {
            twin.addLast(tmp.data);
            tmp = tmp.next;
        }

        return twin;
    }

    /**
     * Returns a deep copy of the list
     * Complexity: O(n)
     */
    public MyLinkedList<T> copy2() {
        MyLinkedList<T> twin = new MySinglyLinkedListImpl<T>();
        Node<T> tmp = head;
        while (tmp != null) {
            twin.addFirst(tmp.data);
            tmp = tmp.next;
        }

        return twin.reverse();
    }

    @Override
    public void clear() {
        head=null;
        size = 0;
    }

    @Override
    public boolean contains(T element) {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            Node<T> tmp = head;
            while (tmp.next != null && tmp.data.equals(element)) {
                tmp = tmp.next;
            }
            if(tmp.data.equals(element))
                return true;
            else
                return false;
        }
    }

    @Override
    public MyLinkedList<T> reverse() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            MyLinkedList<T> twin = new MySinglyLinkedListImpl<T>();
            Node<T> tmp = head;
            while (tmp.next != null) {
                twin.addFirst(tmp.data);
                tmp = tmp.next;
            }
            return twin;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Node<T> getHead() {
        return head;
    }

    public String toString() {
        StringBuffer result = new StringBuffer();
        Node<T> tmp = head;
        while(tmp != null) {
            result.append(tmp.data + " ");
            tmp = tmp.next;
        }

        return result.toString();
    }
}

interface MyLinkedList<T>{
    int size();
    boolean isEmpty();
    void addFirst(T element);
    void addLast(T element);
    void add(T element, int pos);
    void removeFirst();
    void removeLast();
    void remove(int pos);
    void remove(T key);
    T getFirst();
    T getLast();
    T get(int pos);
    void append(MyLinkedList<T> myLinkedList);
    MyLinkedList<T> merge(MyLinkedList<T> myLinkedList);
    void clear();
    boolean contains(T element);
    MyLinkedList<T> reverse();
    Iterator<T> iterator();
    Node<T> getHead();
}
