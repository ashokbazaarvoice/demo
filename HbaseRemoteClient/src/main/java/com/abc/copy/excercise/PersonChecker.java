package com.abc.copy.excercise;

/**
 * Created by ashok.agarwal on 7/22/15.
 * It says it can use any type till they extend/implement Comparable
 */
public class PersonChecker<T extends Comparable<T>> {

    public boolean isSame(T x, T y){
        return x.compareTo(y) == 0;
    }

}
