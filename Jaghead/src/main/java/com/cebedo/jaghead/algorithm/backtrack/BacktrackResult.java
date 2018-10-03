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

    private Set<T1> path;
    private Set<T1> sequence;
    private Number distance;
    private boolean pathExists;

    public Set<T1> getSequence() {
        return sequence;
    }

    public void setSequence(Set<T1> sequence) {
        this.sequence = sequence;
    }

    public boolean hasPath() {
        return pathExists;
    }

    public void setHasPath(boolean hasPath) {
        this.pathExists = hasPath;
    }

    public BacktrackResult(Set<T1> p, Set<T1> s, Number d, boolean h) {
        this.path = p;
        this.sequence = s;
        this.distance = d;
        this.pathExists = h;
    }

    public Set<T1> getPath() {
        return path;
    }

    public void setPath(Set<T1> path) {
        this.path = path;
    }

    public Number getDistance() {
        return distance;
    }

    public void setDistance(Number distance) {
        this.distance = distance;
    }

}
