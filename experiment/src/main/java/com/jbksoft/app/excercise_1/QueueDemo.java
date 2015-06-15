package com.jbksoft.app.excercise_1;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 4/22/15
 * Time: 7:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class QueueDemo {
}

class MyQueueImpl<T> implements MyQueue<T>{

    T[] elements;
    int front = 0, end = 0;


    public MyQueueImpl(int CAPACITY){
        elements = (T[])new Object[CAPACITY];
        front = CAPACITY - 1;
        end = CAPACITY - 1;
    }

    @Override
    public int size() {
        return front - end;
    }

    @Override
    public boolean isEmpty() {
        return size() <= 0;
    }

    @Override
    public void offer(T element) {
        if(size() > elements.length -1 ){
            throw new ArrayIndexOutOfBoundsException("Full Queue");
        }
        if(end > -1)
            elements[end--] = element;
        //else resize() or check end < 0 and return full queue
    }

    @Override
    public T poll() {
        if(isEmpty())
            throw new ArrayIndexOutOfBoundsException("Empty Queue");
        return elements[front--]; // if front < 0 then resize()
    }

    @Override
    public T peek() {
        if(isEmpty())
            throw new ArrayIndexOutOfBoundsException("Empty Queue");
        return elements[front];
    }
}

class MyQueueLinked<T> implements MyQueue<T>{
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
    public void offer(T element) {
        if(isEmpty()) {
            head = new Node<T>(element, head);
        } else {
            Node<T> tmp = head;
            while(tmp.next != null) {
                tmp = tmp.next;
            }
            tmp.next = new Node<T>(element, null);
        }
        size++;
    }

    @Override
    public T poll() {
        if(isEmpty())
            throw new IllegalStateException("Queue Empty");
        Node<T> tmp = head;
        head = tmp.next;
        size--;
        return tmp.data;
    }

    @Override
    public T peek() {
        if(isEmpty())
            throw new IllegalStateException("Queue Empty");
        return head.data;
    }
}


/**
 *  A queue is a type of abstract data type that can be implemented as a linear or circular list. A queue has a front and a rear.
    Queue can be of four types:

        1. Simple Queue

        2. Circular Queue

        3. Priority Queue

        4. Dequeue (Double Ended queue)

 * @param <T>
 */
interface MyQueue<T>{
    int size();
    boolean isEmpty();
    void offer(T element);
    T poll();
    T peek();
}