/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.search.bfsdfs;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import com.cebedo.jaghead.Vertex;
import com.cebedo.jaghead.Edge;
import com.cebedo.jaghead.Graph;

/**
 * TODO [Doc].
 *
 * @author Vic Cebedo
 * @param <T1>
 * @param <T2>
 * @param <T3>
 */
final class DFSVertex<T1 extends Vertex, T2 extends Edge, T3 extends Graph<T1, T2>>
        implements SearchAlgorithm<T3, T1, CheckerVertex<T1>> {

    private DFSVertex() {
    }

    static SearchAlgorithm newInstance() {
        return new DFSVertex();
    }

    @Override
    public Set<T1> search(T3 graph, String src, CheckerVertex<T1> checker) {

        // The queue of the search.
        Stack<T1> toVisit = new Stack();
        toVisit.add(graph.vertex(src));

        // List of visited vertices.
        Set<T1> done = new HashSet<>();
        Set<T1> returnSet = new HashSet<>();

        // Loop through all vertices.
        while (!toVisit.isEmpty()) {
            T1 next = toVisit.pop();
            done.add(next);

            // Check conditions for this node.
            if (checker.check(next)) {
                returnSet.add(next);
            }

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