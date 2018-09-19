/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead;

import java.util.Map;
import java.util.Set;

/**
 *
 * @author Vic
 * @param <T1>
 * @param <T2>
 */
public interface GenericGraph<T1, T2> {

    Set<T1> getVertices();

    Set<T2> getEdges();

    Map<AdjacentPair, T2> getIncidenceMap();

}
