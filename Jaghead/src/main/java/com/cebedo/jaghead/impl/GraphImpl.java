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
                    new VertexPair.Builder(
                            edge.getSource(),
                            edge.getTarget())
                            .build(),
                    edge);
        });
    }

    @Override
    public boolean isConnected() {
        if (this.connected == null) {
            this.connected = BFSConnectivity.newInstance().isConnected(this);
        }
        return this.connected;
    }

    @Override
    public boolean isCyclic() {
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

    public static final class Builder {

        private final Set<? extends Vertex> impVertices;
        private final Set<? extends Edge<? extends Vertex>> impEdges;

        public Builder(Set<? extends Vertex> v,
                Set<? extends Edge<? extends Vertex>> e) {
            this.impVertices = v;
            this.impEdges = e;
        }

        public Graph build() {
            return new GraphImpl(this.impVertices, this.impEdges);
        }
    }

    @Override
    public Set<T1> getVertices() {
        return Collections.unmodifiableSet(this.vertices);
    }

    @Override
    public Set<T2> getEdges() {
        return Collections.unmodifiableSet(this.edges);
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
    public int getIncidentDegrees(T1 vtx) {
        return this.getIncidentEdgesAll(vtx).size();
    }

    @Override
    public int getDegreesIncoming(T1 vtx) {
        return this.getPredecessors(vtx).size();
    }

    @Override
    public int getDegreesOutgoing(T1 vtx) {
        return this.getSuccessors(vtx).size();
    }

    @Override
    public T2 getEdge(T1 src, T1 target) {
        return this.incidenceMap.get(
                new VertexPair.Builder(
                        src, target)
                        .build());
    }

    @Override
    public boolean hasEdgeConnecting(T1 src, T1 target) {
        return this.getEdge(src, target) != null;
    }

    @Override
    public Number getEdgeWeight(T1 src, T1 target) {
        return Optional.of(this.getEdge(src, target)).get().getWeight();
    }

    @Override
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

    @Override
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

    @Override
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

    private static final class VertexPair<T1 extends Vertex> {

        private final String id;
        private final T1 src;
        private final T1 tgt;

        private VertexPair(Builder<T1> b) {
            this.src = b.src;
            this.tgt = b.tgt;
            this.id = b.id;
        }

        private static final class Builder<T1 extends Vertex> {

            private final String id;
            private final T1 src;
            private final T1 tgt;

            Builder(T1 s, T1 t) {
                this.src = s;
                this.tgt = t;
                this.id = this.src.getId() + "_" + this.tgt.getId();
            }

            VertexPair build() {
                return new VertexPair(this);
            }
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
