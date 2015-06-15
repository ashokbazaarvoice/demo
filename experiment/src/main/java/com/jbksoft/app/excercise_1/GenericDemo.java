package com.jbksoft.app.excercise_1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 4/22/15
 * Time: 5:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class GenericDemo {

    Person _person = new Person();

    // method is generic here
    <T> T myMethod(){
        T obj = null;
        return obj;
    }

    // You can perform a generic type invocation, passing Number as its type argument,
    // and any subsequent invocation of add will be allowed if the argument is compatible with Number
    public void my1() {
        Box<Number> box = new Box<Number>();
        box.add(new Integer(10));   // OK
        box.add(new Double(10.1));  // OK
    }

    // Are you allowed to pass in Box<Integer> or Box<Double>, as you might expect?
    // The answer is "no", because Box<Integer> and Box<Double> are not subtypes of Box<Number>.
    public void boxTest(Box<Number> n) { /* ... */ }

    public void my() {
        List<A> listA = new ArrayList<A>();
        processElements(listA);

        List<B> listB = new ArrayList<B>();
        processElements(listB);

        List<C> listC = new ArrayList<C>();
        processElements(listC);

        processElements_1(listA);
        processElements_1(listB);
        processElements_1(listC);
    }

    // three ways to define a collection (variable) using generic wildcards
    List<?>           listUnknown1 = new ArrayList<A>();
    List<? extends A> listUnknown2 = new ArrayList<A>();
    List<? super   A> listUnknown3 = new ArrayList<A>();

    // List<?> means a list typed to an unknown type. This could be a List<A>, a List<B>, a List<String> etc.
    public void processElements(List<?> elements){
        for(Object o : elements){
            System.out.println(o);
        }
    }

    // List<? extends A> means a List of objects that are instances of the class A, or subclasses of A (e.g. B and C).
    public void processElements_1(List<? extends A> elements){
        for(A a : elements){
           // System.out.println(a.getValue());
        }
    }

    // List<? super A> means that the list is typed to either the A class, or a superclass of A like Object
    public static void insertElements(List<? super A> list){
        list.add(new A());
        list.add(new B());
        list.add(new C());
    }
    //____________________________________________________________________________________________________________
    public static <T> int countGreaterThan(T[] anArray, T elem) {
        int count = 0;
        for (T e : anArray)
           // if (e > elem)  // compiler error
                ++count;
        return count;
    }

    public static <T extends Comparable<T>> int countGreaterThan(T[] anArray, T elem) {
        int count = 0;
        for (T e : anArray)
            if (e.compareTo(elem) > 0)
                ++count;
        return count;
    }
    //____________________________________________________________________________________________________________
}

class A<T> { }
class B<T> extends A<T> { }
class C extends A { }

class Box<T>{
    void add(Number n){};
}

// class is generic
class Person<T>{
    T data;

    T getData() {
        return data;
    }

    void setData(T data) {
        this.data = data;
    }
}

// a generic class can have multiple type parameters
interface Pair<K, V> {
    public K getKey();
    public V getValue();
}

class OrderedPair<K, V> implements Pair<K, V> {

    private K key;
    private V value;

    public OrderedPair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey()	{ return key; }
    public V getValue() { return value; }
}


// generic method so class declaration doesn't have the K, V

class Util {

    public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) {
        return p1.getKey().equals(p2.getKey()) &&
                p1.getValue().equals(p2.getValue());
    }

    public static <K, V> K concat(Pair<K, V> p1, Pair<K, V> p2) { /// NOTICE return type here
       // consider some operation of concat happening
       // p1.getKey() = p1.getKey()+(p2.getKey())
        return p1.getKey();
    }

    public static <T> T addAndReturn(T element, Collection<T> collection){
        collection.add(element);
        return element;
    }
}