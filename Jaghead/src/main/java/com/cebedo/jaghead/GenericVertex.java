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
public interface GenericVertex<T1, T2> {

    T1 getId();

    T2 getGraph();

}
