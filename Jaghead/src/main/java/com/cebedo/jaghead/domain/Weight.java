/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.domain;

/**
 * TODO Need better naming/definition.
 *
 * @author Vic
 */
public class Weight {

    private int value;

    public Weight(int v) {
        this.value = v;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
