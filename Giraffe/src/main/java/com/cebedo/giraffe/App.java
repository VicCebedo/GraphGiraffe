/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.giraffe;

import com.cebedo.giraffe.algorithm.DepthFirstTraversal;
import com.cebedo.giraffe.builder.GraphBuilder;
import com.cebedo.giraffe.data.SampleDataExportStrategy;
import com.cebedo.giraffe.domain.IGraph;
import com.cebedo.giraffe.data.SampleDataImportStrategy;
import com.cebedo.giraffe.domain.GraphServant;

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
                .withData(new SampleDataImportStrategy())
                .build();
        System.out.println(new GraphServant().checkConnectivity(graph, new DepthFirstTraversal()));
        new SampleDataExportStrategy().export(graph);
    }

}
