package com.jbksoft.hadoop;

import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.serializer.Deserializer;
import org.apache.hadoop.io.serializer.SerializationFactory;
import org.apache.hadoop.io.serializer.Serializer;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 4/25/14
 * Time: 2:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class TaggedDBInputSplit
        extends InputSplit
        implements Configurable, Writable {
    private Class<? extends InputSplit> inputSplitClass;
    private InputSplit inputSplit;
    private Class<? extends InputFormat> inputFormatClass;
    private Class<? extends Mapper> mapperClass;
    private Configuration conf;

    public TaggedDBInputSplit() {
    }

    public TaggedDBInputSplit(InputSplit inputSplit, Configuration conf, Class<? extends InputFormat> inputFormatClass, Class<? extends Mapper> mapperClass) {
        this.inputSplitClass = inputSplit.getClass();
        this.inputSplit = inputSplit;
        this.conf = conf;
        this.inputFormatClass = inputFormatClass;
        this.mapperClass = mapperClass;
    }

    public InputSplit getInputSplit() {
        return this.inputSplit;
    }

    public Class<? extends InputFormat> getInputFormatClass() {
        return this.inputFormatClass;
    }

    public Class<? extends Mapper> getMapperClass() {
        return this.mapperClass;
    }

    public long getLength()
            throws IOException, InterruptedException {
        return this.inputSplit.getLength();
    }

    public String[] getLocations()
            throws IOException, InterruptedException {
        return this.inputSplit.getLocations();
    }

    public void readFields(DataInput in)
            throws IOException {
        inputSplitClass = (Class<? extends InputSplit>) readClass(in);
        inputFormatClass = (Class<? extends InputFormat<?, ?>>) readClass(in);
        mapperClass = (Class<? extends Mapper<?, ?, ?, ?>>) readClass(in);
        this.inputSplit = ((InputSplit) ReflectionUtils.newInstance(this.inputSplitClass, this.conf));

        SerializationFactory factory = new SerializationFactory(this.conf);
        Deserializer deserializer = factory.getDeserializer(this.inputSplitClass);
        deserializer.open((DataInputStream) in);
        this.inputSplit = ((InputSplit) deserializer.deserialize(this.inputSplit));
    }

    private Class<?> readClass(DataInput in)
            throws IOException {
        String className = Text.readString(in);
        try {
            return this.conf.getClassByName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("readObject can't find class", e);
        }
    }

    public void write(DataOutput out)
            throws IOException {
        Text.writeString(out, this.inputSplitClass.getName());
        Text.writeString(out, this.inputFormatClass.getName());
        if(this.mapperClass == null )
            System.out.println("null");
        Text.writeString(out, this.mapperClass.getName());
        SerializationFactory factory = new SerializationFactory(this.conf);
        Serializer serializer = factory.getSerializer(this.inputSplitClass);

        serializer.open((DataOutputStream) out);
        serializer.serialize(this.inputSplit);
    }

    public Configuration getConf() {
        return this.conf;
    }

    public void setConf(Configuration conf) {
        this.conf = conf;
    }
}
