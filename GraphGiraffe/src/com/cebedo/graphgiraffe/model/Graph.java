/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.graphgiraffe.model;

import com.cebedo.graphgiraffe.immutable.ImmutableEdge;
import com.cebedo.graphgiraffe.immutable.ImmutableVertex;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Vic
 */
final public class Graph {

    final private Set<ImmutableVertex> vertices = new HashSet<>();

    public Set<ImmutableVertex> getVertices() {
        return vertices;
    }

    public void addVertex(ImmutableVertex v) {
        this.vertices.add(v);
    }

    public void removeVertex(ImmutableVertex v) {
        this.vertices.remove(v);
    }

    /**
     * Get all edges from vertices. TODO Need to optimize.
     *
     * @return
     */
    public Set<ImmutableEdge> getEdges() {
        Set<ImmutableEdge> edges = new HashSet<>();
        Iterator<ImmutableVertex> itr = this.vertices.iterator();
        while (itr.hasNext()) {
            edges.addAll(itr.next().getEdges());
        }
        return edges;
    }

    /**
     * Two vertices are adjacent if they share the same edge.
     *
     * @param vertexA
     * @return
     */
    public Set<ImmutableVertex> getAdjacentVertices(ImmutableVertex vertexA) {
        Set<ImmutableEdge> edgesA = vertexA.getEdges();
        Set<ImmutableVertex> adjacentVertices = new HashSet<>();

        // Loop through each vertex.
        // And in each vertex, compare edges with vertex A's edges.
        Iterator<ImmutableVertex> itr = this.vertices.iterator();
        while (itr.hasNext()) {
            ImmutableVertex vertexB = itr.next();
            if (vertexB.equals(vertexA)) {
                continue;
            }
            Set<ImmutableEdge> edgesB = vertexB.getEdges();
            Set<ImmutableEdge> temp = new HashSet<>();
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
    public Set<ImmutableVertex> getIncidentVertices(ImmutableEdge edge) {
        Set<ImmutableVertex> incidentVertices = new HashSet<>();
        Iterator<ImmutableVertex> itr = this.vertices.iterator();
        while (itr.hasNext()) {
            ImmutableVertex vertexX = itr.next();
            if (vertexX.getEdges().contains(edge)) {
                incidentVertices.add(vertexX);
            }
        }
        return incidentVertices;
    }
}
