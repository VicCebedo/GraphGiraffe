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
 */
public class VertexBuilder<T1> {

    private T1 id;
    private Graph graph;

    public VertexBuilder withId(T1 i) {
        this.id = i;
        return this;
    }

    public VertexBuilder ofGraph(Graph g) {
        this.graph = g;
        return this;
    }

    public Vertex build() {
        Preconditions.checkNotNull(this.id);
        Preconditions.checkNotNull(this.graph);
        return new Vertex<>(this.id, this.graph);
    }
}
