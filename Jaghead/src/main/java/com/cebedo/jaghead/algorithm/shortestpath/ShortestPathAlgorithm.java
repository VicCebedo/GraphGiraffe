/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.shortestpath;

import com.cebedo.jaghead.GenericEdge;
import com.cebedo.jaghead.GenericVertex;
import com.cebedo.jaghead.Graph;
import java.util.Map;

/**
 *
 * @author Vic
 * @param <T1>
 * @param <T2>
 */
public interface ShortestPathAlgorithm<T1 extends GenericVertex, T2 extends GenericEdge<T1, T1>> {

    /**
     * Find the shortest path.
     *
     * @param graph
     * @param src
     * @return
     */
    Map<T1, ? extends Number> findPath(Graph<T1, T2> graph, T1 src);

}