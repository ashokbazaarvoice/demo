package com.abc.copy.collections;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 10/14/14
 * Time: 7:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class SetDemo {

    //Set set = new EnumSet();
    Set setB = new HashSet();
    Set setC = new LinkedHashSet();
    Set setD = new TreeSet();

    SortedSet setA = new TreeSet();

    NavigableSet original = new TreeSet();

    char[] symbols = {'{','}','(',')'};

    public static void main(String[] args) {

        Set<String> set = new HashSet();

        Iterator iter = set.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }


        for (String s : set) {
            System.out.println(s);
        }

        set.forEach(System.out::println); // Java 8

        set.forEach(element -> System.out.println(element));

        set.stream().forEach((elem) -> {
            System.out.println(elem);
        });



        Set<String> mySet = Sets.newTreeSet();
        mySet.add("Ram");

        List<String> nameList = Lists.newArrayList();
        nameList.add("Sita");
        nameList.add("Janki");

        mySet.addAll(nameList);
        System.out.println(mySet);

        mySet.remove("Janki");
        System.out.println(mySet);

        nameList.clear();
        nameList.add("Lakshman");
        System.out.println(mySet.retainAll(nameList));

    }


}
