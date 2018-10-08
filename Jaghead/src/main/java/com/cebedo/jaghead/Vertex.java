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
public class Vertex implements GenericVertex<Graph> {

    private final String id;
    private final Graph graph;

    private Vertex(Builder b) {
        this.id = b.id;
        this.graph = b.graph;
    }

    public static class Builder {

        private final String id;
        private final Graph graph;

        public Builder(String i, Graph g) {
            this.id = i;
            this.graph = g;
        }

        public Vertex build() {
            return new Vertex(this);
        }
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Graph getGraph() {
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
