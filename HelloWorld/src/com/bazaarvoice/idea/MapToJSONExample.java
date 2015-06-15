package com.bazaarvoice.idea;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 3/20/14
 * Time: 1:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class MapToJSONExample {
    public static void main(String[] args) throws Exception {

        MyClass myClass1 = new MyClass();
        myClass1.setName("Ashok");
        MyClass myClass2 = new MyClass();
        myClass2.setName("Manoj");

        //Different Object but same hashcode
        HashMap hashMap = new HashMap();
        hashMap.put(myClass1,"Ashok");
        hashMap.put(myClass2,"Ashok");
        System.out.println(hashMap.size());

        //Same object(equals method) so same hashcode
        HashMap hashMap1 = new HashMap();
        hashMap1.put("Ashok",myClass1);
        hashMap1.put("Ashok",myClass2);
        System.out.println(hashMap1.size());
    }
}
class MyClass implements Comparable {

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyClass)) return false;

        MyClass myClass = (MyClass) o;

        if (name != null ? !name.equals(myClass.name) : myClass.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return 23;
    }

    @Override
    public int compareTo(Object o) {
        MyClass myClass = (MyClass) o;
        return name.compareTo(myClass.name);
    }
}