package com.jbksoft.app.excercise_1;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by ashok.agarwal on 4/27/15.
 */
public class TreeDemo {
}

class MyTreeImpl<T> implements MyTree<T> {
    TNode<T> root;
    int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    @Override
    public void add(T element) {
        if(isEmpty())
            root = new TNode<T>(element);
        else
            root.childs.add(new TNode<T>(element));
    }

    public void add(T element, int level) {
        if(isEmpty())
            root = new TNode<T>(element);
        else {
            TNode<T> tmp = root;
            for(int i = 1; i < level; i++) {
                if(tmp.childs != null)
                    tmp = root.childs.get(0);
            }
            tmp.childs.add(new TNode<T>(element));
        }
    }

    @Override
    public void remove() {
        T element;
        if(isEmpty())
            throw new NoSuchElementException();
        TNode tmp = root;
//        if(tmp.data.equals(element))
//            tmp.

    }

    @Override
    public T get() {
        return null;
    }

    @Override
    public boolean contains(T element) {
        return false;
    }

    @Override
    public int maxDepth() {
        return 0;
    }

    @Override
    public T minValue() {
        return null;
    }

    @Override
    public T maxValue() {
        return null;
    }

    @Override
    public void preOrderTraversal() {

    }

    @Override
    public void inOrderTraversal() {

    }

    @Override
    public void postOrderTraversal() {

    }

    @Override
    public void BreadthFirstTraversal() {

    }

    @Override
    public void mirror() {

    }

    @Override
    public boolean isBST() {
        return false;
    }

    @Override
    public boolean hasPathSum(int sum) {
        return false;
    }
}

interface MyTree<T>{
    int size();
    boolean isEmpty();
    void add(T element);
    void remove();
    T get();
    boolean contains(T element);
    int maxDepth();
    T minValue();
    T maxValue();
    void preOrderTraversal(); // Depth first
    void inOrderTraversal();
    void postOrderTraversal();
    void BreadthFirstTraversal();
    void mirror();
    boolean isBST();
    boolean hasPathSum(int sum);
}

class TNode<T>{
    T data;
    TNode<T> left; // for binary tree only
    TNode<T> right; // for binary tree only
    List<TNode<T>> childs; // for tree with any number of branch
    TNode<T> parent; // for tree with any number of branch

    TNode(T data) {
        this(data, null, null);
    }

    TNode(T data, TNode<T> right) {
        this(data, null, right);
    }

    TNode(T data, TNode<T> left, TNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    TNode(T data, List<TNode<T>> childs) {
        this.data = data;
        this.childs = childs;
    }

    // getter and setter

    public List<TNode<T>> getChilds() {
        if (childs == null)
            childs = Lists.newArrayList();
        return childs;
    }

    public void setChilds(List<TNode<T>> childs) {
        this.childs = childs;
    }

    public void addChild(TNode<T> child) {
        getChilds().add(child);
    }
}
