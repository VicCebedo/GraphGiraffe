/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.core;

/**
 *
 * @author Vic
 * @param <T1>
 */
public interface DataExporter<T1> {

    void export(T1 graph);

}
