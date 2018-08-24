/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.giraffe.domain.immutable;

import com.cebedo.giraffe.domain.IVertex;
import java.util.Set;

/**
 *
 * @author Vic
 */
final public class ImmutableVertex implements IVertex {

    final private String id;
    final private Set<ImmutableEdge> edges;

    public ImmutableVertex(String i, Set<ImmutableEdge> e) {
        this.id = i;
        this.edges = e;
    }

    public void addEdge(ImmutableEdge e) {
        this.edges.add(e.cloneObject());
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Set<ImmutableEdge> getEdges() {
        return edges;
    }

}
