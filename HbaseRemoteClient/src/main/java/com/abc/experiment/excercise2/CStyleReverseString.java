package com.abc.experiment.excercise2;

/**
 * Created by ashok.agarwal on 6/10/15.
 */
public class CStyleReverseString {
    // the string will have one character extra as null like “abcd” is represented as five characters, including the null character.
    public static void main(String[] args){
        System.out.println(new CStyleReverseString().reverse("hello"));
        System.out.println(new CStyleReverseString().reverseIterative("hello"));
    }

    // recursive
    public String reverse(String str){ // hello
        if(str.length() < 2)
            return str;
        else
            return reverse(str.substring(1, str.length()))+str.substring(0,1);
    }

    // iterative reverse
    public String reverseIterative(String str){
        StringBuilder sb = new StringBuilder();
        if(str.length() < 2)
            return str;
        else {
            int len = str.length();
            for(int i = 0; i < len; i++)
                sb.append(str.charAt(len-1-i));
        }
        return sb.toString();
    }

    // iterative reverse starting from middle so O(n/2)

}
