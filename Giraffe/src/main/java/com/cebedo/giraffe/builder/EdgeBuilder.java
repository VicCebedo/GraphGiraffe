/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.giraffe.builder;

import com.cebedo.giraffe.constant.EdgeType;
import com.cebedo.giraffe.domain.immutable.ImmutableEdge;
import com.cebedo.giraffe.domain.Weight;
import com.cebedo.giraffe.domain.IEdge;
import com.cebedo.giraffe.data.computation.IWeightStrategy;
import com.cebedo.giraffe.domain.IGraph;
import com.cebedo.giraffe.domain.IVertex;
import com.cebedo.giraffe.domain.immutable.ImmutableVertex;
import com.cebedo.giraffe.exception.MissingEdgeSourceException;
import com.cebedo.giraffe.exception.MissingEdgeTargetException;
import com.cebedo.giraffe.exception.MissingGraphException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vic
 */
public class EdgeBuilder {

    private IVertex source;
    private IVertex target;
    private IGraph graph;
    private Weight weight;
    private IWeightStrategy weightStrategy;
    private EdgeType type = EdgeType.UNDIRECTED;

    public EdgeBuilder ofGraph(IGraph g) {
        this.graph = g;
        return this;
    }

    public EdgeBuilder withSource(IVertex s) {
        this.source = s;
        return this;
    }

    public EdgeBuilder withTarget(IVertex t) {
        this.target = t;
        return this;
    }

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
        try {
            if (this.graph == null) {
                throw new MissingGraphException();
            }
            if (this.source == null) {
                throw new MissingEdgeSourceException();
            }
            if (this.target == null) {
                throw new MissingEdgeTargetException();
            }
            if (immutable) {
                return new ImmutableEdge(
                        (ImmutableVertex) this.source,
                        (ImmutableVertex) this.target,
                        this.graph,
                        this.weightStrategy.compute(this.weight),
                        this.type);
            }
            throw new UnsupportedOperationException("Not supported yet.");
        } catch (MissingEdgeSourceException | MissingEdgeTargetException | UnsupportedOperationException | MissingGraphException e) {
            Logger.getLogger(EdgeBuilder.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public IEdge build() {
        return build(true);
    }

}
