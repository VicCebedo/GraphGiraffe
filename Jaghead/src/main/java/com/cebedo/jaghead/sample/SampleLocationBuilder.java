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
public class SampleLocationBuilder {

    private String id;
    private Graph graph;

    public SampleLocationBuilder withId(String i) {
        this.id = i;
        return this;
    }

    public SampleLocationBuilder ofGraph(Graph g) {
        this.graph = g;
        return this;
    }

    public SampleLocation build() {
        Preconditions.checkNotNull(this.id);
        Preconditions.checkNotNull(this.graph);
        return new SampleLocation(this.id, this.graph);
    }
}
