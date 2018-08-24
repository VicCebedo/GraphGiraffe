/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.giraffe.builder;

import com.cebedo.giraffe.constant.EdgeType;
import com.cebedo.giraffe.immutable.ImmutableEdge;
import com.cebedo.giraffe.immutable.ImmutableWeight;
import com.cebedo.giraffe.domain.Weight;
import com.cebedo.giraffe.domain.IEdge;
import com.cebedo.giraffe.strategy.IWeightStrategy;

/**
 *
 * @author Vic
 */
public class EdgeBuilder {

    private Weight weight;
    private IWeightStrategy weightStrategy;
    private EdgeType type;

    /**
     * Set the weight with strategy on how to compute.
     *
     * @param w
     * @param s
     * @return
     */
    public EdgeBuilder withWeight(Weight w, IWeightStrategy s) {
        this.weight = w;
        this.weightStrategy = s;
        return this;
    }

    /**
     * Edge type: Directed or undirected.
     *
     * @param t
     * @return
     */
    public EdgeBuilder withType(EdgeType t) {
        this.type = t;
        return this;
    }

    /**
     * Build an immutable edge.
     *
     * @param immutable
     * @return
     */
    public IEdge build(boolean immutable) {
        if (immutable) {
            ImmutableWeight finalWeight = this.weightStrategy.compute(this.weight);
            return new ImmutableEdge(finalWeight, this.type);
        }
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public IEdge build() {
        return build(true);
    }

}
