/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.sample;

import com.cebedo.jaghead.GenericVertex;
import com.cebedo.jaghead.Graph;
import com.cebedo.jaghead.algorithm.shortestpath.DijkstraAlgorithm;
import com.cebedo.jaghead.data.DataImporter;

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
        new DijkstraAlgorithm<>().findPath(graph, (GenericVertex) graph.getVertices().iterator().next());
    }

}
