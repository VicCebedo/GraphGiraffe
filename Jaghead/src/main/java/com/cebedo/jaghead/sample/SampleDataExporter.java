/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.sample;

import com.cebedo.jaghead.GenericEdge;
import com.cebedo.jaghead.GenericGraph;
import com.cebedo.jaghead.GenericVertex;
import com.cebedo.jaghead.sample.gson.CytoscapeData;
import com.cebedo.jaghead.sample.gson.CytoscapeElement;
import com.google.gson.Gson;
import java.util.HashSet;
import java.util.Set;
import com.cebedo.jaghead.data.DataExporter;

/**
 *
 * @author Vic
 * @param <T1>
 * @param <T2>
 */
public class SampleDataExporter<T1 extends GenericVertex, T2 extends GenericEdge<T1, T1, T3>, T3 extends GenericGraph<T1, T2>>
        implements DataExporter<T3> {

    @Override
    public void export(T3 graph) {
        Set<CytoscapeElement> data = new HashSet<>();
        graph.getVertices().forEach(vertex -> {
            data.add(new CytoscapeElement(new CytoscapeData(vertex)));
        });
        graph.getEdges().forEach(edge -> {
            data.add(new CytoscapeElement(new CytoscapeData(edge)));
        });
        String export = new Gson().toJson(data);
        System.out.println(export);
    }

}
