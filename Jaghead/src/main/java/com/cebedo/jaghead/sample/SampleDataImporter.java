/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.sample;

import com.cebedo.jaghead.EdgeBuilder;
import com.cebedo.jaghead.VertexBuilder;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import com.cebedo.jaghead.Edge;
import com.cebedo.jaghead.Graph;
import com.cebedo.jaghead.Vertex;
import com.cebedo.jaghead.algorithm.AbstractGraph;
import com.cebedo.jaghead.data.DataImporter;

/**
 *
 * @author Vic
 */
public class SampleDataImporter extends AbstractGraph implements DataImporter<Vertex, Edge> {

    public static final int NUMBER_OF_EDGES = 500;
    public static final int NUMBER_OF_VERTICES = 500;
    public static final int EDGE_WEIGHT_MAX = 1000;
    final private Graph graph;
    final private Set<Vertex> vertices = new HashSet<>();
    final private Set<Edge> edges = new HashSet<>();

    public SampleDataImporter(Graph g) {
        graph = g;
        createDummyData();
    }

    @Override
    public Set<Vertex> importVertices() {
        return this.vertices;
    }

    @Override
    public Set<Edge> importEdges() {
        return this.edges;
    }

    /**
     * Create edges and vertices.
     *
     * @return
     */
    private void createDummyData() {
        // Create vertices.
        for (int x = 0; x < NUMBER_OF_VERTICES; x++) {
            this.vertices.add(
                    new VertexBuilder<>()
                            .withId(String.valueOf(x))
                            .ofGraph(graph)
                            .build());
        }

        // Attach some edges,
        // before returning.
        createDummyEdges();
        graph.getVertices().addAll(vertices);
        graph.getEdges().addAll(edges);
        removeOrphanedVertices();
    }

    private void removeOrphanedVertices() {
        // Loop through all vertices,
        // if has adjacent, add to new set.
        Set<Vertex> newSet = new HashSet<>();
        this.vertices.forEach(vertex -> {
            Set<Vertex> adjacent = this.getAdjacentVertices(graph, vertex);
            if (!adjacent.isEmpty()) {
                newSet.add(vertex);
            }
        });
        this.vertices.clear();
        this.vertices.addAll(newSet);
    }

    private void createDummyEdges() {
        for (int x = 0; x < NUMBER_OF_EDGES; x++) {
            Vertex src = getRandomVertex(this.vertices);
            Vertex tgt = getRandomVertex(this.vertices);

            // TODO Avoid self-loops. Enable soon.
            if (src.getId().equals(tgt.getId())) {
                continue;
            }

            this.edges.add(this.createDummyEdge(src, tgt));
        }
    }

    private Edge createDummyEdge(Vertex source, Vertex target) {
        return new EdgeBuilder<>()
                .withId(String.format("%s_%s", source.getId(), target.getId()))
                .ofGraph(graph)
                .withSource(source)
                .withTarget(target)
                .withWeight(new Random().nextInt(EDGE_WEIGHT_MAX))
                .build();
    }

    private Vertex getRandomVertex(Set<Vertex> vertices) {
        int randomIndex = new Random().nextInt(vertices.size());
        int index = 0;
        for (Vertex v : vertices) {
            if (index == randomIndex) {
                return v;
            }
            index++;
        }
        return null;
    }

}
