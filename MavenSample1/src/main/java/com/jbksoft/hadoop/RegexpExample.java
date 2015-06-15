package com.jbksoft.hadoop;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 10/11/14
 * Time: 11:54 PM
 * To change this template use File | Settings | File Templates.
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexpExample
{
    public static void main(String[] args)
    {
        String string = "var1[value1]";

        //String string = "var1[value1], var2[value2], var3[value3]";
        Pattern pattern = Pattern.compile("(.*?)(\\[)(.*?)(\\])");
        Matcher matcher = pattern.matcher(string);

        System.out.println(matcher.matches());

        System.out.println(matcher.find());


//        List<String> listMatches = new ArrayList<String>();
//
//        while(matcher.find())
//        {
//            listMatches.add(matcher.group(2));
//        }
//
//        for(String s : listMatches)
//        {
//            System.out.println(s);
//        }
    }
}
