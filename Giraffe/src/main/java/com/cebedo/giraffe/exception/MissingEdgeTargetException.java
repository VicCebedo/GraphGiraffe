/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.giraffe.exception;

/**
 *
 * @author Vic
 */
public class MissingEdgeTargetException extends Exception {

    public MissingEdgeTargetException() {
        super("Missing edge target vertex.");
    }
}