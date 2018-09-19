/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.sample;

import com.cebedo.jaghead.Graph;
import com.cebedo.jaghead.Vertex;

/**
 *
 * @author Vic
 */
public class SampleLocation extends Vertex {

    private String code;
    private String name;

    public SampleLocation(String i, Graph g) {
        super(i, g);
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

}
