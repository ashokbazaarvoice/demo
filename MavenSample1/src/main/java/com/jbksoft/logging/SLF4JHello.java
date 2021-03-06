package com.jbksoft.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 4/16/14
 * Time: 11:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class SLF4JHello {
    private final Logger slf4jLogger = LoggerFactory.getLogger(SLF4JHello.class);

    /**
     * Print hello in log.
     *
     * @param name
     */
    public void sayHello(String name) {
        slf4jLogger.info("Hi, {}", name);
        slf4jLogger.info("Welcome to the HelloWorld example of SLF4J");
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        SLF4JHello slf4jHello = new SLF4JHello();
        slf4jHello.sayHello("srccodes.com");
    }
}