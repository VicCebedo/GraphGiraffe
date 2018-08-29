/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.builder;

import com.cebedo.jaghead.domain.IGraph;
import com.cebedo.jaghead.domain.immutable.ImmutableEdge;
import com.cebedo.jaghead.domain.immutable.ImmutableVertex;
import com.cebedo.jaghead.domain.IVertex;
import com.cebedo.jaghead.exception.MissingGraphException;
import com.cebedo.jaghead.exception.MissingVertexIdException;
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
    private IGraph graph;
    private final Set<ImmutableEdge> edges = new HashSet<>();

    public VertexBuilder ofGraph(IGraph g) {
        this.graph = g;
        return this;
    }

    public VertexBuilder withId(String i) {
        this.id = i;
        return this;
    }

    public IVertex build(boolean immutable) {
        try {
            if (this.id == null) {
                throw new MissingVertexIdException();
            }
            if (this.graph == null) {
                throw new MissingGraphException();
            }
            if (immutable) {
                return new ImmutableVertex(this.id, this.edges, this.graph);
            }
            throw new UnsupportedOperationException("Not supported yet.");
        } catch (MissingVertexIdException | MissingGraphException e) {
            Logger.getLogger(VertexBuilder.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public IVertex build() {
        return build(true);
    }

}
