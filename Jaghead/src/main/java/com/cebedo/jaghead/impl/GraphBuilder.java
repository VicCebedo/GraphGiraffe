/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.impl;

import com.cebedo.jaghead.Edge;
import com.cebedo.jaghead.Graph;
import com.cebedo.jaghead.Vertex;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Vic Cebedo <cebedo.vii@gmail.com>
 */
public final class GraphBuilder {

    private final Set<? extends Vertex> vertices;
    private final Set<? extends Edge> edges;

    public GraphBuilder(Set<? extends Vertex> v, Set<? extends Edge> e) {
        Objects.requireNonNull(v);
        Objects.requireNonNull(e);
        this.vertices = v;
        this.edges = e;
    }

    public Graph build() {
        return new GraphImpl(this.vertices, this.edges);
    }
}
