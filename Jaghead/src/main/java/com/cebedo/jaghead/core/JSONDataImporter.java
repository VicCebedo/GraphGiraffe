/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.core;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Vic
 * @param <T1>
 * @param <T2>
 */
public final class JSONDataImporter<T1 extends Vertex, T2 extends Edge>
        implements DataImporter<T1, T2> {

    private static final String PROPERTY_VERTICES = "vertices";
    private static final String PROPERTY_EDGES = "edges";

    private static final String ATTR_ID = "id";
    private static final String ATTR_SOURCE = "source";
    private static final String ATTR_TARGET = "target";
    private static final String ATTR_WEIGHT = "weight";

    private final Set<T1> vertices;
    private final Set<T2> edges;
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

    // TODO Improve function.
    // Get using hashcode?
    private T1 getVertexById(String id) {
        for (T1 vtxObj : this.vertices) {
            if (vtxObj.getId().equals(id)) {
                return vtxObj;
            }
        }
        return null;
    }

    private void parseJson() {
        JsonObject json = new JsonParser().parse(this.rawJson).getAsJsonObject();
        JsonArray verticesJson = json.getAsJsonArray(PROPERTY_VERTICES);
        JsonArray edgesJson = json.getAsJsonArray(PROPERTY_EDGES);

        verticesJson.forEach(vtx -> {
            String id = vtx.getAsJsonObject().get(ATTR_ID).toString().replace("\"", "");
            this.vertices.add(new VertexImpl.Builder<T1>(id).build());
        });

        edgesJson.forEach(e -> {
            JsonObject edge = e.getAsJsonObject();
            String src = edge.get(ATTR_SOURCE).getAsString().replace("\"", "");
            String tgt = edge.get(ATTR_TARGET).getAsString().replace("\"", "");
            Number weight = edge.get(ATTR_WEIGHT).getAsNumber();

            this.edges.add(new EdgeImpl.Builder<T1, T2, Number>(
                    src + "_" + tgt,
                    getVertexById(src),
                    getVertexById(tgt))
                    .withWeight(weight)
                    .build());
        });
    }

    @Override
    public Set<T1> getVertices() {
        return Collections.unmodifiableSet(this.vertices);
    }

    @Override
    public Set<T2> getEdges() {
        return Collections.unmodifiableSet(this.edges);
    }

}
