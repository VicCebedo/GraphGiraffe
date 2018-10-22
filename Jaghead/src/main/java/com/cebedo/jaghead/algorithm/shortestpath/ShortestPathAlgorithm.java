/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.shortestpath;

import java.util.Map;
import com.cebedo.jaghead.Vertex;
import com.cebedo.jaghead.Graph;

/**
 * In graph theory, the shortest path problem is the problem of finding a path
 * between two vertices (or nodes) in a graph such that the sum of the weights
 * of its constituent edges is minimized.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Shortest_path_problem">Wikipedia
 * (Shortest Path Problem)</a>
 * @author Vic Cebedo
 * @param <T1> {@link Vertex} or any subclass.
 * @param <T2> {@link Graph} or any subclass.
 */
interface ShortestPathAlgorithm<T1 extends Vertex, T2 extends Graph> {

    /**
     * Returns the shortest path from a source {@link Vertex}
     * <code>sourceId</code> to all the other vertices in the given graph.
     *
     * @param graph Graph where our algorithm will traverse on.
     * @param sourceId ID of the source vertex where we start traversing.
     * @return A {@link Map} where the key is the vertex and the value
     * represents the total weight needed to arrive to that vertex.
     */
    Map<T1, ? extends Number> findPath(T2 graph, String sourceId);

}
