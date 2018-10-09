/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.core;

/**
 *
 * @author Vic
 * @param <T1>
 * @param <T2>
 */
public interface Edge<T1, T2> {

    String getId();

    T1 getSource();

    T2 getTarget();

    <N extends Number> N getWeight();

}
