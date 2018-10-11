/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.search;

import com.cebedo.jaghead.Graph;

/**
 *
 * @author Vic
 * @param <T>
 */
public interface ConnectivityAlgorithm<T extends Graph> {

    boolean isConnected(T graph);

}
