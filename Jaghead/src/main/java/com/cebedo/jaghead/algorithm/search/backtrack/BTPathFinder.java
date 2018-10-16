/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.search.backtrack;

import com.cebedo.jaghead.util.GraphUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import com.cebedo.jaghead.Vertex;
import com.cebedo.jaghead.Edge;
import com.cebedo.jaghead.Graph;
import com.cebedo.jaghead.algorithm.search.PathFindingAlgorithm;

/**
 * TODO [Doc].
 *
 * @author Vic Cebedo
 * @param <T1>
 * @param <T2>
 * @param <T3>
 */
public final class BTPathFinder<T1 extends Vertex, T2 extends Edge<T1>, T3 extends Graph<T1, T2>>
        implements PathFindingAlgorithm<T3, T1> {

    private final List<List<T1>> allPaths;
    private final List<T1> pathTracker;
    private final Set<T2> visitedEdges;
    private final Set<String> visitedRoute;

    private BTPathFinder() {
        this.allPaths = new ArrayList<>();
        this.pathTracker = new LinkedList<>();
        this.visitedEdges = new HashSet<>();
        this.visitedRoute = new HashSet<>();
    }

    public static PathFindingAlgorithm newInstance() {
        return new BTPathFinder();
    }

    @Override
    public List<List<T1>> findPaths(T3 graph, String srcId, String tgtId) {
        if (!graph.connected()) {
            throw new IllegalArgumentException("Graph should be connected.");
        }
        T1 src = graph.vertex(srcId);
        T1 tgt = graph.vertex(tgtId);
        pathTracker.add(src);
        return backtrack(graph, src, tgt, null);
    }

    private List<List<T1>> backtrack(T3 graph, T1 parent, T1 destination, T1 ancestor) {
        // Explore all outgoing edge of current vertex.
        for (T2 edge : graph.incidentEdgesOutgoing(parent)) {

            // We are now visiting this edge.
            // Check if has already been visited so that we dont do cycle.
            String route = sourceToEdgeKey(ancestor, edge);
            if (this.isVisited(route)) {
                continue;
            }

            // If not yet visited,
            // keep track.
            T1 currentVertx = edge.getTarget();
            visitedEdges.add(edge);
            visitedRoute.add(route);
            pathTracker.add(currentVertx);

            // If this is destination, OR deadend
            // then backtrack to parent of current.
            if (this.isDestination(currentVertx, destination)
                    || this.isDeadend(graph.incidentEdgesOutgoing(currentVertx))) {
                allPaths.add(new LinkedList<>(pathTracker));
                pathTracker.remove(currentVertx);
                return this.backtrack(graph, parent, destination, ancestor);
            }

            // Not destination, and has children.
            return this.backtrack(graph, currentVertx, destination, parent);
        }

        // If we have visited already all edges,
        // then end operation. Else, backtrack to parent.
        if (GraphUtils.equals(visitedEdges, graph.edges())) {
            return Collections.unmodifiableList(allPaths);
        }
        pathTracker.remove(parent);
        return this.backtrack(
                graph,
                graph.predecessors(parent).iterator().next(),
                destination,
                ancestor);
    }

    private boolean isVisited(String e) {
        return visitedRoute.contains(e);
    }

    private String sourceToEdgeKey(T1 src, T2 edge) {
        return (src == null ? "NULL" : src.getId()) + "_" + edge.getId();
    }

    private boolean isDestination(T1 currentVertx, T1 destination) {
        return currentVertx.getId().equalsIgnoreCase(destination.getId());
    }

    private boolean isDeadend(Set<T2> incidentOutgoing) {
        return incidentOutgoing.isEmpty();
    }
}
