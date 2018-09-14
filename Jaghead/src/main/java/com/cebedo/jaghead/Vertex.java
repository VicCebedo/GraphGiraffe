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
 */
public class Vertex<T1> implements GenericVertex<T1, Graph> {

    final private T1 id;
    final private Graph graph;

    public Vertex(T1 i, Graph g) {
        this.id = i;
        this.graph = g;
    }

    @Override
    public T1 getId() {
        return id;
    }

    @Override
    public Graph getGraph() {
        return graph;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final Vertex<?> other = (Vertex<?>) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
