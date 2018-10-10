/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.search;

import com.cebedo.jaghead.algorithm.search.backtrack.BTResult;

/**
 *
 * @author Vic
 * @param <T1>
 * @param <T3>
 */
public interface PathDistanceAlgorithm<T1, T3> {

    BTResult pathMoreThanK(T3 graph, T1 src, Number k);

}
