package com.abc.regex;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 3/20/15
 * Time: 3:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class RegexDemo {

    public static void main(String[] args) {

        String s = "true";
        System.out.println(s.matches("true"));

        s = "this is true";
        System.out.println(s.matches("true"));

        s = "this is true";
        System.out.println(s.matches(".*true.*"));

        System.out.println("Here");

        s = "this is true";
        System.out.println(s.matches(".*.true"));

        s = "this is True";
        System.out.println(s.matches(".*.[tT]rue"));

        s = "this is yes";
        System.out.println(s.matches(".*.([tT]rue|[yY]es)"));

        s = "this is True";
        System.out.println(s.matches(".*.[tT]rue|[yY]es"));

        s = "this is True";
        System.out.println(s.matches("[a-zA-Z\\s]*"));

    }
}
