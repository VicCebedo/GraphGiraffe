/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.domain.immutable;

import com.cebedo.jaghead.constant.EdgeType;
import com.cebedo.jaghead.domain.IType;

/**
 *
 * @author Vic
 */
final public class ImmutableType implements IType {

    final private EdgeType type;

    public ImmutableType(EdgeType t) {
        this.type = t;
    }

    @Override
    public EdgeType getType() {
        return type;
    }

}
