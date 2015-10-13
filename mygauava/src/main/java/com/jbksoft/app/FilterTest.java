package com.jbksoft.app;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;

/**
 * Created by ashok.agarwal on 10/7/15.
 */
public class FilterTest {
    public static void main(String[] args){

        List<String> names = Lists.newArrayList("John", "Jane", "Adam", "Tom");
        Iterable<String> result = Iterables.filter(names, Predicates.containsPattern("a"));

        System.out.println(result);

        Collection<String> result1 = Collections2.filter(names, Predicates.containsPattern("J"));
        System.out.println(result1);

        names = Lists.newArrayList();
        names.add("Ram");
        names.add("Sita");
        names.add("123");
        names.add("456");

        // filtering using predicates
        // the elements which returns true becomes the part of filtered collection.
        Predicate<String> pred = new Predicate<String>() {
            @Override
            public boolean apply(String input) {
                return input.matches("[0-9]+");
            }
        };

        result1 = Collections2.filter(names, pred);
        System.out.println(result1);


        Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean apply(String input) {
                return input.startsWith("A") || input.startsWith("J");
            }
        };

        names = Lists.newArrayList("John", "Jane", "Adam", "Tom");
        Collection<String> result2 = Collections2.filter(names, predicate);

        System.out.println(result2);

        Collection<String> result3 = Collections2.filter(names,
                Predicates.or(Predicates.containsPattern("J"),
                        Predicates.not(Predicates.containsPattern("a"))));
        System.out.println(result3);

        boolean resultbool = Iterables.all(names, Predicates.containsPattern("n|m"));
        System.out.println(resultbool);

        resultbool = Iterables.all(names, Predicates.containsPattern("a"));
        System.out.println(resultbool);


        Function<String, Integer> function = new Function<String, Integer>() {
            @Override
            public Integer apply(String input) {
                return input.length();
            }
        };

        Iterable<Integer> result4 = Iterables.transform(names, function);

        System.out.println(result4);
    }
}
