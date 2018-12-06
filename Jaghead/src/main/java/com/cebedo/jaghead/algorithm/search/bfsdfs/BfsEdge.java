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
import java.util.stream.Collectors;

/**
 * This class is an implementation of {@link SearchAlgorithm}. A new instance
 * can only be created through the factory method {@link #newInstance()}. This
 * is simply a breadth-first search algorithm implementation and can be ran
 * through {@link #search}.
 *
 * @author Vic Cebedo <cebedo.vii@gmail.com>
 * @param <T1> {@link Vertex} or any subclass.
 * @param <T2> {@link Edge} or any subclass.
 * @param <T3> {@link Graph} or any subclass.
 */
final class BfsEdge<T1 extends Vertex, T2 extends Edge, T3 extends Graph<T1, T2>>
        implements SearchAlgorithm<T3, T2, CheckerEdge<T2>> {

    private BfsEdge() {
    }

    static SearchAlgorithm newInstance() {
        return new BfsEdge();
    }

    /**
     * @inheritdoc
     */
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
            returnSet.addAll(
                    graph.incidentOutEdges(next)
                            .stream()
                            .filter(edge -> checker.check(edge))
                            .collect(Collectors.toSet()));

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
