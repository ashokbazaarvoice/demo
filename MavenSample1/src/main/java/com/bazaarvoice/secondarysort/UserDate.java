package com.bazaarvoice.secondarysort;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 6/26/14
 * Time: 10:24 PM
 * To change this template use File | Settings | File Templates.
 */
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;
import org.apache.hadoop.io.WritableUtils;

public class UserDate implements WritableComparable<UserDate> {

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
    public void readFields(DataInput dataInput) throws IOException {
        userId = dataInput.readUTF();
        datetime = dataInput.readLong();
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(userId);
        dataOutput.writeLong(datetime);
    }

    @Override
    public int compareTo(UserDate otherObject) {
        int cmp = this.userId.compareTo(otherObject.userId);
        if (cmp != 0) {
            return cmp;
        } else if (this.datetime != otherObject.datetime) {
            return this.datetime < otherObject.datetime ? -1 : 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object ob) {
        if (ob == null || this.getClass() != ob.getClass())
            return false;

        UserDate k = (UserDate) ob;
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

    /** A Comparator optimized for UserDate. */

    //the comparator for IntWritables implements the raw compare() method by reading an integer
    // from each of the byte arrays b1 and b2 and comparing them directly from the given start
    // positions (s1 and s2) and lengths (l1 and l2).
    public static class Comparator extends WritableComparator {
        public Comparator() {
            super(UserDate.class);
        }
        @Override
        public int compare(byte[] b1, int s1, int l1,
                           byte[] b2, int s2, int l2) {
            int n1 = WritableUtils.decodeVIntSize(b1[s1]);
            int n2 = WritableUtils.decodeVIntSize(b2[s2]);
            return compareBytes(b1, s1+n1, l1-n1, b2, s2+n2, l2-n2);
        }
    }

    static {                                        // register this comparator
        WritableComparator.define(UserDate.class, new Comparator());
    }

}
