package com.abc.copy.collections;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by ashok.agarwal on 6/4/15.
 */
public class ArrayListDuplicatesDemo {
    public static void main(String[] args) {
        List list = new ArrayList<String>();
        list.add(1); //0
        list.add(2);
        list.add(13);
        list.add(27);//3
        list.add(90);
        list.add(13);
        list.add(27);//6
        list.add(90);
        System.out.println(list.indexOf(27));
        System.out.println(list.lastIndexOf(27));
        System.out.println(list);
        list.remove(1);
        System.out.println(list);
        list.add(0,0);
        System.out.println(list);
        List<String>[] arrayOfList = new List[10];
        arrayOfList[0] = new ArrayList<String>();
        Object obj = new Object(); // Remember 11 methods of Object equals, hashcode, getClass,
                                   // toString, notify, notifyAll, 3 wait, finalize, clone
        //obj.

        Set<Integer> listWithoutDuplicates = new LinkedHashSet<Integer>(list);
        list.clear();
        list.addAll(listWithoutDuplicates);

        System.out.println("Hmmm : "+list);

        Collections.reverse(list); // Note how the API uses with list iterator

        System.out.println(list);

        // Synchronizing ArrayList in Java
        list = Collections.synchronizedList(list);

        // While Iterating over synchronized list, you must synchronize
        // on it to avoid non-deterministic behavior

        synchronized(list){
            Iterator<Integer> myIterator = list.iterator();

            while(myIterator.hasNext()){
                System.out.println(myIterator.next());
            }
        }

        // thread safe arrayList : CopyOnWriteArrayList
        CopyOnWriteArrayList<String> threadSafeList = new CopyOnWriteArrayList<String>();
        threadSafeList.add("Java");
        threadSafeList.add("J2EE");
        threadSafeList.add("Collection");

        //add, remove operator is not supported by CopyOnWriteArrayList iterator
        Iterator<String> failSafeIterator = threadSafeList.iterator();
        while(failSafeIterator.hasNext()){
            System.out.printf("Read from CopyOnWriteArrayList : %s %n", failSafeIterator.next());
            failSafeIterator.remove(); //not supported in CopyOnWriteArrayList in Java
        }
    }
}
