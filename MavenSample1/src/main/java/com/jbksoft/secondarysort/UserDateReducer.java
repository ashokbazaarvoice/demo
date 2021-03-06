package com.jbksoft.secondarysort;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 6/26/14
 * Time: 10:27 PM
 * To change this template use File | Settings | File Templates.
 */
import java.io.IOException;
import java.text.ParseException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class UserDateReducer extends Reducer<UserDate, Text, Text, Text> {

    private static final Log log = LogFactory.getLog(UserDateReducer.class);

    private Text itemList = new Text();
    private Text userIdDate = new Text();

    @Override
    public void reduce(UserDate key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {

        String str = "";
        for (final Text val : values) {
            if (StringUtils.isValid(val.toString()))
                str = str + "," + val.toString();
        }
        str = str.replaceFirst(",", "");
        log.debug("######### " + str);
        log.debug("######### " + key.getUserId());
        String userIdDateStr = null;
        try {
            userIdDateStr = key.getUserId() + "\t"
                    + StringUtils.getDateStr(key.getDatetime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemList.set(str);
        userIdDate.set(userIdDateStr);
        context.write(userIdDate, itemList);
    }

}