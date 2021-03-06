/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead;

import com.cebedo.jaghead.impl.VertexBuilder;

/**
 *
 * @author Vic Cebedo <cebedo.vii@gmail.com>
 */
public interface Vertex {

    String id();

    static Vertex merge(Vertex v1, Vertex v2) {
        return new VertexBuilder(v1.id() + "+" + v2.id()).build();
    }

}
