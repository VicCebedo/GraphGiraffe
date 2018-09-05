/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm;

/**
 *
 * @author Vic
 * @param <T1>
 * @param <T2>
 */
public interface ShortestPathAlgorithm<T1, T2> {

    void findPath(T1 t1, T2 t2);

}
