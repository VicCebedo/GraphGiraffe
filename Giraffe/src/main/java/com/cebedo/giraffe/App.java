/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.giraffe;

import com.cebedo.giraffe.builder.GraphBuilder;
import com.cebedo.giraffe.domain.GraphServant;
import com.cebedo.giraffe.domain.IGraph;
import com.cebedo.giraffe.domain.IVertex;
import com.cebedo.giraffe.strategy.SampleImmutableDataImportStrategy;

/**
 *
 * @author Vic
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IGraph graph = new GraphBuilder()
                .withData(new SampleImmutableDataImportStrategy())
                .build();

        GraphServant servant = new GraphServant(graph);

        graph.getVertices().stream().map((IVertex vertex) -> {
            System.out.println(vertex.getId());
            System.out.print("=== ");
            servant.getAdjacentVertices(vertex).forEach((adjacentVertx) -> {
                System.out.print(adjacentVertx.getId() + " ");
            });
            return vertex;
        }).forEachOrdered((_item) -> {
            System.out.println("\n");
        });
    }

}
