/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.giraffe.strategy;

import com.cebedo.giraffe.domain.Weight;
import com.cebedo.giraffe.domain.IWeight;

/**
 *
 * @author Vic
 */
public interface IWeightStrategy {

    <T extends IWeight> T compute(Weight w);

}
