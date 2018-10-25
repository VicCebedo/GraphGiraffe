/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.shortestpath;

import com.cebedo.jaghead.Graph;
import com.cebedo.jaghead.Vertex;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Vic Cebedo <cebedo.vii@gmail.com>
 */
public enum JagheadShortestPath {

    DIJKSTRA {
        @Override
        Map<Vertex, Number> run(Graph graph, String sourceId) {
            return DijkstraShortestPath.newInstance().findPath(graph, sourceId);
        }
    };

    abstract Map<Vertex, Number> run(Graph graph, String sourceId);

    public <T extends Graph> Map<Vertex, Number> shortestPath(T graph, String sourceId) {
        Objects.requireNonNull(graph);
        Objects.requireNonNull(sourceId);
        return this.run(graph, sourceId);
    }
}
