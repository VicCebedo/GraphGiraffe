/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead;

import java.util.Set;

/**
 *
 * @author Vic Cebedo <cebedo.vii@gmail.com>
 */
public interface DataImporter {

    /**
     * Returns the set of {@link Vertex} which was imported by the
     * implementation.
     *
     * @return Set of vertices (or subclass of a vertex).
     */
    Set<? extends Vertex> vertices();

    /**
     * Returns the set of {@link Edge} which was imported by the implementation.
     *
     * @return Set of edges (or subclass of a edge).
     */
    Set<? extends Edge> edges();

}
