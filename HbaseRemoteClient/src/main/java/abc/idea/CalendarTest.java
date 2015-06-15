package abc.idea;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 3/26/14
 * Time: 11:41 AM
 * To change this template use File | Settings | File Templates.
 */
public class CalendarTest {
    public static void main(String[] args) throws Exception {
        // Constructor allows to set year, month and date
        Calendar cal1 = new GregorianCalendar(2008, 01, 01);
        // Constructor could also be empty
        // Calendar cal2 = new GregorianCalendar();
        // Change the month
        cal1.set(Calendar.MONTH, Calendar.MAY);

        System.out.println("Year: " + cal1.get(Calendar.YEAR));
        System.out.println("Month: " + (cal1.get(Calendar.MONTH) + 1));
        System.out.println("Days: " + cal1.get(Calendar.DAY_OF_MONTH));

        // format the output with leading zeros for days and month
        SimpleDateFormat date_format = new SimpleDateFormat("yyyyMMdd");
        System.out.println(date_format.format(cal1.getTime()));
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        System.out.println(new Date());
        //System.out.println(GetUTCdatetimeAsString());

        Date prevDate;

       // <name>magpieNominalISO8601</name>
       // <value>2014-06-16T00:00:00Z</value>
        String currentDateStr = "2014-01-06T01:00:00Z";
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        df.setTimeZone(tz);
        Date currentDate = df.parse(currentDateStr);

        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.DATE, -10); // add 10 days

        prevDate = cal.getTime();

        System.out.println(prevDate);

        int day = cal.get(Calendar.DAY_OF_MONTH);//Calendar.DATE
        int month = cal.get(Calendar.MONTH)+1;
        int year = cal.get(Calendar.YEAR);
        System.out.println(day+"/"+month+"/"+year);
        System.out.println(getPrevDate(6));

    }
    public static String GetUTCdatetimeAsString()
    {
        final SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        final String utcTime = sdf.format(new Date());

        return utcTime;
    }

    public static String getPrevDate(int periodInDays) throws ParseException {

        String currentDateStr = "2014-02-06T01:00:00Z";
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        df.setTimeZone(tz);
        Date currentDate = df.parse(currentDateStr);

        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        int curDay = cal.get(Calendar.DAY_OF_MONTH);
        int curMonth = cal.get(Calendar.MONTH)+1;
        int curYear = cal.get(Calendar.YEAR);
        cal.add(Calendar.DATE, -periodInDays); // add -10 days

        int day = cal.get(Calendar.DAY_OF_MONTH);
        String dayStr;
        if(day<10)
            dayStr = "0"+day;
        else
            dayStr = Integer.toString(day);
        int month = cal.get(Calendar.MONTH)+1;
        String monthStr;
        if(month<10)
            monthStr = "0"+month;
        else
            monthStr = Integer.toString(month);

        int year = cal.get(Calendar.YEAR);


        if(curYear > year && (curDay >= periodInDays) )
            return Integer.toString(year);
        else if(curMonth > month && (curDay >= periodInDays))
            return year+"/"+monthStr;
        else
            return year+"/"+monthStr+"/"+dayStr;
    }
}
