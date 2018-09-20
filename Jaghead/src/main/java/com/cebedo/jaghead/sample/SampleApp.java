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
import java.util.HashMap;
import java.util.Map;

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

        // Run algorithm.
        long start = System.currentTimeMillis();
        Map<? extends GenericVertex, Double> distanceMap = new DijkstraAlgorithm<>().findPath(graph, (GenericVertex) graph.getVertices().iterator().next());

        // Remove unreachable nodes.
        Map<GenericVertex, Double> distanceMapFiltered = new HashMap<>();
        distanceMap.keySet().forEach(vtx -> {
            Double value = distanceMap.get(vtx);
            if (value < Double.MAX_VALUE) {
                distanceMapFiltered.put(vtx, value);
            }
        });
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(distanceMapFiltered);
    }

}
