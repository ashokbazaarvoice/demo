package com.abc.copy.collections;

import java.util.*;
import java.util.function.ObjDoubleConsumer;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 10/14/14
 * Time: 7:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class MapDemo {

    Map mapA =  new java.util.HashMap();
    Map mapB =  new java.util.Hashtable();
    //Map mapC =  new java.util.EnumMap();
    Map mapD =  new java.util.IdentityHashMap();
    Map mapF =  new java.util.LinkedHashMap();
    Map mapG =  new java.util.Properties();

    Map mapI =  new java.util.WeakHashMap();

    SortedMap mapK = new TreeMap();

    NavigableMap original = new TreeMap();

    public static void main(String[] args) {

        HashMap<String, Object> map = new HashMap<>();

        map.put("A", 1);
        map.put("C", 3);
        map.put("B", 2);
        System.out.println(map);
        Map mapH =  new java.util.TreeMap();
        mapH.putAll(map);
        System.out.println(mapH);

        for (String key : map.keySet()) {

        }

        for (Object value : map.values()) {

        }

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            // ...
        }

        System.out.println("Using Iterator");
        Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator() ;
        while(iterator.hasNext()){
            Map.Entry<String, Object> mapEntry = iterator.next();
            System.out.println(mapEntry.getKey() +" :: "+ mapEntry.getValue());
            //You can remove elements while iterating.
            iterator.remove();
        }

        // Compute a new value for the existing key
        System.out.println(map.compute("A",
                (k, v) -> v == null ? 42 : v.toString() + 41));
        System.out.println(map);

        // This will add a new (key, value) pair
        System.out.println(map.compute("X",
                (k, v) -> v == null ? 42 : v.toString() + 41));
        System.out.println(map);

        map.forEach((k, v) ->
                System.out.println(k + "=" + v));

        //

        String key = "A";

        Object msg = new Object();

        Object value = map.get(key);
        if (value == null)
            map.put(key, msg);
        else
            map.put(key, value.toString().concat(msg.toString()));


        //map.merge(key, msg, String::concat);


        map.put("X", null);

//        System.out.println(map.merge("X", null, (v1, v2) -> null));

        System.out.println(map);

        HashMap<String,Double> map1 = new HashMap<String,Double>();
        ValueComparator bvc =  new ValueComparator(map1);
        TreeMap<String,Double> sorted_map = new TreeMap<String,Double>(bvc);

        map1.put("A",99.5);
        map1.put("B",67.5); // change map1.put("B",67.4); and see difference
        map1.put("D",67.3);
        map1.put("C",67.4);


        System.out.println("unsorted map: "+map1);

        sorted_map.putAll(map1);

        System.out.println("results: "+sorted_map);

    }
}
class ValueComparator implements Comparator<String> {

    Map<String, Double> base;
    public ValueComparator(Map<String, Double> base) {
        this.base = base;
    }

    // Note: this comparator imposes orderings that are inconsistent with equals.
    public int compare(String a, String b) {
        if (base.get(a) >= base.get(b)) {
            return -1;
        } else {
            return 1;
        } // returning 0 would merge keys
    }
}

