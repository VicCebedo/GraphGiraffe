/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.sample;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import com.cebedo.jaghead.Graph;
import com.cebedo.jaghead.data.DataImporter;

/**
 *
 * @author Vic
 */
public class SampleDataImporter implements DataImporter<SampleLocation, SampleRate> {

    public static final int NUMBER_OF_EDGES = 100;
    public static final int NUMBER_OF_VERTICES = 20;
    public static final int EDGE_WEIGHT_MAX = 30;
    final private Graph graph;
    final private Set<SampleLocation> vertices = new HashSet<>();
    final private Set<SampleRate> edges = new HashSet<>();

    public SampleDataImporter(Graph g) {
        graph = g;
        createDummyData();
    }

    @Override
    public Set<SampleLocation> importVertices() {
        return this.vertices;
    }

    @Override
    public Set<SampleRate> importEdges() {
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
            this.vertices.add(new SampleLocationBuilder()
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
        Set<SampleLocation> newSet = new HashSet<>();
        this.vertices.forEach(vertex -> {
            Set<SampleLocation> adjacent = graph.getAdjacentVertices(vertex);
            if (!adjacent.isEmpty()) {
                newSet.add(vertex);
            }
        });
        this.vertices.clear();
        this.vertices.addAll(newSet);
    }

    private void createDummyEdges() {
        for (int x = 0; x < NUMBER_OF_EDGES; x++) {
            SampleLocation src = getRandomVertex(this.vertices);
            SampleLocation tgt = getRandomVertex(this.vertices);

            // TODO Avoid self-loops. Enable soon.
            if (src.getId().equals(tgt.getId())) {
                continue;
            }

            this.edges.add(this.createDummyEdge(src, tgt));
        }
    }

    private SampleRate createDummyEdge(SampleLocation source, SampleLocation target) {
        return new SampleRateBuilder()
                .withId(String.format("%s_%s", source.getId(), target.getId()))
                .ofGraph(graph)
                .withSource(source)
                .withTarget(target)
                .withWeight(new Random().nextInt(EDGE_WEIGHT_MAX))
                .build();
    }

    private SampleLocation getRandomVertex(Set<SampleLocation> vertices) {
        int randomIndex = new Random().nextInt(vertices.size());
        int index = 0;
        for (SampleLocation v : vertices) {
            if (index == randomIndex) {
                return v;
            }
            index++;
        }
        return null;
    }

}
