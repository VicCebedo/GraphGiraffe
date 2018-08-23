/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.graphgiraffe.strategy;

import com.cebedo.graphgiraffe.domain.IVertex;
import java.util.Set;

/**
 *
 * @author Vic
 */
public interface IDataImportStrategy {

    Set<? extends IVertex> importVertices();

}
