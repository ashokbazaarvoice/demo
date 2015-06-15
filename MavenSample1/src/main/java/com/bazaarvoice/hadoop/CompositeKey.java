package com.bazaarvoice.hadoop;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 4/24/14
 * Time: 10:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class CompositeKey implements WritableComparable<CompositeKey> {

    private String sessionId;
    private String datetime;

    /**
     * @return the sessionId
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * @param sessionId
     *            the sessionId to set
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * @return the datetime
     */
    public String getDatetime() {
        return datetime;
    }

    /**
     * @param datetime
     *            the datetime to set
     */
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        sessionId = dataInput.readUTF();
        datetime = dataInput.readUTF();
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(sessionId);
        dataOutput.writeUTF(datetime);
    }

    @Override
    public int compareTo(CompositeKey otherObject) {
        int cmp = this.sessionId.compareTo(otherObject.sessionId);
        if (cmp != 0) {
            return cmp;
        }
        return this.datetime.compareTo(otherObject.datetime);
    }

    @Override
    public boolean equals(Object ob) {
        if (ob == null || this.getClass() != ob.getClass())
            return false;

        CompositeKey k = (CompositeKey) ob;
        if (k.datetime != null && this.datetime != null
                && !k.datetime.equals(this.datetime))
            return false;
        if (k.sessionId != null && this.sessionId != null
                && !k.sessionId.equals(this.sessionId))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = sessionId.hashCode();
        result = 31 * result + datetime.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return sessionId + "\u0002" + datetime;
    }

}