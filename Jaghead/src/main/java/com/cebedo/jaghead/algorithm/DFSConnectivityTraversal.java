/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm;

import com.cebedo.jaghead.Edge;
import com.cebedo.jaghead.Graph;
import com.cebedo.jaghead.Vertex;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 *
 * @author Vic
 */
public class DFSConnectivityTraversal
        extends AbstractOperations
        implements SearchAlgorithm<Graph<Vertex<String, Graph>, Edge<String, Graph, Vertex, Vertex, Integer>>, Vertex> {

    @Override
    public Set<Vertex> traverse(Graph<Vertex<String, Graph>, Edge<String, Graph, Vertex, Vertex, Integer>> graph) {

        // The queue of the search.
        Stack<Vertex> toVisit = new Stack();
        toVisit.add(graph.getVertices().iterator().next());

        // List of visited vertices.
        Set<Vertex> traversed = new HashSet<>();

        // Loop through all vertices.
        while (!toVisit.isEmpty()) {
            Vertex<String, Graph> next = toVisit.pop();
            traversed.add(next);
            this.getAdjacentVertices(graph, next).forEach(neighbor -> {
                if (!traversed.contains(neighbor)) {
                    toVisit.add(neighbor);
                }
            });
        }
        return traversed;
    }
}
