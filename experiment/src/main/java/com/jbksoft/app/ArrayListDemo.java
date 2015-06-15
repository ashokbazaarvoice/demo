package com.jbksoft.app;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by ashok.agarwal on 5/30/15.
 */
public class ArrayListDemo {
    public static void main( String[] args ){
        ArrayList<String> arrayList = new ArrayList();
        arrayList.add("Ram");
        arrayList.add("Lakshman");
        arrayList.add("Sita");
        System.out.println(arrayList);
        System.out.println(Arrays.toString(arrayList.toArray())); //--- not required

        ArrayList list = new ArrayList<Person>();
        // before implementing toString()
        list.add(new Person("Sita","Ram")); //--- the same person is not same as the person doesnt have hashcode and equals method
        list.add(new Person("Sita","Ram"));
        System.out.println(list);

        // after implmenting toString()
        System.out.println(list);

        //TODO need to search any approach so that i can inject toString() method in Person class

        System.out.println(arrayList.size()+" : "+arrayList.get(0));

    }
}
