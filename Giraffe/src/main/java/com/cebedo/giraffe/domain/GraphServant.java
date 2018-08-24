/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.giraffe.domain;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Vic
 */
final public class GraphServant {

    final private IGraph graph;

    public GraphServant(IGraph g) {
        this.graph = g;
    }

    /**
     * Two vertices are adjacent if they share the same edge.
     *
     * @param vertexA
     * @return
     */
    public Set<IVertex> getAdjacentVertices(IVertex vertexA) {
        Set<? extends IEdge> edgesA = vertexA.getEdges();
        Set<IVertex> adjacentVertices = new HashSet<>();

        // Loop through each vertex.
        // And in each vertex, compare edges with vertex A's edges.
        Iterator<? extends IVertex> itr = this.graph.getVertices().iterator();
        while (itr.hasNext()) {
            IVertex vertexB = itr.next();
            if (vertexB.equals(vertexA)) {
                continue;
            }
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
        }
        return adjacentVertices;
    }

    /**
     * Incident vertices are the vertices connected to the edge.
     *
     * @param edge
     * @return
     */
    public Set<IVertex> getIncidentVertices(IEdge edge) {
        Set<IVertex> incidentVertices = new HashSet<>();
        Iterator<? extends IVertex> itr = this.graph.getVertices().iterator();
        while (itr.hasNext()) {
            IVertex vertexX = itr.next();
            if (vertexX.getEdges().contains(edge)) {
                incidentVertices.add(vertexX);
            }
        }
        return incidentVertices;
    }

}
