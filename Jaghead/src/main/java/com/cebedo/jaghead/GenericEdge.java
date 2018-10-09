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
 */
public interface GenericEdge<T1, T2, T3> {

    String getId();

    T3 getGraph();

    T1 getSource();

    T2 getTarget();

    <N extends Number> N getWeight();

}
