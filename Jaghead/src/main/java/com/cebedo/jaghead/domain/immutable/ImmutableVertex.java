/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.domain.immutable;

import com.cebedo.jaghead.domain.IEdge;
import com.cebedo.jaghead.domain.IGraph;
import com.cebedo.jaghead.domain.IVertex;
import java.util.Set;

/**
 *
 * @author Vic
 */
final public class ImmutableVertex implements IVertex {

    final private String id;
    final private Set<ImmutableEdge> edges;
    final private IGraph graph;

    public ImmutableVertex(String i, Set<ImmutableEdge> e, IGraph g) {
        this.id = i;
        this.edges = e;
        this.graph = g;
    }

    public void addEdge(ImmutableEdge e) {
        this.edges.add(e.cloneObject());
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void addEdge(IEdge e) {
        this.edges.add((ImmutableEdge) e);
    }

    @Override
    public Set<ImmutableEdge> getEdges() {
        return this.edges;
    }

    @Override
    public IGraph getGraph() {
        return graph;
    }

}
