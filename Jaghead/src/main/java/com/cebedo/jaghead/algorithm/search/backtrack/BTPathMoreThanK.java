/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.search.backtrack;

import java.util.LinkedHashSet;
import java.util.Set;
import com.cebedo.jaghead.Vertex;
import com.cebedo.jaghead.Edge;
import com.cebedo.jaghead.Graph;
import com.cebedo.jaghead.algorithm.search.PathDistanceAlgorithm;

/**
 *
 * @author Vic
 * @param <T1>
 * @param <T2>
 * @param <T3>
 */
public final class BTPathMoreThanK<T1 extends Vertex, T2 extends Edge<T1, T1>, T3 extends Graph<T1, T2>>
        implements PathDistanceAlgorithm<T1, T3> {

    private final Set<T1> visited;
    private final Set<T1> path;
    private Double distanceFromSource;

    private BTPathMoreThanK() {
        this.visited = new LinkedHashSet<>();
        this.path = new LinkedHashSet<>();
        this.distanceFromSource = 0.0;
    }

    public static PathDistanceAlgorithm newInstance() {
        return new BTPathMoreThanK();
    }

    private boolean isVisited(Set<T1> visited, T1 vtx) {
        return visited.contains(vtx);
    }

    /**
     * Get path where total distance is more than K.
     *
     * @param graph
     * @param src
     * @param k
     * @return
     */
    @Override
    public BTResult pathMoreThanK(T3 graph, T1 src, Number k) {
        visited.add(src);
        path.add(src);
        return backtrack(graph, src, k);
    }

    private BTResult backtrack(T3 graph, T1 origin, Number k) {
        // Explore all paths from current vertex.
        for (T2 edge : graph.getIncidentEdgesOutgoing(origin)) {

            // If this vertex is already visited,
            // then skip.
            T1 currentVertx = edge.getTarget();
            if (isVisited(visited, currentVertx)) {
                continue;
            }

            // Keep track of current path vertices.
            visited.add(currentVertx);
            path.add(currentVertx);

            // If distance becomes more than k, path is found,
            // update total distance and return.
            Double currentEdgeWeight = edge.getWeight().doubleValue();
            distanceFromSource += currentEdgeWeight;
            if (distanceFromSource > k.doubleValue()) {
                return BTResult.newInstance(path, visited, distanceFromSource, true);
            }

            // If has no successor or all edges of this vertex has been visited,
            // then backtrack to parent of current.
            if (this.isDeadend(graph.getSuccessors(currentVertx))) {
                distanceFromSource -= currentEdgeWeight;
                path.remove(currentVertx);
                return this.backtrack(graph, origin, k);
            }

            // Path doesn’t produce more than k distance.
            return this.backtrack(graph, currentVertx, k);

        }
        return BTResult.newInstance(
                path,
                visited,
                distanceFromSource,
                distanceFromSource > k.doubleValue());
    }

    private boolean isDeadend(Set<T1> successors) {
        return successors.isEmpty() || visited.containsAll(successors);
    }
}
