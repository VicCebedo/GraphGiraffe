/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead;

import java.util.Objects;

/**
 *
 * @author Vic
 * @param <T1>
 * @param <N>
 */
class Edge<T1 extends GenericVertex, T2 extends GenericEdge, N extends Number, T3 extends GenericGraph<T1, T2>>
        implements GenericEdge<T1, T1, T3> {

    private final String id;
    private final T3 graph;
    private final T1 source;
    private final T1 target;
    private final N weight;

    private Edge(Builder<T1, T2, N, T3> b) {
        this.id = b.id;
        this.graph = b.graph;
        this.source = b.source;
        this.target = b.target;
        this.weight = b.weight;
    }

    static class Builder<T1 extends GenericVertex, T2 extends GenericEdge, N extends Number, T3 extends GenericGraph<T1, T2>> {

        private final String id;
        private final T3 graph;
        private final T1 source;
        private final T1 target;
        private N weight;

        Builder(String i, T3 g, T1 s, T1 t) {
            this.id = i;
            this.graph = g;
            this.source = s;
            this.target = t;
        }

        Builder withWeight(N w) {
            this.weight = w;
            return this;
        }

        Edge build() {
            return new Edge(this);
        }
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public T3 getGraph() {
        return graph;
    }

    @Override
    public T1 getSource() {
        return source;
    }

    @Override
    public T1 getTarget() {
        return target;
    }

    @Override
    public N getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Edge{" + "id=" + id + ", weight=" + weight + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.id);
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
        final Edge<?, ?, ?, ?> other = (Edge<?, ?, ?, ?>) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
