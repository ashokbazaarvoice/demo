package com.abc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashok.agarwal on 6/4/15.
 */
public class ArrayListDemo {
    public static void main(String[] args) {
        List list = new ArrayList<String>();
        list.add(1);
        list.add(2);
        System.out.println(list);
        list.remove(1);
        System.out.println(list);
    }
}
