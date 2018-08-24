/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.giraffe.data;

import com.cebedo.giraffe.domain.IGraph;

/**
 *
 * @author Vic
 */
public interface IDataExportStrategy {

    void export(IGraph graph);

}
