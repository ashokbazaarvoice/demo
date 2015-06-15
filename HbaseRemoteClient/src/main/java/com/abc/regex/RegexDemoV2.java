package com.abc.regex;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 3/20/15
 * Time: 3:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class RegexDemoV2 {

    public static void main(String[] args) {

        String s = "true";
        System.out.println(s.matches("[a-z]*"));

        s = "this is true";
        System.out.println(s.matches("[a-z\\s]*"));

        s = "this is true";
        System.out.println(s.matches("[a-z\\s]{0,}"));

        System.out.println("hello");


        //if the string contains of three letters
        s = "this";
        System.out.println(s.matches("[a-z]{4}"));

        s = "this is true";
        System.out.println(s.matches("[a-z\\s]{3}"));

        s = "this is true";
        System.out.println(s.matches("[a-z\\s]{12}"));

        s = "this is True";
        System.out.println(s.matches("[^\\d].*")); // Note the difference here first is not digit

        s = "th2is is 2 True";
        System.out.println(s.matches("[^\\d]*")); // Here no digit at all

        s = "th2is is True";
        System.out.println(s.matches("[^\\d]*")); // Here no digit at all

        //if the string does not have a number at the beginning
        s = "this is True";
        System.out.println(s.matches("^[^\\d].*"));

        System.out.println("hello");

        s = "thi2s is yes";
        System.out.println(s.matches("[2]*"));

        s = "this is 2 yes";
        System.out.println(s.matches("([2a-z\\s]*)"));

        s = "23";
        System.out.println(s.matches("([2]?)([3])"));

        s = "t";
        System.out.println(s.matches("[0-9]?"));

    }
}
