/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.sample;

import com.cebedo.jaghead.sample.gson.CytoscapeData;
import com.cebedo.jaghead.sample.gson.CytoscapeElement;
import com.cebedo.jaghead.Edge;
import com.google.gson.Gson;
import java.util.HashSet;
import java.util.Set;
import com.cebedo.jaghead.Graph;
import com.cebedo.jaghead.Vertex;
import com.cebedo.jaghead.data.DataExporter;

/**
 *
 * @author Vic
 */
public class SampleDataExporter implements DataExporter<Graph> {

    @Override
    public void export(Graph graph) {
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
