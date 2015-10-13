package com.abc.copy;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 4/8/14
 * Time: 11:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class ThreadTest {
    public static void main(String[] args) {
        final NameList nl = new NameList();
        nl.add("Ozymandias");
        class NameDropper extends Thread {
            @Override
            public void run() {
                String name = nl.removeFirst();
                System.out.println(name);
            }
        }
        Thread t1 = new NameDropper();
        Thread t2 = new NameDropper();
        t1.start();
        t2.start();
    }

}

class NameList {
    private List names = //Collections.synchronizedList(
            new LinkedList()
            //)
            ;

    public void add(String name) {
        names.add(name);
    }

    public String removeFirst() {
        if (names.size() > 0)
            return (String) names.remove(0);
        else
            return null;
    }
}