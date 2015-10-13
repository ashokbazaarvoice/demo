package com.abc.experiment.excercise2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashok.agarwal on 6/10/15.
 */
public class StringPermutation {

    public static void main(String[] args){
        System.out.println(new StringPermutation().getPerms("hel", new ArrayList<String>()));
        new StringPermutation().printPerms("hel", "");
    }

    public List getPerms(String str, List<String> list){
        if(str.length() < 2){
            list.add(str);
            return list;
        }
        // loop for first character
        for(int i = 0; i < str.length(); i++) {
            // call recursively getPerms for rest chars
            char c = str.charAt(i);
            String newStr = getString(str, i);
            List<String> l = getPerms(newStr, new ArrayList<String>());
            for(String s : l) {
                list.add(c+s);
            }
        }
        return list;
    }

    public String getString(String str, int index){
        return str.substring(0, index)+str.substring(index+1);
    }

    public void printPerms(String str, String prefix){
        if(str.length() < 2){
            System.out.print(prefix + str + ", ");
            return;
        }
        // loop for first character
        for(int i = 0; i < str.length(); i++) {
            // call recursively getPerms for rest chars
            char c = str.charAt(i);
            String newStr = getString(str, i);
            printPerms(newStr, prefix+c);
        }
    }
}
