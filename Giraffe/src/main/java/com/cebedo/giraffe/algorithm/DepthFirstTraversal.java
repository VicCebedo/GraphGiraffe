/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.giraffe.algorithm;

import com.cebedo.giraffe.domain.IGraph;
import com.cebedo.giraffe.domain.IVertex;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 *
 * @author Vic
 */
public class DepthFirstTraversal implements ITraversalAlgorithm {

    final private Set<IVertex> traversed = new HashSet<>();

    @Override
    public void traverse(IGraph graph) {
        // Stack to visit.
        Stack<IVertex> toVisit = new Stack();
        toVisit.push(graph.getVertices().iterator().next());

        // Already visited.
        this.traversed.clear();

        // Loop through all to-visits.
        while (!toVisit.isEmpty()) {
            IVertex next = toVisit.pop();
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
