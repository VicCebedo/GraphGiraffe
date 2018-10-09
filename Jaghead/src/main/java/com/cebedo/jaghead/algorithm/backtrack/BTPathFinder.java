/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.backtrack;

import com.cebedo.jaghead.util.GraphUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
public final class BTPathFinder<T1 extends Vertex, T2 extends Edge<T1, T1>, T3 extends Graph<T1, T2>>
        implements PathFinder<T3> {

    private final List<List<T1>> paths;
    private final Set<T2> visited;
    private final Set<SourceToEdge> visitedPairSet;
    private final List<T1> path;

    private BTPathFinder() {
        this.paths = new ArrayList<>();
        this.visited = new HashSet<>();
        this.visitedPairSet = new HashSet<>();
        this.path = new LinkedList<>();
    }

    public static PathFinder newInstance() {
        return new BTPathFinder();
    }

    @Override
    public List findPath(T3 graph, String srcId, String tgtId) {
        T1 src = getVertexById(graph, srcId);
        T1 tgt = getVertexById(graph, tgtId);
        path.add(src);
        return backtrack(graph, src, tgt, null);
    }

    private boolean isVisited(SourceToEdge e) {
        return visitedPairSet.contains(e);
    }

    private List backtrack(T3 graph, T1 parent, T1 destination, T1 ancestor) {
        // Explore all outgoing edge of current vertex.
        for (T2 edge : graph.getIncidentEdgesOutgoing(parent)) {

            // We are now visiting this edge.
            // Check if has already been visited so that we dont do cycle.
            SourceToEdge pair = SourceToEdge.newInstance(ancestor.getId(), edge.getId());
            if (this.isVisited(pair)) {
                continue;
            }

            // If not yet visited,
            // keep track.
            T1 currentVertx = edge.getTarget();
            visited.add(edge);
            visitedPairSet.add(pair);
            path.add(currentVertx);

            // If this is destination, OR deadend
            // then backtrack to parent of current.
            if (this.isDestination(currentVertx, destination)
                    || this.isDeadend(graph.getIncidentEdgesOutgoing(currentVertx))) {
                paths.add(new LinkedList<>(path));
                path.remove(currentVertx);
                return this.backtrack(graph, parent, destination, ancestor);
            }
            return this.backtrack(graph, currentVertx, destination, parent);
        }

        // If we have visited already all edges,
        // then end operation. Else, backtrack to parent.
        if (GraphUtils.equals(visited, graph.getEdges())) {

            // TODO What if some edges are unreachable? Infinite loop.
            return Collections.unmodifiableList(paths);
        }
        path.remove(parent);
        return this.backtrack(
                graph,
                graph.getPredecessors(parent).iterator().next(),
                destination,
                ancestor);
    }

    private boolean isDestination(T1 currentVertx, T1 destination) {
        return currentVertx.getId().equalsIgnoreCase(destination.getId());
    }

    private boolean isDeadend(Set<T2> incidentOutgoing) {
        return incidentOutgoing.isEmpty();
    }

    private T1 getVertexById(T3 graph, String id) {
        T1 returnObj = null;
        for (Object vtx : graph.getVertices()) {
            T1 vtxObj = (T1) vtx;
            if (vtxObj.getId().equalsIgnoreCase(id)) {
                returnObj = vtxObj;
                break;
            }
        }
        return Optional.of(returnObj).get();
    }

    private static final class SourceToEdge {

        private final String id;

        private SourceToEdge(String srcId, String edgeId) {
            this.id = (srcId == null ? "NULL" : srcId) + "_" + edgeId;
        }

        public static SourceToEdge newInstance(String srcId, String edgeId) {
            return new SourceToEdge(srcId, edgeId);
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 97 * hash + Objects.hashCode(this.id);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final SourceToEdge other = (SourceToEdge) obj;
            if (!Objects.equals(this.id, other.id)) {
                return false;
            }
            return true;
        }
    }
}
