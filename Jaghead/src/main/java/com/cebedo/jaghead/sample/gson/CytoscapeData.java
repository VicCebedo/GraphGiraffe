/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.sample.gson;

import com.cebedo.jaghead.Edge;
import com.cebedo.jaghead.Vertex;

/**
 *
 * @author Vic
 */
public class CytoscapeData {

    private String id;
    private String source;
    private String target;
    private Integer weight;

    public CytoscapeData(Vertex<String> v) {
        this.id = v.getId();
    }

    public CytoscapeData(Edge<Integer> e) {
        this.id = e.getId();
        this.source = e.getSource().getId();
        this.target = e.getTarget().getId();
        this.weight = e.getWeight();
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

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

}
