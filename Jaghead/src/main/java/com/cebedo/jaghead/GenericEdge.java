/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead;

/**
 *
 * @author Vic
 * @param <T3>
 * @param <T4>
 */
public interface GenericEdge<T3, T4> {

    String getId();

    <T extends GenericGraph> T getGraph();

    T3 getSource();

    T4 getTarget();

    <N extends Number> N getWeight();

}
