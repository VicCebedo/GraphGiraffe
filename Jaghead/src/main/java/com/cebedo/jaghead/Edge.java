/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead;

/**
 *
 * @author Vic
 * @param <T>
 */
public interface Edge<T extends Vertex> {

    String getId();

    T getSource();

    T getTarget();

    <N extends Number> N getWeight();

}
