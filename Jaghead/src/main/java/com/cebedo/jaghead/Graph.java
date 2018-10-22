/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead;

import java.util.Set;

/**
 *
 * @author Vic Cebedo
 * @param <T1>
 * @param <T2>
 */
public interface Graph<T1 extends Vertex, T2 extends Edge> {

    /**
     * Returns an immutable copy of all {@link Vertex} in this graph.
     *
     * @return Immutable set of all vertices.
     */
    Set<T1> vertices();

    /**
     * Returns an immutable copy of all {@link Edge} in this graph.
     *
     * @return Immutable set of all edges.
     */
    Set<T2> edges();

    /**
     * Returns the set of all incoming and outgoing {@link Edge} connected to a
     * {@link Vertex}.
     *
     * @param vertex Source (or target) vertex of the edges.
     * @return Set of all incident edges.
     */
    Set<T2> incidentEdgesAll(T1 vertex);

    /**
     * Returns the set of incoming {@link Edge} connected to a {@link Vertex}.
     *
     * @param vertex Target vertex of the edges.
     * @return Set of incoming incident edges.
     */
    Set<T2> incidentEdgesIncoming(T1 vertex);

    /**
     * Returns the set of outgoing {@link Edge} connected to a {@link Vertex}.
     *
     * @param vertex Source vertex of the edges.
     * @return Set of outgoing incident edges.
     */
    Set<T2> incidentEdgesOutgoing(T1 vertex);

    /**
     * Returns the set of all vertices adjacent to the given vertex. If
     * {@link Vertex} 'A' and 'B' are connected by an {@link Edge} 'C', then
     * vertices 'A' and 'B' are mutually adjacent.
     *
     * @param vertex If this vertex is connected to another vertex 'B'
     * (regardless whether it's a source or target), then vertex 'B' is adjacent
     * with this vertex. Vertex 'B' will be added to the return set.
     *
     * @return Set of all adjacent vertices.
     */
    Set<T1> adjacent(T1 vertex);

    /**
     * Returns the set of all predecessor vertices of the given vertex. If an
     * {@link Edge} 'C' exists where the source is {@link Vertex} 'A' and target
     * is vertex 'B', then vertex 'A' is a predecessor of vertex 'B'.
     *
     * @param vertex If this vertex is a target vertex of edge 'C' with source
     * vertex 'B', then vertex 'B' is a predecessor of this vertex. Vertex 'B'
     * will be added to the return set.
     *
     * @return Set of all predecessor vertices.
     */
    Set<T1> predecessors(T1 vertex);

    /**
     * Returns the set of all successor vertices of the given vertex. If an
     * {@link Edge} 'C' exists where the source is {@link Vertex} 'A' and target
     * is vertex 'B', then vertex 'B' is a successor of vertex 'A'.
     *
     * @param vertex If this vertex is a source vertex of edge 'C' with target
     * vertex 'B', then vertex 'B' is a successor of this vertex. Vertex 'B'
     * will be added to the return set.
     *
     * @return Set of all successor vertices.
     */
    Set<T1> successors(T1 vertex);

    /**
     * Returns the size of the set of all predecessors. To understand
     * predecessors, read {@link #predecessors(com.cebedo.jaghead.Vertex)}.
     *
     * @param vertex Successor vertex of the returned predecessors.
     * @return Size of the set of all predecessors.
     */
    int degreesOfPredecessors(T1 vertex);

    /**
     * Returns the size of the set of all successors. To understand successors,
     * read {@link #successors(com.cebedo.jaghead.Vertex)}.
     *
     * @param vertex Predecessor vertex of the returned successors.
     * @return Size of the set of all successors.
     */
    int degreesOfSuccessors(T1 vertex);

    /**
     * Returns the total incoming and outgoing incident {@link Edge} of a
     * {@link Vertex}.
     *
     * @param vertex Source (or target) vertex of the edges.
     * @return Set size of all incident edges.
     */
    int degreesOfAllIncidentEdges(T1 vertex);

    /**
     * Returns a {@link Vertex} that matches a given ID.
     *
     * @param id ID of the vertex to return.
     * @return Vertex that matches the given ID.
     */
    T1 vertex(String id);

    /**
     * Returns an {@link Edge} that matches a given source and target
     * {@link Vertex} ID.
     *
     * @param sourceId ID used to match the source vertex of the edge.
     * @param targetId ID used to match the target vertex of the edge.
     * @return Edge that has a source and target vertex equal to the given
     * parameters.
     */
    T2 edge(String sourceId, String targetId);

    /**
     * Gets the {@link Edge} that matches the given source and target
     * {@link Vertex} ID, then returns its weight.
     *
     * @param <N> Any object which extends a {@link Number}.
     * @param sourceId ID used to match ID of the source vertex of the edge.
     * @param targetId ID used to match ID of the target vertex of the edge.
     * @return Weight of the edge which matched the given source and target
     * vertex ID.
     */
    <N extends Number> N edgeWeight(String sourceId, String targetId);

    /**
     * Returns true if there exists an {@link Edge} where the source
     * {@link Vertex} ID matches the given <code>sourceId</code>, and a target
     * vertex ID which matches the given <code>targetId</code>.
     *
     * @param sourceId ID used to match ID of the source vertex of the edge.
     * @param targetId ID used to match ID of the target vertex of the edge.
     * @return True or false, if there is an edge connecting two vertices.
     */
    boolean edgeConnecting(String sourceId, String targetId);

    // TODO Add docs.
    boolean connected();

    // TODO Add docs.
    boolean cyclic();

}
