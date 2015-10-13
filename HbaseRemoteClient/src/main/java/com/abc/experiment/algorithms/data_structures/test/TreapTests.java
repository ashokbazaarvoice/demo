package com.abc.experiment.algorithms.data_structures.test;

import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;

import com.abc.experiment.algorithms.data_structures.BinarySearchTree;
import com.abc.experiment.algorithms.data_structures.Treap;
import com.abc.experiment.algorithms.data_structures.test.common.JavaCollectionTest;
import com.abc.experiment.algorithms.data_structures.test.common.TreeTest;
import com.abc.experiment.algorithms.data_structures.test.common.Utils;
import com.abc.experiment.algorithms.data_structures.test.common.Utils.TestData;
import com.abc.experiment.algorithms.data_structures.test.common.Utils.Type;

@SuppressWarnings("static-method")
public class TreapTests {

    @Test
    public void testTreap() {
        TestData data = Utils.generateTestData(1000);

        String bstName = "Treap";
        BinarySearchTree<Integer> bst = new Treap<Integer>();
        Collection<Integer> bstCollection = bst.toCollection();

        assertTrue(TreeTest.testTree(bst, Type.Integer, bstName,
                                     data.unsorted, data.invalid));
        assertTrue(JavaCollectionTest.testCollection(bstCollection, Type.Integer, bstName,
                                                     data.unsorted, data.sorted, data.invalid));
    }
}
