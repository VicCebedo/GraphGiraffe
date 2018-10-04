/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.backtrack;

import com.cebedo.jaghead.GenericEdge;
import com.cebedo.jaghead.GenericVertex;
import com.cebedo.jaghead.Graph;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Vic
 * @param <T1>
 * @param <T2>
 */
public class PathFinder<T1 extends GenericVertex, T2 extends GenericEdge<T1, T1>> {

    private List<List<T1>> paths = new ArrayList<>();
    private Set<T1> visited = new HashSet<>();
    private List<T1> path = new LinkedList<>();

    private boolean isVisited(T1 vtx) {
        return visited.contains(vtx);
    }

    public List findAllOutgoingPaths(Graph<T1, T2> graph, T1 src) {
        visited.add(src);
        path.add(src);
        return backtrack(graph, src);
    }

    private List backtrack(Graph<T1, T2> graph, T1 origin) {
        // Explore all paths from current vertex.
        for (T2 edge : graph.getIncidentEdgesOutgoing(origin)) {

            // If this vertex is already visited,
            // then skip, to avoid infinite cycles.
            T1 currentVertx = edge.getTarget();

            // Do not allow graph cycles.
            if (this.isVisited(currentVertx)) {
                continue;
            }

            // We are now visiting this vertex.
            // Keep track of current path.
            visited.add(currentVertx);
            path.add(currentVertx);

            // If has no successor or all edges of this vertex has been visited,
            // then backtrack to parent of current.
            if (this.isDeadend(graph.getSuccessors(currentVertx))) {
                paths.add(new LinkedList<>(path));
                path.remove(currentVertx);
                return this.backtrack(graph, origin);
            }

            // Path doesn’t produce more than k distance.
            return this.backtrack(graph, currentVertx);
        }
        return paths;
    }

    private boolean isDeadend(Set<T1> successors) {
        return successors.isEmpty() || visited.containsAll(successors);
    }

}
