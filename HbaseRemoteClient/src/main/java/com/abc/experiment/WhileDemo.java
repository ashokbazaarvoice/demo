package com.abc.experiment;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ashok.agarwal on 5/23/15.
 */
public class WhileDemo {
    public static void main(String[] args){

        int i = 0;
        while(++i < 10);
        System.out.println(i);

        int j;
        for(j =0; j<10;j++);
        System.out.println(j);

        for (i = 1, j = 5; i <= 5 && j > 0; i = i - 1, j = j-1) {
            System.out.println("Inside For Loop");
        }

        i = 0;
        while(i < 10)
            i++;
        System.out.println(i);

        boolean status = true;
        while(status != false){
            System.out.println(status);
            i++;
            if(i == 12)
                status = false;
        }
        System.out.println(i+" "+status);
        do{
            System.out.println(i++);
        } while(i < 15);
        int x = 0;
        while (x < 5){
            x++;
            // do something
        }
        String[] strArray = {"1","2","3","4","5","6"};
        List intList = Arrays.asList(strArray);
        Iterator itr = intList.iterator();
        while(itr.hasNext())
            System.out.println(itr.next());
    }
}
