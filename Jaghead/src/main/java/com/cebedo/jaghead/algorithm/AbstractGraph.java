/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm;

import com.cebedo.jaghead.Edge;
import java.util.HashSet;
import java.util.Set;
import com.cebedo.jaghead.Graph;
import com.cebedo.jaghead.Vertex;
import java.util.Optional;

/**
 *
 * @author Vic
 */
public abstract class AbstractGraph {

    public Edge getIncidentEdge(Graph graph, Vertex source, Vertex target) {
        return Optional.of(graph.getIncidenceMap().get(source).get(target)).get();
    }

    /**
     * Two vertices are adjacent if they share the same edge.
     *
     * @param graph
     * @param vtx
     * @return
     */
    public Set<Vertex> getAdjacentVertices(Graph graph, Vertex vtx) {
        Set<Vertex> adjacentVertices = new HashSet<>();
        graph.getEdges().forEach(edge -> {
            Vertex edgeSource = edge.getSource();
            Vertex edgeTarget = edge.getTarget();

            // If our vertex is the source,
            // then its neighbor is the target, and vice-versa.
            if (vtx.getId().equals(edgeSource.getId())) {
                adjacentVertices.add(edgeTarget);
            }
            if (vtx.getId().equals(edgeTarget.getId())) {
                adjacentVertices.add(edgeSource);
            }
        });
        return adjacentVertices;
    }

}
