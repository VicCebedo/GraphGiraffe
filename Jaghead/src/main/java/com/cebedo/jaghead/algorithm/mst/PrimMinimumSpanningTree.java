/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.mst;

import com.cebedo.jaghead.GenericEdge;
import com.cebedo.jaghead.GenericVertex;
import com.cebedo.jaghead.Graph;
import com.cebedo.jaghead.util.GraphUtils;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Vic
 * @param <T1>
 * @param <T2>
 */
public class PrimMinimumSpanningTree<T1 extends GenericVertex, T2 extends GenericEdge<T1, T1>>
        implements MSTAlgorithm<T1, T2> {

    static class Key {

        private static final Key INSTANCE = new Key();
        private Object edge;
        private Double key;

        private static Key pair(Object e, Double k) {
            INSTANCE.edge = e;
            INSTANCE.key = k;
            return INSTANCE;
        }

        private static Key weight(Double k) {
            INSTANCE.edge = null;
            INSTANCE.key = k;
            return INSTANCE;
        }
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
    public Graph getMST(Graph<T1, T2> graph) {
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

        Graph mst = new Graph();
        mst.initialize(
                treeVertices,
                treeEdges);
        return mst;
    }

}
