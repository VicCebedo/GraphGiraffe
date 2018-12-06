/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.mst;

import com.cebedo.jaghead.Graph;

/**
 * A minimum spanning tree (MST) or minimum weight spanning tree is a subset of
 * the edges of a connected, edge-weighted (un)directed graph that connects all
 * the vertices together, without any cycles and with the minimum possible total
 * edge weight. That is, it is a spanning tree whose sum of edge weights is as
 * small as possible.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Minimum_spanning_tree">Wikipedia
 * (Minimum Spanning Tree)</a>
 * @author Vic Cebedo <cebedo.vii@gmail.com>
 * @param <T1> Any subclass of {@link Graph}.
 */
interface MstAlgorithm<T1 extends Graph> {

    /**
     * Returns the minimum spanning tree of the given {@link Graph}.
     *
     * @param graph Graph that will be converted to a minimum spanning tree.
     * @return Minimum spanning tree of the given graph.
     */
    Graph mst(T1 graph);

}
