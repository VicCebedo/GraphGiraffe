/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.impl;

import com.cebedo.jaghead.Vertex;
import java.util.Objects;

/**
 *
 * @author Vic
 */
final class VertexImpl implements Vertex {

    private final String id;

    private VertexImpl(Builder b) {
        this.id = b.id;
    }

    static final class Builder {

        private final String id;

        Builder(String i) {
            this.id = i;
        }

        Vertex build() {
            return new VertexImpl(this);
        }
    }

    @Override
    public String getId() {
        return id;
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
        final VertexImpl other = (VertexImpl) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
