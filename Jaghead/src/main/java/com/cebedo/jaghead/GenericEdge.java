/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead;

/**
 *
 * @author Vic
 * @param <T1>
 * @param <T2>
 * @param <T3>
 * @param <T4>
 */
public interface GenericEdge<T1, T2, T3, T4> {

    T1 getId();

    T2 getGraph();

    T3 getSource();

    T4 getTarget();

}
