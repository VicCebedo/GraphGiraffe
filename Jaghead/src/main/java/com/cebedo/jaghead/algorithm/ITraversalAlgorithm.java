/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm;

import com.cebedo.jaghead.domain.IGraph;
import com.cebedo.jaghead.domain.IVertex;
import java.util.Set;

/**
 *
 * @author Vic
 */
public interface ITraversalAlgorithm {

    Set<IVertex> traverse(IGraph graph);
}
