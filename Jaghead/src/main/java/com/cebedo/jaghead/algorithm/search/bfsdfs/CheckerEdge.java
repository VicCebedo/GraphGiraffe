/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.search.bfsdfs;

import com.cebedo.jaghead.Edge;

/**
 *
 * @author Vic Cebedo
 * @param <T>
 */
public interface CheckerEdge<T extends Edge> extends Checker {

    boolean check(T t1);

}
