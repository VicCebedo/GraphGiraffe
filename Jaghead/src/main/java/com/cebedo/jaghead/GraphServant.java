/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead;

import com.cebedo.jaghead.algorithm.SearchAlgorithm;

/**
 *
 * @author Vic
 */
public class GraphServant {

    public boolean checkConnectivity(Graph graph, SearchAlgorithm algo) {
        return algo.traverse(graph).equals(graph.getVertices());
    }

}
