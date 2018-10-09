/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.shortestpath;

import java.util.Map;
import com.cebedo.jaghead.core.Vertex;
import com.cebedo.jaghead.core.Graph;

/**
 *
 * @author Vic
 * @param <T1>
 * @param <T2>
 */
public interface ShortestPathAlgorithm<T1 extends Vertex, T2 extends Graph> {

    /**
     * Find the shortest path.
     *
     * @param graph
     * @param src
     * @return
     */
    Map<T1, ? extends Number> findPath(T2 graph, T1 src);

}
