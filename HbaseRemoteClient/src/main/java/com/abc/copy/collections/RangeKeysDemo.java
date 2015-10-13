package com.abc.copy.collections;

import java.util.*;

/**
 * Created by ashok.agarwal on 6/30/15.
 */
public class RangeKeysDemo {
    public static void main(String[] args) {
        LinkedHashMap<Integer, String> hashmap = new LinkedHashMap<Integer, String>();

        hashmap.put(1, "apple" );
        hashmap.put(2, "lemon" );
        hashmap.put(3, "orange" );
        hashmap.put(4, "banana" );
        hashmap.put(5, "litchi" );
        hashmap.put(6, "mango" );
        hashmap.put(7, "papaya" );

        System.out.println(hashmap);

        System.out.println(valuesBetween(2,5,hashmap));

//        // note insertion order lost
//        Map map1 = new HashMap<>();
//        //for (int i = 1; i < 15; i++) {
//        for (int i = 14; i > 0; i--) {
//            map1.put(String.format("%04d", i), "");
//            System.out.println(String.format("%04d:", i) + map1);
//        }
//
//        map1 = new LinkedHashMap<>();
//        //for (int i = 1; i < 15; i++) {
//        for (int i = 14; i > 0; i--) {
//            map1.put(String.format("%04d", i), "");
//            System.out.println(String.format("%04d:", i) + map1);
//        }

        Map<String, String> myMap = new HashMap<>();
        myMap.put("A", "1");
        myMap.put("B", "1");
        myMap.put("C", "1");
        System.out.println(myMap);

        myMap = new LinkedHashMap<>();
        myMap.put("A", "1");
        myMap.put("C", "1");
        myMap.put("B", "1");

        NavigableMap<Integer,String> map =
                new TreeMap<Integer, String>();

        map.put(0, "Kid");
        map.put(11, "Teens");
        map.put(20, "Twenties");
        map.put(30, "Thirties");
        map.put(40, "Forties");
        map.put(50, "Senior");
        map.put(100, "OMG OMG OMG!");

        System.out.println(map.get(map.floorKey(13)));
        System.out.println(map.get(map.higherKey(20)));
    }

    public static List<String> valuesBetween(int from, int to, LinkedHashMap<Integer, String> map) {
        List<String> result = new ArrayList<>();
        boolean inRange = false;
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (entry.getKey() == from)
                inRange = true;
            if (inRange)
                result.add(entry.getValue());
            if (entry.getKey() == to)
                break;
        }
        return result;
    }
}
