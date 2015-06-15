package com.aranin.spring.algorithms.graphs;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Niraj Singh
 * Date: 6/25/13
 * Time: 3:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyGraph {
    Map<Object, LinkedList<MyNode>> nodeMap =   null;
    List<MyNode> visited = new ArrayList<MyNode>();
    Deque<MyNode> dftstack = new ArrayDeque<MyNode>();

    public MyGraph(){

    }

    public void addVertex(MyNode myNode){
        LinkedList<MyNode> adjacentList =  null;
        if(this.nodeMap == null){
            nodeMap = new LinkedHashMap<Object, LinkedList<MyNode>>();
            adjacentList = new LinkedList<MyNode>();
            nodeMap.put(myNode.getUniqueId() , adjacentList);
        }else{
            adjacentList = new LinkedList<MyNode>();
            nodeMap.put(myNode.getUniqueId() , adjacentList);
        }
    }

    public void addEdge(MyNode source, MyNode target){
        LinkedList<MyNode> adjacentList =  null;
        if(this.nodeMap == null){
            nodeMap = new LinkedHashMap<Object, LinkedList<MyNode>>();
            adjacentList = new LinkedList<MyNode>();
            adjacentList.add(target);
            nodeMap.put(source.getUniqueId() , adjacentList);
        }else{
            adjacentList =   nodeMap.get(source.getUniqueId());
            adjacentList.add(target);
            nodeMap.put(source.getUniqueId() , adjacentList);
        }
    }


    public boolean isAdjacent(MyNode node1, MyNode node2) throws MyGraphException {
        boolean isAdjacent = false;
        if(this.nodeMap == null){
            throw new MyGraphException("MyGraphException: Graph is empty!!");
        }

        LinkedList<MyNode> adjacentList = nodeMap.get(node1.getUniqueId());
        if(adjacentList == null || adjacentList.size() == 0){
            isAdjacent = false;
        }else{
            for(MyNode node:adjacentList){
                if(node.getUniqueId().equals(node2.getUniqueId())){
                    isAdjacent = true;
                    break;
                }

            }

        }
        return isAdjacent;

    }

    public LinkedList<MyNode> getAdjacentList(MyNode source){
        if(nodeMap == null || nodeMap.size()==0){
            return null;
        }

        else return this.nodeMap.get(source);
    }

    private MyNode getVertex(MyNode source){
        return new MyNode(source.getUniqueId(), null);
    }
    private MyNode getNextAdcacentNode(MyNode source){
       if(nodeMap == null || nodeMap.size()==0){
            return null;
        }
        LinkedList<MyNode> adjacentList =  getAdjacentList(source);
        for(MyNode nextNode:adjacentList){
            if(!this.visited.contains(nextNode)){
                return getVertex(nextNode);
            }
        }

        return null;
    }
    public void depthFirstTraversal(MyNode source){
        if(nodeMap == null || nodeMap.size()==0){
            return;
        }

        //add the source node
        this.visited.add(source);
        this.dftstack.add(source);

        while(this.dftstack.size() > 0){

        }


    }

    public void depthFirstSearch(MyNode myNode){

    }




}
