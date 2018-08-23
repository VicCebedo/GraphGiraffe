/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.graphgiraffe.strategy;

import com.cebedo.graphgiraffe.builder.EdgeBuilder;
import com.cebedo.graphgiraffe.builder.VertexBuilder;
import com.cebedo.graphgiraffe.constant.EdgeType;
import com.cebedo.graphgiraffe.immutable.ImmutableEdge;
import com.cebedo.graphgiraffe.domain.IEdge;
import com.cebedo.graphgiraffe.domain.IVertex;
import com.cebedo.graphgiraffe.domain.Weight;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Vic
 */
public class SampleImmutableDataImportStrategy implements IDataImportStrategy {

    public static final int NUMBER_OF_EDGES = 20;
    public static final int NUMBER_OF_VERTICES = 300;
    public static final int EDGE_WEIGHT_MAX = 1000;

    @Override
    public Set<IVertex> importVertices() {
        return listDummyVertices();
    }

    /**
     * Create edges and vertices.
     *
     * @return
     */
    private Set<IVertex> listDummyVertices() {
        // Create edges.
        Set<IEdge> edges = listDummyEdges();

        // Create vertices.
        Set<IVertex> vertices = new HashSet<>();
        for (int x = 0; x < NUMBER_OF_VERTICES; x++) {
            vertices.add(
                    new VertexBuilder()
                            .withId(String.valueOf(System.currentTimeMillis()))
                            .withImmutableEdge((ImmutableEdge) getRandomEdge(edges))
                            .build());
        }
        return vertices;
    }

    private IEdge getRandomEdge(Set<IEdge> edges) {
        int randomIndex = new Random().nextInt(edges.size());
        int index = 0;
        for (IEdge edge : edges) {
            if (index == randomIndex) {
                return edge;
            }
            index++;
        }
        return null;
    }

    private Set<IEdge> listDummyEdges() {
        Set<IEdge> edges = new HashSet<>();
        for (int x = 0; x < NUMBER_OF_EDGES; x++) {
            edges.add(this.createDummyEdge());
        }
        return edges;
    }

    private IEdge createDummyEdge() {
        return new EdgeBuilder()
                .withType(EdgeType.DIRECTED)
                .withWeight(
                        new Weight(new Random().nextInt(EDGE_WEIGHT_MAX)),
                        new SampleImmutableWeightStrategy())
                .build();
    }

}
