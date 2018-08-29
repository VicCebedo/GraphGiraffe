/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.domain;

/**
 *
 * @author Vic
 */
public interface IEdge {

    IWeight getWeight();

    IType getType();

    IVertex getSource();

    IVertex getTarget();

    IGraph getGraph();

}
