/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.search.pathfinder;

import com.cebedo.jaghead.Graph;
import com.cebedo.jaghead.Vertex;
import java.util.List;

/**
 *
 * @author Vic Cebedo
 * @param <T1>
 * @param <T2>
 */
interface PathFindingAlgorithm<T1 extends Graph, T2 extends Vertex> {

    List<List<T2>> findPaths(T1 graph, String srcId, String tgtId);

}
