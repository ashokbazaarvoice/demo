package com.jbksoft.oozie;

/**
 * Oozie job with Java Main.
 */
public class DemoJavaMainJob {
    public static void main(String[] args)
            throws Exception {
        System.out.println("Hello World!");
        long time = System.currentTimeMillis();
        System.out.println("Hello : " + time);
        Thread.sleep(100000);
        System.out.println("Hello : " + (System.currentTimeMillis() - time));
        System.out.println("Hello : " + Thread.currentThread().getName());
        if (args.length <= 2) {
            System.out.println("Hello : " + args[0] + " : " + args[1]);
        }
    }
}
