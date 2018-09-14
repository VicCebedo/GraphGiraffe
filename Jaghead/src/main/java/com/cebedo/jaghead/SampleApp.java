/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead;

import com.cebedo.jaghead.algorithm.BFSEdge;
import com.cebedo.jaghead.algorithm.BFSVertex;
import com.cebedo.jaghead.data.DataImporter;
import com.cebedo.jaghead.sample.SampleDataExporter;
import com.cebedo.jaghead.sample.SampleDataImporter;

/**
 *
 * @author Vic
 */
public class SampleApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Prepare data.
        Graph graph = new Graph();
        DataImporter importer = new SampleDataImporter(graph);
        graph.initialize(
                importer.importVertices(),
                importer.importEdges());
        new SampleDataExporter().export(graph);

        // Run algorithm.
        new BFSEdge().search(graph, (edge) -> {
            return edge.getWeight().doubleValue() > 400.0;
        });

        new BFSVertex().search(graph, (vertex) -> {
            System.out.println(vertex);
            return true;
        });
    }

}
