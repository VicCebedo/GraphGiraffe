/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead;

import com.google.common.base.Preconditions;

/**
 *
 * @author Vic
 * @param <T1>
 * @param <T2>
 * @param <T3>
 * @param <T4>
 * @param <N>
 */
public class EdgeBuilder<T1, T2, T3, T4, N extends Number> {

    private T1 id;
    private T2 graph;
    private T3 source;
    private T4 target;
    private N weight;

    public EdgeBuilder withId(T1 i) {
        this.id = i;
        return this;
    }

    public EdgeBuilder ofGraph(T2 g) {
        this.graph = g;
        return this;
    }

    public EdgeBuilder withSource(T3 s) {
        this.source = s;
        return this;
    }

    public EdgeBuilder withTarget(T4 t) {
        this.target = t;
        return this;
    }

    public EdgeBuilder withWeight(N w) {
        this.weight = w;
        return this;
    }

    public Edge build() {
        Preconditions.checkNotNull(this.graph);
        Preconditions.checkNotNull(this.source);
        Preconditions.checkNotNull(this.target);
        return new Edge<>(this.id, this.graph, this.source, this.target, this.weight);
    }
}
