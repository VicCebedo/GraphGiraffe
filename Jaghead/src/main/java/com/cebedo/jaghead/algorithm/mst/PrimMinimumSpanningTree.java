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
import com.cebedo.jaghead.impl.GraphImpl;

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
 * @author Vic Cebedo
 * @param <T1> {@link Vertex} or any subclass.
 * @param <T2> {@link Edge} or any subclass.
 * @param <T3> {@link Graph} or any subclass.
 */
public final class PrimMinimumSpanningTree<T1 extends Vertex, T2 extends Edge, T3 extends Graph<T1, T2>>
        implements MSTAlgorithm<T3> {

    private PrimMinimumSpanningTree() {
    }

    public static MSTAlgorithm newInstance() {
        return new PrimMinimumSpanningTree();
    }

    /**
     * @inheritdoc
     */
    @Override
    public Graph getMST(T3 graph) {
        Set<T1> treeVertices = new HashSet<>();
        Map<T1, EdgeKey<T2>> keys = new HashMap<>();

        // Initialize all vertices equate to max,
        // run will check source first (zero weight).
        graph.vertices().forEach(vertx -> {
            keys.put(vertx, EdgeKey.weight(Double.MAX_VALUE));
        });
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
            graph.successors(minObj).forEach(successor -> {
                T2 edge = graph.edge(minObj.getId(), successor.getId());
                keys.put(successor, EdgeKey.pair(edge, edge.getWeight().doubleValue()));
            });
        }

        // TODO [Optimize] Collect all edges.
        Set<T2> treeEdges = new HashSet<>();
        keys.values().forEach(edgeKey -> {
            if (edgeKey.edge != null) {
                treeEdges.add(edgeKey.edge);
            }
        });
        return new GraphImpl.Builder(treeVertices, treeEdges).build();
    }

    private T1 getMinNotInSet(Map<T1, EdgeKey<T2>> keys, Set<T1> mstSet) {
        T1 minObj = null;
        Double min = Double.MAX_VALUE;
        for (T1 obj : keys.keySet()) {

            // If this is already processed,
            // then continue.
            if (mstSet.contains(obj)) {
                continue;
            }

            // If current value is less than min.
            Double objVal = keys.get(obj).weight;
            if (objVal < min) {
                min = objVal;
                minObj = obj;
            }
        }
        return minObj;
    }

    private static final class EdgeKey<T2 extends Edge> {

        private final T2 edge;
        private final Double weight;

        private EdgeKey(T2 e, Double k) {
            this.edge = e;
            this.weight = k;
        }

        private EdgeKey(Double k) {
            this.edge = null;
            this.weight = k;
        }

        private static <T2 extends Edge> EdgeKey pair(T2 e, Double k) {
            return new EdgeKey(e, k);
        }

        private static EdgeKey weight(Double k) {
            return new EdgeKey(k);
        }
    }
}
