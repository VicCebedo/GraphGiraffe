/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.backtrack;

import com.cebedo.jaghead.GenericEdge;
import com.cebedo.jaghead.GenericVertex;
import com.cebedo.jaghead.Graph;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * @author Vic
 * @param <T1>
 * @param <T2>
 */
public class PathMoreThanK<T1 extends GenericVertex, T2 extends GenericEdge<T1, T1>> {

    private final Set<T1> visited = new LinkedHashSet<>();
    private final Set<T1> path = new LinkedHashSet<>();
    private Double distanceFromSource = 0.0;

    private boolean isVisited(Set<T1> visited, T1 vtx) {
        return visited.contains(vtx);
    }

    /**
     * Is there a path in the graph where total distance is more than K?
     *
     * @param graph
     * @param src
     * @param k
     * @return
     */
    public BacktrackResult backtrack(Graph<T1, T2> graph, T1 src, Number k) {
        visited.add(src);
        path.add(src);
        return doBacktrack(graph, src, k);
    }

    private BacktrackResult doBacktrack(Graph<T1, T2> graph, T1 origin, Number k) {
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
                return BacktrackResult.newInstance(path, visited, distanceFromSource, true);
            }

            // If has no successor or all edges of this vertex has been visited,
            // then backtrack to parent of current.
            if (this.isDeadend(graph.getSuccessors(currentVertx))) {
                distanceFromSource -= currentEdgeWeight;
                path.remove(currentVertx);
                return this.doBacktrack(graph, origin, k);
            }

            // Path doesn’t produce more than k distance.
            return this.doBacktrack(graph, currentVertx, k);

        }
        return BacktrackResult.newInstance(
                path,
                visited,
                distanceFromSource,
                distanceFromSource > k.doubleValue());
    }

    private boolean isDeadend(Set<T1> successors) {
        return successors.isEmpty() || visited.containsAll(successors);
    }

}
