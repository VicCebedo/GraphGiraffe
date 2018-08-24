/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.giraffe.builder;

import com.cebedo.giraffe.domain.Graph;
import com.cebedo.giraffe.domain.IGraph;
import com.cebedo.giraffe.domain.IVertex;
import com.cebedo.giraffe.strategy.IDataImportStrategy;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Vic
 */
public class GraphBuilder {

    final private Set<IVertex> vertices = new HashSet<>();

    public GraphBuilder withData(IDataImportStrategy importer) {
        this.vertices.addAll(importer.importVertices());
        return this;
    }

    public IGraph build() {
        IGraph graph = new Graph(); // TODO Instantiating as graph, option as immutable graph?
        graph.getVertices().addAll(this.vertices);
        return graph;
    }

}
