/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.graphgiraffe.immutable;

import com.cebedo.graphgiraffe.constant.EdgeType;

/**
 *
 * @author Vic
 */
final public class ImmutableEdge {

    final private ImmutableWeight weight;
    final private ImmutableType type;

    public ImmutableEdge(ImmutableWeight w, EdgeType t) {
        this.weight = new ImmutableWeight(w.getValue());
        this.type = new ImmutableType(t);
    }

    public ImmutableWeight getWeight() {
        return weight;
    }

    public ImmutableType getType() {
        return type;
    }

}
