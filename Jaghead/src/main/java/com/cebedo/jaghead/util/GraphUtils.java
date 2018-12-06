/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.util;

import com.cebedo.jaghead.Edge;
import com.google.common.collect.Sets;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 *
 * @author Vic Cebedo <cebedo.vii@gmail.com>
 */
public final class GraphUtils {

    private GraphUtils() {
        throw new AssertionError();
    }

    public static <E> boolean equals(Set<E> s1, Set<E> s2) {
        Objects.requireNonNull(s1);
        Objects.requireNonNull(s2);
        return Sets.symmetricDifference(s1, s2).isEmpty();
    }

    public static <E extends Edge> Set<Edge> removeSelfLoops(Set<E> edges) {
        Objects.requireNonNull(edges);
        return edges
                .stream()
                .filter(edge -> !edge.source().equals(edge.target()))
                .collect(Collectors.toSet());
    }

    public static <E extends Edge> Edge randomEdge(Set<E> edges) {
        Objects.requireNonNull(edges);
        int i = ThreadLocalRandom.current().nextInt(edges.size());
        return (Edge) edges.toArray()[i];
    }

}
