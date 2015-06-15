package com.aranin.spring.algorithms.graphs;

/**
 * Created by IntelliJ IDEA.
 * User: Niraj Singh
 * Date: 7/8/13
 * Time: 1:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class TravelGraph {

    public static void main(String[] args){
        TravelGraph travelGraph = new TravelGraph();

        MyGraph myGraph = new MyGraph();

        /**
         * create vertices
         */
        MyNode<String, String> myNode = new  MyNode<String, String>("A",null);
        myGraph.addVertex(myNode);
        myNode = new  MyNode<String, String>("B","");
        myGraph.addVertex(myNode);
        myNode = new  MyNode<String, String>("C","");
        myGraph.addVertex(myNode);
        myNode = new  MyNode<String, String>("D","");
        myGraph.addVertex(myNode);
        myNode = new  MyNode<String, String>("E","");
        myGraph.addVertex(myNode);
        myNode = new  MyNode<String, String>("F","");
        myGraph.addVertex(myNode);
        myNode = new  MyNode<String, String>("G","");
        myGraph.addVertex(myNode);

        /**
         * create edges
         * A -> B -> C -> D
         * B -> A -> C
         * C -> A -> B -> E -> F
         * D -> A -> G
         * E -> C -> F -> G
         * F -> C -> E
         * G -> D -> E
         *
         *
         */

        /**
         * edges for A
         */
         MyNode<String, String> sourceNode =   new  MyNode<String, String>("A",null);
         myGraph.addEdge(sourceNode,
                        travelGraph.initializeEdge("B", 12,500));
         myGraph.addEdge(sourceNode,
                        travelGraph.initializeEdge("C", 20,2000));
         myGraph.addEdge(sourceNode,
                        travelGraph.initializeEdge("D", 15,600));


        /**
         * edges for B
         */
         sourceNode =   new  MyNode<String, String>("B",null);


         myGraph.addEdge(sourceNode,
                        travelGraph.initializeEdge("A", 12,500));

         myGraph.addEdge(sourceNode,
                        travelGraph.initializeEdge("C", 15,100));


         /**
         * edges for C
         */
         sourceNode =   new  MyNode<String, String>("C",null);


         myGraph.addEdge(sourceNode,
                        travelGraph.initializeEdge("A", 20,2000));

         myGraph.addEdge(sourceNode,
                        travelGraph.initializeEdge("B", 15,200));

         myGraph.addEdge(sourceNode,
                        travelGraph.initializeEdge("E", 10,10));

         myGraph.addEdge(sourceNode,
                        travelGraph.initializeEdge("F", 10,10));


         /**
         * edges for D
         */
         sourceNode =   new  MyNode<String, String>("D",null);


         myGraph.addEdge(sourceNode,
                        travelGraph.initializeEdge("A", 15,600));

         myGraph.addEdge(sourceNode,
                        travelGraph.initializeEdge("G", 18,300));



         /**
         * edges for E
         */
         sourceNode =   new  MyNode<String, String>("E",null);


         myGraph.addEdge(sourceNode,
                        travelGraph.initializeEdge("C", 15,600));

         myGraph.addEdge(sourceNode,
                        travelGraph.initializeEdge("F", 10,10));


         myGraph.addEdge(sourceNode,
                        travelGraph.initializeEdge("G", 10,30));



         /**
         * edges for F
         */
         sourceNode =   new  MyNode<String, String>("F",null);


         myGraph.addEdge(sourceNode,
                        travelGraph.initializeEdge("C", 10,10));

         myGraph.addEdge(sourceNode,
                        travelGraph.initializeEdge("E", 5,20));


         /**
         * edges for G
         */
         sourceNode =   new  MyNode<String, String>("G",null);


         myGraph.addEdge(sourceNode,
                        travelGraph.initializeEdge("D", 18,300));

         myGraph.addEdge(sourceNode,
                        travelGraph.initializeEdge("E", 10,25));
    }

    public MyNode<String, String> initializeEdge(String uniqueId, int distance, int numVehiclePerMin){
        MyNode<String, String> myNode = new  MyNode<String, String>("B","");
        float weight = this.calculateWeight(distance,numVehiclePerMin);
        myNode.setWeight(weight);
        return myNode;
    }
    /**
     *
     * let us assume that
     * 1. Speed of vehicle on empty road = 60 KMPH
     * 2.speed of vehicle reduces by 10% with every addition of 100 vehicle on road
     *
     * Hence speed of vehicle with n hundred vehicle on road = 60 * (1 - 1/10) power (n-1)
     * Density factor =  (1 - 1/10) power (n-1)
     * weight = distance / (speed on empty road * density factor)
     *
     */
    private float calculateWeight(int distance, int numVehiclePerSecond) {
        int powerFactor =  numVehiclePerSecond/100;
        System.out.println("powerFactor : " + powerFactor);
        float densityFactor = (float)Math.pow(1.0 - (1.0/10.0), powerFactor);
        System.out.println("densityFactor : " + densityFactor);
        float weight =   (float)(distance/(60.0*densityFactor));
        return weight;
    }
}
