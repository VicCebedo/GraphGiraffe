/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.data.gson;

import com.cebedo.jaghead.domain.IEdge;
import com.cebedo.jaghead.domain.IVertex;

/**
 *
 * @author Vic
 */
public class CytoscapeData {

    private String id;
    private String source;
    private String target;
    private int weight;

    public CytoscapeData(IVertex v) {
        this.id = v.getId();
    }

    public CytoscapeData(IEdge e) {
        this.source = e.getSource().getId();
        this.target = e.getTarget().getId();
        this.id = String.format("%s_%s", this.source, this.target);
        this.weight = e.getWeight().getValue();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

}
