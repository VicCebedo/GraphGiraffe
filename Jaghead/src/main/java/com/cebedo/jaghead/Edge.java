/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead;

/**
 *
 * @author Vic Cebedo
 * @param <T>
 */
public interface Edge<T extends Vertex> {

    String getId();

    /**
     * Returns the source {@link Vertex} of this edge. An edge can only exist if
     * two vertices, source and target, are connected by an edge.
     *
     * @return Source vertex of the edge.
     */
    T getSource();

    /**
     * Returns the target {@link Vertex} of this edge. An edge can only exist if
     * two vertices, source and target, are connected by an edge.
     *
     * @return Target vertex of the edge.
     */
    T getTarget();

    /**
     * Returns the weight of this edge.
     *
     * @param <N> Any object which is a subclass of {@link Number}.
     * @return Number value of the edge weight.
     */
    <N extends Number> N getWeight();

}
