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
public class AdjacentPair<T1 extends GenericVertex> {

    private String id;
    private T1 src;
    private T1 tgt;

    public AdjacentPair(T1 s, T1 t) {
        this.src = s;
        this.tgt = t;
        this.id = String.format("%s_%s", this.src.getId(), this.tgt.getId());
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
        final AdjacentPair<?> other = (AdjacentPair<?>) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
