package com.abc.experiment.algorithms.data_structures.test;

import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;

import com.abc.experiment.algorithms.data_structures.PatriciaTrie;
import com.abc.experiment.algorithms.data_structures.test.common.JavaCollectionTest;
import com.abc.experiment.algorithms.data_structures.test.common.TreeTest;
import com.abc.experiment.algorithms.data_structures.test.common.Utils;
import com.abc.experiment.algorithms.data_structures.test.common.Utils.TestData;
import com.abc.experiment.algorithms.data_structures.test.common.Utils.Type;

@SuppressWarnings("static-method")
public class PatriciaTreeTests {

    @Test
    public void testPatriciaTrie() {
        TestData data = Utils.generateTestData(1000);

        String bstName = "PatriciaTrie";
        PatriciaTrie<String> bst = new PatriciaTrie<String>();
        Collection<String> bstCollection = bst.toCollection();

        assertTrue(TreeTest.testTree(bst, Type.String, bstName,
                                     data.unsorted, data.invalid));
        assertTrue(JavaCollectionTest.testCollection(bstCollection, Type.String, bstName,
                                                     data.unsorted, data.sorted, data.invalid));
    }
}