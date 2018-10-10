/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.search.backtrack;

import com.cebedo.jaghead.Vertex;
import java.util.Set;

/**
 *
 * @author Vic
 * @param <T1>
 */
public final class BTResult<T1 extends Vertex> {

    private final Set<T1> path;
    private final Set<T1> sequence;
    private final Number distance;
    private final boolean pathExists;

    private BTResult(Set<T1> p, Set<T1> s, Number d, boolean h) {
        this.path = p;
        this.sequence = s;
        this.distance = d;
        this.pathExists = h;
    }

    static BTResult newInstance(
            Set<? extends Vertex> p,
            Set<? extends Vertex> s,
            Number d,
            boolean h) {
        return new BTResult(p, s, d, h);
    }

    public Set<T1> getSequence() {
        return sequence;
    }

    public boolean hasPath() {
        return pathExists;
    }

    public Set<T1> getPath() {
        return path;
    }

    public Number getDistance() {
        return distance;
    }
}
