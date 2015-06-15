package com.bazaarvoice.secondarysort;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 6/26/14
 * Time: 10:33 PM
 * To change this template use File | Settings | File Templates.
 */
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class UserDateGroupingComparator extends WritableComparator {

    protected UserDateGroupingComparator() {
        super(UserDate.class, true);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public int compare(WritableComparable w1, WritableComparable w2) {
        System.out.println("### in UserDateGroupingComparator ");
        UserDate key1 = (UserDate) w1;
        UserDate key2 = (UserDate) w2;

        // (check on UserId)
        return key1.getUserId().compareTo(key2.getUserId());
    }

    // add the raw byte compare method
}
