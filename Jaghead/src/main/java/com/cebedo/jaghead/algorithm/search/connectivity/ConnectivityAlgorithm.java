/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.search.connectivity;

import com.cebedo.jaghead.Graph;

/**
 * A graph is connected when there is a path between every pair of vertices. In
 * a connected graph, there are no unreachable vertices. A graph that is not
 * connected is disconnected. A graph G is said to be disconnected if there
 * exist two nodes in G such that no path in G has those nodes as endpoints. A
 * graph with just one vertex is connected.
 *
 * @see
 * <a href="https://en.wikipedia.org/wiki/Connectivity_(graph_theory)">Wikipedia
 * (Connectivity (Graph Theory))</a>
 * @author Vic Cebedo <cebedo.vii@gmail.com>
 * @param <T> Any subclass of {@link Graph}.
 */
interface ConnectivityAlgorithm<T extends Graph> {

    /**
     * Returns true if the given {@link Graph} is connected. False, if
     * otherwise.
     *
     * @param graph Graph to be checked for connectivity.
     * @return True if the graph is connected, or false if otherwise.
     */
    boolean isConnected(T graph);

}
