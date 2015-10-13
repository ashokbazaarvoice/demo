package com.abc.experiment;

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
        list.add(new Person("Sita","Ram")); //--- the same person object is not same as the person class doesnt
                                            // have hashcode and equals method and default hashcode is native
                                            // method(which converts address of the object into an integer)

        list.add(new Person("Sita","Ram")); // default equals method is this == obj basically comparing references
        System.out.println(list); // [com.abc.experiment.Person@12504e0, com.abc.experiment.Person@25630eb6]
                                  // as default to string method
                                  // is getClass().getName() + "@" + Integer.toHexString(hashCode())

        // after implmenting toString()
        System.out.println(list);

        //TODO need to search any approach so that i can inject toString() method in Person class

        System.out.println(arrayList.size()+" : "+arrayList.get(0));

    }
}
