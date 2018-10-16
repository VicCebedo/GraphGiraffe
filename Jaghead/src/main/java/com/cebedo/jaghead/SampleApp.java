/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead;

import com.cebedo.jaghead.algorithm.mst.PrimMinimumSpanningTree;
import com.cebedo.jaghead.algorithm.search.backtrack.BTPathFinder;
import com.cebedo.jaghead.algorithm.search.backtrack.BTPathMoreThanK;
import com.cebedo.jaghead.algorithm.search.bfs.BFSEdge;
import com.cebedo.jaghead.algorithm.search.bfs.BFSVertex;
import com.cebedo.jaghead.algorithm.search.checker.EdgeChecker;
import com.cebedo.jaghead.algorithm.search.checker.VertexChecker;
import com.cebedo.jaghead.algorithm.search.dfs.DFSEdge;
import com.cebedo.jaghead.algorithm.search.dfs.DFSVertex;
import com.cebedo.jaghead.algorithm.shortestpath.DijkstraShortestPath;
import com.cebedo.jaghead.algorithm.sort.KahnTopologicalSorter;
import com.cebedo.jaghead.impl.CytoscapeDataExporter;
import com.cebedo.jaghead.impl.GraphImpl;
import com.cebedo.jaghead.impl.JSONDataImporter;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        // primMinimumSpanningTree(graph);
        // btPathFinder(graph);
        // btPathMoreThanK(graph);
        // bfsConnectivity(graph);
        // bfsEdge(graph);
        // bfsVertex(graph);
        // dfsEdge(graph);
        // dfsVertex(graph);
        // dijkstraShortestPath(graph);
        // kahnTopologicalSorter(graph);
    }

    private static void kahnTopologicalSorter(Graph graph) {
        List<Vertex> topologicalSorting = KahnTopologicalSorter.newInstance().sort(graph);
        System.out.println(topologicalSorting);
    }

    private static void dijkstraShortestPath(Graph graph) {
        Map<Vertex, ? extends Number> distMap = DijkstraShortestPath.newInstance().findPath(graph, "A");
        System.out.println(distMap);
    }

    private static void dfsVertex(Graph graph) {
        Set<Vertex> results = DFSVertex.newInstance().search(graph, "A", (VertexChecker) (Vertex t1) -> {
            return t1.getId().equalsIgnoreCase("H");
        });
        System.out.println(results);
    }

    private static void dfsEdge(Graph graph) {
        Set<Vertex> results = DFSEdge.newInstance().search(graph, "A", (EdgeChecker) (Edge t1) -> {
            return t1.getTarget().getId().equalsIgnoreCase("H");
        });
        System.out.println(results);
    }

    private static void bfsVertex(Graph graph) {
        Set<Vertex> results = BFSVertex.newInstance().search(graph, "A", (VertexChecker) (Vertex t1) -> {
            return t1.getId().equalsIgnoreCase("H");
        });
        System.out.println(results);
    }

    private static void bfsEdge(Graph graph) {
        Set<Vertex> results = BFSEdge.newInstance().search(graph, "A", (EdgeChecker) (Edge t1) -> {
            return t1.getTarget().getId().equalsIgnoreCase("H");
        });
        System.out.println(results);
    }

    private static void bfsConnectivity(Graph graph) {
        // TODO [Bug] Add a warning to all traversing algorithms if graph is not connected.
        System.out.println(graph.connected());
    }

    private static void btPathMoreThanK(Graph graph) {
        // TODO [Bug] Does not check other paths, should have same algorithm with btPathFinder().
        Map<String, ?> path = BTPathMoreThanK.newInstance().findPath(graph, "A", 60);
        System.out.println(path);
    }

    private static void btPathFinder(Graph graph) {
        // TODO [Bug] Improve presentation of results, duplicate nodes.
        List<List<Vertex>> paths = BTPathFinder.newInstance().findPaths(graph, "A", "H");
        paths.forEach(path -> {
            System.out.println(path);
        });
    }

    private static void primMinimumSpanningTree(Graph graph) {
        Graph mst = PrimMinimumSpanningTree.newInstance().getMST(graph);
        CytoscapeDataExporter.newInstance().export(mst);
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
