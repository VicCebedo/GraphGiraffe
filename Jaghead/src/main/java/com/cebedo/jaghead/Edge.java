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
 * @param <T3>
 * @param <T4>
 * @param <N>
 */
public class Edge<T1, T2, T3, T4, N extends Number> implements GenericEdge<T1, T2, T3, T4> {

    final private T1 id;
    final private T2 graph;
    final private T3 source;
    final private T4 target;
    final private N weight;

    public Edge(T1 i, T2 g, T3 s, T4 t, N w) {
        this.id = i;
        this.graph = g;
        this.source = s;
        this.target = t;
        this.weight = w;
    }

    @Override
    public T1 getId() {
        return this.id;
    }

    @Override
    public T2 getGraph() {
        return graph;
    }

    @Override
    public T3 getSource() {
        return source;
    }

    @Override
    public T4 getTarget() {
        return target;
    }

    public N getWeight() {
        return weight;
    }

}
