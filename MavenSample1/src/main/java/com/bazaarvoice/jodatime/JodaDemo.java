package com.bazaarvoice.jodatime;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.ISODateTimeFormat;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 12/11/14
 * Time: 11:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class JodaDemo {
    public static void main(String[] args) {
        DateTime _eventDate = new DateTime();
        System.out.println(_eventDate);
        DateTime _datasetDate = _eventDate.minusDays(1);
        System.out.println(_datasetDate);
        System.out.println(ISODateTimeFormat.dateTimeNoMillis().withZone(DateTimeZone.UTC).print(_datasetDate));
        System.out.println(ISODateTimeFormat.dateTime().withZone(DateTimeZone.UTC).print(_datasetDate));
//        System.out.println(ISODateTimeFormat.dateTimeParser().withZone(DateTimeZone.UTC).print(_datasetDate));
    }
}
