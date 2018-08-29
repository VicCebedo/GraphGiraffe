/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.exception;

/**
 *
 * @author Vic
 */
public class MissingEdgeSourceException extends Exception {

    public MissingEdgeSourceException() {
        super("Missing edge source vertex.");
    }

}
