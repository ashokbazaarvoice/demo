package com.abc.experiment.algorithms.data_structures.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.abc.experiment.algorithms.data_structures.HashArrayMappedTrie;
import com.abc.experiment.algorithms.data_structures.test.common.JavaMapTest;
import com.abc.experiment.algorithms.data_structures.test.common.MapTest;
import com.abc.experiment.algorithms.data_structures.test.common.Utils;
import com.abc.experiment.algorithms.data_structures.test.common.Utils.TestData;
import com.abc.experiment.algorithms.data_structures.test.common.Utils.Type;

@SuppressWarnings("static-method")
public class HashArrayMappedTreeTests {

    @Test
    public void testHAMT() {
        TestData data = Utils.generateTestData(1000);

        String mapName = "HAMT";
        HashArrayMappedTrie<Integer,String> map = new HashArrayMappedTrie<Integer,String>();
        java.util.Map<Integer,String> jMap = map.toMap();

        assertTrue(MapTest.testMap(map, Type.Integer, mapName,
                                   data.unsorted, data.invalid));
        assertTrue(JavaMapTest.testJavaMap(jMap, Type.Integer, mapName,
                                           data.unsorted, data.sorted, data.invalid));
    }
}
