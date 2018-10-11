/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.search.dfs;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import com.cebedo.jaghead.Vertex;
import com.cebedo.jaghead.Edge;
import com.cebedo.jaghead.Graph;
import com.cebedo.jaghead.algorithm.search.SearchAlgorithm;
import com.cebedo.jaghead.algorithm.search.checker.EdgeChecker;

/**
 * TODO [Doc].
 *
 * @author Vic
 * @param <T1>
 * @param <T2>
 * @param <T3>
 */
public final class DFSEdge<T1 extends Vertex, T2 extends Edge, T3 extends Graph<T1, T2>>
        implements SearchAlgorithm<T3, T2, EdgeChecker<T2>> {

    private DFSEdge() {
    }

    public static SearchAlgorithm newInstance() {
        return new DFSEdge();
    }

    @Override
    public Set search(T3 graph, String src, EdgeChecker<T2> checker) {

        // The queue of the search.
        Stack<T1> toVisit = new Stack();
        toVisit.add(graph.getVertex(src));

        // List of visited vertices.
        Set<T1> done = new HashSet<>();
        Set<T2> returnSet = new HashSet<>();

        // Loop through all vertices.
        while (!toVisit.isEmpty()) {
            T1 next = toVisit.pop();
            done.add(next);

            // Check conditions for this node.
            graph.getIncidentEdgesOutgoing(next).forEach(edge -> {
                if (checker.check(edge)) {
                    returnSet.add(edge);
                }
            });

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
