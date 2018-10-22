/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.search.connectivity;

import com.cebedo.jaghead.Graph;

/**
 *
 * @author Vic
 */
public enum JagheadConnectivity {

    BREADTH_FIRST {
        @Override
        boolean run(Graph graph) {
            return BFSConnectivity.newInstance().isConnected(graph);
        }
    };

    abstract boolean run(Graph graph);

    public <T extends Graph> boolean connected(T graph) {
        return this.run(graph);
    }

}
