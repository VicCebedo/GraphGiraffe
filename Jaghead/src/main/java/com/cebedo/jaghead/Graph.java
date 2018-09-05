/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Vic
 */
public class Graph implements GenericGraph<Vertex, Edge> {

    private Set<Vertex> vertices;
    private Set<Edge> edges;
    private Map<Vertex, Map<Vertex, Edge>> incidenceMap;

    public void initialize(Set<Vertex> vertices, Set<Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;
        this.incidenceMap = new HashMap<>();
        this.edges.forEach(edge -> {
            incidenceMap.put(
                    edge.getSource(),
                    this.incidentValue(edge.getTarget(), edge));
        });
        System.out.print("Edge set has duplicates.");
    }

    private Map incidentValue(Vertex v, Edge e) {
        Map map = new HashMap<>();
        map.put(v, e);
        return map;
    }

    @Override
    public Set<Vertex> getVertices() {
        return this.vertices;
    }

    @Override
    public Set<Edge> getEdges() {
        return this.edges;
    }

    @Override
    public Map<Vertex, Map<Vertex, Edge>> getIncidenceMap() {
        return incidenceMap;
    }

}
