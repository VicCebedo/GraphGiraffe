/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.giraffe.data;

import com.cebedo.giraffe.data.gson.CytoscapeData;
import com.cebedo.giraffe.data.gson.CytoscapeElement;
import com.cebedo.giraffe.domain.IGraph;
import com.google.gson.Gson;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Vic
 */
public class SampleDataExportStrategy implements IDataExportStrategy {

    @Override
    public void export(IGraph graph) {
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
