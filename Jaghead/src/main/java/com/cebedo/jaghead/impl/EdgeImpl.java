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
 * @author Vic Cebedo <cebedo.vii@gmail.com>
 * @param <T1>
 * @param <N>
 */
final class EdgeImpl<T1 extends Vertex, T2 extends Edge, N extends Number>
        implements Edge<T1> {

    private final String id;
    private final T1 source;
    private final T1 target;
    private final N weight;

    EdgeImpl(EdgeBuilder<T1, N> b) {
        this.id = b.id();
        this.source = b.source();
        this.target = b.target();
        this.weight = b.weight();
    }

    @Override
    public String id() {
        return this.id;
    }

    /**
     * @inheritdoc
     */
    @Override
    public T1 source() {
        return source;
    }

    /**
     * @inheritdoc
     */
    @Override
    public T1 target() {
        return target;
    }

    /**
     * @inheritdoc
     */
    @Override
    public N weight() {
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
