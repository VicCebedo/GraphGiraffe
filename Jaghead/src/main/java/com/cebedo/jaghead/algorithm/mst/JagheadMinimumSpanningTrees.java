/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.mst;

import com.cebedo.jaghead.Graph;

/**
 *
 * @author Vic
 */
public enum JagheadMinimumSpanningTrees {

    PRIM {
        @Override
        Graph run(Graph graph) {
            return PrimMinimumSpanningTree.newInstance().getMST(graph);
        }
    };

    abstract Graph run(Graph graph);

    public <T extends Graph> Graph mst(T graph) {
        return this.run(graph);
    }

}
