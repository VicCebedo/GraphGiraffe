/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.sample;

import com.cebedo.jaghead.GenericEdge;
import com.cebedo.jaghead.Graph;

/**
 *
 * @author Vic
 */
public class SampleRate implements GenericEdge<SampleLocation, SampleLocation> {

    private String id;
    private Graph graph;
    private SampleLocation source;
    private SampleLocation target;
    private Integer weight;

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public Graph getGraph() {
        return this.graph;
    }

    @Override
    public SampleLocation getSource() {
        return this.source;
    }

    @Override
    public SampleLocation getTarget() {
        return this.target;
    }

    @Override
    public Integer getWeight() {
        return weight;
    }
}
