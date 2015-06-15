package com.jbksoft.secondarysort;

import com.google.common.base.Throwables;
import org.apache.hadoop.io.BinaryComparable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.google.common.base.Preconditions.checkState;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 3/24/15
 * Time: 3:45 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseAnnotatedWritable extends BinaryComparable
        implements WritableComparable<BinaryComparable> {

    // Buffer that will be allocated and grown as needed, stores the encoded/serialized
    // form of the instance
    private byte[] _bytes = null;
    // Number of bytes in the _bytes buffer that represent the encoded form of this instance
    private int _length;

    // An object is considered "decoded" until its serial form is read from a stream.
    private boolean _decoded = true;
    // An object is considered "encoded" only after its fields are serialized to _bytes
    private boolean _encoded = false;

    /**
     * Read all in the bytes representing this instance, but defer field-level deserialization
     * until someone calls {@link #decode()}
     */
    @Override
    public void readFields(DataInput in)
            throws IOException {
        int newLength = WritableUtils.readVInt(in);
        if (_bytes == null || _bytes.length < newLength) {
            _bytes = new byte[newLength];
        }
        in.readFully(_bytes, 0, newLength);
        _length = newLength;

        _decoded = false;    // I now have serial state that's not reflected in my fields
        _encoded = true;     // OTOH, I'm encoded
    }

    /**
     * Compute the serial form of this Serialized fields in this instance and
     * write the resulting byte[] to the output stream with a VInt prefix indicating the
     * number of bytes.  If the serial form has already been computed, it will be used.
     */
    @Override
    public void write(DataOutput out)
            throws IOException {
        encode();
        WritableUtils.writeVInt(out, _length);
        out.write(_bytes, 0, _length);
    }

    /**
     * Subclasses must invoke this before a Serialized field is updated.  This will
     * ensure that any un-decoded field information present in the serialized form of the
     * object is first decoded.  This is important, because after a field is updated,
     * the subclass must also call {@link #afterFieldUpdated()} to invalidate any cached
     * serialization data (since the field update has invalidated it).  If the decode doesn't
     * happen first, the instance state previously present only in the encoded bytes will be lost
     */
    protected void beforeFieldUpdate() {
        decode();
    }

    /**
     * Subclasses must invoke this when a Serialized field is updated.  This will
     * clear any cached state tied to the values of the instance's serialized fields.  NOTE:
     * make sure to call {@link #beforeFieldUpdate()} before updating the field.
     */
    protected void afterFieldUpdated() {
        _encoded = false;
    }

    /**
     * Subclasses must invoke this when a Serialized field is accessed.  This will
     * ensure that any previously cached serialized form is decoded onto individual fields.
     */
    protected void decode() {
        if (!_decoded) {
            checkState(_encoded, "Cannot decode object without an encoded serial form");
            InputStream in = new ByteArrayInputStream(_bytes);
            try {
                //Serialization.deserialize(new DataInputStream(in), this);

                this.readFields(new DataInputStream(in));
                in.close();

            } catch (IOException ex) {
                Throwables.propagate(ex);
            }
            _decoded = true;
        }
    }

    private void encode() {
        if (!_encoded) {
            try {
                ByteArrayOutputStream baos;
                if (_bytes == null) {
                    baos = new ByteArrayOutputStream();
                } else {
                    baos = new ByteArrayOutputStream(_length);
                }
                DataOutputStream os = new DataOutputStream(baos);
                //Serialization.serialize(this, os); // This class will serialize all the fields marked as @Serialized

                this.write(os);
                os.flush();
                os.close();

                // Always re-assign the _bytes reference to what the output stream
                // finished with.  This takes care both of the initial _bytes allocation,
                // and also any re-allocation that occurs when the buffer is too small
                _bytes = baos.toByteArray();
                _length = baos.size();
                _encoded = true;
            } catch (Exception ex) {
                throw Throwables.propagate(ex);
            }
        }
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

    /**
     * Get the underlying byte array representation of this writable.  You cannot use
     * this value as a seed for deserializing an instance.  IOW,
     * {@link #readFields(java.io.DataInput)} is only compatible with bytes that have been
     * written by {@link #write(java.io.DataOutput)}.  This operation is stipulated by
     * {@link BinaryComparable#getBytes()}
     */
    @Override
    public byte[] getBytes() {
        encode();
        return _bytes;
    }

    /**
     * Get the number of bytes in the {@link #getBytes()} that are valid, as obligated by
     * {@link BinaryComparable#getLength()}
     */
    @Override
    public int getLength() {
        encode();
        return _length;
    }
}
