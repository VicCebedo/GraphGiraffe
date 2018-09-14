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
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author Vic
 */
public class BFSEdge extends AbstractGraph implements SearchAlgorithm<Graph, Edge, Condition<Edge>> {

    @Override
    public Set<Edge> search(Graph graph, Condition<Edge> condition) {

        // The queue of the search.
        Queue<Vertex> toVisit = new LinkedList<>();
        toVisit.add(graph.getVertices().iterator().next());

        // List of visited vertices.
        Set<Vertex> done = new HashSet<>();
        Set<Edge> returnSet = new HashSet<>();

        // Loop through all vertices.
        while (!toVisit.isEmpty()) {
            Vertex next = toVisit.poll();
            done.add(next);

            // Check conditions for this node.
            this.getEdges(graph, next).forEach(edge -> {
                if (condition.check(edge)) {
                    returnSet.add(edge);
                }
            });

            // Add the neighbors to visit.
            this.getAdjacentVertices(graph, next).forEach(neighbor -> {
                if (!done.contains(neighbor)) {
                    toVisit.add(neighbor);
                }
            });
        }
        return returnSet;
    }
}
