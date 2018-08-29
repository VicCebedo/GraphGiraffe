/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead;

import com.cebedo.jaghead.algorithm.DepthFirstTraversal;
import com.cebedo.jaghead.data.IDataImportStrategy;
import com.cebedo.jaghead.data.SampleDataExportStrategy;
import com.cebedo.jaghead.data.SampleDataImportStrategy;
import com.cebedo.jaghead.domain.IGraph;
import com.cebedo.jaghead.domain.Graph;
import com.cebedo.jaghead.domain.GraphServant;

/**
 *
 * @author Vic
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Construct graph, vertices and edges.
        IGraph graph = new Graph();
        IDataImportStrategy importStrat = new SampleDataImportStrategy(graph);
        graph.getVertices().addAll(importStrat.importVertices());
        graph.getEdges().addAll(importStrat.importEdges());

        // 
        System.out.println(
                new GraphServant()
                        .checkConnectivity(
                                graph,
                                new DepthFirstTraversal()));
        new SampleDataExportStrategy().export(graph);
    }

}
