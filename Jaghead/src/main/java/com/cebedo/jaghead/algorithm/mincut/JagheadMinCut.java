/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.mincut;

import com.cebedo.jaghead.Graph;

/**
 *
 * @author Vic Cebedo <cebedo.vii@gmail.com>
 */
public enum JagheadMinCut {

    KARGER {
        @Override
        Graph run(Graph graph) {
            return KargerMinCut.newInstance().minCut(graph);
        }
    };

    abstract Graph run(Graph graph);

    public <T extends Graph> Graph minCut(T graph) {
        return this.run(graph);
    }

}
