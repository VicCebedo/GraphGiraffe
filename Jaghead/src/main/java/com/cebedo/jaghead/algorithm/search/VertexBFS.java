/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.search;

import com.cebedo.jaghead.GenericEdge;
import com.cebedo.jaghead.GenericVertex;
import com.cebedo.jaghead.Graph;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author Vic
 * @param <T1>
 * @param <T2>
 */
public class VertexBFS<T1 extends GenericVertex, T2 extends GenericEdge<T1, T1>>
        implements SearchAlgorithm<Graph<T1, T2>, T1, T1, VertexCondition<T1>> {

    @Override
    public Set<T1> search(Graph<T1, T2> graph, T1 src, VertexCondition<T1> condition) {

        // The queue of the search.
        Queue<T1> toVisit = new LinkedList<>();
        toVisit.add(src);

        // List of visited vertices.
        Set<T1> done = new HashSet<>();
        Set<T1> returnSet = new HashSet<>();

        // Loop through all vertices.
        while (!toVisit.isEmpty()) {
            T1 next = toVisit.poll();
            done.add(next);

            // Check conditions for this node.
            if (condition.check(next)) {
                returnSet.add(next);
            }

            // Add the neighbors to visit.
            graph.getAdjacentVertices(next).forEach(neighbor -> {
                if (!done.contains(neighbor)) {
                    toVisit.add(neighbor);
                }
            });
        }
        return returnSet;
    }
}
