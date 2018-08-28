/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.giraffe.domain;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author Vic
 */
final public class Graph implements IGraph {

    final private Set<IVertex> vertices = new HashSet<>();
    final private Set<IEdge> edges = new HashSet<>();

    public Graph(Set<IVertex> v, Set<IEdge> e) {
        this.vertices.addAll(v);
        this.edges.addAll(e);
    }

    @Override
    public Set<IEdge> getEdges() {
        return this.edges;
    }

    @Override
    public Set<IVertex> getVertices() {
        return this.vertices;
    }

    /**
     * A graph is connected when there is a path between every pair of vertices.
     *
     * @return
     */
    @Override
    public boolean isConnected() {
        // If visited is same as original set,
        // then graph is connected.
        return this.traverseBreadthFirst(this.vertices.iterator().next()).equals(this.vertices);
    }

    private Set<IVertex> traverseBreadthFirst(IVertex source) {
        // The queue of the search.
        Queue<IVertex> toVisit = new LinkedList<>();
        toVisit.add(source);

        // List of visited vertices.
        Set<IVertex> visited = new HashSet<>();

        // Loop through all vertices.
        while (!toVisit.isEmpty()) {
            IVertex next = toVisit.poll();
            visited.add(next);
            this.getAdjacentVertices(next).forEach(neighbor -> {
                if (!visited.contains(neighbor)) {
                    toVisit.add(neighbor);
                }
            });
        }
        return visited;
    }

    /**
     * Incident vertices are the vertices connected to the edge.
     *
     * @param edge
     * @return
     */
    public Set<IVertex> getIncidentVertices(IEdge edge) {
        Set<IVertex> incidentVertices = new HashSet<>();
        this.vertices.forEach(vertexX -> {
            if (vertexX.getEdges().contains(edge)) {
                incidentVertices.add(vertexX);
            }
        });
        return incidentVertices;
    }

    /**
     * Two vertices are adjacent if they share the same edge.
     *
     * @param vertexA
     * @return
     */
    private Set<IVertex> getAdjacentVertices(IVertex vertexA) {
        Set<? extends IEdge> edgesA = vertexA.getEdges();
        Set<IVertex> adjacentVertices = new HashSet<>();

        // Loop through each vertex.
        // And in each vertex, compare edges with vertex A's edges.
        this.vertices.forEach(vertexB -> {
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
