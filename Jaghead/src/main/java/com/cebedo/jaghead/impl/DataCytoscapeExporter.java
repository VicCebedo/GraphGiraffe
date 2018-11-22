/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.impl;

import com.cebedo.jaghead.DataExporter;
import com.cebedo.jaghead.Edge;
import com.cebedo.jaghead.Graph;
import com.cebedo.jaghead.Vertex;
import com.google.gson.Gson;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author Vic Cebedo <cebedo.vii@gmail.com>
 * @param <T1>
 * @param <T2>
 * @param <T3>
 */
public final class DataCytoscapeExporter<T1 extends Vertex, T2 extends Edge, T3 extends Graph<T1, T2>>
        implements DataExporter<T3> {

    private DataCytoscapeExporter() {
    }

    public static DataExporter newInstance() {
        return new DataCytoscapeExporter();
    }

    /**
     * @inheritdoc
     */
    @Override
    public void export(T3 graph) {
        Objects.requireNonNull(graph);
        Set<CytoscapeElement> data = new HashSet<>();

        data.addAll(graph.vertices()
                .stream()
                .map(vertex -> new CytoscapeElement(new CytoscapeData(vertex)))
                .collect(Collectors.toSet()));

        data.addAll(graph.edges()
                .stream()
                .map(edge -> new CytoscapeElement(new CytoscapeData(edge)))
                .collect(Collectors.toSet()));

        String export = new Gson().toJson(data);
        System.out.println(export);
    }

    private static final class CytoscapeElement {

        private final CytoscapeData data;

        private CytoscapeElement(CytoscapeData d) {
            this.data = d;
        }

    }

    private static final class CytoscapeData<T1 extends Vertex, T2 extends Edge<T1>> {

        private final String id;
        private final String source;
        private final String target;
        private final Number weight;

        private CytoscapeData(T1 v) {
            this.id = v.id();
            this.source = null;
            this.target = null;
            this.weight = null;
        }

        private CytoscapeData(T2 e) {
            this.id = e.id();
            this.source = e.source().id();
            this.target = e.target().id();
            this.weight = e.weight();
        }
    }
}
