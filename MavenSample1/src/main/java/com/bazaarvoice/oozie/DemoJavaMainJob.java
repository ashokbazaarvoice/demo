package com.bazaarvoice.oozie;

/**
 * Created by ashok.agarwal on 3/11/14.
 */
public class DemoJavaMainJob {

    public static void main(String[] args) throws Exception {
        long time =System.currentTimeMillis();
        System.out.println("Hello : " + time);
        Thread.sleep(400000);
        System.out.println("Hello : " + (System.currentTimeMillis() - time));
        System.out.println("Hello : " + Thread.currentThread().getName());
        if (args.length <= 2) {
            System.out.println("Hello : " + args[0] + " : " + args[1]);
        }

    }
}
