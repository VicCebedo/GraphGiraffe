/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead;

/**
 *
 * @author Vic Cebedo <cebedo.vii@gmail.com>
 * @param <T>
 */
public interface DataExporter<T extends Graph> {

    /**
     * Exports the graph data to a different view or format.
     *
     * @param graph Data we wish to export.
     */
    void export(T graph);

}
