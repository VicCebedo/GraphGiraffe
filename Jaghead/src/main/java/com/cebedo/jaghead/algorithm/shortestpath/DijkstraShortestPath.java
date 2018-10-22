/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.shortestpath;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import com.cebedo.jaghead.Vertex;
import com.cebedo.jaghead.Edge;
import com.cebedo.jaghead.Graph;

/**
 * @see <a href="https://en.wikipedia.org/wiki/Dijkstra's_algorithm">Wikipedia
 * (Dijkstra's Algorithm)</a>
 * @author Vic Cebedo
 * @param <T1> {@link Vertex} or any subclass.
 * @param <T2> {@link Graph} or any subclass.
 */
final class DijkstraShortestPath<T1 extends Vertex, T2 extends Graph<T1, ? extends Edge>>
        implements ShortestPathAlgorithm<T1, T2> {

    private final Map<T1, Boolean> done;
    private final Map<T1, Double> distanceFromSource;

    private DijkstraShortestPath() {
        this.done = new HashMap<>();
        this.distanceFromSource = new HashMap<>();
    }

    static ShortestPathAlgorithm newInstance() {
        return new DijkstraShortestPath();
    }

    /**
     * @inheritdoc
     */
    @Override
    public Map<T1, ? extends Number> findPath(T2 graph, String src) {
        // Initialize all distances as INFINITE,
        // and not yet done.
        graph.vertices().forEach(vtx -> {
            distanceFromSource.put(vtx, Double.MAX_VALUE);
            done.put(vtx, Boolean.FALSE);
        });

        // Distance of source vertex from itself is always 0.
        distanceFromSource.put(graph.vertex(src), 0.0);

        // Find shortest path for all vertices.
        graph.vertices().forEach(vtx -> {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. Minimum object is always equal to src in first
            // iteration.
            T1 min = this.getMinDistance();

            // Mark the minimum vertex as processed.
            this.done.put(min, Boolean.TRUE);

            // Update distance value of the adjacent vertices of the
            // minimum vertex. Get adjacents of minimum.
            graph.successors(min).forEach(child -> {

                // Distance of source to min.
                Double distanceSrcToMin = distanceFromSource.get(min);

                // Distance from min to its child.
                Double distanceMinToChild = graph.edge(min.getId(), child.getId()).getWeight().doubleValue();

                // Update distance of adjacent only if it is NOT yet done,
                // and total weight of path from src to child through min
                // is smaller than current value of distance of child.
                if (distanceSrcToMin != Double.MAX_VALUE && (distanceSrcToMin + distanceMinToChild) < distanceFromSource.get(child)) {
                    distanceFromSource.put(child, distanceFromSource.get(min) + distanceMinToChild);
                }
            });
        });
        return Collections.unmodifiableMap(distanceFromSource);
    }

    private T1 getMinDistance() {
        Double min = Double.MAX_VALUE;
        T1 minObj = null;

        for (T1 vertx : distanceFromSource.keySet()) {
            // Only compare vertices that are not yet done.
            if (done.get(vertx)) {
                continue;
            }

            // If current value is less than min,
            // set the min, and get object.
            if (distanceFromSource.get(vertx) <= min) {
                min = distanceFromSource.get(vertx);
                minObj = vertx;
            }
        }
        return minObj;
    }
}
