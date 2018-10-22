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
 * TODO [Doc].
 *
 * @author Vic Cebedo
 */
public final class JSONDataImporter implements DataImporter {

    private static enum Property {
        VERTICES, EDGES;

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }

    private static enum Attribute {
        ID, SOURCE, TARGET, WEIGHT;

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }

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
        json.getAsJsonArray(Property.VERTICES.toString()).forEach(vtx -> {
            String id = vtx.getAsJsonObject().get(Attribute.ID.toString()).toString().replace("\"", "");
            this.vertices.add(new VertexImpl.Builder(id).build());
        });

        // Extract edges.
        json.getAsJsonArray(Property.EDGES.toString()).forEach(e -> {
            JsonObject edge = e.getAsJsonObject();
            String src = edge.get(Attribute.SOURCE.toString()).getAsString().replace("\"", "");
            String tgt = edge.get(Attribute.TARGET.toString()).getAsString().replace("\"", "");
            Number weight = edge.get(Attribute.WEIGHT.toString()).getAsNumber();
            this.edges.add(new EdgeImpl.Builder<>(
                    src + "_" + tgt,
                    GraphImpl.getVertex(this.vertices, src),
                    GraphImpl.getVertex(this.vertices, tgt))
                    .withWeight(weight)
                    .build());
        });
    }

    /**
     * @inheritdoc
     */
    @Override
    public Set<Vertex> getVertices() {
        return Collections.unmodifiableSet(this.vertices);
    }

    /**
     * @inheritdoc
     */
    @Override
    public Set<Edge> getEdges() {
        return Collections.unmodifiableSet(this.edges);
    }

}
