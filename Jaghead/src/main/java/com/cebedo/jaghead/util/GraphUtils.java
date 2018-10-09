/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.util;

import com.google.common.collect.Sets;
import java.util.Set;

/**
 *
 * @author Vic
 */
public final class GraphUtils {

    private GraphUtils() {
        throw new AssertionError();
    }

    public static boolean equals(Set<?> s1, Set<?> s2) {
        return Sets.symmetricDifference(s1, s2).isEmpty();
    }

}
