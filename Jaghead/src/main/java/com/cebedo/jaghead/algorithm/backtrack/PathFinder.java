/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.backtrack;

import com.cebedo.jaghead.GenericEdge;
import com.cebedo.jaghead.GenericVertex;
import com.cebedo.jaghead.Graph;
import com.cebedo.jaghead.util.GraphUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 *
 * @author Vic
 * @param <T1>
 * @param <T2>
 */
public class PathFinder<T1 extends GenericVertex, T2 extends GenericEdge<T1, T1>> {

    private List<List<T1>> paths = new ArrayList<>();
    private Set<T2> visited = new HashSet<>();
    private List<T1> path = new LinkedList<>();

    private boolean isVisited(T2 e) {
        return visited.contains(e);
    }

    public List findPath(Graph<T1, T2> graph, String srcId, String tgtId) {
        T1 src = getVertexById(graph, srcId);
        T1 tgt = getVertexById(graph, tgtId);
        path.add(src);
        return backtrack(graph, src, tgt);
    }

    private List backtrack(Graph<T1, T2> graph, T1 parent, T1 destination) {
        // Explore all outgoing edge of current vertex.
        for (T2 edge : graph.getIncidentEdgesOutgoing(parent)) {

            // We are now visiting this edge.
            // Check if has already been visited so that we dont do cycle.
            if (this.isVisited(edge)) {
                continue;
            }

            // If not yet visited,
            // keep track.
            T1 currentVertx = edge.getTarget();
            visited.add(edge);
            path.add(currentVertx);

            // If this is destination, OR
            // if has no successor or all edges of this vertex has been visited,
            // then backtrack to parent of current.
            if (currentVertx.getId().equalsIgnoreCase(destination.getId())
                    || this.isDeadend(graph.getIncidentEdgesOutgoing(currentVertx))) {
                paths.add(new LinkedList<>(path));
                path.remove(currentVertx);
                return this.backtrack(graph, parent, destination);
            }
            return this.backtrack(graph, currentVertx, destination);
        }

        // If we have visited already all edges,
        // then end operation. Else, backtrack to parent.
        if (GraphUtils.equals(visited, graph.getEdges())) {
            return paths;
        }
        path.remove(parent);
        return this.backtrack(
                graph,
                graph.getPredecessors(parent).iterator().next(),
                destination);
    }

    private boolean isDeadend(Set<T2> incidentOutgoing) {
        return incidentOutgoing.isEmpty() || visited.containsAll(incidentOutgoing);
    }

    private T1 getVertexById(Graph<T1, T2> graph, String id) {
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
}
