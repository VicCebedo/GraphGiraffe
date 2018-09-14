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
public class Edge<N extends Number> implements GenericEdge<String, Graph, Vertex<String>, Vertex<String>> {

    final private String id;
    final private Graph graph;
    final private Vertex<String> source;
    final private Vertex<String> target;
    final private N weight;

    public Edge(String i, Graph g, Vertex<String> s, Vertex<String> t, N w) {
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
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
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
        final Edge<?> other = (Edge<?>) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
