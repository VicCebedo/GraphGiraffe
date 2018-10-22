/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead;

import com.cebedo.jaghead.algorithm.mst.JagheadMinimumSpanningTrees;
import com.cebedo.jaghead.algorithm.search.bfsdfs.JagheadSearch;
import com.cebedo.jaghead.algorithm.search.pathfinder.JagheadPathFinder;
import com.cebedo.jaghead.impl.CytoscapeDataExporter;
import com.cebedo.jaghead.impl.GraphImpl;
import com.cebedo.jaghead.impl.JSONDataImporter;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.cebedo.jaghead.algorithm.search.bfsdfs.CheckerEdge;
import com.cebedo.jaghead.algorithm.search.bfsdfs.CheckerVertex;
import com.cebedo.jaghead.algorithm.search.connectivity.JagheadConnectivity;
import com.cebedo.jaghead.algorithm.search.pathdistance.JagheadPathDistance;
import com.cebedo.jaghead.algorithm.shortestpath.JagheadShortestPath;
import com.cebedo.jaghead.algorithm.sort.JagheadTopologicalSorting;

/**
 *
 * @author Vic Cebedo
 */
public class SampleApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Construct graph and print JSON.
        Graph graph = jsonDataImporter();
        CytoscapeDataExporter.newInstance().export(graph);

        // Run function.
        primMinimumSpanningTree(graph);
        btPathFinder(graph);
        btPathMoreThanK(graph);
        bfsConnectivity(graph);
        bfsEdge(graph);
        bfsVertex(graph);
        dfsEdge(graph);
        dfsVertex(graph);
        dijkstraShortestPath(graph);
        kahnTopologicalSorter(graph);
    }

    private static void kahnTopologicalSorter(Graph graph) {
        List<Vertex> topologicalSorting = JagheadTopologicalSorting.KAHN.sort(graph);
        System.out.println(topologicalSorting);
    }

    private static void dijkstraShortestPath(Graph graph) {
        Map<Vertex, Number> distMap = JagheadShortestPath.DIJKSTRA.shortestPath(graph, "A");
        System.out.println(distMap);
    }

    private static void dfsVertex(Graph graph) {
        Set<Vertex> results = JagheadSearch.DepthFirst.VERTEX.search(graph, "A", (CheckerVertex) (Vertex t1) -> {
            return t1.getId().equalsIgnoreCase("H");
        });
        System.out.println(results);
    }

    private static void dfsEdge(Graph graph) {
        Set<Vertex> results = JagheadSearch.DepthFirst.EDGE.search(graph, "A", (CheckerEdge) (Edge t1) -> {
            return t1.getTarget().getId().equalsIgnoreCase("H");
        });
        System.out.println(results);
    }

    private static void bfsVertex(Graph graph) {
        Set<Vertex> results = JagheadSearch.BreadthFirst.VERTEX.search(graph, "A", (CheckerVertex) (Vertex t1) -> {
            return t1.getId().equalsIgnoreCase("H");
        });
        System.out.println(results);
    }

    private static void bfsEdge(Graph graph) {
        Set<Vertex> results = JagheadSearch.BreadthFirst.EDGE.search(graph, "A", (CheckerEdge) (Edge t1) -> {
            return t1.getTarget().getId().equalsIgnoreCase("H");
        });
        System.out.println(results);
    }

    private static void bfsConnectivity(Graph graph) {
        // TODO [Bug] Add a warning to all traversing algorithms if graph is not connected.
        System.out.println(JagheadConnectivity.BREADTH_FIRST.connected(graph));
    }

    private static void btPathMoreThanK(Graph graph) {
        // TODO [Bug] Does not check other paths, should have same algorithm with btPathFinder().
        Map<String, ?> path = JagheadPathDistance.BACKTRACK.findPath(graph, "A", 60);
        System.out.println(path);
    }

    private static void btPathFinder(Graph graph) {
        // TODO [Bug] Improve presentation of results, duplicate nodes.
        List<List<Vertex>> paths = JagheadPathFinder.BACKTRACK.findPaths(graph, "A", "H");
        paths.forEach(path -> {
            System.out.println(path);
        });
    }

    private static void primMinimumSpanningTree(Graph graph) {
        System.out.println(JagheadMinimumSpanningTrees.PRIM.mst(graph));
    }

    private static Graph jsonDataImporter() {
        String json = "{\n"
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
        DataImporter importer = new JSONDataImporter.Builder(json).build();
        return new GraphImpl.Builder(
                importer.getVertices(),
                importer.getEdges())
                .build();
    }

}
