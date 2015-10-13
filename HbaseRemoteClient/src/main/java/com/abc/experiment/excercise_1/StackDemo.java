package com.abc.experiment.excercise_1;

import java.util.NoSuchElementException;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 4/22/15
 * Time: 5:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class StackDemo {


    public static void main(String[] args){
//        MyStack _stack = new MyStackLinked(10);
        MyStack _stack = new MyStackLinked();
        System.out.println(_stack.isEmpty());
        _stack.push("2");
        _stack.push("3");
        System.out.println("Size of Stack : " + _stack.size());
        System.out.println("Check if stack empty : "+_stack.isEmpty());
        System.out.println("Peek stack : "+_stack.peek());
        System.out.println("Pop stack : "+_stack.pop());
        System.out.println("Size of Stack : "+_stack.size());
        System.out.println("Peek stack : "+_stack.peek());
        System.out.println("Peek stack : "+_stack.peek());
        System.out.println("Size of Stack : "+_stack.size());
        System.out.println("#####");
        _stack.push("4");
        _stack.push("5");
        System.out.println("Size of Stack : "+_stack.size());
        System.out.println("Pop stack : "+_stack.pop());
        System.out.println("Pop stack : "+_stack.pop());
        System.out.println("Size of Stack : "+_stack.size());
        System.out.println("Peek stack : "+_stack.peek());
    }

}

class MyStackImpl<T> implements MyStack<T> {

    T[] elements;
    int size = 0;

    MyStackImpl(int capacity) {
        elements = (T[]) new Object[capacity];
    }

    @Override
    public int size() {
        return size;  
    }

    @Override
    public boolean isEmpty() {
        return size == 0;  
    }

    @Override
    public void push(T element) {
        if(size > elements.length - 1 )
            throw new ArrayIndexOutOfBoundsException("Full Stack");
        elements[size++] = element;
    }

    @Override
    public T pop() {
        if(isEmpty())
            throw new ArrayIndexOutOfBoundsException("Empty Stack");
        return elements[--size];
    }

    @Override
    public T peek() {
        if(isEmpty())
            throw new ArrayIndexOutOfBoundsException("Empty Stack");
        return elements[size - 1];
    }
}

interface MyStack<T>{
    int size();
    boolean isEmpty();
    void push(T element);
    T pop();
    T peek();

    //additional method
    //boolean contains(T element); For this method we will have to iterate through stack, queue so time complexity is O(n).
}

class MyStackLinked<T> implements MyStack<T> {

    Node<T> head;
    int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void push(T element) {
        if(isEmpty()) {
            head = new Node<T>(element);
        } else {
            head = new Node<T>(element, head);
        }
        size++;
    }

    @Override
    public T pop() {
        if(isEmpty()) {
            throw new NoSuchElementException("Stack Empty");
        }
        T data = head.data;
        head = head.next;
        size--;
        return data;
    }

    @Override
    public T peek() {
        if(isEmpty()) {
            throw new NoSuchElementException("Stack Empty");
        }
        return head.data;
    }
}

class Node<T>{
    T data;
    Node<T> prev;
    Node<T> next;

    Node(T data) {
        this(data, null, null);
    }

    Node(T data, Node<T> next) {
        this(data, null, next);
    }

    Node(T data, Node<T> prev, Node<T> next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }

}
