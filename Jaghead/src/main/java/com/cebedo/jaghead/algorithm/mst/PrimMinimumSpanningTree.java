/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.mst;

import com.cebedo.jaghead.util.GraphUtils;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import com.cebedo.jaghead.Vertex;
import com.cebedo.jaghead.Edge;
import com.cebedo.jaghead.Graph;
import com.cebedo.jaghead.impl.GraphBuilder;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Prim's algorithm is a greedy algorithm that finds a minimum spanning tree for
 * a weighted undirected graph. This means it finds a subset of the edges that
 * forms a tree that includes every vertex, where the total weight of all the
 * edges in the tree is minimized. The algorithm operates by building this tree
 * one vertex at a time, from an arbitrary starting vertex, at each step adding
 * the cheapest possible connection from the tree to another vertex.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Prim%27s_algorithm">Wikipedia
 * (Prim's Algorithm)</a>
 * @author Vic Cebedo <cebedo.vii@gmail.com>
 * @param <T1> {@link Vertex} or any subclass.
 * @param <T2> {@link Edge} or any subclass.
 * @param <T3> {@link Graph} or any subclass.
 */
final class PrimMinimumSpanningTree<T1 extends Vertex, T2 extends Edge, T3 extends Graph<T1, T2>>
        implements MSTAlgorithm<T3> {

    private PrimMinimumSpanningTree() {
    }

    static MSTAlgorithm newInstance() {
        return new PrimMinimumSpanningTree();
    }

    /**
     * @inheritdoc
     */
    @Override
    public Graph getMST(T3 graph) {
        Objects.requireNonNull(graph);
        if (!graph.connected()) {
            throw new IllegalArgumentException("Graph should be connected.");
        }

        Set<T1> treeVertices = new HashSet<>();
        Map<T1, EdgeKey<T2>> keys = new HashMap<>();

        // Initialize all vertices equate to max,
        // run will check source first (zero weight).
        keys.putAll(graph.vertices()
                .stream()
                .collect(Collectors.toMap(Function.identity(), e -> EdgeKey.weight(Double.MAX_VALUE))));
        keys.put(graph.vertices().iterator().next(), EdgeKey.weight(0.0));

        // While all vertices are not yet processed.
        while (!GraphUtils.equals(graph.vertices(), treeVertices)) {

            // Get the minimum object.
            T1 minObj = this.getMinNotInSet(keys, treeVertices);
            if (minObj == null) {
                break;
            }
            treeVertices.add(minObj);

            // Update key values of adjacent vertices.
            graph.successors(minObj)
                    .stream()
                    .collect(
                            Collectors.toMap(
                                    Function.identity(),
                                    successor -> createEdgeKeyEntry(graph, minObj, successor)));
        }

        Set<T2> treeEdges = keys.values()
                .stream()
                .filter(edgeKey -> edgeKey.edge != null)
                .map(edgeKey -> edgeKey.edge)
                .collect(Collectors.toSet());
        return new GraphBuilder(treeVertices, treeEdges).build();
    }

    private EdgeKey createEdgeKeyEntry(T3 graph, T1 minObj, T1 successor) {
        T2 edge = graph.edge(minObj.id(), successor.id());
        return EdgeKey.pair(edge, edge.weight().doubleValue());
    }

    private T1 getMinNotInSet(Map<T1, EdgeKey<T2>> keys, Set<T1> mstSet) {
        T1 minObj = null;
        double min = Double.MAX_VALUE;
        for (T1 obj : keys.keySet()) {

            // If this is already processed,
            // then continue.
            if (mstSet.contains(obj)) {
                continue;
            }

            // If current value is less than min.
            double objVal = keys.get(obj).weight;
            if (objVal < min) {
                min = objVal;
                minObj = obj;
            }
        }
        return minObj;
    }

    private static final class EdgeKey<T2 extends Edge> {

        private final T2 edge;
        private final double weight;

        private EdgeKey(T2 e, double k) {
            this.edge = e;
            this.weight = k;
        }

        private EdgeKey(double k) {
            this.edge = null;
            this.weight = k;
        }

        private static <T2 extends Edge> EdgeKey pair(T2 e, double k) {
            return new EdgeKey(e, k);
        }

        private static EdgeKey weight(double k) {
            return new EdgeKey(k);
        }
    }
}
