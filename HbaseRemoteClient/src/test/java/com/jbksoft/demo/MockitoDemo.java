package com.jbksoft.demo;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//Let's import Mockito statically so that the code looks clearer

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 10/29/14
 * Time: 2:55 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith (MockitoJUnitRunner.class)
public class MockitoDemo {

    @Test
    public void myTest() {

        //mock creation
        List mockedList = mock(List.class);

        //using mock object
        mockedList.add("one");

        System.out.println(mockedList.size());

        mockedList.clear();

        //verification
        verify(mockedList).add("one");
        verify(mockedList).clear();
    }

    @Test
    public void myTest2() {


//You can mock concrete classes, not only interfaces
        LinkedList mockedList = mock(LinkedList.class);

//stubbing
        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenThrow(new RuntimeException());

//following prints "first"
        System.out.println(mockedList.get(0));

        System.out.println(mockedList.size());

//following throws runtime exception
//        System.out.println(mockedList.get(1));

//following prints "null" because get(999) was not stubbed
        System.out.println(mockedList.get(999));

//Although it is possible to verify a stubbed invocation, usually it's just redundant
//If your code cares what get(0) returns then something else breaks (often before even verify() gets executed).
//If your code doesn't care what get(0) returns then it should not be stubbed. Not convinced? See here.
        verify(mockedList).get(0);


    }


}
