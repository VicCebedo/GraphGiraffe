/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.mincut;

import com.cebedo.jaghead.Graph;

/**
 *
 * @author Vic Cebedo <cebedo.vii@gmail.com>
 */
public interface MinCutAlgorithm {

    <T extends Graph> T minCut(T graph);

}
