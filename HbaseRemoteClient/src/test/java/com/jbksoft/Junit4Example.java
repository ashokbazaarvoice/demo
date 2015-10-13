package com.jbksoft;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by ashok.agarwal on 7/3/15.
 */
public class Junit4Example {

    private int myInt;

    @Before
    public void assignIntValue() {
        myInt = 42;
    }

    @Test
    public void checkIntValueIsCorrect() {
        Assert.assertEquals(42, myInt);
    }

    @After
    public void unsetIntValue() {
        myInt = -1;
    }
}
