/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.backtrack;

import com.cebedo.jaghead.GenericVertex;
import java.util.Set;

/**
 *
 * @author Vic
 * @param <T1>
 */
public class BacktrackResult<T1 extends GenericVertex> {

    private final Set<T1> path;
    private final Set<T1> sequence;
    private final Number distance;
    private final boolean pathExists;

    private BacktrackResult(Set p, Set s, Number d, boolean h) {
        this.path = p;
        this.sequence = s;
        this.distance = d;
        this.pathExists = h;
    }

    public static BacktrackResult newInstance(Set p, Set s, Number d, boolean h) {
        return new BacktrackResult(p, s, d, h);
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
