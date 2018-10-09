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
 */
class Vertex<T1 extends GenericGraph> implements GenericVertex<T1> {

    private final String id;
    private final T1 graph;

    private Vertex(Builder<T1> b) {
        this.id = b.id;
        this.graph = b.graph;
    }

    static class Builder<T1> {

        private final String id;
        private final T1 graph;

        Builder(String i, T1 g) {
            this.id = i;
            this.graph = g;
        }

        Vertex build() {
            return new Vertex(this);
        }
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public T1 getGraph() {
        return graph;
    }

    @Override
    public String toString() {
        return "Vertex{" + "id=" + id + '}';
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
        final Vertex other = (Vertex) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
