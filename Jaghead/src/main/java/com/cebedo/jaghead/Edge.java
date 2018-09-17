/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead;

import java.util.Objects;

/**
 *
 * @author Vic
 * @param <T1>
 * @param <N>
 */
public class Edge<T1 extends GenericVertex, N extends Number>
        implements GenericEdge<T1, T1> {

    final private String id;
    final private Graph graph;
    final private T1 source;
    final private T1 target;
    final private N weight;

    public Edge(String i, Graph g, T1 s, T1 t, N w) {
        this.id = i;
        this.graph = g;
        this.source = s;
        this.target = t;
        this.weight = w;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public Graph getGraph() {
        return graph;
    }

    @Override
    public T1 getSource() {
        return source;
    }

    @Override
    public T1 getTarget() {
        return target;
    }

    @Override
    public N getWeight() {
        return weight;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Edge<?, ?> other = (Edge<?, ?>) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
