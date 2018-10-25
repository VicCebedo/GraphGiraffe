/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.search.bfsdfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import com.cebedo.jaghead.Vertex;
import com.cebedo.jaghead.Edge;
import com.cebedo.jaghead.Graph;
import java.util.Objects;

/**
 * TODO [Doc].
 *
 * @author Vic Cebedo <cebedo.vii@gmail.com>
 * @param <T1>
 * @param <T2>
 * @param <T3>
 */
final class BFSEdge<T1 extends Vertex, T2 extends Edge, T3 extends Graph<T1, T2>>
        implements SearchAlgorithm<T3, T2, CheckerEdge<T2>> {

    private BFSEdge() {
    }

    static SearchAlgorithm newInstance() {
        return new BFSEdge();
    }

    @Override
    public Set<T2> search(T3 graph, String src, CheckerEdge<T2> checker) {
        Objects.requireNonNull(graph);
        Objects.requireNonNull(src);
        Objects.requireNonNull(checker);
        if (!graph.connected()) {
            throw new IllegalArgumentException("Graph should be connected.");
        }

        // The queue of the search.
        Queue<T1> toVisit = new LinkedList<>();
        toVisit.add(graph.vertex(src));

        // List of visited vertices.
        Set<T1> done = new HashSet<>();
        Set<T2> returnSet = new HashSet<>();

        // Loop through all vertices.
        while (!toVisit.isEmpty()) {
            T1 next = toVisit.poll();
            done.add(next);

            // Check conditions for this node.
            graph.incidentEdgesOutgoing(next).forEach(edge -> {
                if (checker.check(edge)) {
                    returnSet.add(edge);
                }
            });

            // Add the neighbors to visit.
            graph.successors(next).forEach(neighbor -> {
                if (!done.contains(neighbor)) {
                    toVisit.add(neighbor);
                }
            });
        }
        return returnSet;
    }
}
