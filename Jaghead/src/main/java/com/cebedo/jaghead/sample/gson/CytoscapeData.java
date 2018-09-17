/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.sample.gson;

import com.cebedo.jaghead.GenericEdge;
import com.cebedo.jaghead.GenericVertex;

/**
 *
 * @author Vic
 * @param <N>
 * @param <T1>
 * @param <T2>
 */
public class CytoscapeData<T1 extends GenericVertex, T2 extends GenericEdge<T1, T1>, N extends Number> {

    private Object id;
    private Object source;
    private Object target;
    private N weight;

    public CytoscapeData(T1 v) {
        this.id = v.getId();
    }

    public CytoscapeData(T2 e) {
        this.id = e.getId();
        this.source = e.getSource().getId();
        this.target = e.getTarget().getId();
        this.weight = e.getWeight();
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public N getWeight() {
        return weight;
    }

    public void setWeight(N weight) {
        this.weight = weight;
    }

}
