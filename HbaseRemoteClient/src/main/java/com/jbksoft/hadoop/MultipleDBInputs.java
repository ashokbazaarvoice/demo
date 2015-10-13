package com.jbksoft.hadoop;

import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.classification.InterfaceStability;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.Mapper;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 4/25/14
 * Time: 1:42 PM
 * To change this template use File | Settings | File Templates.
 */
@InterfaceAudience.Public
@InterfaceStability.Stable
public class MultipleDBInputs {
    public static final String TABLE_FORMATS =
            "hbase.mapreduce.multipleinputtables.formats";
    public static final String TABLE_MAPPERS =
            "hbase.mapreduce.multipleinputtables.mappers";

    /**
     * Add a {@link org.apache.hadoop.fs.Path} with a custom {@link org.apache.hadoop.mapreduce.InputFormat} to the list of
     * inputs for the map-reduce job.
     *
     * @param job The {@link org.apache.hadoop.mapreduce.Job}
     * @param htable {@link org.apache.hadoop.fs.Path} to be added to the list of inputs for the job
     * @param inputFormatClass {@link org.apache.hadoop.mapreduce.InputFormat} class to use for this path
     */
    @SuppressWarnings("unchecked")
    public static void addInputTable(Job job, HTable htable,
                                    Class<? extends InputFormat> inputFormatClass) {
        System.out.println("com.jbksoft.hadoop.MultipleDBInputs->addInputTable3");
        String inputFormatMapping = htable.toString() + ";"
                + inputFormatClass.getName();
        Configuration conf = job.getConfiguration();
        String inputFormats = conf.get(TABLE_FORMATS);
        conf.set(TABLE_FORMATS,
                inputFormats == null ? inputFormatMapping : inputFormats + ","
                        + inputFormatMapping);

        job.setInputFormatClass(DelegatingDBInputFormat.class);
    }

    /**
     * Add a {@link Path} with a custom {@link InputFormat} and
     * {@link org.apache.hadoop.mapreduce.Mapper} to the list of inputs for the map-reduce job.
     *
     * @param job The {@link Job}
     * @param htable {@link HTable} to be added to the list of inputs for the job
     * @param inputFormatClass {@link InputFormat} class to use for this path
     * @param mapperClass {@link org.apache.hadoop.mapreduce.Mapper} class to use for this path
     */
    @SuppressWarnings("unchecked")
    public static void addInputTable(Job job, HTable htable,
                                    Class<? extends InputFormat> inputFormatClass,
                                    Class<? extends Mapper> mapperClass) {
        System.out.println("com.jbksoft.hadoop.MultipleDBInputs->addInputTable4");
        addInputTable(job, htable, inputFormatClass);
        Configuration conf = job.getConfiguration();
        String mapperMapping = htable.toString() + ";" + mapperClass.getName();
        String mappers = conf.get(TABLE_MAPPERS);
        conf.set(TABLE_MAPPERS, mappers == null ? mapperMapping
                : mappers + "," + mapperMapping);

        job.setMapperClass(DelegatingDBMapper.class);
    }

    /**
     * Retrieves a map of {@link Path}s to the {@link InputFormat} class
     * that should be used for them.
     *
     * @param job The {@link org.apache.hadoop.mapreduce.JobContext}
     * @see #addInputPath(JobConf, HTable, Class)
     * @return A map of paths to inputformats for the job
     */
    @SuppressWarnings("unchecked")
    static Map<String, Class<? extends InputFormat>> getInputFormatMap(JobContext job) {
        System.out.println("com.jbksoft.hadoop.MultipleDBInputs->getInputFormatMap");
        Map<String, Class<? extends InputFormat>> m = new HashMap<String, Class<? extends InputFormat>>();
        Configuration conf = job.getConfiguration();
        String[] pathMappings = conf.get(TABLE_FORMATS).split(",");
        for (String pathMapping : pathMappings) {
            String[] split = pathMapping.split(";");
            conf.set("hbase.mapreduce.inputtable",split[0]);
            Class<? extends InputFormat> inputFormat;
            try {
//                inputFormat = (Class<? extends InputFormat>) ReflectionUtils.newInstance(conf
//                        .getClassByName(split[1]), conf);
                inputFormat = (Class<? extends InputFormat>) conf.getClassByName(split[1]);
                m.put(split[0], inputFormat);
                System.out.println("com.jbksoft.hadoop.MultipleDBInputs->getInputFormatMap "+split[0]+" : "+split[1]);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return m;
    }

    /**
     * Retrieves a map of {@link Path}s to the {@link Mapper} class that
     * should be used for them.
     *
     * @param job The {@link JobContext}
     * @see #addInputPath(JobConf, HTable, Class, Class)
     * @return A map of paths to mappers for the job
     */
    @SuppressWarnings("unchecked")
    static Map<String, Class<? extends Mapper>>
    getMapperTypeMap(JobContext job) {
        System.out.println("com.jbksoft.hadoop.MultipleDBInputs->getMapperTypeMap");
        Configuration conf = job.getConfiguration();
        if (conf.get(TABLE_MAPPERS) == null) {
            return Collections.emptyMap();
        }
        Map<String, Class<? extends Mapper>> m =
                new HashMap<String, Class<? extends Mapper>>();
        String[] pathMappings = conf.get(TABLE_MAPPERS).split(",");
        for (String pathMapping : pathMappings) {
            String[] split = pathMapping.split(";");
            conf.set("hbase.mapreduce.inputtable",split[0]);
            Class<? extends Mapper> mapClass;
            try {
                mapClass =
                        (Class<? extends Mapper>) conf.getClassByName(split[1]);
                m.put(split[0], mapClass);
                System.out.println("com.jbksoft.hadoop.MultipleDBInputs->getMapperTypeMap "+split[0]+" : "+split[1]);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return m;
    }
}
