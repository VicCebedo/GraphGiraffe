/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.giraffe.data;

import com.cebedo.giraffe.data.computation.SampleImmutableWeightStrategy;
import com.cebedo.giraffe.builder.EdgeBuilder;
import com.cebedo.giraffe.builder.VertexBuilder;
import com.cebedo.giraffe.constant.EdgeType;
import com.cebedo.giraffe.domain.IEdge;
import com.cebedo.giraffe.domain.IGraph;
import com.cebedo.giraffe.domain.IVertex;
import com.cebedo.giraffe.domain.Weight;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Vic
 */
public class SampleDataImportStrategy implements IDataImportStrategy {

    public static final int NUMBER_OF_EDGES = 40;
    public static final int NUMBER_OF_VERTICES = 20;
    public static final int EDGE_WEIGHT_MAX = 1000;
    final private Set<IVertex> vertices = new HashSet<>();
    final private Set<IEdge> edges = new HashSet<>();

    public SampleDataImportStrategy(IGraph graph) {
        createDummyData(graph);
    }

    @Override
    public Set<IVertex> importVertices() {
        return this.vertices;
    }

    @Override
    public Set<IEdge> importEdges() {
        return this.edges;
    }

    /**
     * Create edges and vertices.
     *
     * @return
     */
    private void createDummyData(IGraph graph) {
        // Create vertices.
        for (int x = 0; x < NUMBER_OF_VERTICES; x++) {
            this.vertices.add(
                    new VertexBuilder()
                            .withId(String.valueOf(x))
                            .ofGraph(graph)
                            .build());
        }

        // Attach some edges,
        // before returning.
        createDummyEdges(graph);
        removeOrphanedVertices();
    }

    private void removeOrphanedVertices() {
        this.vertices.clear();
        this.edges.forEach(edge -> {
            IVertex src = edge.getSource();
            IVertex target = edge.getTarget();
            src.addEdge(edge);
            target.addEdge(edge);
            this.vertices.add(src);
            this.vertices.add(target);
        });
    }

    private void createDummyEdges(IGraph graph) {
        for (int x = 0; x < NUMBER_OF_EDGES; x++) {
            IVertex src = getRandomVertex(this.vertices);
            IVertex tgt = getRandomVertex(this.vertices);
            // TODO Avoid self-loops. Enable soon.
            if (src.getId().equals(tgt.getId())) {
                continue;
            }
            this.edges.add(this.createDummyEdge(src, tgt, graph));
        }
    }

    private IEdge createDummyEdge(IVertex source, IVertex target, IGraph graph) {
        return new EdgeBuilder()
                .withSource(source)
                .withTarget(target)
                .ofGraph(graph)
                .withType(EdgeType.DIRECTED)
                .withWeight(
                        new Weight(new Random().nextInt(EDGE_WEIGHT_MAX)),
                        new SampleImmutableWeightStrategy())
                .build();
    }

    private IVertex getRandomVertex(Set<IVertex> vertices) {
        int randomIndex = new Random().nextInt(vertices.size());
        int index = 0;
        for (IVertex v : vertices) {
            if (index == randomIndex) {
                return v;
            }
            index++;
        }
        return null;
    }

}
