package com.abc.experiment.algorithms.data_structures.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.abc.experiment.algorithms.data_structures.SuffixTrie;
import com.abc.experiment.algorithms.data_structures.test.common.SuffixTreeTest;

@SuppressWarnings("static-method")
public class SuffixTrieTests {

    @Test
    public void testSuffixTrie() {
        String bookkeeper = "bookkeeper";
        SuffixTrie<String> trie = new SuffixTrie<String>(bookkeeper);
        assertTrue(SuffixTreeTest.suffixTreeTest(trie, bookkeeper));
    }
}
