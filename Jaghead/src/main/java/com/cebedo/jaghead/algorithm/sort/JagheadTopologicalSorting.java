/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.sort;

import com.cebedo.jaghead.Graph;
import com.cebedo.jaghead.Vertex;
import java.util.List;
import java.util.Objects;

/**
 * Strategy enumeration for each algorithm that can sort topologically.
 *
 * @author Vic Cebedo <cebedo.vii@gmail.com>
 */
public enum JagheadTopologicalSorting {

    KAHN {
        @Override
        List<Vertex> run(Graph graph) {
            return KahnTopologicalSorter.newInstance().sort(graph);
        }
    };

    abstract List<Vertex> run(Graph graph);

    public <T extends Graph> List<Vertex> sort(T graph) {
        Objects.requireNonNull(graph);
        return this.run(graph);
    }

}
