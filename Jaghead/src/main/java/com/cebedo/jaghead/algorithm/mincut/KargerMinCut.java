/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.mincut;

import com.cebedo.jaghead.Edge;
import com.cebedo.jaghead.Graph;
import com.cebedo.jaghead.Vertex;
import com.cebedo.jaghead.impl.EdgeBuilder;
import com.cebedo.jaghead.impl.GraphBuilder;
import com.cebedo.jaghead.util.GraphUtils;
import com.google.common.collect.Sets;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Vic Cebedo <cebedo.vii@gmail.com>
 */
final class KargerMinCut implements MinCutAlgorithm {

    private KargerMinCut() {
    }

    static KargerMinCut newInstance() {
        return new KargerMinCut();
    }

    @Override
    public Graph minCut(Graph graph) {
        Objects.requireNonNull(graph);

        // We need to create a new copy since graph object
        // contains immutable sets.
        Set<Vertex> remainingVertices = Sets.newHashSet(graph.vertices());
        Set<Edge> remainingEdges = Sets.newHashSet(graph.edges());

        // While there are more than 2 vertices.
        while (remainingVertices.size() > 2) {

            // Pick a random edge from the graph.
            // Remove it and fuse its two corners.
            Edge randomEdge = GraphUtils.getRandomEdge(remainingEdges);
            remainingEdges.remove(randomEdge);

            // Merge source and target into a single vertex.
            Vertex src = randomEdge.source();
            Vertex tgt = randomEdge.target();
            Vertex mergedVertex = Vertex.merge(src, tgt);
            remainingVertices.remove(src);
            remainingVertices.remove(tgt);
            remainingVertices.add(mergedVertex);

            // Update the contracted graph edges
            // according to new merged vertex.
            updateEdges(remainingEdges, mergedVertex, src, tgt);

            // Remove self-loops.
            Set<Edge> filtered = GraphUtils.removeSelfLoops(remainingEdges);
            remainingEdges.clear();
            remainingEdges.addAll(filtered);
        }

        return new GraphBuilder(remainingVertices, remainingEdges).build();
    }

    private void updateEdges(Set<Edge> edges, Vertex mergedVtx, Vertex src, Vertex tgt) {
        // Get the current vertices incident with source and target.
        // Remove all these vertices from master list.
        Set<Edge> edgesToSrc = Graph.incidentInEdges(edges, src);
        Set<Edge> edgesFromSrc = Graph.incidentOutEdges(edges, src);
        Set<Edge> edgesToTgt = Graph.incidentInEdges(edges, tgt);
        Set<Edge> edgesFromTgt = Graph.incidentOutEdges(edges, tgt);
        edges.removeAll(edgesToSrc);
        edges.removeAll(edgesFromSrc);
        edges.removeAll(edgesToTgt);
        edges.removeAll(edgesFromTgt);

        // Updates these edges to point to new merged vertex.
        // Then update master list according to new list.
        Set<Edge> updatedEdges = new HashSet<>();
        updatedEdges.addAll(updateInEdges(edgesToSrc, mergedVtx));
        updatedEdges.addAll(updateOutEdges(edgesFromSrc, mergedVtx));
        updatedEdges.addAll(updateInEdges(edgesToTgt, mergedVtx));
        updatedEdges.addAll(updateOutEdges(edgesFromTgt, mergedVtx));
        edges.addAll(updatedEdges);
    }

    private Set<Edge> updateInEdges(Set<Edge> edges, Vertex merged) {
        Set<Edge> mergedEdges = new HashSet<>();
        edges.forEach(edge -> {
            String id = edge.source().id() + "_" + merged.id();
            mergedEdges.add(new EdgeBuilder(id, edge.source(), merged)
                    .withWeight(edge.weight())
                    .build());
        });
        return mergedEdges;
    }

    private Set<Edge> updateOutEdges(Set<Edge> edges, Vertex merged) {
        Set<Edge> mergedEdges = new HashSet<>();
        edges.forEach(edge -> {
            String id = merged.id() + "_" + edge.target().id();
            Edge newEdge = new EdgeBuilder(id, merged, edge.target())
                    .withWeight(edge.weight())
                    .build();
            mergedEdges.add(newEdge);
        });
        return mergedEdges;
    }
}
