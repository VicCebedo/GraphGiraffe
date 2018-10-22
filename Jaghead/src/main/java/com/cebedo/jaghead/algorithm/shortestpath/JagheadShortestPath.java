/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.shortestpath;

import com.cebedo.jaghead.Graph;
import com.cebedo.jaghead.Vertex;
import java.util.Map;

/**
 *
 * @author Vic
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
        return this.run(graph, sourceId);
    }
}
