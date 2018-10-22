/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.search.pathfinder;

import com.cebedo.jaghead.Graph;
import com.cebedo.jaghead.Vertex;
import java.util.List;

/**
 *
 * @author Vic
 */
public enum JagheadPathFinder {

    BACKTRACK {
        @Override
        List<List<Vertex>> run(Graph graph, String srcId, String tgtId) {
            return BTPathFinder.newInstance().findPaths(graph, srcId, tgtId);
        }
    };

    abstract List<List<Vertex>> run(Graph graph, String srcId, String tgtId);

    public <T extends Graph> List<List<Vertex>> findPaths(T graph, String sourceId, String targetId) {
        return this.run(graph, sourceId, targetId);
    }

}
