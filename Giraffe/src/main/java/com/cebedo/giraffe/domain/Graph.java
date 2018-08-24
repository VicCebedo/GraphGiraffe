/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.giraffe.domain;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Vic
 */
final public class Graph implements IGraph {

    final private Set<IVertex> vertices = new HashSet<>();
    final private Set<IEdge> edges = new HashSet<>();

    public Graph(Set<IVertex> v, Set<IEdge> e) {
        this.vertices.addAll(v);
        this.edges.addAll(e);
    }

    @Override
    public Set<IEdge> getEdges() {
        return this.edges;
    }

    @Override
    public Set<IVertex> getVertices() {
        return this.vertices;
    }
}
