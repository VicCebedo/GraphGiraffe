/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm;

import com.cebedo.jaghead.Graph;
import com.cebedo.jaghead.Vertex;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 *
 * @author Vic
 */
public class DijkstraAlgorithm extends AbstractGraph implements ShortestPathAlgorithm<Graph, Vertex> {

    @Override
    public void findPath(Graph graph, Vertex source) {
        // Distance of source to a vertex, initializations.
        Map<Vertex, Number> distanceMap = new HashMap<>();
        Map<Vertex, Vertex> previous = new HashMap<>();

        // Loop through all vertices.
        graph.getVertices().forEach(vertx -> {

            // Initialize maps.
            // Initially, all distances are infinite.
            distanceMap.put(vertx, Double.POSITIVE_INFINITY);
            previous.put(vertx, null);
        });

        // Distance of source to source is zero.
        distanceMap.put(source, 0.0);

        // Queue of set of all nodes.
        Set<Vertex> queue = new HashSet<>();
        queue.addAll(graph.getVertices());

        // While there are items in queue.
        Iterator<Vertex> queueIter = queue.iterator();
        while (queueIter.hasNext()) {

            // Get vertex with least distance,
            // then remove from queue.
            Vertex u = this.getSmallestValue(distanceMap);
            queue.remove(u);

            // Loop the neighbors of 'u' vertex.
            this.getAdjacentVertices(graph, u).forEach(v -> {
                Number uDistanceFromSource = distanceMap.get(u);
                Number vDistanceFromSource = distanceMap.get(v);
                Number vDistanceFromNeighbor = this.getIncidentEdge(graph, u, v).getWeight();
                Double alt = uDistanceFromSource.doubleValue() + vDistanceFromNeighbor.doubleValue();

                // A shorter path to neighbor 'v' has been found.
                if (alt < vDistanceFromSource.doubleValue()) {
                    distanceMap.put(v, alt);
                    previous.put(v, u);
                }
            });
        }

// 1  function Dijkstra(Graph, source):
// 2
// 3      create vertex set Q
// 4
// 5      for each vertex v in Graph:             // Initialization
// 6          dist[v] ← INFINITY                  // Unknown distance from source to v
// 7          prev[v] ← UNDEFINED                 // Previous node in optimal path from source
// 8          add v to Q                          // All nodes initially in Q (unvisited nodes)
// 9
//10      dist[source] ← 0                        // Distance from source to source
//11      
//12      while Q is not empty:
//13          u ← vertex in Q with min dist[u]    // Node with the least distance
//14                                              // will be selected first
//15          remove u from Q 
//16    
//17          for each neighbor v of u:           // where v is still in Q.
//18              alt ← dist[u] + length(u, v)
//19              if alt < dist[v]:               // A shorter path to v has been found
//20                  dist[v] ← alt 
//21                  prev[v] ← u 
//22
//23      return dist[], prev[]     
    }

    /**
     * Get the smallest map value.
     *
     * @param map
     * @return
     */
    private Vertex getSmallestValue(Map<Vertex, Number> map) {
        Number leastValue = Double.POSITIVE_INFINITY;
        Vertex leastKey = null;
        Iterator<Vertex> iter = map.keySet().iterator();

        while (iter.hasNext()) {
            Vertex vertx = iter.next();
            Number value = map.get(vertx);

            if (value.doubleValue() < leastValue.doubleValue()) {
                leastValue = value;
                leastKey = vertx;
            }
        }
        return Optional.of(leastKey).get();
    }

}
