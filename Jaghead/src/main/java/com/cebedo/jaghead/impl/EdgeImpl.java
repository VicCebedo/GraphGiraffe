/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.impl;

import com.cebedo.jaghead.Edge;
import com.cebedo.jaghead.Vertex;
import java.util.Objects;

/**
 *
 * @author Vic
 * @param <T1>
 * @param <N>
 */
final class EdgeImpl<T1 extends Vertex, T2 extends Edge, N extends Number>
        implements Edge<T1> {

    private final String id;
    private final T1 source;
    private final T1 target;
    private final N weight;

    private EdgeImpl(Builder<T1, N> b) {
        this.id = b.id;
        this.source = b.source;
        this.target = b.target;
        this.weight = b.weight;
    }

    static final class Builder<T1 extends Vertex, N> {

        private final String id;
        private final T1 source;
        private final T1 target;
        private N weight;

        Builder(String i, T1 s, T1 t) {
            this.id = i;
            this.source = s;
            this.target = t;
        }

        Builder<T1, N> withWeight(N w) {
            this.weight = w;
            return this;
        }

        Edge build() {
            return new EdgeImpl(this);
        }
    }

    @Override
    public String getId() {
        return this.id;
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
        final EdgeImpl<?, ?, ?> other = (EdgeImpl<?, ?, ?>) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
