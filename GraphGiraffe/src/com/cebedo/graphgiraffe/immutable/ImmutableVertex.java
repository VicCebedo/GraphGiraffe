/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.graphgiraffe.immutable;

import java.util.Set;

/**
 *
 * @author Vic
 */
final public class ImmutableVertex {

    final private String id;
    final private Set<ImmutableEdge> edges;

    public ImmutableVertex(String i, Set<ImmutableEdge> e) {
        this.id = i;
        this.edges = e;
    }

    public String getId() {
        return id;
    }

    public Set<ImmutableEdge> getEdges() {
        return edges;
    }

}
