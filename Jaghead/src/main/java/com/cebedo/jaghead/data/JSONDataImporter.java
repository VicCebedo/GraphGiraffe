/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.data;

import com.cebedo.jaghead.Edge;
import com.cebedo.jaghead.GenericEdge;
import com.cebedo.jaghead.GenericVertex;
import com.cebedo.jaghead.Graph;
import com.cebedo.jaghead.Vertex;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Vic
 * @param <T1>
 * @param <T2>
 */
public class JSONDataImporter<T1 extends GenericVertex<Graph>, T2 extends GenericEdge<T1, T1>>
        implements DataImporter<T1, T2> {

    private static final String PROPERTY_VERTICES = "vertices";
    private static final String PROPERTY_EDGES = "edges";

    private static final String ATTR_ID = "id";
    private static final String ATTR_SOURCE = "source";
    private static final String ATTR_TARGET = "target";
    private static final String ATTR_WEIGHT = "weight";

    private final Set vertices = new HashSet<>();
    private final Set edges = new HashSet<>();
    private final Graph graph;
    private final String rawJson;

    private JSONDataImporter(Builder b) {
        this.graph = b.graph;
        this.rawJson = b.rawJson;
        this.parseJson();
    }

    public static class Builder {

        private final Graph graph;
        private final String rawJson;

        public Builder(Graph g, String j) {
            this.graph = g;
            this.rawJson = j;
        }

        public JSONDataImporter build() {
            return new JSONDataImporter(this);
        }
    }

    // TODO Improve function.
    // Get using hashcode?
    private T1 getVertexById(String id) {
        for (Object vtx : this.vertices) {
            T1 vtxObj = (T1) vtx;
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
            this.vertices.add(new Vertex.Builder(id, graph));
        });

        edgesJson.forEach(e -> {
            JsonObject edge = e.getAsJsonObject();
            String src = edge.get(ATTR_SOURCE).getAsString().replace("\"", "");
            String tgt = edge.get(ATTR_TARGET).getAsString().replace("\"", "");
            double weight = edge.get(ATTR_WEIGHT).getAsDouble();

            this.edges.add(new Edge.Builder(
                    src + "_" + tgt,
                    graph,
                    getVertexById(src),
                    getVertexById(tgt))
                    .withWeight(weight)
                    .build());
        });
    }

    @Override
    public Set<T1> importVertices() {
        return this.vertices;
    }

    @Override
    public Set<T2> importEdges() {
        return this.edges;
    }

}
