package com.bazaarvoice.mockito.demo;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 10/22/14
 * Time: 1:49 PM
 * To change this template use File | Settings | File Templates.
 */


//import com.bazaarvoice.consus.HBasePersister;
//import com.bazaarvoice.consus.HBaseSchema;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.HTablePool;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
//import org.springframework.beans.factory.annotation.Autowired;


import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith (MockitoJUnitRunner.class)
public class EmoReviewTest {
    @Mock
    HTableInterface table1;
    @Mock
    private HTablePool pool;
    @Captor
    private ArgumentCaptor putCaptor;

    Configuration config;
//    HBaseSchema hBaseSchema;

    String DEFAULT_FEEDDATE = "2013-03-31";

//    @Mock
//    private HBasePersister persister;

    @Before
    public void setUp() throws Exception {
        config = new Configuration();
        config.set("importStatsTable", "consus_importstats");
        config.set("nativeReviewsTable", "consus_nativereviews");
        config.set("feeddate", DEFAULT_FEEDDATE);

        when(pool.getTable("nativeReviewsTable")).thenReturn(table1);

//        persister = new HBasePersister(pool, config);
    }

//    @Autowired
//    public void setHTablePool(HTablePool pool) {
//        this.pool = pool;
//    }

    @Test
    public void testInsertRecord()
            throws Exception {
        // return mock table when getTable is called
//        when(pool.getTable("nativeReviewsTable")).thenReturn(table1);

//        Mockito.doReturn(table1).when(pool).getTable("nativeReviewsTable");
       // when(pool.getTable("importStatsTable")).thenReturn(table2);

//        persister = new HBasePersister(pool, config); // mocked persister to check persist method.

        System.out.println(Bytes.toString(pool.getTable("nativeReviewsTable").getTableName()));
       // Put put = putCaptor.getValue();
    }


    @Mock
    Iterator i;

    @Test
    public void iterator_will_return_hello_world(){
        //arrange
//        Iterator i=mock(Iterator.class);
        when(i.next()).thenReturn("Hello").thenReturn("World");
        //act
        String result=i.next()+" "+i.next();
        //assert
        Assert.assertEquals("Hello World", result);
    }
//
//    @Mock
//    Comparable c;
//
//    @Test
//    public void with_arguments(){
////        Comparable c=mock(Comparable.class);
//        when(c.compareTo("Test")).thenReturn(1);
//        when(c.compareTo("Test1")).thenReturn(1);
//        assertEquals(1,c.compareTo("Test1"));
//    }
//
//    @Test
//    public void with_unspecified_arguments(){
//        when(c.compareTo(anyInt())).thenReturn(-1);
//        assertEquals(-1,c.compareTo(5));
//    }
//
//    @Mock
//    OutputStream mock;
//
//    @Test(expected=IOException.class)
//    public void OutputStreamWriter_rethrows_an_exception_from_OutputStream()
//            throws IOException{
////        OutputStream mock=mock(OutputStream.class);
//        OutputStreamWriter osw=new OutputStreamWriter(mock);
//        doThrow(new IOException()).when(mock).close();
//        osw.close();
//    }

    @Test
    public void OutputStreamWriter_Closes_OutputStream_on_Close()
            throws IOException{
        OutputStream mock=mock(OutputStream.class);
        OutputStreamWriter osw=new OutputStreamWriter(mock);
        osw.close();
        verify(mock).close();
    }

//    @Mock
//    Person person;
//
//    @Test
//    public void testFactory(){
//        person
//    }
}
