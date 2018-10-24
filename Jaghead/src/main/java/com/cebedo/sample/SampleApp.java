/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.sample;

import com.cebedo.jaghead.DataImporter;
import com.cebedo.jaghead.Edge;
import com.cebedo.jaghead.Graph;
import com.cebedo.jaghead.Vertex;
import com.cebedo.jaghead.algorithm.mst.JagheadMinimumSpanningTrees;
import com.cebedo.jaghead.algorithm.search.bfsdfs.JagheadSearch;
import com.cebedo.jaghead.algorithm.search.pathfinder.JagheadPathFinder;
import com.cebedo.jaghead.impl.DataCytoscapeExporter;
import com.cebedo.jaghead.impl.GraphImpl;
import com.cebedo.jaghead.impl.DataJSONImporter;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.cebedo.jaghead.algorithm.search.bfsdfs.CheckerEdge;
import com.cebedo.jaghead.algorithm.search.bfsdfs.CheckerVertex;
import com.cebedo.jaghead.algorithm.search.connectivity.JagheadConnectivity;
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
        DataCytoscapeExporter.newInstance().export(graph);

        // Run function.
        // primMinimumSpanningTree(graph);
        // kahnTopologicalSorter(graph);
        // bfsVertex(graph);
        // bfsEdge(graph);
        // dfsVertex(graph);
        btPathFinder(graph);
    }

    private static void kahnTopologicalSorter(Graph graph) {
        // Topologically sort the graph.
        List<Vertex> topologicalSorting = JagheadTopologicalSorting.KAHN.sort(graph);
        System.out.println(topologicalSorting);
    }

    private static void dijkstraShortestPath(Graph graph) {
        // Gets the shortest path from vertex "A" to all the other vertices in the graph.
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
        // Start walking from vertex "A".
        // Loop through all vertices of the graph and
        // collect all vertices if a vertex has more than 5 outgoing incident edges.
        Set<Vertex> results = JagheadSearch.BreadthFirst.VERTEX.search(graph, "A", (CheckerVertex) (Vertex t1) -> {
            return graph.incidentEdgesOutgoing(t1).size() > 1;
        });
        System.out.println(results);
    }

    private static void bfsEdge(Graph graph) {
        // Start walking from vertex "A".
        // Loop through all edges of the graph and
        // collect all vertices where its incident edge weight is greater than 35.
        Set<Vertex> results = JagheadSearch.BreadthFirst.EDGE.search(graph, "A", (CheckerEdge) (Edge t1) -> {
            return t1.getWeight().doubleValue() > 35;
        });
        System.out.println(results);
    }

    private static void bfsConnectivity(Graph graph) {
        // Checks whether a graph is connected or not.
        boolean isConnected = JagheadConnectivity.BREADTH_FIRST.connected(graph);
        System.out.println(isConnected);
    }

    private static void btPathFinder(Graph graph) {
        // Get a list of paths (linked list of vertices) from vertex "A" to "H".
        Set<List<Vertex>> paths = JagheadPathFinder.BACKTRACK.findPaths(graph, "A", "H");

        // Print each path.
        paths.forEach(path -> {
            System.out.println(path);
        });
    }

    private static void primMinimumSpanningTree(Graph graph) {
        // Print the graph before simplifying it to MST.
        // You can use the cytoscape output to visualize the graph.
        System.out.println("Before:");
        DataCytoscapeExporter.newInstance().export(graph);

        // Do the MST operation.
        Graph mst = JagheadMinimumSpanningTrees.PRIM.mst(graph);

        // Print after MST simplification.
        System.out.println("After:");
        DataCytoscapeExporter.newInstance().export(mst);
    }

    private static Graph jsonDataImporter() {
        String json
                = "{"
                + "    'vertices': "
                + "    ["
                + "        { 'id': 'A' },"
                + "        { 'id': 'B' },"
                + "        { 'id': 'C' },"
                + "        { 'id': 'D' },"
                + "        { 'id': 'E' },"
                + "        { 'id': 'F' },"
                + "        { 'id': 'G' },"
                + "        { 'id': 'H' }"
                + "    ],"
                + "    'edges': "
                + "    ["
                + "        { "
                + "            'source': 'A',"
                + "            'target': 'B',"
                + "            'weight': 5"
                + "        },"
                + "        { "
                + "            'source': 'A',"
                + "            'target': 'C',"
                + "            'weight': 4"
                + "        },"
                + "        { "
                + "            'source': 'A',"
                + "            'target': 'D',"
                + "            'weight': 3"
                + "        },"
                + "        { "
                + "            'source': 'A',"
                + "            'target': 'E',"
                + "            'weight': 1"
                + "        },"
                + "        { "
                + "            'source': 'B',"
                + "            'target': 'F',"
                + "            'weight': 2"
                + "        },"
                + "        { "
                + "            'source': 'C',"
                + "            'target': 'H',"
                + "            'weight': 3"
                + "        },"
                + "        { "
                + "            'source': 'D',"
                + "            'target': 'G',"
                + "            'weight': 3"
                + "        },"
                + "        { "
                + "            'source': 'E',"
                + "            'target': 'G',"
                + "            'weight': 3"
                + "        },"
                + "        { "
                + "            'source': 'G',"
                + "            'target': 'H',"
                + "            'weight': 3"
                + "        },"
                + "        { "
                + "            'source': 'D',"
                + "            'target': 'F',"
                + "            'weight': 3"
                + "        },"
                + "        { "
                + "            'source': 'D',"
                + "            'target': 'H',"
                + "            'weight': 3"
                + "        },"
                + "        { "
                + "            'source': 'F',"
                + "            'target': 'H',"
                + "            'weight': 3"
                + "        }"
                + "    ]"
                + "}";

        // Build a data importer,
        // then import results to the graph builder.
        DataImporter importer = new DataJSONImporter.Builder(json).build();
        Graph graph = new GraphImpl.Builder(
                importer.getVertices(),
                importer.getEdges())
                .build();
        return graph;
    }

}
