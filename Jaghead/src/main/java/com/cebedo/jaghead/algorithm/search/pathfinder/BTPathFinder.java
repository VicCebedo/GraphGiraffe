/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.search.pathfinder;

import com.cebedo.jaghead.util.GraphUtils;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import com.cebedo.jaghead.Vertex;
import com.cebedo.jaghead.Edge;
import com.cebedo.jaghead.Graph;
import java.util.Objects;

/**
 * TODO [Doc].
 *
 * @author Vic Cebedo <cebedo.vii@gmail.com>
 * @param <T1>
 * @param <T2>
 * @param <T3>
 */
final class BTPathFinder<T1 extends Vertex, T2 extends Edge<T1>, T3 extends Graph<T1, T2>>
        implements PathFindingAlgorithm<T3, T1> {

    private final Set<List<T1>> successPaths;
    private final List<T1> pathTracker;
    private final Set<T2> visitedEdges;
    private final Set<String> visitedRoute;

    private BTPathFinder() {
        this.successPaths = new HashSet<>();
        this.pathTracker = new LinkedList<>();
        this.visitedEdges = new HashSet<>();
        this.visitedRoute = new HashSet<>();
    }

    static PathFindingAlgorithm newInstance() {
        return new BTPathFinder();
    }

    @Override
    public Set<List<T1>> findPaths(T3 graph, String srcId, String tgtId) {
        Objects.requireNonNull(graph);
        Objects.requireNonNull(srcId);
        Objects.requireNonNull(tgtId);
        if (!graph.connected()) {
            throw new IllegalArgumentException("Graph should be connected.");
        }
        T1 src = graph.vertex(srcId);
        T1 tgt = graph.vertex(tgtId);
        pathTracker.add(src);
        return backtrack(graph, src, tgt, null);
    }

    private Set<List<T1>> backtrack(T3 graph, T1 parent, T1 destination, T1 ancestor) {
        // Explore all outgoing edge of current vertex.
        for (T2 edge : graph.incidentOutEdges(parent)) {

            // We are now visiting this edge.
            // Check if has already been visited so that we dont do cycle.
            String route = sourceToEdgeKey(ancestor, edge);
            if (this.isVisited(route)) {
                continue;
            }

            // If not yet visited,
            // keep track.
            T1 currentVertx = edge.target();
            visitedEdges.add(edge);
            visitedRoute.add(route);
            pathTracker.add(currentVertx);

            // If this is destination, OR deadend
            // then backtrack to parent of current.
            if (this.isDestination(currentVertx, destination)) {
                successPaths.add(new LinkedList<>(pathTracker));
                pathTracker.remove(currentVertx);
                return this.backtrack(graph, parent, destination, ancestor);
            } else if (this.isDeadend(graph.incidentOutEdges(currentVertx))) {
                pathTracker.remove(currentVertx);
                return this.backtrack(graph, parent, destination, ancestor);
            }

            // Not destination, and has children.
            return this.backtrack(graph, currentVertx, destination, parent);
        }

        // If we have visited already all edges,
        // then end operation. Else, backtrack to parent.
        if (GraphUtils.equals(visitedEdges, graph.edges())) {
            return Collections.unmodifiableSet(successPaths);
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
        return (src == null ? "NULL" : src.id()) + "_" + edge.id();
    }

    private boolean isDestination(T1 currentVertx, T1 destination) {
        return currentVertx.id().equalsIgnoreCase(destination.id());
    }

    private boolean isDeadend(Set<T2> incidentOutgoing) {
        return incidentOutgoing.isEmpty();
    }
}
