/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.giraffe.domain;

import java.util.Set;

/**
 *
 * @author Vic
 */
public interface IVertex {

    String getId();

    Set<? extends IEdge> getEdges();

}
