/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.giraffe.domain;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Vic
 */
final public class GraphServant {

    final private IGraph graph;

    public GraphServant(IGraph g) {
        this.graph = g;
    }

    /**
     * Incident vertices are the vertices connected to the edge.
     *
     * @param edge
     * @return
     */
    public Set<IVertex> getIncidentVertices(IEdge edge) {
        Set<IVertex> incidentVertices = new HashSet<>();
        Iterator<? extends IVertex> itr = this.graph.getVertices().iterator();
        while (itr.hasNext()) {
            IVertex vertexX = itr.next();
            if (vertexX.getEdges().contains(edge)) {
                incidentVertices.add(vertexX);
            }
        }
        return incidentVertices;
    }

}
