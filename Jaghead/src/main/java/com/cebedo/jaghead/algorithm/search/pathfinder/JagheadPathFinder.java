/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.search.pathfinder;

import com.cebedo.jaghead.Graph;
import com.cebedo.jaghead.Vertex;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Vic Cebedo <cebedo.vii@gmail.com>
 */
public enum JagheadPathFinder {

    BACKTRACK {
        @Override
        Set<List<Vertex>> run(Graph graph, String srcId, String tgtId) {
            return BtPathFinder.newInstance().findPaths(graph, srcId, tgtId);
        }
    };

    abstract Set<List<Vertex>> run(Graph graph, String srcId, String tgtId);

    public <T extends Graph> Set<List<Vertex>> findPaths(T graph, String sourceId, String targetId) {
        Objects.requireNonNull(graph);
        Objects.requireNonNull(sourceId);
        Objects.requireNonNull(targetId);
        return this.run(graph, sourceId, targetId);
    }

}
