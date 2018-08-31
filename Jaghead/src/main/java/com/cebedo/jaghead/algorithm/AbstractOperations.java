/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm;

import java.util.HashSet;
import java.util.Set;
import com.cebedo.jaghead.Edge;
import com.cebedo.jaghead.Graph;
import com.cebedo.jaghead.Vertex;

/**
 *
 * @author Vic
 */
public abstract class AbstractOperations {

    /**
     * Two vertices are adjacent if they share the same edge.
     *
     * @param graph
     * @param vertexA
     * @return
     */
    public Set<Vertex> getAdjacentVertices(Graph<Vertex<String, Graph>, Edge<String, Graph, Vertex, Vertex, Integer>> graph, Vertex<String, Graph> vertexA) {
        Set<Vertex> adjacentVertices = new HashSet<>();
        graph.getEdges().forEach(edge -> {
            Vertex src = edge.getSource();
            Vertex tgt = edge.getTarget();
            String id = vertexA.getId();

            if (id.equals(src.getId())) {
                adjacentVertices.add(src);
            }
            if (id.equals(tgt.getId())) {
                adjacentVertices.add(tgt);
            }
        });
        return adjacentVertices;
    }

}
