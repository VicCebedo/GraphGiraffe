/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.giraffe.data;

import com.cebedo.giraffe.data.gson.CytoscapeElement;
import com.cebedo.giraffe.domain.GraphServant;
import com.cebedo.giraffe.domain.IGraph;

/**
 *
 * @author Vic
 */
public class SampleDataExportStrategy implements IDataExportStrategy {

    @Override
    public void export(IGraph graph) {
        GraphServant servant = new GraphServant(graph);
        graph.getVertices().stream().map((vertex) -> {
            CytoscapeElement elem = new CytoscapeElement(vertex);
            return vertex;
        });
    }

}
