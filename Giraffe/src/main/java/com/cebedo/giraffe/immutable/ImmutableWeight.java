/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.giraffe.immutable;

import com.cebedo.giraffe.domain.IWeight;

/**
 *
 * @author Vic
 */
final public class ImmutableWeight implements IWeight {

    final private int value;

    public ImmutableWeight(int v) {
        this.value = v;
    }

    @Override
    public int getValue() {
        return value;
    }

}
