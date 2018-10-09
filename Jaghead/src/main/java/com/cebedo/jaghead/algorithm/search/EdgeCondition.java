/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.search;

import com.cebedo.jaghead.GenericEdge;

/**
 *
 * @author Vic
 * @param <T1>
 */
public interface EdgeCondition<T1 extends GenericEdge<?, ?, ?>> {

    boolean check(T1 t1);

}
