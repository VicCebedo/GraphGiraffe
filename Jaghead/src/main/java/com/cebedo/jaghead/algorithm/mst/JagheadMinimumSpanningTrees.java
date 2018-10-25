/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.mst;

import com.cebedo.jaghead.Graph;
import java.util.Objects;

/**
 *
 * @author Vic Cebedo <cebedo.vii@gmail.com>
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
        Objects.requireNonNull(graph);
        return this.run(graph);
    }

}
