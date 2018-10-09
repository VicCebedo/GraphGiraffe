/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.backtrack;

import java.util.LinkedHashSet;
import java.util.Set;
import com.cebedo.jaghead.core.Vertex;
import com.cebedo.jaghead.core.Edge;
import com.cebedo.jaghead.core.Graph;

/**
 *
 * @author Vic
 * @param <T1>
 * @param <T2>
 * @param <T3>
 */
public final class BTPathMoreThanK<T1 extends Vertex, T2 extends Edge<T1, T1>, T3 extends Graph<T1, T2>>
        implements PathMoreThanK<T1, T3> {

    private final Set<T1> visited;
    private final Set<T1> path;
    private Double distanceFromSource;

    private BTPathMoreThanK() {
        this.visited = new LinkedHashSet<>();
        this.path = new LinkedHashSet<>();
        this.distanceFromSource = 0.0;
    }

    public static PathMoreThanK newInstance() {
        return new BTPathMoreThanK();
    }

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
    @Override
    public BacktrackResult backtrack(T3 graph, T1 src, Number k) {
        visited.add(src);
        path.add(src);
        return doBacktrack(graph, src, k);
    }

    private BacktrackResult doBacktrack(T3 graph, T1 origin, Number k) {
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

    public static final class BacktrackResult<T1 extends Vertex> {

        private final Set<T1> path;
        private final Set<T1> sequence;
        private final Number distance;
        private final boolean pathExists;

        private BacktrackResult(Set<T1> p, Set<T1> s, Number d, boolean h) {
            this.path = p;
            this.sequence = s;
            this.distance = d;
            this.pathExists = h;
        }

        private static BacktrackResult newInstance(
                Set<? extends Vertex> p,
                Set<? extends Vertex> s,
                Number d,
                boolean h) {
            return new BacktrackResult(p, s, d, h);
        }

        Set<T1> getSequence() {
            return sequence;
        }

        boolean hasPath() {
            return pathExists;
        }

        Set<T1> getPath() {
            return path;
        }

        Number getDistance() {
            return distance;
        }
    }
}
