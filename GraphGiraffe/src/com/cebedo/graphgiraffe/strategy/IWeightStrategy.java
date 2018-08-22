/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.graphgiraffe.strategy;

import com.cebedo.graphgiraffe.immutable.ImmutableWeight;
import com.cebedo.graphgiraffe.model.Weight;

/**
 *
 * @author Vic
 */
public interface IWeightStrategy {

    public ImmutableWeight compute(Weight w);

}
