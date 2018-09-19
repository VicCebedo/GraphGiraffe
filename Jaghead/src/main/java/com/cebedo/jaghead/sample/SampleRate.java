/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.sample;

import com.cebedo.jaghead.Edge;
import com.cebedo.jaghead.GenericVertex;
import com.cebedo.jaghead.Graph;

/**
 *
 * @author Vic
 */
public class SampleRate extends Edge {

    public SampleRate(String i, Graph g, GenericVertex s, GenericVertex t, Number w) {
        super(i, g, s, t, w);
    }

}
