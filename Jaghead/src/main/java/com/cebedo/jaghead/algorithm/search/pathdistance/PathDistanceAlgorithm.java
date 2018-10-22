/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.search.pathdistance;

import com.cebedo.jaghead.Graph;
import java.util.Map;

/**
 *
 * @author Vic Cebedo
 * @param <T>
 */
interface PathDistanceAlgorithm<T extends Graph> {

    <N extends Number> Map<String, ?> findPath(T graph, String srcId, N k);

}
