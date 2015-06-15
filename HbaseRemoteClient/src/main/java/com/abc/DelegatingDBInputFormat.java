package com.abc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.IOException;
import java.util.*;

public class DelegatingDBInputFormat<K, V>
        extends InputFormat<K, V> {
    public DelegatingDBInputFormat() {
    }

    public List<InputSplit> getSplits(JobContext job)
            throws IOException, InterruptedException {
        System.out.println("com.abc.DelegatingDBInputFormat->getSplits");

        Configuration conf = job.getConfiguration();
        Job jobCopy =new Job(conf);

        List<InputSplit> splits = new ArrayList<InputSplit>();

        Map<String, Class<? extends InputFormat>> formatMap =
                MultipleDBInputs.getInputFormatMap(job);

        Map<String, Class<? extends Mapper>> mapperMap = MultipleDBInputs
                .getMapperTypeMap(job);

        System.out.println("com.abc.DelegatingDBInputFormat->getSplits "+mapperMap.size());
        for(Map.Entry<String, Class<? extends Mapper>> mapEntry :
                mapperMap.entrySet()){
            System.out.println("com.abc.DelegatingDBInputFormat->getSplits "+mapEntry.getKey() +" : "+mapEntry.getValue());
        }

//        Map<Class<? extends InputFormat>, HTable> formatPaths
//                = new HashMap<Class<? extends InputFormat>, HTable>();

        // First, build a map of InputFormats to Paths
//        for (Map.Entry<HTable, InputFormat> entry : formatMap.entrySet()) {
//            if (!formatPaths.containsKey(entry.getValue().getClass())) {
//                formatPaths.put(entry.getValue().getClass(), entry.getKey());
//            }
//            System.out.println("com.abc.DelegatingDBInputFormat->getSplits "+entry.getValue().getClass()+" : "+entry.getKey());
////            formatPaths.get(entry.getValue().getClass()).add(entry.getKey());
//        }


        for (Map.Entry<String, Class<? extends InputFormat>> formatEntry :
                formatMap.entrySet()) {

            Class<? extends InputFormat> formatClass = formatEntry.getValue();

            InputFormat format = (InputFormat) ReflectionUtils.newInstance(formatClass, conf);

            HTable hTable = new HTable(formatEntry.getKey());
            System.out.println(hTable+" : "+formatClass);

//            Map<Class<? extends Mapper>, HTable> mapperPaths
//                    = new HashMap<Class<? extends Mapper>, HTable>();

            // Now, for each set of paths that have a common InputFormat, build
            // a map of Mappers to the paths they're used for
            //for (HTable path : paths) {
            Class<? extends Mapper> mapperClass = mapperMap.get(formatEntry.getKey());
//            if (!mapperPaths.containsKey(mapperClass)) {
//                mapperPaths.put(mapperClass, hTable);
//            }
           // }

            // Now each set of paths that has a common InputFormat and Mapper can
            // be added to the same job, and split together.
//            for (Map.Entry<Class<? extends Mapper>, HTable> mapEntry :
//                    mapperPaths.entrySet()) {

//            if (mapperClass == null) {
//                    try {
//                        mapperClass = job.getMapperClass();
//                    } catch (ClassNotFoundException e) {
//                        throw new IOException("Mapper class is not found", e);
//                    }
//            }

//                TableInputFormat.setHTable(jobCopy, paths.toArray(new Path[paths
//                        .size()]));

            conf = jobCopy.getConfiguration();
            conf.set("hbase.mapreduce.inputtable", formatEntry.getKey());



                // Get splits for each input path and tag with InputFormat
                // and Mapper types by wrapping in a TaggedInputSplit.

            List<InputSplit> pathSplits = format.getSplits(jobCopy);
            System.out.println("com.abc.DelegatingDBInputFormat->getSplits "+formatClass+" : "+pathSplits.size()+" : "+mapperClass+" : "+formatEntry.getKey() + " : "+hTable);
            for (InputSplit pathSplit : pathSplits) {
                splits.add(new TaggedDBInputSplit(pathSplit, conf, format.getClass(),
                        mapperClass));
            }
//            }
        }
        System.out.println("com.abc.DelegatingDBInputFormat->getSplits "+splits.size());
        return splits;
    }

    @Override
    public RecordReader<K, V> createRecordReader(InputSplit split,
                                                 TaskAttemptContext context) throws IOException, InterruptedException {
        System.out.println("com.abc.DelegatingDBInputFormat->createRecordReader");
        return new DelegatingDBRecordReader<K, V>(split, context);
    }
}


