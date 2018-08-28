/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.giraffe.algorithm;

import com.cebedo.giraffe.domain.IGraph;
import com.cebedo.giraffe.domain.IVertex;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author Vic
 */
public class BreadthFirstTraversal implements ITraversalAlgorithm {

    final private Set<IVertex> traversed = new HashSet<>();

    @Override
    public void traverse(IGraph graph) {
        // The queue of the search.
        Queue<IVertex> toVisit = new LinkedList<>();
        toVisit.add(graph.getVertices().iterator().next());

        // List of visited vertices.
        traversed.clear();

        // Loop through all vertices.
        while (!toVisit.isEmpty()) {
            IVertex next = toVisit.poll();
            traversed.add(next);
            graph.getAdjacentVertices(next).forEach(neighbor -> {
                if (!traversed.contains(neighbor)) {
                    toVisit.add(neighbor);
                }
            });
        }
    }

    @Override
    public Set<IVertex> getTraversed() {
        return this.traversed;
    }

}
