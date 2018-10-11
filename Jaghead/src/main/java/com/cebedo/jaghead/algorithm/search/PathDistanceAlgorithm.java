/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.search;

import com.cebedo.jaghead.Graph;
import com.cebedo.jaghead.Vertex;
import com.cebedo.jaghead.algorithm.search.backtrack.BTResult;

/**
 *
 * @author Vic
 * @param <T1>
 * @param <T2>
 */
public interface PathDistanceAlgorithm<T1 extends Vertex, T2 extends Graph> {

    <N extends Number> BTResult pathMoreThanK(T2 graph, T1 src, N k);

}
