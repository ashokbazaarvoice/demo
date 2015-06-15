package com.jbksoft.app;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 3/31/15
 * Time: 3:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class CStyleStringReverse {

    public static void main(String[] args){
        System.out.println(getReverse("hello "));
    }

    public static String getReverse(String st){
        if(st.length()>1) {
            return getReverse(st.substring(1, (st.length()))) + String.valueOf(st.charAt(0));
           // return String.valueOf(st.charAt(st.length())) + getReverse(st.substring(0, st.length()-1));
        } else {
            return null;
            //return String.valueOf(st.charAt(0));
        }
    }
}
