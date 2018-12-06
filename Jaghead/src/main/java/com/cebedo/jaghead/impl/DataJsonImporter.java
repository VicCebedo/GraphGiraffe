/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.impl;

import com.cebedo.jaghead.Edge;
import com.cebedo.jaghead.Vertex;
import com.cebedo.jaghead.DataImporter;
import com.cebedo.jaghead.Graph;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TODO [Doc].
 *
 * @author Vic Cebedo <cebedo.vii@gmail.com>
 */
public final class DataJsonImporter implements DataImporter {

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

    private DataJsonImporter(Builder b) {
        this.vertices = new HashSet<>();
        this.edges = new HashSet<>();
        this.rawJson = b.inputJson;
        this.parseJson();
    }

    public static final class Builder {

        private final String inputJson;

        public Builder(String j) {
            Objects.requireNonNull(j);
            this.inputJson = j;
        }

        public DataImporter build() {
            return new DataJsonImporter(this);
        }
    }

    private Vertex buildVertex(JsonElement vtx) {
        String id = vtx.getAsJsonObject().get(Attribute.ID.toString()).toString().replace("\"", "");
        return new VertexBuilder(id).build();
    }

    private Edge buildEdge(JsonElement e) {
        JsonObject edge = e.getAsJsonObject();
        String src = edge.get(Attribute.SOURCE.toString()).getAsString().replace("\"", "");
        String tgt = edge.get(Attribute.TARGET.toString()).getAsString().replace("\"", "");
        Number weight = edge.get(Attribute.WEIGHT.toString()).getAsNumber();
        return new EdgeBuilder<>(
                src + "_" + tgt,
                Graph.vertex(this.vertices, src),
                Graph.vertex(this.vertices, tgt),
                weight)
                .build();
    }

    private void parseJson() {
        JsonObject json = new JsonParser().parse(this.rawJson).getAsJsonObject();

        // Extract vertices.
        this.vertices.addAll(
                Stream.of(json.getAsJsonArray(Property.VERTICES.toString()))
                        .map(vtx -> buildVertex(vtx))
                        .collect(Collectors.toSet()));

        // Extract edges.
        this.edges.addAll(
                Stream.of(json.getAsJsonArray(Property.EDGES.toString()))
                        .map(e -> buildEdge(e))
                        .collect(Collectors.toSet()));
    }

    /**
     * @inheritdoc
     */
    @Override
    public Set<Vertex> vertices() {
        return Collections.unmodifiableSet(this.vertices);
    }

    /**
     * @inheritdoc
     */
    @Override
    public Set<Edge> edges() {
        return Collections.unmodifiableSet(this.edges);
    }
}
