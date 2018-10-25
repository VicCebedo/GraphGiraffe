/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.impl;

import com.cebedo.jaghead.Vertex;
import java.util.Objects;

/**
 *
 * @author Vic Cebedo <cebedo.vii@gmail.com>
 */
final public class VertexBuilder {

    private final String id;

    public String id() {
        return id;
    }

    public VertexBuilder(String i) {
        Objects.requireNonNull(i);
        this.id = i;
    }

    public <T extends Vertex> VertexBuilder(T vtx) {
        Objects.requireNonNull(vtx);
        this.id = vtx.id();
    }

    public Vertex build() {
        return new VertexImpl(this);
    }

}
