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
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * This class is an implementation of {@link SearchAlgorithm}. A new instance
 * can only be created through the factory method {@link #newInstance()}. This
 * is simply a depth-first search algorithm implementation and can be ran
 * through {@link #search}.
 *
 * @author Vic Cebedo <cebedo.vii@gmail.com>
 * @param <T1> {@link Vertex} or any subclass.
 * @param <T2> {@link Edge} or any subclass.
 * @param <T3> {@link Graph} or any subclass
 */
final class DfsVertex<T1 extends Vertex, T2 extends Edge, T3 extends Graph<T1, T2>>
        implements SearchAlgorithm<T3, T1, CheckerVertex<T1>> {

    private DfsVertex() {
    }

    static SearchAlgorithm newInstance() {
        return new DfsVertex();
    }

    /**
     * @inheritdoc
     */
    @Override
    public Set<T1> search(T3 graph, String src, CheckerVertex<T1> checker) {
        Objects.requireNonNull(graph);
        Objects.requireNonNull(src);
        Objects.requireNonNull(checker);
        if (!graph.connected()) {
            throw new IllegalArgumentException("Graph should be connected.");
        }

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
            toVisit.addAll(
                    graph.successors(next)
                            .stream()
                            .filter(neighbor -> !done.contains(neighbor))
                            .collect(Collectors.toList()));
        }
        return returnSet;
    }
}
