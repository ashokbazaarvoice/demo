package com.abc.experiment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 4/23/15
 * Time: 11:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class JodaTimeDemo {
    public static void main(String[] args){
        org.joda.time.DateTime now = new org.joda.time.DateTime();
        System.out.println( "Local time in ISO 8601 format: " + now );
        System.out.println( "UTC/GMT date-time in ISO 8601 format: " + new org.joda.time.DateTime( org.joda.time.DateTimeZone.UTC ));

        String DATEFORMAT = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);
        Date date = new Date();
        String localTime = sdf.format(date);
        System.out.println( "Local date-time in "+DATEFORMAT +" format: " + localTime);

        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String utcTime = sdf.format(date);
        System.out.println( "UTC/GMT date-time in "+DATEFORMAT +" format: " + utcTime);

        TimeZone tz = TimeZone.getTimeZone("UTC");
        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        sdf.setTimeZone(tz);
        String nowAsISO = sdf.format(new Date());
        System.out.println( "UTC/GMT date-time in yyyy-MM-dd'T'HH:mm'Z' format: " + nowAsISO);
    }
}
