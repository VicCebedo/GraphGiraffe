/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.search.pathdistance;

import com.cebedo.jaghead.Graph;
import java.util.Map;

/**
 *
 * @author Vic
 */
public enum JagheadPathDistance {

    BACKTRACK {
        @Override
        Map<String, ?> run(Graph graph, String srcId, Number k) {
            return BTPathMoreThanK.newInstance().findPath(graph, srcId, k);
        }
    };

    abstract Map<String, ?> run(Graph graph, String sourceId, Number k);

    public <N extends Number, T extends Graph> Map<String, ?> findPath(T graph, String sourceId, N k) {
        return this.run(graph, sourceId, k);
    }

}
