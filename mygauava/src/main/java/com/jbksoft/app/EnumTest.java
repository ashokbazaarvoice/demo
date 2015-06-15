package com.jbksoft.app;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 1/26/15
 * Time: 4:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class EnumTest {

    public static void main(String[] str){

        for (Color color : Color.values()) {
            System.out.println(color+" "+color.name()+" "+color.ordinal());
        }

        Color color = Color.BLACK;
        System.out.println(color);

        AgeRange child = AgeRange.KID;
        System.out.println(child);

        List myList = Lists.newArrayList();
        myList.add(Color.BLACK);
        myList.add(Color.BLUE);

        System.out.println(myList);
        System.out.println(myList.contains(Color.BLACK));
        System.out.println(myList.contains(Color.RED));
        myList.add(Color.BLACK);
        System.out.println(myList);

        myList.remove(Color.BLACK);
        System.out.println(myList);

        myList.clear();

        System.out.println(myList);

        ImmutableSet<Integer> numbers = ImmutableSet.of(8, 6, 7, 5, 3, 0, 9);
        System.out.println(numbers);
        //numbers.clear();
        System.out.println(numbers);

        ImmutableList<Integer> numbersList = ImmutableList.of(8, 6, 7, 5, 3, 0, 9, 8);
        System.out.println(numbersList);
        //numbersList.clear();
        System.out.println(numbersList);

        ImmutableMap<String, Integer> stringsMap = new ImmutableMap.Builder<String, Integer>()
                .put("one", 1)
                .put("two", 2)
                .put("three", 3)
                .build();

        System.out.println(stringsMap);

        ImmutableMap.Builder<String, Integer> stringsMap_duplicate = new ImmutableMap.Builder<String, Integer>()
                .put("one", 1)
                .put("two", 2)
                .put("three", 3);


        System.out.println(stringsMap_duplicate.build());

        ImmutableMap<String, Integer> stringsMap_short = ImmutableMap.of("one", 1, "two", 2, "three", 3);
        System.out.println(stringsMap_short);
    }
}
