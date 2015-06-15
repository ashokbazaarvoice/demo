package com.jbksoft.app;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by ashok.agarwal on 4/27/15.
 */
public class DoublyLinkedList_v2<E> implements Iterable {
    private final ListItem head, tail;
    private int size;
	/*
	 * Representation Invariants:
	 * (1) For all ListItems i except head and tail, i.next.prev == i and i.prev.next == i
	 * (2) head.prev == null
	 * (3) tail.next == null
	 * (4) size == the number of elements in the list (not counting head and tail)
	 */

    public DoublyLinkedList_v2() {
        head = new ListItem(null, null, null);
        tail = new ListItem(null, null, head);
        head.next = tail;
        size = 0;
    }

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    public void addFirst(E element) {
        addAfter(head, element);
    }

    public void addLast(E element) {
        addAfter(tail.prev, element);
    }

    void addAfter(ListItem pred, E element) {
        ListItem item = new ListItem(element, pred.next, pred);
        item.prev.next = item;
        item.next.prev = item;
        size++;
    }

    public E removeLast() {
        if (size == 0) throw new NoSuchElementException();
        return removeItem(tail.prev);
    }

    public E removeFirst() {
        if (size == 0) throw new NoSuchElementException();
        return removeItem(head.next);
    }

    private E removeItem(ListItem item) {
        System.out.println("removing" + item.element);
        item.next.prev = item.prev;
        item.prev.next = item.next;
        size--;
        return item.element;
    }

    private	class ListItem {
        E element;
        ListItem next;
        ListItem prev;

        public ListItem(E element, ListItem next, ListItem prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }

    }

    // *** STARTED HERE IN LECTURE ON 10-20-05


    private ListItem find(E element) {
        ListItem ptr = head.next;
        while (ptr != tail) {
            if (element.equals(ptr.element))
                return ptr;
            ptr = ptr.next;
        }
        return null;
    }

    public boolean contains(E element) {
        return find(element) != null;
    }

    public boolean remove(E element) {
        ListItem item = find(element);
        if (item == null)
            return false;
        else {
            removeItem(item);
            return true;
        }
    }

    public Iterator<E> iterator() {
        return new DoublyLinkedListIterator();
    }

    class DoublyLinkedListIterator implements Iterator<E> {
        ListItem location = head;
        boolean returnedElement = false;

        public boolean hasNext() {
            return location.next != tail;
        }

        public E next() {
            if (location.next == tail)
                throw new NoSuchElementException();
            location = location.next;
            E element = location.element;
            returnedElement = true;
            return element;
        }

        public void remove() {
            if (returnedElement == false)
                throw new NoSuchElementException();
            removeItem(location);
            returnedElement = false;
        }
    }


}
