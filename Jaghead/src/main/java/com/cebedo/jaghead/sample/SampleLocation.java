/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.sample;

import com.cebedo.jaghead.GenericVertex;
import com.cebedo.jaghead.Graph;

/**
 *
 * @author Vic
 */
public class SampleLocation implements GenericVertex {

    private String id;
    private Graph graph;
    private String code;
    private String name;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Graph getGraph() {
        return graph;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
