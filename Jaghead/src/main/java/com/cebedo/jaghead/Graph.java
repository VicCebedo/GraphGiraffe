/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead;

import java.util.Set;

/**
 *
 * @author Vic
 * @param <T1>
 * @param <T2>
 */
public class Graph<T1, T2> implements GenericGraph<T1, T2> {

    private Set<T1> vertices;
    private Set<T2> edges;

    public void setVertices(Set<T1> vertices) {
        this.vertices = vertices;
    }

    public void setEdges(Set<T2> edges) {
        this.edges = edges;
    }

    @Override
    public Set<T1> getVertices() {
        return this.vertices;
    }

    @Override
    public Set<T2> getEdges() {
        return this.edges;
    }

}
