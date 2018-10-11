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
import java.util.HashMap;
import java.util.Map;

/**
 * TODO [Doc].
 *
 * @author Vic
 * @param <T1>
 * @param <T2>
 * @param <T3>
 */
public final class BTPathMoreThanK<T1 extends Vertex, T2 extends Edge<T1>, T3 extends Graph<T1, T2>>
        implements PathDistanceAlgorithm<T3> {

    private static final String KEY_PATH = "path";
    private static final String KEY_SEQUENCE = "sequence";
    private static final String KEY_DISTANCE = "distance";
    private static final String KEY_PATH_EXISTS = "pathExists";

    private final Set<T1> visitedVertices;
    private final Set<T1> pathTracker;
    private Double distanceFromSource;

    private BTPathMoreThanK() {
        this.visitedVertices = new LinkedHashSet<>();
        this.pathTracker = new LinkedHashSet<>();
        this.distanceFromSource = 0.0;
    }

    public static PathDistanceAlgorithm newInstance() {
        return new BTPathMoreThanK();
    }

    /**
     * Get path where total distance is more than K.
     *
     * @param <N>
     * @param graph
     * @param srcId
     * @param k
     * @return
     */
    @Override
    public <N extends Number> Map<String, ?> findPath(T3 graph, String srcId, N k) {
        T1 src = graph.getVertex(srcId);
        visitedVertices.add(src);
        pathTracker.add(src);
        return backtrack(graph, src, k);
    }

    private Map<String, ?> backtrack(T3 graph, T1 parent, Number k) {
        // Explore all paths from current vertex.
        for (T2 edge : graph.getIncidentEdgesOutgoing(parent)) {

            // If this vertex is already visited,
            // then skip.
            T1 currentVertx = edge.getTarget();
//            if (this.isVisited(currentVertx)) {
//                continue;
//            }

            // Keep track of current path vertices.
            visitedVertices.add(currentVertx);
            pathTracker.add(currentVertx);

            // If distance becomes more than k, path is found,
            // update total distance and return.
            Double currentEdgeWeight = edge.getWeight().doubleValue();
            distanceFromSource += currentEdgeWeight;
            if (distanceFromSource > k.doubleValue()) {
                return this.resultMap(pathTracker, visitedVertices, distanceFromSource, true);
            }

            // If has no successor or all edges of this vertex has been visited,
            // then backtrack to parent of current.
            if (this.isDeadend(graph.getSuccessors(currentVertx))) {
                distanceFromSource -= currentEdgeWeight;
                pathTracker.remove(currentVertx);
                return this.backtrack(graph, parent, k);
            }

            // Path doesn’t produce more than k distance.
            return this.backtrack(graph, currentVertx, k);
        }
        return this.resultMap(
                pathTracker,
                visitedVertices,
                distanceFromSource,
                distanceFromSource > k.doubleValue());
    }

    private Map<String, Object> resultMap(Set<T1> pat, Set<T1> seq, Number dist, boolean exists) {
        Map<String, Object> result = new HashMap<>();
        result.put(KEY_PATH, pat);
        result.put(KEY_SEQUENCE, seq);
        result.put(KEY_DISTANCE, dist);
        result.put(KEY_PATH_EXISTS, exists);
        return result;
    }

    private boolean isDeadend(Set<T1> successors) {
        return successors.isEmpty() || visitedVertices.containsAll(successors);
    }

    private boolean isVisited(T1 vtx) {
        return visitedVertices.contains(vtx);
    }
}
