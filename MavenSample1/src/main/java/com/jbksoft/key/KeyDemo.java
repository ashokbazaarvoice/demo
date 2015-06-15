package com.jbksoft.key;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 11/11/14
 * Time: 2:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class KeyDemo {
    public static void main(String[] args){
        System.out.println(System.getenv("magpieS3accessKeyId"));
        System.out.println(System.getenv("magpieS3secretAccessKey"));
        System.out.println(System.getenv("AWS_ACCESS_KEY_ID"));
        System.out.println(System.getenv("AWS_SECRET_ACCESS_KEY"));
        System.out.println(System.getProperty("AWS_ACCESS_KEY_ID"));
        System.out.println(System.getProperty("AWS_SECRET_ACCESS_KEY"));

//        System.out.println(ProcessBuilder.class.environment().get("variablename"));
        for( Object o : System.getenv().entrySet()  ){
            System.out.println( o );
        }
        for( Object o : System.getProperties().entrySet()  ){
            System.out.println( o );
        }
    }
}
