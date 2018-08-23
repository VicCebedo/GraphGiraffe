/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.graphgiraffe.immutable;

import com.cebedo.graphgiraffe.constant.EdgeType;
import com.cebedo.graphgiraffe.domain.IEdge;

/**
 *
 * @author Vic
 */
final public class ImmutableEdge implements IEdge {

    final private ImmutableWeight weight;
    final private ImmutableType type;

    public ImmutableEdge(ImmutableWeight w, EdgeType t) {
        this.weight = new ImmutableWeight(w.getValue());
        this.type = new ImmutableType(t);
    }

    @Override
    public ImmutableWeight getWeight() {
        return weight;
    }

    @Override
    public ImmutableType getType() {
        return type;
    }

}
