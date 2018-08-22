/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.graphgiraffe;

import com.cebedo.graphgiraffe.builder.EdgeBuilder;
import com.cebedo.graphgiraffe.builder.VertexBuilder;
import com.cebedo.graphgiraffe.constant.EdgeType;
import com.cebedo.graphgiraffe.immutable.ImmutableEdge;
import com.cebedo.graphgiraffe.immutable.ImmutableVertex;
import com.cebedo.graphgiraffe.model.Graph;
import com.cebedo.graphgiraffe.model.Weight;
import com.cebedo.graphgiraffe.strategy.SampleWeightStrategy;

/**
 *
 * @author Vic
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ImmutableEdge edgeAB
                = new EdgeBuilder()
                        .withType(EdgeType.DIRECTED)
                        .withWeight(
                                new Weight(250),
                                new SampleWeightStrategy())
                        .build();

        ImmutableVertex vertexA
                = new VertexBuilder()
                        .withId("testA")
                        .withEdge(edgeAB)
                        .build();

        ImmutableVertex vertexB
                = new VertexBuilder()
                        .withId("testB")
                        .withEdge(edgeAB)
                        .build();

        Graph graph = new Graph();
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        System.out.println(graph.getEdges());
        System.out.println(graph.getAdjacentVertices(vertexA));
    }

}
