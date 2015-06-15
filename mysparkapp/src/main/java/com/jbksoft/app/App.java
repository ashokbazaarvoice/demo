package com.jbksoft.app;

import org.apache.spark.launcher.SparkLauncher;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        Process spark = new SparkLauncher()
                        .setAppResource("/my/app.jar")
                    .setMainClass("my.spark.app.Main")
                    .setMaster("local")
                     .setConf(SparkLauncher.DRIVER_MEMORY, "2g")
                     .launch();
               spark.waitFor();
    }
}
