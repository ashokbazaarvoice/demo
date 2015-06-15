package com.jbksoft.hbase.demo.client;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 9/16/14
 * Time: 12:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class RegexDemo {
    public static void main(String[] args) {
        System.out.println(9>9);
        String path = "/consus/exports/dev/2014/12/12/_distcp";
        String regex = "[^_].*";
        System.out.println(path.matches(regex));

        String txt = "This is text for regex";
        regex = "\\s+";
        System.out.println(txt.matches(regex));
        regex = ".*";
        System.out.println(txt.matches(regex));




//        Pattern pattern = Pattern.compile("\\s+");
//        Matcher matcher = pattern.matcher(txt);
//        System.out.println(matcher.find());
//        System.out.print("Start index: " + matcher.start());
//        System.out.print(" End index: " + matcher.end() + " ");
//        System.out.println(matcher.group());


//        path = "/consus/exports/dev/2014/12/12/distcp";
//        System.out.println(path.matches(regex));
//
//        path = "_consus/exports/dev/2014/12/12/distcp";
//        System.out.println(path.matches(regex));
//
//
//        path = "/consus/exports/dev/2014/12/12/_distcp";
//        Pattern pattern = Pattern.compile(regex);
//        System.out.println(pattern.matcher(path).matches());
//        path = "/consus/exports/dev/2014/12/12/distcp";
//        System.out.println(pattern.matcher(path).matches());
//
//        path = "_consus/exports/dev/2014/12/12/_distcp";
//        System.out.println(pattern.matcher(path).matches());
//        path = "/consus/exports/dev/2014/12/12/distcp";
//        System.out.println(pattern.matcher(path).matches());
    }
}
