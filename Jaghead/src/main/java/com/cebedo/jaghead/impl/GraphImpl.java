/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.impl;

import com.cebedo.jaghead.Edge;
import com.cebedo.jaghead.Graph;
import com.cebedo.jaghead.Vertex;
import com.cebedo.jaghead.algorithm.search.connectivity.JagheadConnectivity;
import com.cebedo.jaghead.algorithm.topologicalsort.JagheadTopologicalSorting;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * TODO [Doc].
 *
 * @author Vic Cebedo <cebedo.vii@gmail.com>
 * @param <T1> {@link Vertex} or any subclass.
 * @param <T2> {@link Edge} or any subclass.
 */
public final class GraphImpl<T1 extends Vertex, T2 extends Edge<T1>>
        implements Graph<T1, T2> {

    private final Set<T1> vertices;
    private final Set<T2> edges;
    private final Map<VertexPair, T2> incidenceMap;

    // TODO [Optimize] Cache heavy functions.
    // Result cache.
    private Boolean cyclic;
    private Boolean connected;

    GraphImpl(Set<T1> v, Set<T2> e) {
        this.vertices = v;
        this.edges = e;
        this.incidenceMap = this.edges
                .stream()
                .collect(Collectors.toMap(edge
                        -> VertexPair.of(edge.source(), edge.target()), Function.identity()));
    }

    /**
     * @inheritdoc
     */
    @Override
    public boolean connected() {
        if (this.connected == null) {
            this.connected = JagheadConnectivity.BREADTH_FIRST.connected(this);
        }

        // Defensive copy of mutable field.
        return new Boolean(this.connected);
    }

    /**
     * @inheritdoc
     */
    @Override
    public boolean cyclic() {
        if (this.cyclic == null) {
            try {
                // Try to sort topologically.
                // If it fails, then it has cycles.
                JagheadTopologicalSorting.KAHN.sort(this);
                this.cyclic = false;
            } catch (IllegalArgumentException e) {

                // TODO Change implementation.
                // Exceptions should not be used for ordinary control flow.
                // Don't use try-catch.
                this.cyclic = true;
            }
        }

        // Creating defensive copy of mutable field.
        return new Boolean(this.cyclic);
    }

    /**
     * @inheritdoc
     */
    @Override
    public Set<T1> vertices() {
        return Collections.unmodifiableSet(this.vertices);
    }

    /**
     * @inheritdoc
     */
    @Override
    public Set<T2> edges() {
        return Collections.unmodifiableSet(this.edges);
    }

    /**
     * @inheritdoc
     */
    @Override
    public Set<T2> incidentAllEdges(T1 vertex) {
        Objects.requireNonNull(vertex);
        return this.edges
                .stream()
                .filter(edge -> vertex.id().equals(edge.source().id()) || vertex.id().equals(edge.target().id()))
                .collect(Collectors.toSet());
    }

    /**
     * @inheritdoc
     */
    @Override
    public Set<T2> incidentInEdges(T1 vtx) {
        Objects.requireNonNull(vtx);
        return this.edges
                .stream()
                .filter(edge -> vtx.id().equals(edge.target().id()))
                .collect(Collectors.toSet());
    }

    /**
     * @inheritdoc
     */
    @Override
    public Set<T2> incidentOutEdges(T1 vtx) {
        Objects.requireNonNull(vtx);
        return this.edges
                .stream()
                .filter(edge -> vtx.id().equals(edge.source().id()))
                .collect(Collectors.toSet());
    }

    /**
     * @inheritdoc
     */
    @Override
    public int degreesOfAllIncidentEdges(T1 vtx) {
        Objects.requireNonNull(vtx);
        return this.incidentAllEdges(vtx).size();
    }

    /**
     * @inheritdoc
     */
    @Override
    public int degreesOfPredecessors(T1 vtx) {
        Objects.requireNonNull(vtx);
        return this.predecessors(vtx).size();
    }

    /**
     * @inheritdoc
     */
    @Override
    public int degreesOfSuccessors(T1 vtx) {
        Objects.requireNonNull(vtx);
        return this.successors(vtx).size();
    }

    /**
     * @inheritdoc
     */
    @Override
    public T2 edge(String srcId, String targetId) {
        Objects.requireNonNull(srcId);
        Objects.requireNonNull(targetId);
        return this.incidenceMap.get(VertexPair.of(vertex(srcId), vertex(targetId)));
    }

    /**
     * @inheritdoc
     */
    @Override
    public boolean edgeConnecting(String srcId, String targetId) {
        Objects.requireNonNull(srcId);
        Objects.requireNonNull(targetId);
        return this.edge(srcId, targetId) != null;
    }

    /**
     * @inheritdoc
     */
    @Override
    public <N extends Number> N edgeWeight(String sourceId, String targetId) {
        Objects.requireNonNull(sourceId);
        Objects.requireNonNull(targetId);
        return this.edge(sourceId, targetId).weight();
    }

    /**
     * @inheritdoc
     */
    @Override
    public Set<T1> adjacent(T1 vtx) {
        Objects.requireNonNull(vtx);
        Set<T1> adjacentVertices = new HashSet<>();

        // TODO Use parallel.
        // If our vertex is the source,
        // then its neighbor is the target, and vice-versa.
        this.edges
                .stream()
                .filter(edge -> vtx.id().equals(edge.source().id()))
                .map(edge -> edge.target())
                .collect(Collectors.toSet());

        this.edges
                .stream()
                .filter(edge -> vtx.id().equals(edge.target().id()))
                .map(edge -> edge.source())
                .collect(Collectors.toSet());
        return adjacentVertices;
    }

    /**
     * @inheritdoc
     */
    @Override
    public Set<T1> predecessors(T1 vtx) {
        Objects.requireNonNull(vtx);
        // If our vertex is the source,
        // then its neighbor is the target, and vice-versa.
        return this.edges
                .stream()
                .filter(edge -> vtx.id().equals(edge.target().id()))
                .map(edge -> edge.source())
                .collect(Collectors.toSet());
    }

    /**
     * @inheritdoc
     */
    @Override
    public Set<T1> successors(T1 vtx) {
        Objects.requireNonNull(vtx);
        return this.edges
                .stream()
                .filter(edge -> vtx.id().equals(edge.source().id()))
                .map(edge -> edge.target())
                .collect(Collectors.toSet());
    }

    /**
     * @inheritdoc
     */
    @Override
    public T1 vertex(String id) {
        Objects.requireNonNull(id);
        return Graph.vertex(this.vertices, id);
    }

    @Override
    public String toString() {
        return "GraphImpl{" + "vertices=" + vertices + ", edges=" + edges + ", incidenceMap=" + incidenceMap + '}';
    }

    private static final class VertexPair<T1 extends Vertex> {

        private final String id;
        private final T1 src;
        private final T1 tgt;

        private VertexPair(T1 s, T1 t) {
            this.src = s;
            this.tgt = t;
            this.id = this.src.id() + "_" + this.tgt.id();
        }

        private static <T1 extends Vertex> VertexPair of(T1 s, T1 t) {
            return new VertexPair(s, t);
        }

        @Override
        public String toString() {
            return "VertexPair{" + "id=" + id + '}';
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 61 * hash + Objects.hashCode(this.id);
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
            final VertexPair<?> other = (VertexPair<?>) obj;
            if (!Objects.equals(this.id, other.id)) {
                return false;
            }
            return true;
        }
    }
}
