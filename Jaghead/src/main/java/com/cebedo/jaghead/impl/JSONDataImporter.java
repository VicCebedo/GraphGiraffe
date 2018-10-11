/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.impl;

import com.cebedo.jaghead.Edge;
import com.cebedo.jaghead.Vertex;
import com.cebedo.jaghead.DataImporter;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * TODO [Run in sample, test, then doc].
 *
 * @author Vic
 */
public final class JSONDataImporter implements DataImporter {

    private static final String PROPERTY_VERTICES = "vertices";
    private static final String PROPERTY_EDGES = "edges";

    private static final String ATTR_ID = "id";
    private static final String ATTR_SOURCE = "source";
    private static final String ATTR_TARGET = "target";
    private static final String ATTR_WEIGHT = "weight";

    private final Set<Vertex> vertices;
    private final Set<Edge> edges;
    private final String rawJson;

    private JSONDataImporter(Builder b) {
        this.vertices = new HashSet<>();
        this.edges = new HashSet<>();
        this.rawJson = b.inputJson;
        this.parseJson();
    }

    public static final class Builder {

        private final String inputJson;

        public Builder(String j) {
            this.inputJson = j;
        }

        public DataImporter build() {
            return new JSONDataImporter(this);
        }
    }

    private void parseJson() {
        // Extract vertices.
        JsonObject json = new JsonParser().parse(this.rawJson).getAsJsonObject();
        json.getAsJsonArray(PROPERTY_VERTICES).forEach(vtx -> {
            String id = vtx.getAsJsonObject().get(ATTR_ID).toString().replace("\"", "");
            this.vertices.add(new VertexImpl.Builder(id).build());
        });

        // Extract edges.
        json.getAsJsonArray(PROPERTY_EDGES).forEach(e -> {
            JsonObject edge = e.getAsJsonObject();
            String src = edge.get(ATTR_SOURCE).getAsString().replace("\"", "");
            String tgt = edge.get(ATTR_TARGET).getAsString().replace("\"", "");
            Number weight = edge.get(ATTR_WEIGHT).getAsNumber();
            this.edges.add(new EdgeImpl.Builder<>(
                    src + "_" + tgt,
                    GraphImpl.getVertex(this.vertices, src),
                    GraphImpl.getVertex(this.vertices, tgt))
                    .withWeight(weight)
                    .build());
        });
    }

    @Override
    public Set<Vertex> getVertices() {
        return Collections.unmodifiableSet(this.vertices);
    }

    @Override
    public Set<Edge> getEdges() {
        return Collections.unmodifiableSet(this.edges);
    }

}
