/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.sample;

import com.cebedo.jaghead.Graph;
import com.google.common.base.Preconditions;

/**
 *
 * @author Vic
 */
public class SampleRateBuilder {

    private String id;
    private Graph graph;
    private SampleLocation source;
    private SampleLocation target;
    private Integer weight;

    public SampleRateBuilder withId(String i) {
        this.id = i;
        return this;
    }

    public SampleRateBuilder ofGraph(Graph g) {
        this.graph = g;
        return this;
    }

    public SampleRateBuilder withSource(SampleLocation s) {
        this.source = s;
        return this;
    }

    public SampleRateBuilder withTarget(SampleLocation t) {
        this.target = t;
        return this;
    }

    public SampleRateBuilder withWeight(Integer w) {
        this.weight = w;
        return this;
    }

    public SampleRate build() {
        Preconditions.checkNotNull(this.graph);
        Preconditions.checkNotNull(this.source);
        Preconditions.checkNotNull(this.target);
        return new SampleRate(this.id, this.graph, this.source, this.target, this.weight);
    }
}
