/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.graphgiraffe.builder;

import com.cebedo.graphgiraffe.domain.Graph;
import com.cebedo.graphgiraffe.domain.IGraph;
import com.cebedo.graphgiraffe.domain.IVertex;
import com.cebedo.graphgiraffe.strategy.IDataImportStrategy;
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
