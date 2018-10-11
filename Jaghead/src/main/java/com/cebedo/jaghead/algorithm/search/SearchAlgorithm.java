/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.search;

import com.cebedo.jaghead.Graph;
import java.util.Set;

/**
 *
 * @author Vic
 * @param <T1>
 * @param <T2>
 * @param <T3>
 * @param <T4>
 */
public interface SearchAlgorithm<T1 extends Graph, T2, T3, T4 extends Checker> {

    Set<T3> search(T1 graph, T2 source, T4 checker);
}
