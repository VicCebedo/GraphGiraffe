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
 */
public interface Edge<T1 extends Vertex> {

    String getId();

    T1 getSource();

    T1 getTarget();

    <N extends Number> N getWeight();

}
