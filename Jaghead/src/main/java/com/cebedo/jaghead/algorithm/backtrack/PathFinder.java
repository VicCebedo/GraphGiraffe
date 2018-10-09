/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.backtrack;

import java.util.List;

/**
 *
 * @author Vic
 * @param <T1>
 */
public interface PathFinder<T1> {

    List findPath(T1 graph, String srcId, String tgtId);

}
