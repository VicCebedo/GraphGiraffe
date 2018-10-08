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
public class VertexPair<T1 extends GenericVertex> {

    private final String id;
    private final T1 src;
    private final T1 tgt;

    private VertexPair(Builder<T1> b) {
        this.src = b.src;
        this.tgt = b.tgt;
        this.id = b.id;
    }

    public static class Builder<T1 extends GenericVertex> {

        private final String id;
        private final T1 src;
        private final T1 tgt;

        public Builder(T1 s, T1 t) {
            this.src = s;
            this.tgt = t;
            this.id = this.src.getId() + "_" + this.tgt.getId();
        }

        public VertexPair build() {
            return new VertexPair(this);
        }
    }

    @Override
    public String toString() {
        return "VertexPair{" + "id=" + id + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.id);
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
        final VertexPair<?> other = (VertexPair<?>) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
