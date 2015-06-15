package com.bazaarvoice.secondarysort;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 6/26/14
 * Time: 10:24 PM
 * To change this template use File | Settings | File Templates.
 */

import org.apache.hadoop.io.BinaryComparable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class UserDate_V2 extends BaseAnnotatedWritable {

    private String userId;
    private long datetime;

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the datetime
     */
    public long getDatetime() {
        return datetime;
    }

    /**
     * @param datetime
     *            the datetime to set
     */
    public void setDatetime(long datetime) {
        this.datetime = datetime;
    }

    @Override
    public boolean equals(Object ob) {
        if (ob == null || this.getClass() != ob.getClass())
            return false;

        UserDate_V2 k = (UserDate_V2) ob;
        if (this.datetime == k.datetime)
            return false;
        if (k.userId != null && this.userId != null
                && !k.userId.equals(this.userId))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        return 31 * result;
       // return 31 * result + datetime.hashCode();
    }

    @Override
    public String toString() {
        return userId + "\t" + datetime;
    }

//    @Override
//    public byte[] getBytes() {
//        return new byte[0];  //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    @Override
//    public int getLength() {
//        return 0;  //To change body of implemented methods use File | Settings | File Templates.
//    }
}
