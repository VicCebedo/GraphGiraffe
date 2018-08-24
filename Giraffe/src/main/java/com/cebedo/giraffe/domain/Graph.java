/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.giraffe.domain;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Vic
 */
final public class Graph implements IGraph {

    final private Set<IVertex> vertices = new HashSet<>();

    @Override
    public Set<IEdge> getEdges() {
        Set<IEdge> edges = new HashSet<>();
        Iterator<? extends IVertex> itr = this.getVertices().iterator();
        while (itr.hasNext()) {
            edges.addAll(itr.next().getEdges());
        }
        return edges;
    }

    @Override
    public Set<IVertex> getVertices() {
        return this.vertices;
    }

    @Override
    public void addVertex(IVertex v) {
        this.vertices.add(v);
    }

    @Override
    public void removeVertex(IVertex v) {
        this.vertices.remove(v);
    }
}
