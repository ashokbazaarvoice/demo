package com.aranin.spring.algorithms.graphs;

/**
 * Created by IntelliJ IDEA.
 * User: Niraj Singh
 * Date: 7/3/13
 * Time: 4:12 PM
 * To change this template use File | Settings | File Templates.
 */

/**
 * This is a generic node class. A node class will have
 * @param <T1>
 * @param <T2>
 */
public class MyNode<T1, T2> {

    private T1 uniqueId;
    private T2 nodeProperties;
    private float weight;
    public MyNode(T1 el, T2 nodeProperties){
        this.uniqueId = el;
        this.nodeProperties = nodeProperties;
    }

    public T1 getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(T1 uniqueId) {
        this.uniqueId = uniqueId;
    }

    public T2 getNodeProperties() {
        return nodeProperties;
    }

    public void setNodeProperties(T2 nodeProperties) {
        this.nodeProperties = nodeProperties;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
