/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.graphgiraffe.immutable;

/**
 *
 * @author Vic
 */
final public class ImmutableWeight {

    final private int value;

    public ImmutableWeight(int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }

}
