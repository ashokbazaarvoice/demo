package com.abc;

import java.util.Map;
import java.util.NavigableMap;
import java.util.SortedMap;
import java.util.TreeMap;

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
    Map mapH =  new java.util.TreeMap();
    Map mapI =  new java.util.WeakHashMap();

    SortedMap mapK = new TreeMap();

    NavigableMap original = new TreeMap();
}
