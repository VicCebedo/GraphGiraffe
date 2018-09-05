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
 * @param <N>
 */
public class Edge<T1, N extends Number> implements GenericEdge<T1, Graph, Vertex<String>, Vertex<String>> {

    final private T1 id;
    final private Graph graph;
    final private Vertex<String> source;
    final private Vertex<String> target;
    final private N weight;

    public Edge(T1 i, Graph g, Vertex<String> s, Vertex<String> t, N w) {
        this.id = i;
        this.graph = g;
        this.source = s;
        this.target = t;
        this.weight = w;
    }

    @Override
    public T1 getId() {
        return this.id;
    }

    @Override
    public Graph getGraph() {
        return graph;
    }

    @Override
    public Vertex<String> getSource() {
        return source;
    }

    @Override
    public Vertex<String> getTarget() {
        return target;
    }

    public N getWeight() {
        return weight;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.id);
        hash = 83 * hash + Objects.hashCode(this.source);
        hash = 83 * hash + Objects.hashCode(this.target);
        hash = 83 * hash + Objects.hashCode(this.weight);
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
        return true;
    }

}
