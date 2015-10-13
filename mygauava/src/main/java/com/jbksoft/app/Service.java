package com.jbksoft.app;

/**
 * Created by ashok.agarwal on 8/20/15.
 *
 * java -Dmyvar=hello1 -cp target/classes com.jbksoft.app.Service
 */
public class Service {
    public static void main( String[] args ){
        String context = System.getProperty("myvar");
        System.out.println("context: "+context);
    }
}
