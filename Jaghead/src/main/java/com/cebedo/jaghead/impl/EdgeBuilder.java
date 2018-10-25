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
final public class EdgeBuilder<T1 extends Vertex, N> {

    private final String id;
    private final T1 source;
    private final T1 target;
    private N weight;

    public EdgeBuilder(String id, T1 src, T1 tgt) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(src);
        Objects.requireNonNull(tgt);
        this.id = id;
        this.source = src;
        this.target = tgt;
    }

    public EdgeBuilder<T1, N> withWeight(N w) {
        Objects.requireNonNull(w);
        this.weight = w;
        return this;
    }

    public Edge build() {
        return new EdgeImpl(this);
    }

    public String id() {
        return id;
    }

    public T1 source() {
        return source;
    }

    public T1 target() {
        return target;
    }

    public N weight() {
        return weight;
    }

}
