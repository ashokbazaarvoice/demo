package com.abc;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 4/25/14
 * Time: 2:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class DelegatingDBMapper <K1, V1, K2, V2> extends Mapper<K1, V1, K2, V2> {

    private Mapper<K1, V1, K2, V2> mapper;

    @SuppressWarnings("unchecked")
    protected void setup(Context context)
            throws IOException, InterruptedException {
        System.out.println("com.abc.DelegatingDBMapper->setup");
        // Find the Mapper from the TaggedInputSplit.
        TaggedDBInputSplit inputSplit = (TaggedDBInputSplit) context.getInputSplit();
        System.out.println(inputSplit.getMapperClass());
        mapper = (Mapper<K1, V1, K2, V2>) ReflectionUtils.newInstance(inputSplit
                .getMapperClass(), context.getConfiguration());

    }

    @SuppressWarnings("unchecked")
    public void run(Context context)
            throws IOException, InterruptedException {
        System.out.println("com.abc.DelegatingDBMapper->run");
        setup(context);
        mapper.run(context);
        cleanup(context);
    }
}