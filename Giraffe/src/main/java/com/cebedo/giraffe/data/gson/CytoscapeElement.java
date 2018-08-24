/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.giraffe.data.gson;

import com.cebedo.giraffe.domain.IVertex;

/**
 *
 * @author Vic
 */
public class CytoscapeElement {

    private String id;
    private String source;
    private String target;

    public CytoscapeElement(IVertex v) {
        this.id = v.getId();
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

}
