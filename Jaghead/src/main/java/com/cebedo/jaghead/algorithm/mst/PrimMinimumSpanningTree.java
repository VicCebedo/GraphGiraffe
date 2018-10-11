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

/**
 * TODO [Run in sample, test, then doc].
 *
 * @author Vic
 * @param <T1>
 * @param <T2>
 * @param <T3>
 */
public final class PrimMinimumSpanningTree<T1 extends Vertex, T2 extends Edge, T3 extends Graph<T1, T2>>
        implements MSTAlgorithm<T3> {

    private static final String KEY_VERTICES = "vertices";
    private static final String KEY_EDGES = "edges";

    private PrimMinimumSpanningTree() {
    }

    public static MSTAlgorithm newInstance() {
        return new PrimMinimumSpanningTree();
    }

    @Override
    public Map getMST(T3 graph) {
        Set<T1> treeVertices = new HashSet<>();
        Map<T1, EdgeKey<T2>> keys = new HashMap<>();

        // Initialize all vertices equate to max,
        // run will check source first (zero weight).
        graph.getVertices().forEach(vertx -> {
            keys.put(vertx, EdgeKey.weight(Double.MAX_VALUE));
        });
        keys.put(graph.getVertices().iterator().next(), EdgeKey.weight(0.0));

        // While all vertices are not yet processed.
        while (!GraphUtils.equals(graph.getVertices(), treeVertices)) {

            // Get the minimum object.
            T1 minObj = this.getMinNotInSet(keys, treeVertices);
            if (minObj == null) {
                break;
            }
            treeVertices.add(minObj);

            // Update key values of adjacent vertices.
            graph.getSuccessors(minObj).forEach(successor -> {
                T2 edge = graph.getEdge(minObj, successor);
                keys.put(successor, EdgeKey.pair(edge, edge.getWeight().doubleValue()));
            });
        }

        // TODO [Optimize] Collect all edges.
        Set<T2> treeEdges = new HashSet<>();
        keys.values().forEach(edgeKey -> {
            treeEdges.add(edgeKey.edge);
        });

        Map results = new HashMap<>();
        results.put(KEY_VERTICES, treeVertices);
        results.put(KEY_EDGES, treeEdges);
        return results;
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
