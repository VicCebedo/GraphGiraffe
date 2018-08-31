/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.sample.gson;

/**
 *
 * @author Vic
 */
public class CytoscapeElement {

    private CytoscapeData data;

    public CytoscapeElement(CytoscapeData d) {
        this.data = d;
    }

    public CytoscapeData getData() {
        return data;
    }

    public void setData(CytoscapeData data) {
        this.data = data;
    }

}
