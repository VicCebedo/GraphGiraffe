/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.mst;

import com.cebedo.jaghead.GenericEdge;
import com.cebedo.jaghead.GenericVertex;
import com.cebedo.jaghead.Graph;

/**
 *
 * @author Vic
 * @param <T1>
 * @param <T2>
 */
public interface MSTAlgorithm<T1 extends GenericVertex, T2 extends GenericEdge<T1, T1>> {

    Graph getMST(Graph<T1, T2> graph);

}
