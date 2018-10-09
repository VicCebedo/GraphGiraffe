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
import com.cebedo.jaghead.core.Vertex;
import com.cebedo.jaghead.core.Edge;
import com.cebedo.jaghead.core.Graph;

/**
 *
 * @author Vic
 * @param <T1>
 * @param <T2>
 * @param <T3>
 */
public final class PrimMinimumSpanningTree<T1 extends Vertex, T2 extends Edge, T3 extends Graph<T1, T2>>
        implements MSTAlgorithm<T3> {

    private PrimMinimumSpanningTree() {
    }

    public static MSTAlgorithm newInstance() {
        return new PrimMinimumSpanningTree();
    }

    private T1 getMinNotInSet(Map<T1, Key> keys, Set<T1> mstSet) {
        T1 minObj = null;
        Double min = Double.MAX_VALUE;
        for (T1 obj : keys.keySet()) {
            Double objVal = keys.get(obj).key;

            // If current value is less than min,
            // and NOT yet included in set.
            if (objVal < min && !mstSet.contains(obj)) {
                min = objVal;
                minObj = obj;
            }
        }
        return minObj;
    }

    @Override
    public Map getMST(T3 graph) {
        Set<T1> treeVertices = new HashSet<>();
        Map<T1, Key> keys = new HashMap<>();

        // Initialize all vertices equate to max,
        // run will check source first (zero weight).
        graph.getVertices().forEach(vertx -> {
            keys.put(vertx, Key.weight(Double.MAX_VALUE));
        });
        keys.put(graph.getVertices().iterator().next(), Key.weight(0.0));

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
                keys.put(successor, Key.pair(edge, edge.getWeight().doubleValue()));
            });
        }

        // TODO Optimize.
        // Collect all edges.
        Set<T2> treeEdges = new HashSet<>();
        keys.values().forEach(edgeKey -> {
            treeEdges.add((T2) edgeKey.edge);
        });

        Map results = new HashMap<>();
        results.put("vertices", treeVertices);
        results.put("edges", treeEdges);
        return results;
    }

    private static final class Key<T2> {

        private final T2 edge;
        private final Double key;

        private Key(T2 e, Double k) {
            this.edge = e;
            this.key = k;
        }

        private Key(Double k) {
            this.edge = null;
            this.key = k;
        }

        private static Key pair(Object e, Double k) {
            return new Key(e, k);
        }

        private static Key weight(Double k) {
            return new Key(k);
        }
    }
}
