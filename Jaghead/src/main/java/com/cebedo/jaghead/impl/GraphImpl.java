/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.impl;

import com.cebedo.jaghead.Edge;
import com.cebedo.jaghead.Graph;
import com.cebedo.jaghead.Vertex;
import com.cebedo.jaghead.algorithm.search.bfs.BFSConnectivity;
import com.cebedo.jaghead.algorithm.sort.KahnTopologicalSorter;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * TODO [Doc].
 *
 * @author Vic
 * @param <T1>
 * @param <T2>
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

    private GraphImpl(Set<T1> v, Set<T2> e) {
        this.vertices = v;
        this.edges = e;
        this.incidenceMap = new HashMap<>();
        this.edges.forEach(edge -> {
            this.incidenceMap.put(
                    VertexPair.of(edge.getSource(), edge.getTarget()),
                    edge);
        });
    }

    public static final class Builder {

        private final Set<? extends Vertex> impVertices;
        private final Set<? extends Edge> impEdges;

        public Builder(Set<? extends Vertex> v, Set<? extends Edge> e) {
            this.impVertices = v;
            this.impEdges = e;
        }

        public Graph build() {
            return new GraphImpl(this.impVertices, this.impEdges);
        }
    }

    /**
     * @inheritdoc
     */
    @Override
    public boolean connected() {
        if (this.connected == null) {
            this.connected = BFSConnectivity.newInstance().isConnected(this);
        }
        return this.connected;
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
                KahnTopologicalSorter.newInstance().sort(this);
                this.cyclic = false;
            } catch (IllegalArgumentException e) {
                this.cyclic = true;
            }
        }
        return this.cyclic;
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
    public Set<T2> incidentEdgesAll(T1 vtx) {
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

    /**
     * @inheritdoc
     */
    @Override
    public Set<T2> incidentEdgesIncoming(T1 vtx) {
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

    /**
     * @inheritdoc
     */
    @Override
    public Set<T2> incidentEdgesOutgoing(T1 vtx) {
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

    /**
     * @inheritdoc
     */
    @Override
    public int degreesOfAllIncidentEdges(T1 vtx) {
        return this.incidentEdgesAll(vtx).size();
    }

    /**
     * @inheritdoc
     */
    @Override
    public int degreesOfPredecessors(T1 vtx) {
        return this.predecessors(vtx).size();
    }

    /**
     * @inheritdoc
     */
    @Override
    public int degreesOfSuccessors(T1 vtx) {
        return this.successors(vtx).size();
    }

    /**
     * @inheritdoc
     */
    @Override
    public T2 edge(String srcId, String targetId) {
        return this.incidenceMap.get(VertexPair.of(vertex(srcId), vertex(targetId)));
    }

    /**
     * @inheritdoc
     */
    @Override
    public boolean edgeConnecting(String srcId, String targetId) {
        return this.edge(srcId, targetId) != null;
    }

    /**
     * @inheritdoc
     */
    @Override
    public <N extends Number> N edgeWeight(String sourceId, String targetId) {
        return Optional.of(this.edge(sourceId, targetId)).get().getWeight();
    }

    /**
     * @inheritdoc
     */
    @Override
    public Set<T1> adjacent(T1 vtx) {
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

    /**
     * @inheritdoc
     */
    @Override
    public Set<T1> predecessors(T1 vtx) {
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

    /**
     * @inheritdoc
     */
    @Override
    public Set<T1> successors(T1 vtx) {
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

    /**
     * @inheritdoc
     */
    @Override
    public T1 vertex(String id) {
        return GraphImpl.getVertex(this.vertices, id);
    }

    public static <T1 extends Vertex> T1 getVertex(Set<T1> vertices, String id) {
        T1 returnObj = null;
        for (T1 vtxObj : vertices) {
            if (vtxObj.getId().equalsIgnoreCase(id)) {
                returnObj = vtxObj;
                break;
            }
        }
        return Optional.of(returnObj).get();
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
            this.id = this.src.getId() + "_" + this.tgt.getId();
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
