/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead;

import java.util.Set;

/**
 *
 * @author Vic
 * @param <T1>
 * @param <T2>
 */
public interface Graph<T1, T2> {

    Set<T1> getVertices();

    Set<T2> getEdges();

    Set<T2> getIncidentEdgesAll(T1 vtx);

    Set<T2> getIncidentEdgesIncoming(T1 vtx);

    Set<T2> getIncidentEdgesOutgoing(T1 vtx);

    int getIncidentDegrees(T1 vtx);

    int getDegreesIncoming(T1 vtx);

    int getDegreesOutgoing(T1 vtx);

    T2 getEdge(T1 src, T1 target);

    boolean hasEdgeConnecting(T1 src, T1 target);

    Number getEdgeWeight(T1 src, T1 target);

    Set<T1> getAdjacentVertices(T1 vtx);

    Set<T1> getPredecessors(T1 vtx);

    Set<T1> getSuccessors(T1 vtx);

    boolean isConnected();

}
