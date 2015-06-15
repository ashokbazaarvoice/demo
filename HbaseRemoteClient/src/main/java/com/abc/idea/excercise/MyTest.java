package com.abc.idea.excercise;

import com.abc.idea.excercise.packageA.Person;
import org.apache.commons.lang.ArrayUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 9/16/14
 * Time: 9:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyTest {

    public static void main(String[] args) {
        Person p = new Person();

        ex233(6);

       // System.out.println(p.city); Not accessible as protected and can be made available in child out of package

        String str = "hello";
        System.out.println(reverse(str));
        char[] c = str.toCharArray();
        System.out.println(reverseRecursive(c));
        System.out.println(reverseRecursive(str));

        //String[] words = "This is interview question".split(" ");
        String[] words = {"This", "is", "interview", "question"};
        System.out.println(reverseRecursive(words));

        System.out.println(factorial(5));
        System.out.println(add(5));

        System.out.println(longestWord(words));
        permuteString("","cat");
        char[] alphabet = new char[] {'a','b'};
        Character[] charObjectArray = ArrayUtils.toObject(alphabet);
//        System.out.println(combinations(charObjectArray));
    }

    public static void ex233(int n) {
        if (n <= 0) {
            System.out.println("returning"+n);
            return;
        }
        System.out.println(n);
        ex233(n-2);
        ex233(n-3);
        System.out.println(n);
    }

    public static String reverse(String str) {
        StringBuffer strbuf = new StringBuffer();
        char[] c = str.toCharArray();
        for (int i = c.length - 1; i > -1; i--) {
            strbuf.append(c[i]);
        }
        return strbuf.toString();
    }

    public static String reverseRecursive(char[] c) {
        if (c.length > 1) {
            return c[c.length - 1] + reverseRecursive(Arrays.copyOf(c, c.length - 1));
        } else {
            return String.valueOf(c[0]);
        }
    }

    public static String reverseRecursive(String str) {
        if (str.length() > 1) {
            return str.substring(str.length() - 1) + reverseRecursive(str.substring(0, str.length() - 1));
        } else {
            return str;
        }
    }

    public static String reverseRecursive(String[] c) {
        if (c.length > 1) {
            return c[c.length - 1] + " " + reverseRecursive(Arrays.copyOf(c, c.length - 1));
        } else {
            return c[0];
        }
    }

    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    public static String longestWord(String[] words) {
        String longestWord = "";
        if (words.length == 1) {
            longestWord = words[0];
//        } else if ((words[words.length - 1]).length() > (longestWord(Arrays.copyOf(words, words.length - 1)).length())) {
//            longestWord =  words[words.length - 1];
//        } else
//            longestWord = longestWord(Arrays.copyOf(words, words.length - 1));
        } else {
            String str = longestWord(Arrays.copyOf(words, words.length - 1));
            longestWord =  (words[words.length - 1]).length() > str.length() ? words[words.length - 1]: str;
        }
        return longestWord;
    }

    public static int add(int n) {
        if (n == 1) {
            return 1;
        } else {
            return n + add(n - 1);
        }
    }

    public static String combinations(Character[] c){
        if(c.length==1)
            return String.valueOf(c[0]);
        for(Character chr : c){
            return chr+combinations(removeElements(c, chr));
        }
        return "";
    }

    public static <T> T[] removeElements(T[] input, T deleteMe) {
        List<T> result = new LinkedList<T>();

        for(T item : input)
            if(!deleteMe.equals(item))
                result.add(item);

        input = (T[])result.toArray();
        return (T[])result.toArray();
    }

    public static void permuteString(String beginningString, String endingString) {
        if (endingString.length() <= 1)
            System.out.println(beginningString + endingString);
        else
            for (int i = 0; i < endingString.length(); i++) {
                String newString = endingString.substring(0, i) + endingString.substring(i + 1);
                System.out.println(endingString.substring(0, i)+" # "+endingString.substring(i + 1)+ " # "+beginningString+ " # "+endingString.charAt(i));
                permuteString(beginningString + endingString.charAt(i), newString);
            }
    }

    public static void stringCombinations(String prefix, String str){

        if(str.length() == 0){
            System.out.println(prefix);
            return;
        }

        for(int i = 0; i < str.length(); i++){

            stringCombinations(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1));

        }
    }

    public static long Fibonacci(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return Fibonacci(n-1) + Fibonacci(n-2);
    }
}
