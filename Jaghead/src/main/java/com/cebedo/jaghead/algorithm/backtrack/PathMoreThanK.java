/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.backtrack;

import com.cebedo.jaghead.algorithm.backtrack.BTPathMoreThanK.BacktrackResult;

/**
 *
 * @author Vic
 * @param <T1>
 * @param <T3>
 */
public interface PathMoreThanK<T1, T3> {

    BacktrackResult backtrack(T3 graph, T1 src, Number k);

}
