/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.mst;

import com.cebedo.jaghead.GenericEdge;
import com.cebedo.jaghead.GenericGraph;
import com.cebedo.jaghead.GenericVertex;
import java.util.Map;

/**
 *
 * @author Vic
 * @param <T1>
 * @param <T2>
 * @param <T3>
 */
public interface MSTAlgorithm<T1 extends GenericVertex, T2 extends GenericEdge<T1, T1, T3>, T3 extends GenericGraph<T1, T2>> {

    Map getMST(T3 graph);

}
