/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.data.computation;

import com.cebedo.jaghead.domain.immutable.ImmutableWeight;
import com.cebedo.jaghead.domain.Weight;

/**
 *
 * @author Vic
 */
public class SampleImmutableWeightStrategy implements IWeightStrategy {

    @Override
    public ImmutableWeight compute(Weight w) {
        ImmutableWeight weight = new ImmutableWeight(w.getValue());
        return weight;
    }

}
