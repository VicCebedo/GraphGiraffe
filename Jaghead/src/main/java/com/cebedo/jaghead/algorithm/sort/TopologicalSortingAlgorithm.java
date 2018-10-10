/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.sort;

import com.cebedo.jaghead.Graph;
import com.cebedo.jaghead.Vertex;
import java.util.List;

/**
 *
 * @author Vic
 * @param <T1>
 * @param <T2>
 */
public interface TopologicalSortingAlgorithm<T1 extends Graph, T2 extends Vertex> {

    List<T2> sort(T1 graph);

}
