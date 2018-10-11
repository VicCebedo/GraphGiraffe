/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.search;

import com.cebedo.jaghead.Vertex;

/**
 *
 * @author Vic
 * @param <T>
 */
public interface VertexChecker<T extends Vertex> extends Checker {

    boolean check(T t1);

}
