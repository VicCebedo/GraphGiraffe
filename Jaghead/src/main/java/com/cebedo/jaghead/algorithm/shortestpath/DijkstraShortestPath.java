/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.shortestpath;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import com.cebedo.jaghead.core.Vertex;
import com.cebedo.jaghead.core.Edge;
import com.cebedo.jaghead.core.Graph;

/**
 *
 * @author Vic
 * @param <T1>
 * @param <T2>
 */
public final class DijkstraShortestPath<T1 extends Vertex, T2 extends Graph<T1, ? extends Edge>>
        implements ShortestPathAlgorithm<T1, T2> {

    private final Map<T1, Boolean> done;
    private final Map<T1, Double> distanceMap;

    private DijkstraShortestPath() {
        this.done = new HashMap<>();
        this.distanceMap = new HashMap<>();
    }

    public static ShortestPathAlgorithm newInstance() {
        return new DijkstraShortestPath();
    }

    @Override
    public Map<T1, ? extends Number> findPath(T2 graph, T1 src) {
        // Initialize all distances as INFINITE,
        // and not yet done.
        graph.getVertices().forEach(vtx -> {
            distanceMap.put(vtx, Double.MAX_VALUE);
            done.put(vtx, Boolean.FALSE);
        });

        // Distance of source vertex from itself is always 0.
        distanceMap.put(src, 0.0);

        // Find shortest path for all vertices.
        graph.getVertices().forEach(vtx -> {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. Minimum object is always equal to src in first
            // iteration.
            T1 minimumObj = this.getMinDistance();

            // Mark the minimum vertex as processed.
            this.done.put(minimumObj, Boolean.TRUE);

            // Update distance value of the adjacent vertices of the
            // minimum vertex. Get adjacents of minimum.
            graph.getSuccessors(minimumObj).forEach(adjacentOfMin -> {

                // Distance from u to v.
                Double distanceFromMin = graph.getEdge(minimumObj, adjacentOfMin).getWeight().doubleValue();

                // Update distance of adjacent only if it is NOT yet done,
                // and total weight of path from src to adjacentOfMin through minimumObj
                // is smaller than current value of distance of adjacentOfMin.
                if (distanceMap.get(minimumObj) != Double.MAX_VALUE
                        && distanceMap.get(minimumObj) + distanceFromMin < distanceMap.get(adjacentOfMin)) {
                    distanceMap.put(adjacentOfMin, distanceMap.get(minimumObj) + distanceFromMin);
                }
            });
        });
        return Collections.unmodifiableMap(distanceMap);
    }

    /**
     * Get the vertex with the least distance.
     *
     * @return
     */
    private T1 getMinDistance() {
        Double min = Double.MAX_VALUE;
        T1 minObj = null;

        for (T1 vertx : distanceMap.keySet()) {
            // Only compare vertices that are not yet done.
            if (done.get(vertx)) {
                continue;
            }
            // If current value is less than min,
            // set the min, and get object.
            if (distanceMap.get(vertx) <= min) {
                min = distanceMap.get(vertx);
                minObj = vertx;
            }
        }
        return minObj;
    }
}
