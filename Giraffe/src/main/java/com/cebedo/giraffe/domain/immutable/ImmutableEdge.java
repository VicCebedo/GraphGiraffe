/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.giraffe.domain.immutable;

import com.cebedo.giraffe.constant.EdgeType;
import com.cebedo.giraffe.domain.IEdge;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vic
 */
final public class ImmutableEdge implements IEdge, Cloneable {

    final private ImmutableVertex source;
    final private ImmutableVertex target;
    final private ImmutableWeight weight;
    final private ImmutableType type;

    public ImmutableEdge(ImmutableVertex sV, ImmutableVertex tV, ImmutableWeight w, EdgeType t) {
        this.source = sV;
        this.target = tV;
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

    @Override
    public ImmutableVertex getSource() {
        return source;
    }

    @Override
    public ImmutableVertex getTarget() {
        return target;
    }

    public ImmutableEdge cloneObject() {
        try {
            return (ImmutableEdge) this.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(ImmutableEdge.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
