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
import com.google.common.collect.Sets;
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
public class PrimMinimumSpanningTree<T1 extends GenericVertex, T2 extends GenericEdge<T1, T1>> implements MSTAlgorithm<T1, T2> {

    class EdgeKeyPair {

        T2 edge;
        Double key;

        EdgeKeyPair(T2 e, Double k) {
            this.edge = e;
            this.key = k;
        }

    }

    private T1 getMinNotInSet(Map<T1, EdgeKeyPair> keys, Set<T1> mstSet) {
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
        Map<T1, EdgeKeyPair> keys = new HashMap<>();

        // Initialize all vertices equate to max,
        // run will check source first (zero weight).
        graph.getVertices().forEach(vertx -> {
            keys.put(vertx, new EdgeKeyPair(null, Double.MAX_VALUE));
        });
        keys.put(graph.getVertices().iterator().next(), new EdgeKeyPair(null, 0.0));

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
                keys.put(successor, new EdgeKeyPair(edge, edge.getWeight().doubleValue()));
            });
        }

        // TODO Optimize.
        // Collect all edges.
        Set<T2> treeEdges = new HashSet<>();
        keys.values().forEach(edgeKey -> {
            treeEdges.add(edgeKey.edge);
        });

        Graph mst = new Graph();
        mst.initialize(
                treeVertices,
                treeEdges);
        return mst;
    }

}
