/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.domain;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Vic
 */
final public class Graph implements IGraph {

    final private Set<IVertex> vertices = new HashSet<>();
    final private Set<IEdge> edges = new HashSet<>();

    @Override
    public Set<IVertex> getVertices() {
        return vertices;
    }

    @Override
    public Set<IEdge> getEdges() {
        return edges;
    }
}
