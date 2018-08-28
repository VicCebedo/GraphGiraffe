/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.giraffe.builder;

import com.cebedo.giraffe.domain.immutable.ImmutableEdge;
import com.cebedo.giraffe.domain.immutable.ImmutableVertex;
import com.cebedo.giraffe.domain.IVertex;
import com.cebedo.giraffe.exception.MissingVertexIdException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public IVertex build(boolean immutable) {
        try {
            if (this.id == null) {
                throw new MissingVertexIdException();
            }
            if (immutable) {
                return new ImmutableVertex(this.id, this.edges);
            }
            throw new UnsupportedOperationException("Not supported yet.");
        } catch (MissingVertexIdException e) {
            Logger.getLogger(VertexBuilder.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public IVertex build() {
        return build(true);
    }

}
