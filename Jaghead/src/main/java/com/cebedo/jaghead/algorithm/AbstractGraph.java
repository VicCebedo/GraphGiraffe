/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm;

import com.cebedo.jaghead.Edge;
import com.cebedo.jaghead.OldGraph;
import com.cebedo.jaghead.Vertex;
import java.util.Optional;

/**
 * TODO Will be extended by Graph?
 *
 * @author Vic
 */
public abstract class AbstractGraph {

    public Edge getIncidentEdge(OldGraph graph, Vertex source, Vertex target) {
        return Optional.of(graph.getIncidenceMap().get(source).get(target)).get();
    }

}
