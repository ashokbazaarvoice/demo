package com.abc.copy;

import java.util.*;

/**
 * Created by ashok.agarwal on 7/2/15.
 */
public class MetricsStore<T> {

    Map<String, Map<String, Map<String, String>>> store = new HashMap<String, Map<String, Map<String, String>>>();

    /** Stores an event in memory
     */
    public void collect(String host, String metrics, String timestamp,String value) {
        // updates central in memory store
        //<host, <metrics, <timestamp, value>>>
        if(store.get(host)!=null) {
            // update existing value
            store.put(host, saveUpdateMetrics(store.get(host), metrics, timestamp, value));
        } else {
            // create new value
            Map<String, Map<String, String>> copy = new HashMap<String, Map<String, String>>();
            store.put(host, saveUpdateMetrics(copy, metrics, timestamp, value));
        }
    }

    /** Return list of values from startStamp to endStamp for given host and metrics name */
    public List query(String host, String metrics, String startStamp, String endStamp) {
        Map<String, Map<String, String>> data = store.get(host);
        Map<String, String> data1 = data.get(metrics);
        // here we have to get the range of data from startStamp to endStamp
        return getValues(data1,startStamp, endStamp);
    }

    public Map saveUpdateMetrics(Map<String, Map<String, String>> map, String metrics, String timestamp, String value){

        if(map.get(metrics) != null) {
            saveUpdateValue(map.get(metrics), timestamp, value);
        } else {
            Map<String, String> copy = new LinkedHashMap<String, String>();
            saveUpdateValue(copy, timestamp, value);
        }
        return map;
    }

    public Map saveUpdateValue(Map<String, String> map, String timestamp,String value){
        //if(map.get(timestamp)!=null)
        map.put(timestamp, value);
        return map;
    }

    public List<String> getValues(Map<String, String> map, String starttimestamp, String endtimestamp) {
        //index of the startTS and index of endTS
        // create 2 custom object with startTS and endTS and find the index of these two object in list and then iterate all the object in range and fetch value of the object and create a list with these values.
        List<String> values = new ArrayList<String>();
        String value = map.get(starttimestamp);
        values.add(value);
        while(map.get(endtimestamp) !=null)
            values.add(value);
        return values;
    }

    // print Nth from the bottom of the stack, index of the first from the // TOP ⇒ |0|1|2|3|4|5|6|7|8|9| - asked to print 2nd, will print 8, 10th - null
// 0 is the top and 9 is bottom -- so n will start from bottom

    public void print( Stack<T> stack, int n ) {
        // pop all the elements from stack and put it in array
        // based on index and size of array print the nth element
        List<T> list = new ArrayList<T>();
        while(!stack.isEmpty()){
            list.add(stack.pop());
        }
        System.out.println(list);
        // list elements 9|8|7|...|1|0
        // n = 9; 9 - 1=> 8
        // n = 1 => 1 - 1 => 0 list.get(0) = 9
        // n = 0; => -1 ; return null
        if(n < 1 || n > list.size()) {
            return;
        }else {
            System.out.println(list.get(list.size() - n));
        }
    }

    public static void main(String[] args){

        Stack<Integer> stack = new Stack<Integer>();
        stack.push(9);
        stack.push(8);
        stack.push(7);
        stack.push(6);
        stack.push(5);
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(1);
        System.out.println(stack);
        new MetricsStore().print(stack, 2);
    }

}
