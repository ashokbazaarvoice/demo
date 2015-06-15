package com.bazaarvoice.idea;


import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 6/11/14
 * Time: 12:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class MutableKeyHashMap {
    public static void main(String []a){

        HashMap<Mutable, String> map = new HashMap<Mutable, String>();
        Mutable m1 = new Mutable(5);
        map.put(m1, "m1");
        Mutable m2 = new Mutable(5);
        System.out.println(map.containsKey(m2));

        m2.setA(6);
        m1.setA(6);
        Mutable m3 = map.keySet().iterator().next();

        System.out.println(map.containsKey(m2)+"    "+m3.hashCode()+"       "+m2.hashCode()+"       "+m3.equals(m2));

    }
}
class Mutable {

    int a;

    public Mutable(int a) {

        this.a = a;
    }

    @Override
    public boolean equals(Object obj) {

        Mutable m = (Mutable) obj;
        return m.a == this.a ? true : false;
    }

    @Override
    public int hashCode(){
        return a;
    }

    public void setA(int a) {

        this.a = a;
    }

    public int getA() {
        return a;
    }
}

