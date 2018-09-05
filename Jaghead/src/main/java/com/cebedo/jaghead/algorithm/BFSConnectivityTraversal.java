/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm;

import com.cebedo.jaghead.Graph;
import com.cebedo.jaghead.Vertex;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author Vic
 */
public class BFSConnectivityTraversal extends AbstractGraph implements SearchAlgorithm<Graph, Vertex> {

    @Override
    public Set<Vertex> traverse(Graph graph) {

        // The queue of the search.
        Queue<Vertex> toVisit = new LinkedList<>();
        toVisit.add(graph.getVertices().iterator().next());

        // List of visited vertices.
        Set<Vertex> traversed = new HashSet<>();

        // Loop through all vertices.
        while (!toVisit.isEmpty()) {
            Vertex<String> next = toVisit.poll();
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
