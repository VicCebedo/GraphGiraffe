/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.graphgiraffe.builder;

import com.cebedo.graphgiraffe.immutable.ImmutableEdge;
import com.cebedo.graphgiraffe.immutable.ImmutableVertex;
import com.cebedo.graphgiraffe.domain.IVertex;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Vic
 */
public class VertexBuilder {

    private String id;
    private final Set<ImmutableEdge> edges = new HashSet<>();

    public VertexBuilder withId(String i) {
        this.id = i;
        return this;
    }

    public VertexBuilder withImmutableEdge(ImmutableEdge e) {
        this.edges.add(e);
        return this;
    }

    public VertexBuilder removeImmutableEdge(ImmutableEdge e) {
        this.edges.remove(e);
        return this;
    }

    public IVertex build(boolean immutable) {
        if (immutable) {
            return new ImmutableVertex(this.id, this.edges);
        }
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public IVertex build() {
        return build(true);
    }

}
