/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.topologicalsort;

import com.cebedo.jaghead.Edge;
import com.cebedo.jaghead.Graph;
import com.cebedo.jaghead.Vertex;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @see
 * <a href="https://en.wikipedia.org/wiki/Topological_sorting#Kahn's_algorithm">Wikipedia
 * (Kahn's Algorithm)</a>
 * @author Vic Cebedo <cebedo.vii@gmail.com>
 * @param <T1> {@link Vertex} or any subclass.
 * @param <T2> {@link Graph} or any subclass.
 */
final class KahnTopologicalSorter<T1 extends Vertex, T2 extends Graph<T1, ? extends Edge>>
        implements TopologicalSortingAlgorithm<T2, T1> {

    private KahnTopologicalSorter() {
    }

    static TopologicalSortingAlgorithm newInstance() {
        return new KahnTopologicalSorter();
    }

    /**
     * @inheritdoc
     */
    @Override
    public List<T1> sort(T2 graph) {
        Objects.requireNonNull(graph);

        // Initialize the count of visited nodes as 0.
        Map<T1, Integer> vertexDegreeMap = new HashMap<>();
        Queue<T1> toVisit = new LinkedList<>();
        List<T1> topologicalOrder = new LinkedList<>();
        int visitedCount = 0;

        // TODO Make this parallel.
        // Get in-degree (number of incoming edges) for each of the vertex.
        // Pick all the vertices with in-degree as 0 and add them into a queue.
        Set<T1> vertices = graph.vertices();
        toVisit.addAll(
                vertices
                        .stream()
                        .filter(vertx -> graph.degreesOfPredecessors(vertx) == 0)
                        .collect(Collectors.toList()));
        vertexDegreeMap.putAll(
                vertices
                        .stream()
                        .collect(
                                Collectors.toMap(
                                        Function.identity(),
                                        vertx -> graph.degreesOfPredecessors(vertx))));

        while (!toVisit.isEmpty()) {
            // Remove a vertex from the queue.
            // Increment count of visited nodes by 1.
            T1 next = toVisit.poll();
            topologicalOrder.add(next);
            visitedCount++;

            // TODO Make this parallel.
            // Decrease in-degree by 1 for all its neighbors.
            Set<T1> adjacents = graph.adjacent(next);
            vertexDegreeMap.putAll(
                    adjacents
                            .stream()
                            .collect(
                                    Collectors.toMap(
                                            Function.identity(),
                                            neighbor -> vertexDegreeMap.get(neighbor) - 1)));

            // If in-degree of a neighbor is reduced to zero,
            // then add it to the queue.
            toVisit.addAll(
                    adjacents
                            .stream()
                            .filter(neighbor -> vertexDegreeMap.get(neighbor) - 1 == 0)
                            .collect(Collectors.toList()));
        }

        // If count of visited nodes is NOT equal to the number of nodes in the graph
        // then the topological sort is NOT possible for the given graph.
        if (visitedCount != graph.vertices().size()) {
            throw new IllegalStateException("Graph is not topologically sortable.");
        }
        return topologicalOrder;
    }
}
