/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead;

import com.cebedo.jaghead.GenericVertex;
import com.cebedo.jaghead.Graph;
import com.cebedo.jaghead.algorithm.backtrack.BacktrackResult;
import com.cebedo.jaghead.algorithm.backtrack.PathFinder;
import com.cebedo.jaghead.algorithm.backtrack.PathMoreThanK;
import com.cebedo.jaghead.algorithm.mst.MSTAlgorithm;
import com.cebedo.jaghead.algorithm.mst.PrimMinimumSpanningTree;
import com.cebedo.jaghead.algorithm.shortestpath.DijkstraShortestPath;
import com.cebedo.jaghead.algorithm.shortestpath.ShortestPathAlgorithm;
import com.cebedo.jaghead.data.DataImporter;
import com.cebedo.jaghead.JSONDataImporter;
import com.cebedo.jaghead.sample.SampleDataExporter;
import java.util.HashMap;
import java.util.List;
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
        Graph graph = getSampleGraphJson();
//        new SampleDataExporter<>().export(graph);
        findPath(graph);
    }

    private static Graph getSampleGraphJson() {
        Graph graph = new Graph();
        String j = "{\n"
                + "    \"vertices\": \n"
                + "    [\n"
                + "        { \"id\": \"A\" },\n"
                + "        { \"id\": \"B\" },\n"
                + "        { \"id\": \"C\" },\n"
                + "        { \"id\": \"D\" },\n"
                + "        { \"id\": \"E\" },\n"
                + "        { \"id\": \"F\" },\n"
                + "        { \"id\": \"G\" },\n"
                + "        { \"id\": \"H\" }\n"
                + "    ],\n"
                + "    \"edges\": \n"
                + "    [\n"
                + "        { \n"
                + "            \"source\": \"A\",\n"
                + "            \"target\": \"B\",\n"
                + "            \"weight\": 5\n"
                + "        },\n"
                + "        { \n"
                + "            \"source\": \"A\",\n"
                + "            \"target\": \"C\",\n"
                + "            \"weight\": 4\n"
                + "        },\n"
                + "        { \n"
                + "            \"source\": \"A\",\n"
                + "            \"target\": \"D\",\n"
                + "            \"weight\": 3\n"
                + "        },\n"
                + "        { \n"
                + "            \"source\": \"A\",\n"
                + "            \"target\": \"E\",\n"
                + "            \"weight\": 1\n"
                + "        },\n"
                + "        { \n"
                + "            \"source\": \"B\",\n"
                + "            \"target\": \"F\",\n"
                + "            \"weight\": 2\n"
                + "        },\n"
                + "        { \n"
                + "            \"source\": \"C\",\n"
                + "            \"target\": \"H\",\n"
                + "            \"weight\": 3\n"
                + "        },\n"
                + "        { \n"
                + "            \"source\": \"D\",\n"
                + "            \"target\": \"G\",\n"
                + "            \"weight\": 3\n"
                + "        },\n"
                + "        { \n"
                + "            \"source\": \"E\",\n"
                + "            \"target\": \"G\",\n"
                + "            \"weight\": 3\n"
                + "        },\n"
                + "        { \n"
                + "            \"source\": \"G\",\n"
                + "            \"target\": \"H\",\n"
                + "            \"weight\": 3\n"
                + "        },\n"
                + "        { \n"
                + "            \"source\": \"D\",\n"
                + "            \"target\": \"F\",\n"
                + "            \"weight\": 3\n"
                + "        },\n"
                + "        { \n"
                + "            \"source\": \"D\",\n"
                + "            \"target\": \"H\",\n"
                + "            \"weight\": 3\n"
                + "        },\n"
                + "        { \n"
                + "            \"source\": \"F\",\n"
                + "            \"target\": \"H\",\n"
                + "            \"weight\": 3\n"
                + "        }\n"
                + "    ]\n"
                + "}";
        DataImporter importer = new JSONDataImporter.Builder(graph, j).build();
        graph.initialize(
                importer.importVertices(),
                importer.importEdges());
        return graph;
    }

    private static void findPath(Graph graph) {
        PathFinder finder = new PathFinder();
        List<List> paths = finder.findPath(graph, "A", "H");
        paths.forEach(path -> {
            path.forEach(vtx -> {
                System.out.print(String.format("%s --> ", ((GenericVertex) vtx).getId()));
            });
            System.out.println();
        });
    }

    /**
     * Run Dijkstra's algorithm.
     *
     * @param graph
     */
    private void dijkstra(Graph graph) {
        // Run algorithm.
        long start = System.currentTimeMillis();
        ShortestPathAlgorithm algo = new DijkstraShortestPath<>();
        Map<? extends GenericVertex, Double> distanceMap = algo.findPath(
                graph,
                (GenericVertex) graph.getVertices().iterator().next());

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
