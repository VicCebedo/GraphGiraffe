/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm;

import com.cebedo.jaghead.domain.IGraph;
import com.cebedo.jaghead.domain.IVertex;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author Vic
 */
public class BreadthFirstTraversal extends AbstractOperations implements ITraversalAlgorithm {

    @Override
    public Set<IVertex> traverse(IGraph graph) {
        // The queue of the search.
        Queue<IVertex> toVisit = new LinkedList<>();
        toVisit.add(graph.getVertices().iterator().next());

        // List of visited vertices.
        Set<IVertex> traversed = new HashSet<>();

        // Loop through all vertices.
        while (!toVisit.isEmpty()) {
            IVertex next = toVisit.poll();
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
