package com.jbksoft.hadoop;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by ashok.agarwal on 3/12/14.
 * -verbose:gc -XX:+PrintGCDetails
 * XX:ParallelGCThreads=8  -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+HeapDumpOnOutOfMemoryError -XX:ErrorFile=${HADOOP_LOG_DIR}/hs_err_pid%p.log
 */
public class DateTest {

    public static void main(String[] args) throws Exception{
        TimeZone tz = TimeZone.getTimeZone("UTC");
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        df.setTimeZone(tz);
        Date current = new Date();
        String date = df.format(current);
        System.out.println(current+" : "+date);

        String dateStr =  "2014-02-23T00:00:00Z";

        System.out.println(current+" : "+df.parse(dateStr));

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(current);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);

        System.out.println(hours);

        System.out.println(current.getTime()+" : "+ System.currentTimeMillis());

        int constant = 60;

        long minsLong = 0, prevTime = 0, currTime = 0;


//        if(constant < 60) {
             minsLong = ((current.getTime() / 1000) / (constant*60)) ;
             prevTime = (minsLong-1)*constant*60*1000;
             currTime = minsLong*constant*60*1000;
//        } else if (60 <= constant && constant < 3600) {
//             minsLong = ((current.getTime() / 1000) / 3600) ;
//             prevTime = (60*minsLong-constant)*60*1000;
//             currTime = minsLong*3600*1000;
//        }
        System.out.println(new Date(prevTime)+" : "+prevTime);
        System.out.println(new Date(currTime)+" : "+(currTime));
        System.out.println(new Date(currTime-1)+" : "+(currTime-1));
        long timestamp = 1394754969615l;
        if (prevTime <= timestamp && timestamp < currTime) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}
