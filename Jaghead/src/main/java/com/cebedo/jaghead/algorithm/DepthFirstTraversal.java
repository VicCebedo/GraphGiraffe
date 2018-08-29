/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm;

import com.cebedo.jaghead.domain.IGraph;
import com.cebedo.jaghead.domain.IVertex;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 *
 * @author Vic
 */
public class DepthFirstTraversal extends AbstractOperations implements ITraversalAlgorithm {

    @Override
    public Set<IVertex> traverse(IGraph graph) {
        // Stack to visit.
        Stack<IVertex> toVisit = new Stack();
        toVisit.push(graph.getVertices().iterator().next());

        // Already visited.
        Set<IVertex> traversed = new HashSet<>();

        // Loop through all to-visits.
        while (!toVisit.isEmpty()) {
            IVertex next = toVisit.pop();
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
