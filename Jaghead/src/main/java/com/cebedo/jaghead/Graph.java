/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 *
 * @author Vic
 * @param <T1>
 * @param <T2>
 */
public class Graph<T1 extends GenericVertex, T2 extends GenericEdge<T1, T1>>
        implements GenericGraph<T1, T2> {

    private Set<T1> vertices = new HashSet<>();
    private Set<T2> edges = new HashSet<>();
    private Map<VertexPair, T2> incidenceMap;

    public void initialize(Set<T1> vertices, Set<T2> edges) {
        this.vertices = vertices;
        this.edges = edges;
        this.incidenceMap = new HashMap<>();
        this.edges.forEach(edge -> {
            incidenceMap.put(
                    new VertexPair.Builder(
                            edge.getSource(),
                            edge.getTarget())
                            .build(),
                    edge);
        });
    }

    @Override
    public Set<T1> getVertices() {
        return this.vertices;
    }

    @Override
    public Set<T2> getEdges() {
        return this.edges;
    }

    @Override
    public Map<VertexPair, T2> getIncidenceMap() {
        return incidenceMap;
    }

    // TODO:
    // Cache heavy functions.
    public Set<T2> getIncidentEdgesAll(T1 vtx) {
        // Loop through each edge,
        // and check if given vertex is either source or target.
        Set<T2> returnSet = new HashSet<>();
        this.edges.forEach(edge -> {
            T1 source = edge.getSource();
            T1 target = edge.getTarget();

            // If vertex is source/target,
            // then add to set.
            if (vtx.getId().equals(source.getId())) {
                returnSet.add(edge);
            }
            if (vtx.getId().equals(target.getId())) {
                returnSet.add(edge);
            }
        });
        return returnSet;
    }

    // TODO:
    // Cache heavy functions.
    public Set<T2> getIncidentEdgesIncoming(T1 vtx) {
        Set<T2> returnSet = new HashSet<>();
        this.edges.forEach(edge -> {
            T1 target = edge.getTarget();

            // If vertex is source/target,
            // then add to set.
            if (vtx.getId().equals(target.getId())) {
                returnSet.add(edge);
            }
        });
        return returnSet;
    }

    // TODO:
    // Cache heavy functions.
    public Set<T2> getIncidentEdgesOutgoing(T1 vtx) {
        Set<T2> returnSet = new HashSet<>();
        this.edges.forEach(edge -> {
            T1 source = edge.getSource();

            // If vertex is source/target,
            // then add to set.
            if (vtx.getId().equals(source.getId())) {
                returnSet.add(edge);
            }
        });
        return returnSet;
    }

    public int getIncidentDegrees(T1 vtx) {
        return this.getIncidentEdgesAll(vtx).size();
    }

    public int getDegreesIncoming(T1 vtx) {
        return this.getPredecessors(vtx).size();
    }

    public int getDegreesOutgoing(T1 vtx) {
        return this.getSuccessors(vtx).size();
    }

    public T2 getEdge(T1 src, T1 target) {
        return this.incidenceMap.get(
                new VertexPair.Builder(
                        src, target)
                        .build());
    }

    public boolean hasEdgeConnecting(T1 src, T1 target) {
        return this.getEdge(src, target) != null;
    }

    public Number getEdgeWeight(T1 src, T1 target) {
        return Optional.of(this.getEdge(src, target)).get().getWeight();
    }

    public Set<T1> getAdjacentVertices(T1 vtx) {
        Set<T1> adjacentVertices = new HashSet<>();
        this.edges.forEach(edge -> {
            T1 edgeSource = edge.getSource();
            T1 edgeTarget = edge.getTarget();

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

    public Set<T1> getPredecessors(T1 vtx) {
        Set<T1> adjacentVertices = new HashSet<>();
        this.edges.forEach(edge -> {
            T1 edgeSource = edge.getSource();
            T1 edgeTarget = edge.getTarget();

            // If our vertex is the source,
            // then its neighbor is the target, and vice-versa.
            if (vtx.getId().equals(edgeTarget.getId())) {
                adjacentVertices.add(edgeSource);
            }
        });
        return adjacentVertices;
    }

    public Set<T1> getSuccessors(T1 vtx) {
        Set<T1> adjacentVertices = new HashSet<>();
        this.edges.forEach(edge -> {
            T1 edgeSource = edge.getSource();
            T1 edgeTarget = edge.getTarget();

            // If our vertex is the source,
            // then its neighbor is the target.
            if (vtx.getId().equals(edgeSource.getId())) {
                adjacentVertices.add(edgeTarget);
            }
        });
        return adjacentVertices;
    }

}
