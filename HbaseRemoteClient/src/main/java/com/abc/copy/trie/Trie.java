package com.abc.copy.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 4/23/15
 * Time: 4:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class Trie {
    private TrieNode root;

    /**
     * Constructor
     */
    public Trie() {
        root = new TrieNode();
    }

    /**
     * Adds a word to the Trie
     *
     * @param word
     */
    public void addWord(String word) {
        root.addWord(word.toLowerCase());
    }

    /**
     * Get the words in the Trie with the given
     * prefix
     *
     * @param prefix
     * @return a List containing String objects containing the words in
     *         the Trie with the given prefix.
     */
    public List getWords(String prefix) {
        //Find the node which represents the last letter of the prefix
        TrieNode lastNode = root;
        for (int i = 0; i < prefix.length(); i++) {
            lastNode = lastNode.getNode(prefix.charAt(i));

            //If no node matches, then no words exist, return empty list
            if (lastNode == null) {
                return new ArrayList();
            }
        }

        //Return the words which eminate from the last node
        return lastNode.getWords();
    }

    public static void main(String[] args){
        Trie trie = new Trie();
        trie.addWord("are");
        trie.addWord("area");
        trie.addWord("base");
        trie.addWord("cat");
        trie.addWord("cater");
        trie.addWord("basement");
        trie.addWord("Hello");
        trie.addWord("Hell");
        trie.addWord("beach");
        trie.addWord("bean");
        System.out.println(Arrays.toString(trie.getWords("ca").toArray()));
    }
}

class TrieNode {
    private TrieNode parent;
    private TrieNode[] children;
    private boolean isLeaf;     //Quick way to check if any children exist
    private boolean isWord;     //Does this node represent the last character of a word
    private char character;     //The character this node represents

    /**
     * Constructor for top level root node.
     */
    public TrieNode() {
        children = new TrieNode[26];
        isLeaf = true;
        isWord = false;
    }

    /**
     * Constructor for child node.
     */
    public TrieNode(char character) {
        this();
        this.character = character;
    }

    /**
     * Adds a word to this node. This method is called recursively and
     * adds child nodes for each successive letter in the word, therefore
     * recursive calls will be made with partial words.
     *
     * @param word the word to add
     */
    protected void addWord(String word) {
        isLeaf = false;
        int charPos = word.charAt(0) - 'a';

        if (children[charPos] == null) {
            children[charPos] = new TrieNode(word.charAt(0));
            children[charPos].parent = this;
        }

        if (word.length() > 1) {
            children[charPos].addWord(word.substring(1));
        } else {
            children[charPos].isWord = true;
        }
    }

    /**
     * Returns the child TrieNode representing the given char,
     * or null if no node exists.
     *
     * @param c
     * @return
     */
    protected TrieNode getNode(char c) {
        return children[c - 'a'];
    }

    /**
     * Returns a List of String objects which are lower in the
     * hierarchy that this node.
     *
     * @return
     */
    protected List getWords() {
        //Create a list to return
        List list = new ArrayList();

        //If this node represents a word, add it
        if (isWord) {
            list.add(toString());
        }

        //If any children
        if (!isLeaf) {
            //Add any words belonging to any children
            for (int i = 0; i < children.length; i++) {
                if (children[i] != null) {
                    list.addAll(children[i].getWords());
                }
            }
        }
        return list;
    }


    /**
     * Gets the String that this node represents.
     * <p/>
     * For example, if this node represents the character t, whose parent
     * <p/>
     * represents the charater a, whose parent represents the character
     * <p/>
     * c, then the String would be "cat".
     *
     * @return
     */

    public String toString() {
        if (parent == null){
            return "";
        } else {
            return parent.toString() + new String(new char[] {character});
        }
    }
}