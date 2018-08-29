/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm;

import com.cebedo.jaghead.domain.IEdge;
import com.cebedo.jaghead.domain.IGraph;
import com.cebedo.jaghead.domain.IVertex;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Vic
 */
public abstract class AbstractOperations {

    /**
     * Incident vertices are the vertices connected to the edge.
     *
     * @param graph
     * @param edge
     * @return
     */
    public Set<IVertex> getIncidentVertices(IGraph graph, IEdge edge) {
        Set<IVertex> incidentVertices = new HashSet<>();
        graph.getVertices().forEach(vertexX -> {
            if (vertexX.getEdges().contains(edge)) {
                incidentVertices.add(vertexX);
            }
        });
        return incidentVertices;
    }

    /**
     * Two vertices are adjacent if they share the same edge.
     *
     * @param graph
     * @param vertexA
     * @return
     */
    public Set<IVertex> getAdjacentVertices(IGraph graph, IVertex vertexA) {
        Set<? extends IEdge> edgesA = vertexA.getEdges();
        Set<IVertex> adjacentVertices = new HashSet<>();

        // Loop through each vertex.
        // And in each vertex, compare edges with vertex A's edges.
        graph.getVertices().forEach(vertexB -> {
            Set<? extends IEdge> edgesB = vertexB.getEdges();
            Set<IEdge> temp = new HashSet<>();
            temp.addAll(edgesA);
            temp.addAll(edgesB);

            // If the total size of temp != aSize + bSize,
            // then edges A and B coincided on a similar edge.
            // Thus, vertex A and B are adjacent.
            if (temp.size() != (edgesA.size() + edgesB.size())) {
                adjacentVertices.add(vertexB);
            }
        });
        return adjacentVertices;
    }

}
