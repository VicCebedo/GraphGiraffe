/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.search.connectivity;

import com.cebedo.jaghead.Edge;
import com.cebedo.jaghead.Graph;
import com.cebedo.jaghead.Vertex;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

/**
 * This class is an implementation of {@link ConnectivityAlgorithm}. A new
 * instance can only be created through the factory method
 * {@link #newInstance()}. The function {@link #isConnected} checks if a certain
 * {@link Graph} is connected or not.
 *
 * @author Vic Cebedo <cebedo.vii@gmail.com>
 * @param <T1> {@link Vertex} or any subclass.
 * @param <T2> {@link Edge} or any subclass.
 * @param <T3> {@link Graph} or any subclass
 */
final class BFSConnectivity<T1 extends Vertex, T2 extends Edge, T3 extends Graph<T1, T2>>
        implements ConnectivityAlgorithm<T3> {

    private BFSConnectivity() {
    }

    static ConnectivityAlgorithm newInstance() {
        return new BFSConnectivity();
    }

    /**
     * @inheritdoc
     */
    @Override
    public boolean isConnected(T3 graph) {
        Objects.requireNonNull(graph);

        // The queue of the search.
        // List of visited vertices.
        Set<T1> done = new HashSet<>();
        Queue<T1> toVisit = new LinkedList<>();
        toVisit.add(graph.vertices().iterator().next());

        // Loop through all vertices.
        while (!toVisit.isEmpty()) {
            T1 next = toVisit.poll();
            done.add(next);

            // Add the neighbors to visit.
            graph.successors(next).forEach(neighbor -> {
                if (!done.contains(neighbor)) {
                    toVisit.add(neighbor);
                }
            });
        }
        return done.size() == graph.vertices().size();
    }
}
