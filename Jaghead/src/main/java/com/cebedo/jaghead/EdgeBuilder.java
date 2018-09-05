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
 * @param <N>
 */
public class EdgeBuilder<T1, N extends Number> {

    private T1 id;
    private Graph graph;
    private Vertex source;
    private Vertex target;
    private N weight;

    public EdgeBuilder withId(T1 i) {
        this.id = i;
        return this;
    }

    public EdgeBuilder ofGraph(Graph g) {
        this.graph = g;
        return this;
    }

    public EdgeBuilder withSource(Vertex s) {
        this.source = s;
        return this;
    }

    public EdgeBuilder withTarget(Vertex t) {
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
