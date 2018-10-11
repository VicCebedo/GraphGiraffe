/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.util;

import com.cebedo.jaghead.Vertex;
import com.google.common.collect.Sets;
import java.util.Optional;
import java.util.Set;

/**
 *
 * @author Vic
 */
public final class GraphUtils {

    private GraphUtils() {
        throw new AssertionError();
    }

    public static <E> boolean equals(Set<E> s1, Set<E> s2) {
        return Sets.symmetricDifference(s1, s2).isEmpty();
    }

    public static <T1 extends Vertex> T1 getVertexById(Set<T1> vertices, String id) {
        T1 returnObj = null;
        for (T1 vtxObj : vertices) {
            if (vtxObj.getId().equalsIgnoreCase(id)) {
                returnObj = vtxObj;
                break;
            }
        }
        return Optional.of(returnObj).get();
    }

}
