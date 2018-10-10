/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.mst;

import java.util.Map;
import com.cebedo.jaghead.Graph;

/**
 *
 * @author Vic
 * @param <T1>
 */
public interface MSTAlgorithm<T1 extends Graph> {

    Map getMST(T1 graph);

}
