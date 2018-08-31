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
public class Vertex<T1, T2> implements GenericVertex<T1, T2> {

    final private T1 id;
    final private T2 graph;

    public Vertex(T1 i, T2 g) {
        this.id = i;
        this.graph = g;
    }

    @Override
    public T1 getId() {
        return id;
    }

    @Override
    public T2 getGraph() {
        return graph;
    }

}
