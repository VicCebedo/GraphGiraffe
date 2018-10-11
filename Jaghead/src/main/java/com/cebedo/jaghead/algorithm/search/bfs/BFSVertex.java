/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.search.bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import com.cebedo.jaghead.Vertex;
import com.cebedo.jaghead.Edge;
import com.cebedo.jaghead.Graph;
import com.cebedo.jaghead.algorithm.search.SearchAlgorithm;
import com.cebedo.jaghead.algorithm.search.checker.VertexChecker;

/**
 * TODO [Run in sample, test, then doc].
 *
 * @author Vic
 * @param <T1>
 * @param <T2>
 * @param <T3>
 */
public final class BFSVertex<T1 extends Vertex, T2 extends Edge, T3 extends Graph<T1, T2>>
        implements SearchAlgorithm<T3, T1, T1, VertexChecker<T1>> {

    private BFSVertex() {
    }

    public static SearchAlgorithm newInstance() {
        return new BFSVertex();
    }

    @Override
    public Set<T1> search(T3 graph, T1 src, VertexChecker<T1> checker) {

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
            if (checker.check(next)) {
                returnSet.add(next);
            }

            // Add the neighbors to visit.
            graph.getSuccessors(next).forEach(neighbor -> {
                if (!done.contains(neighbor)) {
                    toVisit.add(neighbor);
                }
            });
        }
        return returnSet;
    }
}
