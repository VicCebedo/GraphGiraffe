/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.giraffe.data;

import com.cebedo.giraffe.domain.IEdge;
import com.cebedo.giraffe.domain.IVertex;
import java.util.Set;

/**
 *
 * @author Vic
 */
public interface IDataImportStrategy {

    Set<? extends IVertex> importVertices();

    Set<? extends IEdge> importEdges();

}
