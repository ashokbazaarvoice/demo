package com.bazaarvoice.idea;

import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 5/5/14
 * Time: 5:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class VectorTest {
    public static void main(String[] args) {
        Vector<String> vector = new Vector<String>();
//        vector.add("1");
//        vector.add("2");
//        vector.add("3");
//        for (String str : vector) {
//            System.out.println(str);
//        }
//        System.out.println("Size : " + vector.size());
//        vector.set(vector.size() - 1, "4");
//        for (String str : vector) {
//            System.out.println(str);
//        }
        final int c = 500;
        int counter = 0;
        for (int i = 0; i < 10; i++) {
            if (counter % c == 0) {
                System.out.println("in add counter : "+counter+" Cond : "+(counter % c) + " Vector size : " +vector.size());
                vector.add(Integer.toString(i));
            } else {
                System.out.println("in set counter : "+counter+" Cond : "+(counter % c )+ " Vector size : " +vector.size());
                vector.set(vector.size() - 1,
                        i + " " + vector.get(vector.size() - 1));
            }
            System.out.println("####"+counter);
            counter++;
        }
        for (String str : vector) {
            System.out.println(str);
        }
    }
}
