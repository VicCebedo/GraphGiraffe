/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.search.bfs;

import com.cebedo.jaghead.Edge;
import com.cebedo.jaghead.Graph;
import com.cebedo.jaghead.Vertex;
import com.cebedo.jaghead.algorithm.search.ConnectivityAlgorithm;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * TODO [Doc].
 *
 * @author Vic Cebedo
 * @param <T1>
 * @param <T2>
 * @param <T3>
 */
public final class BFSConnectivity<T1 extends Vertex, T2 extends Edge, T3 extends Graph<T1, T2>>
        implements ConnectivityAlgorithm<T3> {

    private BFSConnectivity() {
    }

    public static ConnectivityAlgorithm newInstance() {
        return new BFSConnectivity();
    }

    @Override
    public boolean isConnected(T3 graph) {

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
