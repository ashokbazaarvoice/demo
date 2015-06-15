package com.bazaarvoice.idea;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 6/20/14
 * Time: 6:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class LRUCache<K,V> extends LinkedHashMap<K,V> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int maxSize;

    public LRUCache(int size){
        super(size+1,1,true);
        maxSize = size;
    }
    @Override
    protected boolean removeEldestEntry(Map.Entry<K,V> eldest){
        return size() > maxSize;
    }

    public Object get(Object key)
    {
//        accessCount++;
//        if (containsKey(key))
//        {
//            hitCount++;
//        }
        Object value = super.get(key);
        return value;
    }


}